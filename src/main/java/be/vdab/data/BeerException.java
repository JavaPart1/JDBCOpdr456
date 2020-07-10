package be.vdab.data;

public class BeerException extends Exception{
    public BeerException(String message) {
        super(message);
    }

    public BeerException(Throwable cause) {
        super(cause);
    }
}
