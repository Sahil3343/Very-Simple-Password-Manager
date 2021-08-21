import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/dashboard")
public class dashboard extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equals("GET")) {
            doGet(req, resp);
        }
        else if (method.equals("POST")) {
            doPost(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String _username =(String)session.getAttribute("username");

        try {
            DbConnection connect = new DbConnection("jdbc:sqlserver://passworddbserver.database.windows.net:1433;database=passworddb;encrypt=true;", "", "");
            Connection con = null;
            con = connect.GetConnection();

            String sql = "select Url, Username, Password from Cred where AccUsername = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, _username);
            ResultSet rs = statement.executeQuery();

            PrintWriter out = resp.getWriter();

            if(rs.next()){
                String decryptedString = AES256.decrypt(rs.getString(3));
                //out.println("<style>");
                //out.println("</style>");
                out.print("<table border=\"1\">");
                out.print("<tr><th>URL</th><th>Username</th><th>Password</th></tr><tr><td>"+rs.getString(1)+"</td><td>Username"+rs.getString(2)+"</td><td>"+decryptedString+"</td></tr>");
                while(rs.next()){
                    String dString = AES256.decrypt(rs.getString(3));
                    out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+dString+"</td></tr>");
                }
                out.print("</table>");
                out.println("<form action=\"InsertDetails.jsp\">");
                out.println("<input type=\"submit\" name=\"submit\" value=\"Insert Details\">");
                out.print("</form>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String convertBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte temp : bytes) {
            result.append(String.format("%02x", temp));
        }
        return result.toString();
    }

    // A 256-bit secret key (32 bytes)
    private static SecretKey getKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("ChaCha20");
        keyGen.init(256, SecureRandom.getInstanceStrong());
        return keyGen.generateKey();
    }

    // 96-bit nonce (12 bytes)
    private static byte[] getNonce() {
        byte[] newNonce = new byte[12];
        new SecureRandom().nextBytes(newNonce);
        return newNonce;
    }
}
