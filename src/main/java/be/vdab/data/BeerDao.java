package be.vdab.data;

import java.util.ArrayList;

public interface BeerDao {
    public Beer getBeerById(int id) throws BeerException;
    public void updateBeer(Beer beer) throws BeerException;
    public ArrayList<Beer> getBeersByCategory(int cat) throws BeerException;
    public ArrayList<Beer> getBeersByBrewer(int brewer) throws BeerException;
    public ArrayList<Beer> getBeersByZipCode(String zip) throws BeerException;
    public void deleteBeer(Beer beer) throws BeerException;
    public int checkStock(Beer beer) throws BeerException;
}
