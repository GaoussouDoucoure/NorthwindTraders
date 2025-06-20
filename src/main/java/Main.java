import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "";

        HomeScreen.homeScreen();
        System.out.print("Select an option: ");
        int choice = sc.nextInt();
        switch (choice){
            case 0:
                System.out.println("\nYou chose to exit the application. Application terminated..");
                break;

            case 1:
                query = Queries.displayAllProducts();

                String url = "jdbc:mysql://localhost:3306/northwind";
                sc.nextLine();
                System.out.print("Enter database username: ");
                String username = sc.nextLine();
                System.out.print("Enter database password: ");
                String password = sc.nextLine();

                try {
                    connection = DriverManager.getConnection(url, username, password);
                    preparedStatement = connection.prepareStatement(query);
                    resultSet = preparedStatement.executeQuery();

                    System.out.println("\nProducts Names:");
                    int count = 1;
                    while (resultSet.next()){
                        String productName = resultSet.getString(2);
                        System.out.println(count +".) " + productName);
                        count++;
                    }

                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                finally {
                    // close the resources
                    closeResources(resultSet, preparedStatement, connection);
                }

                break;

            case 2:
                query= Queries.displayAllCustomers();

                url = "jdbc:mysql://localhost:3306/northwind";
                sc.nextLine();
                System.out.print("Enter database username: ");
                username = sc.nextLine();
                System.out.print("Enter database password: ");
                password = sc.nextLine();

                try {
                    connection = DriverManager.getConnection(url, username, password);
                    preparedStatement = connection.prepareStatement(query);
                    resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()){
                        String contactName = resultSet.getString("ContactName");
                        String companyName = resultSet.getString("CompanyName");
                        String city = resultSet.getString("City");
                        String country = resultSet.getString("Country");
                        String phone = resultSet.getString("Phone");
                        System.out.printf("""
                                
                                Contact Name: %s
                                Company Name: %s
                                City: %s
                                Country: %s
                                Phone: %s
                                ----------------------------------------
                                """, contactName, companyName, city, country, phone);
                    }

                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                finally {
                    // close the resources
                    closeResources(resultSet, preparedStatement, connection);
                }

                break;
            default:
                System.out.println("You have entered the wrong choice: " + choice);
                HomeScreen.homeScreen();
        }

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
