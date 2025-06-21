import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/northwind";
        String query = "";

        Functions.homeScreen();
        System.out.print("Select an option: ");
        int choice = sc.nextInt();


        switch (choice){
            case 0:
                System.out.println("\nYou chose to exit the application. Application terminated..");
                break;

            case 1:
                query = Queries.displayAllProducts();
                sc.nextLine();
                System.out.print("Enter database username: ");
                String username = sc.nextLine();
                System.out.print("Enter database password: ");
                String password = sc.nextLine();

                try (Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()){
                        // Print header with pipes
                        System.out.printf("\n| %-8s | %-35s | %-8s | %-5s |%n", "Id", "Name", "Price", "Stock");
                        System.out.println("|----------|-------------------------------------|----------|-------|");

                        // Print each product
                        while (resultSet.next()) {
                            int id = resultSet.getInt("ProductID");
                            String name = resultSet.getString("ProductName");
                            double price = resultSet.getDouble("UnitPrice");
                            int stock = resultSet.getInt("UnitsInStock");

                            System.out.printf("| %-8d | %-35s | $%-7.2f | %5d |%n", id, name, price, stock);
                        }
                    }

                }
                catch (SQLException e) {
                    e.printStackTrace();
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

                try (Connection connection = DriverManager.getConnection(url, username, password);
                     PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    try(ResultSet resultSet = preparedStatement.executeQuery()){
                        while (resultSet.next()) {
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

                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

                break;

            case 3:
                query = Queries.displayAllCategories();
                url = "jdbc:mysql://localhost:3306/northwind";
                sc.nextLine();
                System.out.print("Enter database username: ");
                username = sc.nextLine();
                System.out.print("Enter database password: ");
                password = sc.nextLine();

                try (Connection connection = DriverManager.getConnection(url, username, password);
                     PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        //header
                        System.out.printf("\n| %-20s | %-20s |\n", "Category ID", "Category Name");
                        System.out.print("-----------------------|-----------------------");
                        while (resultSet.next()) {
                            int categoryID = resultSet.getInt("CategoryID");
                            String categoryName = resultSet.getString("CategoryName");
                            System.out.printf("\n| %-20s | %-20s |", categoryID, categoryName);
                        }
                        System.out.println();
                    }

                }
                catch (SQLException e){
                    e.printStackTrace();
                }

                break;

            default:
                System.out.println("You have entered the wrong choice: " + choice);
        }

    }

}
