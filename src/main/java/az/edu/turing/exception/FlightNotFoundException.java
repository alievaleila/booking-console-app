package az.edu.turing.exception;

public class FlightNotFoundException extends RuntimeException{

    public FlightNotFoundException(){
        super("Flight Not Found");
    }

    public FlightNotFoundException(String message){
        super(message);
    }
}
