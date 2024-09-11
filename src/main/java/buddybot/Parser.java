package buddybot;

/**
 * Class for Parser
 */
public class Parser {
    /**
     * Parses the user's input and checks if it is a valid command
     * @param input
     * @return
     * @throws BuddyBotException
     */
        public static Commands parseCmd(String input) throws BuddyBotException {
            assert input != null : "Input should not be empty!";
            String command = input.split(" ", 2)[0];
            try {
                return Commands.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new BuddyBotException("It's not working. Invalid Command\n");
            }
        }

    /**
     * Parses a task from the user's input
     * @param input
     * @return
     * @throws BuddyBotException
     */
        public static String parseArgs(String input) throws BuddyBotException {
            assert input != null : "Input should not be empty!";
            try {
                return input.split(" ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new BuddyBotException("Missing argument");
            }
        }
}
