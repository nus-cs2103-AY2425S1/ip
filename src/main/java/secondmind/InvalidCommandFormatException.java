package secondmind;

public class InvalidCommandFormatException extends Exception {
    @Override
    public String toString() {
        return "Invalid command format detected.";
    }
}