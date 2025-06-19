public class Queries {

    public static void displayAllProducts(){
        String query = "SELECT * FROM northwind.products;";
    }

    public static void displayAllCustomers(){
        String query = "SELECT ContactName, CompanyName, City, Country, Phone FROM northwind.customers;";
    }

}
