package exceptions;

public class MissingParamsException extends Exception {
    private String command;

    public MissingParamsException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("Error: %s command has incomplete description ", this.command);
    }
}
