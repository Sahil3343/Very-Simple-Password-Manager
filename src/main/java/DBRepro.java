import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DBRepro {

    public static Boolean GetUserDetails(String username, String Epassword) throws IllegalAccessException, SQLException, ClassNotFoundException, InstantiationException, NoSuchAlgorithmException {
        DbConnection connect = new DbConnection("jdbc:sqlserver://passworddbserver.database.windows.net:1433;database=passworddb;encrypt=true;", "", "");
        Connection con = connect.GetConnection();


        Boolean result = false;

        String sql = "select username, password from user_details where username = ? AND password = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, Epassword);
        ResultSet rs = statement.executeQuery();

        if (!rs.next()) {
            result = false;
        }
        else {
            result = true;
        }
        connect.Closeconnection(con, statement);
        return result;
    }

    public static Boolean InsertUserDetails(String first_name, String last_name, String email, String username, String Epassword) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        DbConnection connect = new DbConnection("jdbc:sqlserver://passworddbserver.database.windows.net:1433;database=passworddb;encrypt=true;", "", "");
        Connection con = connect.GetConnection();

        Boolean result = false;
        String sql = "insert into user_details values (?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, first_name);
        statement.setString(2, last_name);
        statement.setString(3, email);
        statement.setString(4, username);
        statement.setString(5, Epassword);
        boolean rs = statement.execute();

        connect.Closeconnection(con, statement);
        return true;
    }

    public static List<String> test() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        DbConnection connect = new DbConnection("jdbc:sqlserver://passworddbserver.database.windows.net:1433;database=passworddb;encrypt=true;", "", "");
        Connection con = connect.GetConnection();

        Boolean result = false;

        String sql = "select * from user_details";
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        List<String> list = new ArrayList<String>();

        while(rs.next()) {
            list.add(rs.getString("firstname"));
        }

        connect.Closeconnection(con, statement);

        return list;
    }

}
