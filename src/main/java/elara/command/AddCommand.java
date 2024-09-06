package elara.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import elara.storage.Storage;
import elara.task.DeadlineException;
import elara.task.DeadlineTask;
import elara.task.EventException;
import elara.task.EventTask;
import elara.task.InvalidInputException;
import elara.task.Task;
import elara.task.TaskList;
import elara.task.ToDoException;
import elara.task.ToDoTask;
import elara.ui.Ui;

/**
 * Represents a command that adds a task to the task list that can be executed in the Elara chatbot.
 */
public class AddCommand implements Command {
    private final String commandType;
    private final String details;

    /**
     * Constructs an instance of the AddCommand class
     *
     * @param commandType String representing the type of task to be added to the task list.
     * @param fullInput The full string inputted by the user.
     */
    public AddCommand(String commandType, String fullInput) {
        this.commandType = commandType;
        this.details = (fullInput.split(" ", 2).length > 1) ?  fullInput.split(" ", 2)[1] : null;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidInputException {
        Task newTask = null;

        switch (commandType) {
            case "todo":
                if (details == null || details.isEmpty()) {
                    throw new ToDoException();
                }
                newTask = new ToDoTask(details);
                break;
            case "deadline":
                if (details == null || details.isEmpty()) {
                    throw new DeadlineException();
                }

                String[] deadlineArgs = details.split(" /by ");

                if (deadlineArgs.length == 2) {
                    String description = deadlineArgs[0];
                    String deadlineString = deadlineArgs[1];
                    try {
                        LocalDateTime deadline = LocalDateTime.parse(deadlineString,
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                        newTask = new DeadlineTask(description, deadline, false);
                    } catch (DateTimeParseException e) {
                        throw new DeadlineException();
                    }
                } else {
                    throw new DeadlineException();
                }
                break;
            case "event":
                if (details == null || details.isEmpty()) {
                    throw new EventException();
                }

                String[] eventArgs = details.split(" /from ");
                if (eventArgs.length == 2) {
                    String[] timeArgs = eventArgs[1].split(" /to ");
                    if (timeArgs.length == 2) {
                        String description = eventArgs[0].trim();
                        String startString = timeArgs[0].trim();
                        String endString = timeArgs[1].trim();

                        try {
                            LocalDateTime start = LocalDateTime.parse(startString,
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                            LocalDateTime end = LocalDateTime.parse(endString,
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                            newTask = new EventTask(description, start, end, false);
                        } catch (DateTimeParseException e) {
                            throw new InvalidInputException("Invalid date format for event. Use: yyyy-MM-dd HHmm.");
                        }
                    } else {
                        throw new EventException();
                    }
                } else {
                    throw new EventException();
                }
                break;
            default:
                throw new InvalidInputException("Unknown command type: " + commandType);
        }

        taskList.addTask(newTask);
        ui.showAddTaskMessage(newTask, taskList);
        storage.write(taskList);
    }
}