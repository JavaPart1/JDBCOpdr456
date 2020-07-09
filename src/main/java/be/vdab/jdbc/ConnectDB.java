package be.vdab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {
    public static void main(String[] args) {
        // toch een driver declaratie toevoegen
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String passw = new Pass().getPassw();
        String sql = "SELECT Name, Alcohol, Price FROM Beers";
        /*String sql = "SELECT a.Name, Category, Alcohol, Price, b.Name Brewer" +
                " FROM Beers a INNER JOIN Brewers b ON a.BrewerId = b.Id" +
                " INNER JOIN Categories c ON a.CategoryId = c.Id";*/
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/beersdb?serverTimezone=UTC"
                ,"root",passw);
             Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(sql)
        ) {
            System.out.println("Connectie ok");
            // niet nodig : con.close();
            rs.afterLast();
            while (rs.previous()){
            //while (rs.next()){
                System.out.print("Bier : " + rs.getString("Name"));
                //System.out.print(" ; categorie : " + rs.getString("Category"));
                System.out.print(" ; alcohol: " + rs.getDouble("Alcohol"));
                System.out.println(" ; kost : " + rs.getDouble("Price"));
                //System.out.println(" ; brouwer : " + rs.getString("Brewer"));
            }
        }
        catch (Exception ex){
            System.out.println("Connectie nok");
            ex.printStackTrace(System.err);
        }
    }
}
