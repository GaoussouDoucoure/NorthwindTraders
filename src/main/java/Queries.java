public class Queries {

    public static String displayAllProducts(){
        String query = "SELECT * FROM northwind.products;";
        return query;
    }

    public static String displayAllCustomers(){
        String query = """
                SELECT ContactName, CompanyName, City, Country, Phone
                FROM northwind.customers
                WHERE COUNTRY is not Null
                ORDER BY Country ;
                """;
        return query;
    }

}
