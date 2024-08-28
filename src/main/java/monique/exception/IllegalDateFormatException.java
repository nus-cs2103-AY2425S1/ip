package monique.exception;

public class IllegalDateFormatException extends MoniqueException {
    public IllegalDateFormatException(){super("Illegal Date Format");}

    @Override
    public void advice(){
        System.out.println("You have tried to create an Event without using proper " +
                "date-time formats. Please try again, with the correct input format");
    }
}
