import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/northwind";
        System.out.print("Enter database username: ");
        String username = sc.nextLine();
        System.out.print("Enter database password: ");
        String password = sc.nextLine();
        String query = """
                SELECT ProductID, ProductName, UnitPrice, UnitsInStock
                FROM northwind.products;
                """;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement prepStatement = con.prepareStatement(query);
            ResultSet rs = prepStatement.executeQuery();

            /*
            FIRST OUTPUT OPTION in the book EXERCISE 2 Page-46 JDBC. 2ND OUTPUT OPTION after this COMMENTED SECTION

            while (rs.next()){
                int productId = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                double unitPrice = rs.getDouble("UnitPrice");
                int unitsInStock = rs.getInt("UnitsInStock");

                System.out.printf("""
                    
                    Product Id:      %d
                    Name:            %s
                    Price:           $%.2f
                    Stock:           %d
                    ------------------------""",
                    productId, productName, unitPrice, unitsInStock);
            }

             */

            //SECOND OUTPUT OPTION
            // Print header with pipes
            System.out.printf("| %-8s | %-35s | %-8s | %-5s |%n", "Id", "Name", "Price", "Stock");
            System.out.println("|----------|-------------------------------------|----------|-------|");

            // Print each product
            while (rs.next()) {
                int id = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                double price = rs.getDouble("UnitPrice");
                int stock = rs.getInt("UnitsInStock");

                System.out.printf("| %-8d | %-35s | $%-7.2f | %5d |%n", id, name, price, stock);
            }

            rs.close();
            prepStatement.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }

}
