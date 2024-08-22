public class InvalidCommandException extends RuntimeException {
    private String userCmd;

    public InvalidCommandException(String userCmd) {
        this.userCmd = userCmd;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" is not a valid command.\n" +
                "Type /help to see the list of available commands.", userCmd);
    }
}
