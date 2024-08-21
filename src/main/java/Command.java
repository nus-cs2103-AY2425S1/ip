public enum Command {
    LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

    public static Command fromString(String command) throws MaheshException {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MaheshException(command + " is not a valid command. Use the \"bye\" command if you wish to exit the bot.");
        }
    }
}
