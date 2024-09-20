package secondmind;

public class InvalidCommandFormat extends Exception {
    @Override
    public String toString() {
        return "Invalid command format detected.\n"
                + "Type \\help for a list of available commands.";
    }
}
