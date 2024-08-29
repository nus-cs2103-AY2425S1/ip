public class Parser {
    public enum CommandToken {
        MARK, UNMARK, LIST, DELETE,
        DEADLINE, TODO, EVENT,
        BYE;

        public static CommandToken fromString(String s) throws InvalidCommandException {
            try {
                return CommandToken.valueOf(s.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new InvalidCommandException(s);
            }
        }
    }
}
