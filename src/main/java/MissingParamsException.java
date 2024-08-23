public class MissingParamsException extends Exception {

    String command;
    MissingParamsException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("Error: %s command has incomplete description ", this.command);
    }
}
