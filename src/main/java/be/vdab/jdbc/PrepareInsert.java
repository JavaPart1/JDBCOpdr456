package be.vdab.jdbc;

import java.sql.*;

public class PrepareInsert {
    public static void main(String[] args) {
        // Driver declaratie toevoegen
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String passw = new Pass().passw;
        // SQL om uit te voeren
        String sql = "INSERT INTO Beers (Name,Alcohol,Price,Stock,BrewerId,CategoryId)" +
                " VALUES ('MyBeer',12,3,100,20,42)";
        // DB connectie
        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/beersdb?serverTimezone=UTC"
                ,"root",passw);
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            System.out.println("Connectie ok");

            System.out.println("Insert stmt uitvoeren...");
            int aantalrec = stmt.executeUpdate();

            System.out.println("Aantal toegevoegde records : " + aantalrec);

            try (ResultSet rs = stmt.getGeneratedKeys()){
                if (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Toegevoegde key : " + id);
                }
            }
        }
        catch (Exception ex){
            System.out.println("Connectie nok");
            ex.printStackTrace(System.err);
        }
    }
}
