import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UsingDataSource {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Displaying all products..\n");
        System.out.print("Enter database username: ");
        String username = sc.nextLine();
        System.out.print("Enter database password: ");
        String password = sc.nextLine();

        // Create the datasource
        try (BasicDataSource dataSource = new BasicDataSource()) {

            // Configure the datasource
            dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            // Interact with the database
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(Queries.displayAllProducts())) {

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
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


            } catch (SQLException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}