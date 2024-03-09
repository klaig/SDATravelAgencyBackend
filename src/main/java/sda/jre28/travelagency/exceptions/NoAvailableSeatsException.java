package sda.jre28.travelagency.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoAvailableSeatsException extends Exception{
    private String message;

    public NoAvailableSeatsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return "NoAvailableSeatsException{" +
                "message='" + message + '\'' +
                '}';
    }
}
