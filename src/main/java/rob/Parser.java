package rob;

import java.util.Objects;

public class Parser {
    private String string;

    public Parser(String string) {
        this.string = string;

    }

    /**
     * Checks if the input string is empty and prints an error message if it is.
     *
     */
    public void checkString() {
        if (string.isEmpty()) {
            System.out.println("Invalid input! Please enter a task.");
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

    public String getDesc() throws DukeException {
        if (string.split(" ", 2).length < 2) {
            throw new DukeException("Invalid format... What task would you like to add?");
        } else {
            String rem = string.split(" ", 2)[1].trim(); // ignore first keyword of input
            if (Objects.equals(this.getCommand(), "deadline")) {
                return rem.split(" /by")[0].trim();
            } else if (Objects.equals(this.getCommand(), "event")) {
                return rem.split(" /from")[0].trim();
            } else if (Objects.equals(this.getCommand(), "todo")) {
                return rem;
            }
        }
        return "";
    }

    /**
     * Gets the deadline date from the input string.
     * If the input string does not contain the "/by" keyword, throws an exception.
     * If it contains "/by", returns the date following this keyword.
     *
     * @return The deadline date as a string.
     * @throws DukeException If "/by" is missing in the input string.
     */
    public String getDay() throws DukeException {
        if (!string.contains(" /by")) {
            throw new DukeException("Missing '/by' in deadline command.");
        } else {
            String rem = string.split(" ", 2)[1].trim(); // ignore first keyword of input
            return rem.split(" /by")[1].trim();
        }
    }

    /**
     * Gets the start date from the input string for an event.
     * If the input string does not contain the "/from" keyword, throws an exception.
     * If it contains "/from", returns the date following this keyword.
     *
     * @return The start date of the event as a string.
     * @throws DukeException If "/from" is missing in the input string.
     */
    public String getFrom() throws DukeException {
        if (!string.contains(" /from")) {
            throw new DukeException("Missing '/from' or '/to' in event command.");
        } else {
            String rem = string.split(" ", 2)[1].trim(); // ignore first keyword of input
            return rem.split(" /from")[1].split(" /to")[0].trim();

        }
    }

    /**
     * Gets the end date from the input string for an event.
     * If the input string does not contain the "/to" keyword, throws an exception.
     * If it contains "/to", returns the date following this keyword.
     *
     * @return The end date of the event as a string.
     * @throws DukeException If "/to" is missing in the input string.
     */
    public String getTo() throws DukeException {
        if (!string.contains(" /to")) {
            throw new DukeException("Missing '/from' or '/to' in event command.");
        } else {
            String rem = string.split(" ", 2)[1].trim(); // ignore first keyword of input
            return rem.split(" /from")[1].split(" /to")[1].trim();
        }
    }

    public String getFind() throws DukeException {
        if (string.split(" ", 2).length < 2) {
            throw new DukeException("Invalid format... What task would you like to find?");
        } else {
            System.out.println(string.split(" ", 2)[0].trim());
            return string.split(" ", 2)[1].trim();
        }
    }
}
