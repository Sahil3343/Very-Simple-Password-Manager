import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/registeration")
public class registeration extends HttpServlet {

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
        resp.setContentType("text/hmtl");
        PrintWriter output = resp.getWriter();
        String _firstname = req.getParameter("first");
        String _lastname = req.getParameter("last");
        String _email = req.getParameter("email");
        String _username = req.getParameter("username");
        String _password = req.getParameter("password");
        String _confirmPassword = req.getParameter("confirmpassword");

        if (_password.equals(_confirmPassword)) {
            String encPassword = null;
            encPassword= Encryption.encryptThisString(_password);

            if (encPassword != null) {
                try {
                    if (DBRepro.InsertUserDetails(_firstname, _lastname, _email, _username, encPassword)) {
                        resp.sendRedirect("index.jsp");
                    }
                    else {
                        System.out.println("Error!!");
                    }
                } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        else {
            resp.setContentType("text/html");
            output.println("<script type=\"text/javascript\">");
            output.println("alert('Something doesnt match. Please try again.');");
            output.println("</script>");
        }
    }
}
