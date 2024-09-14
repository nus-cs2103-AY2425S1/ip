package rob;

/**
 * Interprets the user input string and splits string for further processing.
 */
public class Parser {
    private String string;

    /**
     * Constructs a new Parser to interpret and process the input string.
     *
     * @param string The input string to be processed.
     */
    public Parser(String string) {
        this.string = string;

    }

    /**
     * Checks if the input string is empty and prints an error message if it is.
     */
    public void checkString() throws RobException {
        if (string.isEmpty()) {
            System.out.println("Invalid input! Please enter a task.");
        } else if (string.equals(" ")) {
            System.out.println("Invalid input! HERER.");
            throw new RobException("Invalid format... What task would you like to add?");
        }
    }

    /**
     * Gets the command from the input string.
     *
     * @return The command extracted from the input string.
     */
    public String getCommand() {
        return string.split(" ", 2)[0];
    }

    /**
     * Retrieves the description of a task based on the command in the input string.
     *
     * @return The description of the task.
     * @throws RobException If the input string format is invalid or if the command is unknown.
     */
    public String getDesc() throws RobException {
        String[] parts = string.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new RobException("Invalid format... What task would you like to add?");
        }
        String rem = parts[1].trim();
        String desc;
        switch (this.getCommand()) {
        case "deadline":
            desc = rem.split("/by", 2)[0].trim();
            break;
        case "event":
            desc = rem.split("/from", 2)[0].trim();
            break;
        case "todo":
            desc = rem;
            break;
        default:
            throw new RobException("Unknown command...");
        }
        if (desc.isEmpty()) {
            throw new RobException("Invalid format... What task would you like to add?");
        }
        return desc;
    }

    /**
     * Gets the deadline date from the input string.
     * If the input string does not contain the "/by" keyword, throws an exception.
     * If it contains "/by", returns the date following this keyword.
     *
     * @return The deadline date as a string.
     * @throws RobException If "/by" is missing in the input string.
     */
    public String getDay() throws RobException {
        if (!string.contains("/by")) {
            throw new RobException("Missing '/by' in deadline command.");
        }
        String rem = string.split(" ", 2)[1].trim();
        String[] parts = rem.split("/by", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new RobException("Missing 'deadline' field.");
        }
        return parts[1].trim();
    }

    /**
     * Gets the start date from the input string for an event.
     * If the input string does not contain the "/from" keyword, throws an exception.
     * If it contains "/from", returns the date following this keyword.
     *
     * @return The start date of the event as a string.
     * @throws RobException If "/from" is missing in the input string.
     */
    public String getFrom() throws RobException {
        if (!string.contains(" /from")) {
            throw new RobException("Missing '/from' or '/to' in event command.");
        } else {
            String rem = string.split(" ", 2)[1].trim(); // ignore first keyword of input
            String from = rem.split(" /from")[1].split(" /to")[0].trim();
            if (from.isEmpty()) {
                throw new RobException("Missing 'from' or 'to' fields.");
            }
            return from;
        }
    }

    /**
     * Gets the end date from the input string for an event.
     * If the input string does not contain the "/to" keyword, throws an exception.
     * If it contains "/to", returns the date following this keyword.
     *
     * @return The end date of the event as a string.
     * @throws RobException If "/to" is missing in the input string.
     */
    public String getTo() throws RobException {
        if (!string.contains(" /to")) {
            throw new RobException("Missing '/from' or '/to' in event command.");
        } else {
            String rem = string.split(" ", 2)[1].trim(); // ignore first keyword of input
            String to = rem.split(" /from")[1].split(" /to", 2)[1].trim();
            if (to.isEmpty()) {
                throw new RobException("Missing 'from' or 'to' fields.");
            }
            return to;
        }
    }

    public String getFind() throws RobException {
        if (string.split(" ", 2).length < 2) {
            throw new RobException("Invalid format... What task would you like to find?");
        } else {
            System.out.println(string.split(" ", 2)[0].trim());
            return string.split(" ", 2)[1].trim();
        }
    }
}
