package be.vdab.jdbc;

import be.vdab.data.*;

import java.util.ArrayList;

public class BeerClient {
    public static void main(String[] args) throws BeerException {
        BeerDao beerDao = new BeerDaoImpl(JdbcPass.getJDBCURL(), JdbcPass.getJDBCUSER(), JdbcPass.getPASSW());
        Beer beer = beerDao.getBeerById(5);

        System.out.println(beer.getName());

        beer.setStock(3);
        beerDao.updateBeer(beer);

        ArrayList<Beer> bl = new ArrayList<>();
        bl = beerDao.getBeersByCategory(15);
        for (int i = 0; i < bl.size(); i++) {
            System.out.print("Bier : " + bl.get(i).getName());
            System.out.println(" heeft cat : " + bl.get(i).getCategory());
        }

        bl = beerDao.getBeersByBrewer(112);
        for (int i = 0; i < bl.size(); i++) {
            System.out.print("Bier : " + bl.get(i).getName());
            System.out.println(" wordt gebrouwd dr : " + bl.get(i).getBrewer());
        }
    }
}
