package be.vdab.data;

import java.sql.*;
import java.util.ArrayList;

public class BeerDaoImpl implements BeerDao {
    private String url;
    private String user;
    private String password;

    public BeerDaoImpl(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Beer getBeerById(int id) throws BeerException {
        String query = "SELECT * FROM Beers WHERE Id=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            System.out.println("Connection ok");
            stmt.setInt(1, id);
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                if (rs.next()) {
                    System.out.print("Bier gevonden : ");
                    Beer beer = new Beer();
                    beer.setId(id);
                    beer.setName(rs.getString("Name"));
                    beer.setPrice(rs.getFloat("Price"));
                    beer.setAlcohol(rs.getFloat("Alcohol"));
                    beer.setStock(rs.getInt("Stock"));
                    return beer;
                } else {
                    System.out.println("Bier niet gevonden");
                    throw new BeerException("bier niet gevonden");
                }
            }
        } catch (SQLException throwables) {
            throw new BeerException(throwables);
        }
    }

    @Override
    public void updateBeer(Beer beer) throws BeerException {
        String update = "UPDATE Beers SET Name =?, Price =?, Alcohol=?, Stock=?" +
                " WHERE Id=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(update)
        ) {
            stmt.setString(1, beer.getName());
            stmt.setFloat(2, beer.getPrice());
            stmt.setFloat(3, beer.getAlcohol());
            stmt.setInt(4, beer.getStock());
            stmt.setInt(5, beer.getId());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throw new BeerException(throwables);
        }

    }

    @Override
    public ArrayList<Beer> getBeersByCategory(int cat) throws BeerException {
        String query = "SELECT * FROM Beers WHERE CategoryId=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            System.out.println("Connection ok");
            stmt.setInt(1, cat);
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                ArrayList<Beer> beerArrayList = new ArrayList<>();
                Beer beer;
                while (rs.next()){
                    beer = new Beer();
                    beer.setId(rs.getInt("Id"));
                    beer.setName(rs.getString("Name"));
                    beer.setPrice(rs.getFloat("Price"));
                    beer.setAlcohol(rs.getFloat("Alcohol"));
                    beer.setStock(rs.getInt("Stock"));
                    beer.setCategory(rs.getInt("CategoryId"));
                    beerArrayList.add(beer);
                }
                return beerArrayList;
            }
        } catch (SQLException throwables) {
            throw new BeerException(throwables);
        }
    }

    @Override
    public ArrayList<Beer> getBeersByBrewer(int brewer) throws BeerException {
        String query = "SELECT * FROM Beers WHERE BrewerId=?";
        try (
                Connection con = getConnection();
                PreparedStatement stmt = con.prepareStatement(query)
        ) {
            System.out.println("Connection ok");
            stmt.setInt(1, brewer);
            try (
                    ResultSet rs = stmt.executeQuery()
            ) {
                ArrayList<Beer> beerArrayList = new ArrayList<>();
                Beer beer;
                while (rs.next()){
                    beer = new Beer();
                    beer.setId(rs.getInt("Id"));
                    beer.setName(rs.getString("Name"));
                    beer.setPrice(rs.getFloat("Price"));
                    beer.setAlcohol(rs.getFloat("Alcohol"));
                    beer.setStock(rs.getInt("Stock"));
                    beer.setBrewer(rs.getInt("BrewerId"));
                    beerArrayList.add(beer);
                }
                return beerArrayList;
            }
        } catch (SQLException throwables) {
            throw new BeerException(throwables);
        }
    }

    @Override
    public ArrayList<Beer> getBeersByZipCode(String zip) throws BeerException {
        return null;
    }

    @Override
    public void deleteBeer(Beer beer) throws BeerException {

    }

    @Override
    public int checkStock(Beer beer) throws BeerException {
        return 0;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

