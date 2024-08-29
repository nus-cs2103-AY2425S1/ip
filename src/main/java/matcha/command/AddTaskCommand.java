package matcha.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import matcha.exception.MatchaException;
import matcha.storage.Storage;
import matcha.task.Deadline;
import matcha.task.Event;
import matcha.task.Task;
import matcha.task.Todo;
import matcha.tasklist.TaskList;
import matcha.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 */
public class AddTaskCommand extends Command {
    private String commandType;
    private String[] inputWords;

    /**
     * Constructor for AddTaskCommand.
     *
     * @param inputWords Array of words from user input.
     * @param commandType Type of add task command.
     */
    public AddTaskCommand(String[] inputWords, String commandType) {
        this.commandType = commandType;
        this.inputWords = inputWords;
    }

    /**
     * Adds the given task to the task list. Prints out the task details and saves
     * the updated task list to file.
     *
     * @param tasks Task list to add task to.
     * @param ui Ui object to interact with user.
     * @param storage Storage object to save tasks to file.
     * @throws MatchaException If the task details are not provided or are invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MatchaException {
        if (inputWords.length < 2) {
            throw new MatchaException("Please include the " + commandType + " details!");
        }
        switch (commandType) {
        case "todo":
            Todo todo = new Todo(inputWords[1]);
            //add todos to task list
            tasks.addTask(todo);
            //save tasks to file
            storage.saveTasks(tasks.getTasks());
            String todoMessage = "Alright, I have added this Todo:\n" + tasks.showTask(todo);
            super.setResponse(todoMessage);
            break;
        case "deadline":
            Deadline deadline = createDeadline();
            //add deadline to task list
            tasks.addTask(deadline);
            String deadlineMessage = "Alright, I have added this Deadline:\n" + tasks.showTask(deadline);
            //save tasks to file
            storage.saveTasks(tasks.getTasks());
            super.setResponse(deadlineMessage);
            break;
        case "event":
            Event event = createEvent();
            //add event to task list
            tasks.addTask(event);
            String eventMessage = "Alright, I have added this Event:\n" + tasks.showTask(event);
            //save tasks to file
            storage.saveTasks(tasks.getTasks());
            super.setResponse(eventMessage);
            break;
        default:
            throw new MatchaException("Invalid command to add tasks!");
        }
        return super.getResponse();
    }

    /**
     * Creates a Deadline task from the user input.
     *
     * @return Deadline task created from user input.
     * @throws MatchaException If the deadline details are not provided or are invalid.
     */
    private Deadline createDeadline() throws MatchaException {
        if (!inputWords[1].contains(" /by ")) {
            throw new MatchaException("Invalid format to add Deadline.\nPlease use '/by' to specify the "
                    + "time of the Deadline.");
        }

        //extract deadline description and 'by'
        String[] deadlineInfo = inputWords[1].split(" /by ", 2);
        String deadlineDesc = deadlineInfo[0].strip();
        String by = deadlineInfo[1].strip();

        //check if 'by' is in the correct format
        LocalDateTime formattedBy;
        try {
            formattedBy = LocalDateTime.parse(by, Task.getInputFormat());
        } catch (DateTimeParseException e) {
            throw new MatchaException("Invalid date/time format! Please use 'yyyy-MM-dd HH:mm' format.");
        }

        return new Deadline(deadlineDesc, formattedBy);
    }

    /**
     * Creates an Event task from the user input.
     *
     * @return Event task created from user input.
     * @throws MatchaException If the event details are not provided or are invalid.
     */
    private Event createEvent() throws MatchaException {
        if (!inputWords[1].contains(" /from ") || !inputWords[1].contains(" /to ")) {
            throw new MatchaException("Invalid format to add Event.\nPlease use '/from' and '/to' to specify the "
                    + "Event duration.");
        }

        //extract event description, 'from' and 'to'
        String eventDesc = inputWords[1].split(" /from")[0];
        String from = inputWords[1].split(" /from ")[1].split(" /to ")[0];
        String to = inputWords[1].split(" /to ")[1];

        //check if 'from' and 'to' are in the correct format
        LocalDateTime formattedFrom;
        LocalDateTime formattedTo;
        try {
            formattedFrom = LocalDateTime.parse(from, Task.getInputFormat());
            formattedTo = LocalDateTime.parse(to, Task.getInputFormat());
        } catch (DateTimeParseException e) {
            throw new MatchaException("Invalid date/time format! Please use 'yyyy-MM-dd HH:mm' format.");
        }

        return new Event(eventDesc, formattedFrom, formattedTo);
    }
}
