public class Parser {
    /**
     * Enumerates the possible commands that can be executed by the Eevee program.
     */
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;
    }

    public Command parseCommand(String input) throws EeveeException {
        try {
            return Command.valueOf(input.toUpperCase().split(" ")[0]);
        } catch (IllegalArgumentException e) {
            throw new EeveeException("You seemed to have typed wrong. This is not a valid command.");
        }
    }

    public int parseTaskNumber(String input) throws EeveeException {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new EeveeException("No task under the given task number. "
                    + "Did you type the wrong number?");
        }
    }
}
