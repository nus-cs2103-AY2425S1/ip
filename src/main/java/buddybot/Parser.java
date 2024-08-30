package buddybot;

public class Parser {
        public static Commands parseCmd(String input) throws BuddyBotException {
            String command = input.split(" ", 2)[0];
            try {
                return Commands.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new BuddyBotException("It's not working. Invalid Command\n");
            }
        }

    /**
     *
     * @param input
     * @return
     * @throws BuddyBotException
     */
        public static String parseArgs(String input) throws BuddyBotException {
            try {
                return input.split(" ", 2)[1] + "\n";
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new BuddyBotException("Missing argument");
            }
        }
}
