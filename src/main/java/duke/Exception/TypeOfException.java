package duke.Exception;

/**
 * A class that provides different error messages used during the runtime of the program.
 */
public class TypeOfException {

    public String showLoadingError() {
        return "Error loading file";
    }

    public String noIdea() {
        return "Sorry! I don't understand what you are saying. Please enter a valid command. "
                + "todo, deadline, event, list, done, delete, bye";
    }

    public String todoFormatError() {
        return "Incorrect format. Use 'todo <task description>'";
    }

    public String deadlineFormatError() {
        return "Incorrect format. Use 'deadline <task description> /by <date time>'";
    }

    public String eventFormatError() {
        return "Incorrect format. Use 'event <task description> /from <date time> /to <date time>'";
    }

    public String timeFormatError() {
        return "Incorrect time format. Must be in yyyy-MM-dd hh:mm am/pm";
    }

    public String findFormatError() {
        return "Incorrect format. Use 'find <keyword>'";
    }
}
