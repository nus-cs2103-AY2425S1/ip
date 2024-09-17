package Azir;

/**
 * Parser deals with manipulating the user's input
 * for easy use
 */
public class Parser {
    /**
     * Parses the user input into an array for easy access
     *
     * @param command User's input
     * @param size Size of the task list
     * @return Array of command arguments
     * @throws AzirException If user input in wrong format
     */
    public static String[] parse(String command, int size) throws AzirException {
        if (command.equals("list")) {
            return new String[] {"list"};
        } else if (command.startsWith("mark")) {
            String[] result = command.split(" ");
            if (result.length == 1) {
                throw new AzirException("Input the task number you would like to mark.");
            }
            if (Integer.valueOf(result[1]) < 1 || (Integer.valueOf(result[1]) > size)) {
                throw new AzirException("Invalid value");
            }
            return result;
        } else if (command.startsWith("unmark")) {
            String[] result = command.split(" ");
            if (result.length == 1) {
                throw new AzirException("Input the task number you would like to unmark.");
            }
            if (Integer.valueOf(result[1]) < 1 || (Integer.valueOf(result[1]) > size)) {
                throw new AzirException("Invalid value");
            }
            return result;
        } else if (command.startsWith("delete")) {
            String[] result = command.split(" ");
            if (result.length == 1) {
                throw new AzirException("Input the task number you would like to delete.");
            }
            if (Integer.valueOf(result[1]) < 1 || (Integer.valueOf(result[1]) > size)) {
                throw new AzirException("Invalid value");
            }
            return result;
        } else if (command.startsWith("todo")) {
            int todoIndex = command.indexOf("todo");
            if (command.length() == 4 || command.substring(5).trim().isEmpty()) {
                throw new AzirException("todo cannot have an empty description. " +
                        "Format: todo [description]");
            }
            String description = command.substring(5);
            return new String[] {"todo", description};
        } else if (command.startsWith("deadline")) {
            int byIndex = command.indexOf("/by");
            if (byIndex == -1) {
                throw new AzirException("deadline needs a /by date. " +
                        "Format: deadline [description] /by [date]");
            }
            if (command.substring(8, byIndex).trim().isEmpty()) {
                throw new AzirException("deadline needs a description. " +
                        "Format: deadline [description] /by [date]");
            }
            if (command.trim().endsWith("/by")) {
                throw new AzirException("You need a deadline day. " +
                        "Format: deadline [description] /by [date]");
            }
            String description = command.substring(9, byIndex - 1);
            String day = command.substring(byIndex + 4);
            return new String[] {"deadline", description, day};
        } else if (command.startsWith("event")) {
            int fromIndex = command.indexOf("/from");
            int toIndex = command.indexOf("/to");
            if (fromIndex == -1) {
                throw new AzirException("event needs a /from. " +
                        "Format: event [description] /from [date] /to [date]");
            }
            if (toIndex == -1) {
                throw new AzirException("event needs a /to. " +
                        "Format: event [description] /from [date] /to [date]");
            }
            if (command.substring(fromIndex + 5, toIndex).trim().isEmpty()) {
                throw new AzirException("event needs a from date. " +
                        "Format: event [description] /from [date] /to [date]");
            }
            if (command.substring(5, fromIndex).trim().isEmpty()) {
                throw new AzirException("event needs a description. " +
                        "Format: event [description] /from [date] /to [date]");
            }
            if (command.trim().endsWith("/to")) {
                throw new AzirException("You need an ending date. " +
                        "Format: event [description] /from [date] /to [date]");
            }
            String description = command.substring(6, fromIndex - 1);
            String startDay = command.substring(fromIndex + 6, toIndex - 1);
            String endDay = command.substring(toIndex + 4);
            return new String[] {"event", description, startDay, endDay};
        } else if (command.startsWith("find")) {
            int todoIndex = command.indexOf("find");
            if (command.length() == 4 || command.substring(5).trim().isEmpty()) {
                throw new AzirException("find cannot have an empty keyword. " +
                        "Format: find [keyword]");
            }
            String keyword = command.substring(5);
            return new String[] {"find", keyword};
        } else {
            throw new AzirException("Azir does not take in this input");
        }
    }
}
