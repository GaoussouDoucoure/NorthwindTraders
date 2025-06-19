import java.sql.*;
import java.util.Scanner;

public class PreparedStatement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/northwind";
        System.out.print("Enter Database username: ");
        String username = sc.nextLine();
        System.out.print("Enter Database password: ");
        String password = sc.nextLine();

        String query = "SELECT * FROM northwind.products WHERE ProductID = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.print("Enter ProductID to fetch product name (e.g. 12): ");
            int userInput = sc.nextInt();
            preparedStatement.setInt(1, userInput);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            String productName = result.getString(2); //in the parentheses I can also put the actual name of the column
            System.out.println("\nThe product with ProductID " + userInput + " is " + "\"" + productName + "\"");

            connection.close();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("An error has occurred!");
            System.out.println(e);
        }
    }
}