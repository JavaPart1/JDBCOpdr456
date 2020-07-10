package be.vdab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PrepareUpdate {
    public static void main(String[] args) {
        // toch een driver declaratie toevoegen
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String passw = new Pass().getPASSW();
        String sql = "UPDATE Beers SET Stock =? WHERE Name like '%kriek%'";
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/beersdb?serverTimezone=UTC"
                ,"root",passw);
             PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            System.out.println("Connectie ok");
            stmt.setDouble(1,50);

            System.out.println("Update stmt uitvoeren...");

            int aantalrec = stmt.executeUpdate();

            System.out.println("Aantal gewijzigde records : " + aantalrec);
        }
        catch (Exception ex){
            System.out.println("Connectie nok");
            ex.printStackTrace(System.err);
        }
    }
}
