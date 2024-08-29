package botimusprime.parser;

import botimusprime.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class handles the parsing of user commands and the conversion of strings to date and time formats.
 * It interacts with the TaskList, Ui, and Storage components to process user input and manage tasks.
 */
public class Parser {
    private botimusprime.tasks.TaskList taskList;
    private Ui ui;
    private botimusprime.storage.Storage storage;

    /**
     * Constructs a Parser instance with the specified TaskList, UI, and Storage.
     *
     * @param taskList The TaskList to keep track of tasks.
     * @param ui       The Ui component that handles interactions with the user.
     * @param storage  The Storage component to handle saving and loading tasks from disk.
     */
    public Parser(botimusprime.tasks.TaskList taskList, Ui ui, botimusprime.storage.Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Converts a date and time string into a LocalDateTime object.
     * The method tries to parse the string using different date and time formats.
     *
     * @param timeString The date and/or time string to be converted.
     * @return A LocalDateTime object representing the parsed date and time.
     * @throws IllegalArgumentException if the string cannot be parsed into any known date format.
     */
    public static LocalDateTime stringToDateTime(String timeString) {
        DateTimeFormatter withTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter withoutTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter slashWithTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        DateTimeFormatter slashWithoutTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            return LocalDateTime.parse(timeString, withTime);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate date = LocalDate.parse(timeString, withoutTime);
                return date.atStartOfDay();
            } catch (DateTimeParseException e2) {
                try {
                    LocalDate date = LocalDate.parse(timeString, slashWithoutTime);
                    return date.atStartOfDay();
                } catch (DateTimeParseException e3) {
                    try {
                        return LocalDateTime.parse(timeString, slashWithTime);
                    } catch (DateTimeParseException e4) {
                        throw new IllegalArgumentException("Wrong date" + timeString);
                    }
                }
            }
        }
    }

    /**
     * Parses the user input and executes the corresponding command.
     * The method recognizes commands such as "bye", "list", "mark", "unmark", "to\do", "deadline", "event", and "delete".
     * If the command is not recognized, an error message is displayed.
     *
     * @param input The user input string to be parsed.
     * @return true if the command is "bye" and false otherwise.
     */
    public boolean parse(String input) {
        if (input.equals("bye")) {
            return true;
        } else if (input.equals("list")) {
            taskList.showList();
        } else if (input.startsWith("mark")) {
            taskList.markDone(input);
        } else if (input.startsWith("unmark")) {
            taskList.markUndone(input);
        } else if (input.startsWith("todo")) {
            taskList.addToDo(input);
        } else if (input.startsWith("deadline")) {
            taskList.addDeadline(input);
        } else if (input.startsWith("event")) {
            taskList.addEvent(input);
        } else if (input.startsWith("delete")) {
            taskList.delete(input);
        } else if (input.startsWith("find")) {
            taskList.findTask(input);
        } else {
            System.out.println("bro out here speaking nonsense issit");
        }
        return false;
    }

}