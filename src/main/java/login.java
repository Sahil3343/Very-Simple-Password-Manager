import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;

@WebServlet("/login")
public class login extends HttpServlet{

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

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter output = resp.getWriter();
        String _username = req.getParameter("username");
        String _password = req.getParameter("password");

        HttpSession session = req.getSession();
        session.setAttribute("username", _username);

        try {


            //Encrypt Password
            String encPassword = null;
            encPassword= Encryption.encryptThisString(_password);

             if (encPassword != null) {
                 DBRepro.GetUserDetails(_username, encPassword);

                    if (DBRepro.GetUserDetails(_username, encPassword)) {
                        //output.println("Welcome " + username + "!");
                        resp.sendRedirect("/ServletJava8Test_war_exploded/dashboard");
                    }
                    else {
                        resp.sendRedirect("index.jsp");
                        output.println("Wrong username or password. Please try again.");
                    }
             }
             else
             {
                 //resp.sendRedirect("index.jsp");
                 resp.setContentType("text/html");
                 output.println("<script type=\"text/javascript\">");
                 output.println("alert('Wrong Creds Please Try Again');");
                 output.println("</script>");
             }

        } catch (IllegalAccessException | SQLException | ClassNotFoundException | InstantiationException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
