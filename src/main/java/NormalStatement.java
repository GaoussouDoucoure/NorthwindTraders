import java.sql.*;
import java.util.Scanner;

public class NormalStatement {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String url = "jdbc:mysql://localhost:3306/northwind"; //URL for connection
            System.out.print("Enter Database username: ");
            String username = sc.nextLine();   //DATABASE username will be asked to input
            System.out.println("Enter database password: ");
            String password = sc.nextLine();  //DATABASE password will be asked to input

            String query = "SELECT * FROM northwind.products;"; //QUERY to execute

            Class.forName("com.mysql.cj.jdbc.Driver"); //LOADING the DRIVER
            Connection connection = DriverManager.getConnection(url, username, password); //creating CONNECTION to the DATABASE
            Statement statement = connection.createStatement(); //creating STATEMENT object
            ResultSet result = statement.executeQuery(query); //executeQuery because I'm only FETCHING

            //Using the FETCHED DATA
            System.out.println("Products Names:");
            int count = 1;
            while (result.next()){
                String productName = result.getString(2);
                System.out.println(count +".) " + productName);
                count++;
            }

            //closing the RESOURCES
            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
