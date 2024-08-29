package topaz.exception;

public class InvalidCommandException extends Exception {

    private String input;
    public InvalidCommandException(String input) {
        super();
        this.input = input;
    }

    @Override
    public String toString() {
        return "Steady... Your put an invalid command: \"" + input + "\", type \"help\" to see how to use the chatbot.";
    }
}
