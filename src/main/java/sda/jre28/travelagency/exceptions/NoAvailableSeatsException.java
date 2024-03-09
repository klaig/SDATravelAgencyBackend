package sda.jre28.travelagency.exceptions;

public class NoAvailableSeatsException extends Exception{
    private String message;

    public NoAvailableSeatsException(String message) {
        super(message);
        this.message = message;
    }
}
