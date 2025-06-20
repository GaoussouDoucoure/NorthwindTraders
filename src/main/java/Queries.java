public class Queries {

    public static String displayAllProducts(){
        String query = """
                SELECT ProductID, ProductName, UnitPrice, UnitsInStock
                FROM northwind.products;
                """;
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

    public static String displayAllCategories(){
        String query = """
                SELECT CategoryID, CategoryName
                FROM northwind.categories
                ORDER BY CategoryID;
                """;
        return query;
    }

}
