package exceptions;

public class BadDataException extends Exception{

    public BadDataException() {
        super("Error: The data provided in data/tasks.txt is not in the correct format");
    }
}
