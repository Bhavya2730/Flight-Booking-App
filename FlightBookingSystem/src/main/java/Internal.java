import java.sql.*;
import java.lang.*;
import java.sql.SQLException;

public class Internal {

    public static void main(String[] args) 
    throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db","root","");
        String q1 = "select * from employee";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(q1);
        while (rs.next())
        {
        System.out.println("NO:"+rs.getInt(1)+"Name : "+rs.getString(2));
                }
        System.out.println("Hello World!");
    }
}
