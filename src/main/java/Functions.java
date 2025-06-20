import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Functions {
    //Home Screen Method
    public static void homeScreen(){
        System.out.println("""
                
                What do you want to do?
                  1) Display all products?
                  2) Display all customers?
                  3) Display all categories?
                  0) Exit
                
                """);
    }

    // created a method for the final block to clean up the code a bit
    public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
