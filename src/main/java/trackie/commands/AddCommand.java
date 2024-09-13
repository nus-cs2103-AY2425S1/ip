package trackie.commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.tasks.Deadline;
import trackie.tasks.Event;
import trackie.tasks.Task;
import trackie.tasks.Todo;
import trackie.ui.Trackie;
import trackie.ui.TrackieException;
import trackie.ui.Ui;

/**
 * A command that adds a task to <code>TaskList</code>.
 *
 * The type of command that is added is determined by the
 * <code>arguments</code> that are passed in.
 */
public class AddCommand extends Command {
    private String description;
    private String deadline;
    private String start;
    private String end;

    private StringBuilder retriever = new StringBuilder();
    private int ptr = 1;

    /**
     * Constructs a new Add Command with the arguments provided by the user.
     *
     * @param arguments An array of Strings storing the arguments provided by the user.
     */
    public AddCommand(String[] arguments) {
        super(false);
        super.arguments = arguments;
    }

    /**
     * Executes the add command.
     * Depending on the first argument that the user provides, this command will
     * tell the task list to add either a todo, deadline or event task.
     * If an exception is thrown in the process of adding the command, its
     * error message will be displayed to the user.
     *
     * @param tasklist The TaskList object to which a task will be added.
     * @param storage The Storage object used to save the updated task list.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) throws TrackieException {
        try {
            switch (arguments[0]) {
            case "todo":
                if (arguments.length == 1) {
                    throw new TrackieException("Correct usage: todo [desc]");
                }
                while (ptr < arguments.length) {
                    retriever.append(arguments[ptr]).append(" ");
                    ptr++;
                }
                description = retriever.substring(0, retriever.length() - 1);

                Task todoTask = new Todo(description);
                tasklist.addTask(todoTask);
                storage.save();
                return "Added: " + todoTask.toString();
            case "deadline":
                if (arguments.length == 1) {
                    throw new TrackieException("Correct usage: deadline [desc] /by [yyyy-mm-dd]");
                }

                //retrieve the description
                while (!arguments[ptr].equals("/by")) {
                    if (ptr == arguments.length - 1) {
                        throw new TrackieException("Correct usage: deadline [desc] /by [yyyy-mm-dd]");
                    }
                    retriever.append(arguments[ptr]).append(' ');
                    ptr++;
                }
                if (retriever.isEmpty()) {
                    throw new TrackieException("Description cannot be empty!");
                } else {
                    description = retriever.substring(0, retriever.length() - 1);
                }
                retriever.setLength(0); //clear the stringbuilder

                //retrieve the deadline
                ptr++;
                while (ptr < arguments.length) {
                    retriever.append(arguments[ptr]).append(' ');
                    ptr++;
                }

                if (retriever.isEmpty()) {
                    throw new TrackieException("Deadline cannot be empty!");
                } else {
                    deadline = retriever.substring(0, retriever.length() - 1);
                }

                Task deadlineTask = new Deadline(description, deadline);
                tasklist.addTask(deadlineTask);
                storage.save();
                return "Added: " + deadlineTask.toString();

            case "event":
                if (arguments.length == 1) {
                    throw new TrackieException("Correct usage: event [desc] /from [start] /to [end]");
                }

                //retrieve the description
                while (!arguments[ptr].equals("/from")) {
                    if (ptr == arguments.length - 1) {
                        throw new TrackieException("Correct usage: event [desc] /from [start] /to [end]");
                    }
                    retriever.append(arguments[ptr]).append(' ');
                    ptr++;
                }
                if (retriever.isEmpty()) {
                    throw new TrackieException("Description cannot be empty!");
                } else {
                    description = retriever.substring(0, retriever.length() - 1);
                }
                retriever.setLength(0); //clear the stringbuilder;

                ptr++;
                if (ptr >= arguments.length) {
                    throw new TrackieException("Correct usage: event [desc] /from [start] /to [end]");
                }
                //retrieve the start time
                while (!arguments[ptr].equals("/to")) {
                    if (ptr == arguments.length - 1) {
                        throw new TrackieException("Correct usage: event [desc] /from [start] /to [end]");
                    }
                    retriever.append(arguments[ptr]).append(" ");
                    ptr++;
                }
                if (retriever.isEmpty()) {
                    throw new TrackieException("Start timing cannot be empty!");
                } else {
                    start = retriever.substring(0, retriever.length() - 1);
                }
                retriever.setLength(0); //clear the retriever
                ptr++;
                //retrieve the end time
                while (ptr < arguments.length) {
                    retriever.append(arguments[ptr]).append(" ");
                    ptr++;
                }
                if (retriever.isEmpty()) {
                    throw new TrackieException("End timing cannot be empty!");
                } else {
                    end = retriever.substring(0, retriever.length() - 1);
                }
                Task eventTask = new Event(description, start, end);
                tasklist.addTask(eventTask);
                storage.save();
                return "Added: " + eventTask.toString();
            default:
                return "Invalid Command bro";
            }
        } catch (TrackieException | DateTimeParseException e) {
            return e.getMessage();
        }
    }
}
