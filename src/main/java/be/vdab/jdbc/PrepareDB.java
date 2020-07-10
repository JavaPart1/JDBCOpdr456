package be.vdab.jdbc;

import java.sql.*;
import be.vdab.jdbc.Pass.*;

public class PrepareDB {
    public static void main(String[] args) {
        // toch een driver declaratie toevoegen
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String passw = new Pass().getPASSW();
        String sql = "SELECT Name, Alcohol, Price FROM Beers WHERE Alcohol =?";
        /*String sql = "SELECT a.Name, Category, Alcohol, Price, b.Name Brewer" +
                " FROM Beers a INNER JOIN Brewers b ON a.BrewerId = b.Id" +
                " INNER JOIN Categories c ON a.CategoryId = c.Id";*/
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/beersdb?serverTimezone=UTC"
                ,"root",passw);
             PreparedStatement stmt = con.prepareStatement(sql);
             //ResultSet rs = stmt.executeQuery(sql)
        ) {
            stmt.setDouble(1,7);
            ResultSet rs = stmt.executeQuery();

            //ResultSet.TYPE_SCROLL_INSENSITIVE; omgekeerd door een resultset gaan, zal hier niet mogelijk zijn !
            System.out.println("Connectie ok");
            // niet nodig : con.close();
            //rs.afterLast();
            //while (rs.previous()){
            System.out.println("run met alcohol = 7");
            while (rs.next()){
                System.out.print("Bier : " + rs.getString("Name"));
                //System.out.print(" ; categorie : " + rs.getString("Category"));
                System.out.print(" ; alcohol: " + rs.getDouble("Alcohol"));
                System.out.println(" ; kost : " + rs.getDouble("Price"));
                //System.out.println(" ; brouwer : " + rs.getString("Brewer"));
            }
            stmt.setDouble(1,9);
            rs = stmt.executeQuery();
            System.out.println("run met alcohol = 9");
            while (rs.next()){
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
