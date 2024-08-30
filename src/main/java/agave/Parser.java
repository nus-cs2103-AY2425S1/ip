package agave;

import java.time.format.DateTimeParseException;

public class Parser {
    private String userInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    public String getCommand() {
        return userInput.split(" ")[0].toLowerCase();
    }

    public int getTaskNumber() throws AgaveException {
        try {
            return Integer.parseInt(userInput.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new AgaveException("Please enter a valid task number.");
        }
    }

    public String getKey() throws AgaveException {
        try {
            return userInput.split(" ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AgaveException("Please enter a keyword to search for.");
        }
    }

    public Task parseTodo() throws AgaveException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new AgaveException("The description of a todo cannot be empty.");
        }
        return new ToDo(description);
    }

    public Deadline parseDeadline() throws AgaveException {
        try {
            String[] split = userInput.split(" /by ");
            String description = split[0].substring(8).trim();
            String by = split[1].trim();
            if (description.isEmpty() || by.isEmpty()) {
                throw new AgaveException("The description and deadline of a task cannot be empty.");
            }
            return new Deadline(description, by);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AgaveException("Please enter the deadline in the correct format: 'deadline <description> /by <yyyy/MM/dd HHmm>'.");
        } catch (DateTimeParseException e) {
            throw new AgaveException("Please enter the date and time in the correct format: 'yyyy/MM/dd HHmm'.");
        }
    }

    public Event parseEvent() throws AgaveException {
        try {
            String[] split = userInput.split(" /from | /to ");
            String description = split[0].substring(5).trim();
            String from = split[1].trim();
            String to = split[2].trim();
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new AgaveException("The description, start time, and end time of an event cannot be empty.");
            }
            return new Event(description, from, to);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AgaveException("Please enter the event in the correct format: 'event <description> /from <yyyy/MM/dd HHmm> /to <yyyy/MM/dd HHmm>'.");
        } catch (DateTimeParseException e) {
            throw new AgaveException("Please enter the date and time in the correct format: 'yyyy/MM/dd HHmm'.");
        }
    }
}
