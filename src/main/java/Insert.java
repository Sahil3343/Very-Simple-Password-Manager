import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/Insert")
public class Insert extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equals("GET")) {
            doGet(req, resp);
        } else if (method.equals("POST")) {
            doPost(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter output = resp.getWriter();
        String _url = req.getParameter("url");
        String _username = req.getParameter("username");
        String _password = req.getParameter("password");

        HttpSession session = req.getSession(false);
        String session_username =(String)session.getAttribute("username");

        String encryptedString = AES256.encrypt(_password);

        DbConnection connect = new DbConnection("jdbc:sqlserver://passworddbserver.database.windows.net:1433;database=passworddb;encrypt=true;", "", "");
        Connection con = null;
        try {
            con = connect.GetConnection();
            String sql = "insert into Cred values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, _url);
            statement.setString(2, _username);
            statement.setString(3, encryptedString);
            statement.setString(4, "sahil");
            boolean rs = statement.execute();
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/ServletJava8Test_war_exploded/dashboard");

    }
}
