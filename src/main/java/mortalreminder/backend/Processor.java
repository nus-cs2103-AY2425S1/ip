package mortalreminder.backend;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import mortalreminder.commands.Command;
import mortalreminder.commands.CommandType;
import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.io.FormattedPrinting;
import mortalreminder.tasks.Deadline;
import mortalreminder.tasks.Event;
import mortalreminder.tasks.Task;
import mortalreminder.tasks.TimedTask;
import mortalreminder.tasks.ToDo;

// javadocs were generated using ChatGPT with minor edits.

/**
 * Handles the processing of commands related to task management.
 * <p>
 * The {@code Processor} class is responsible for interpreting and executing commands
 * related to tasks, such as creating tasks, marking them as done, deleting them, and
 * listing upcoming tasks.
 */
public class Processor {

    /**
     * Processes the given command and modifies the {@link TaskList} accordingly.
     * <p>
     * Depending on the command type, this method can print the task list, create a new task,
     * mark or unmark a task as done, delete a task, clear all tasks, or list upcoming tasks.
     *
     * @param command  the {@link Command} to process.
     * @param taskList the {@link TaskList} to modify based on the command.
     * @return response string which is the output message of the entire command.
     * @throws MortalReminderException based on different input or command errors found in the given command.
     */
    public String handleCommand(Command command, TaskList taskList) throws MortalReminderException {
        CommandType commandType = command.commandType();

        String commandDetails = Arrays.stream(command.commandDetails())
                .reduce((accumulator, element) -> accumulator + " " + element)
                .orElse("");

        switch (commandType) {
        case LIST:
            return FormattedPrinting.printList(taskList);

        case FIND:
            TaskList foundTasks = taskList.findTasks(commandDetails.split(","));
            return FormattedPrinting.printSimilarTasks(foundTasks);

        case MARK:
            // Fallthrough
        case UNMARK:
            // Fallthrough
        case DELETE:
            return markUnmarkOrDelete(commandDetails, taskList, commandType);

        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            return createTask(commandDetails, taskList, commandType);

        case CLEAR_TASKS:
            Storage.clearListFile();
            return taskList.clearList();

        case UPCOMING_TASKS:
            return getUpcomingTasks(taskList);

        default:
            return feedbackUnrecognisedCommand();
        }
    }

    /**
     * Marks, unmarks, or deletes a task based on the given command type.
     * <p>
     * This method attempts to parse the task index from the command details and then
     * performs the appropriate action (mark as done, unmark, or delete) on the task.
     * This method was optimised using ChatGPT.
     *
     * @param commandDetails the details of the command, typically the task index.
     * @param taskList       the {@link TaskList} containing the tasks to modify.
     * @param commandType    the {@link CommandType} indicating the action to perform.
     * @return returns a confirmation message of the corresponding type of command done.
     * @throws MortalReminderException if the index given is invalid (does not exist in the {@link TaskList}).
     */
    public String markUnmarkOrDelete(String commandDetails, TaskList taskList, CommandType commandType)
            throws MortalReminderException {
        try {
            // Retrieve the specified task.
            String response = "";
            int index = Integer.parseInt(commandDetails) - 1;
            Task newTask = taskList.getTask(index);

            // execute the given command on the task.
            if (commandType == CommandType.MARK) {
                response = newTask.markDone();
                Storage.refreshStorageFile(taskList);
            } else if (commandType == CommandType.UNMARK) {
                response = newTask.markUndone();
                Storage.refreshStorageFile(taskList);
            } else {
                assert commandType == CommandType.DELETE;
                // There should only be the delete command here, if not there is an error in the
                // handleCommand method
                response = taskList.deleteTask(newTask);
            }
            return response;
        } catch (NumberFormatException e) {
            throw new MortalReminderException("Please enter a valid number after the command!");
        }

    }

    /**
     * Creates a new task based on the command type and adds it to the task list.
     * <p>
     * This method interprets the command details to create the appropriate type of task
     * (ToDo, Deadline, or Event) and adds it to the task list. It handles potential errors
     * such as an incorrect number of details or an invalid date format. This method is also
     * optimised using ChatGPT.
     *
     * @param commandDetails the details of the command, typically the task description.
     * @param taskList       the {@link TaskList} to add the new task to.
     * @param commandType    the {@link CommandType} indicating the type of task to create.
     * @return returns a confirmation message of type of task created or the reason why the task could not be created.
     * @throws MortalReminderException if there is an error in the initialisation in the new {@link Task} to be created.
     */
    public String createTask(String commandDetails, TaskList taskList, CommandType commandType)
            throws MortalReminderException {
        Task newTask;
        if (commandType == CommandType.TODO) {
            newTask = new ToDo(commandDetails);
        } else if (commandType == CommandType.DEADLINE) {
            newTask = new Deadline(commandDetails);
        } else {
            assert commandType == CommandType.EVENT;
            // the only other commandType that should be passed in at this point should be EVENT.
            // If this assert is false, that means that either this method or handleCommand was modified incorrectly.
            newTask = new Event(commandDetails);
        }
        return taskList.addTask(newTask);
    }

    /**
     * Lists all upcoming tasks that have a due date in the future.
     * <p>
     * This method filters the tasks in the task list to find those that are either deadlines or events,
     * have a due date in the future, and are not yet marked as done. The upcoming tasks are then printed.
     *
     * @param taskList the {@link TaskList} containing the tasks to check for upcoming due dates.
     * @return a string message containing list of upcoming tasks that have not been marked yet.
     */
    public String getUpcomingTasks(TaskList taskList) throws MortalReminderException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);

            /* stops the current iteration if the task is not a TimedTask or already done. */
            if (Objects.equals(task.getType(), "T") || task.getIsDone()) {
                continue;
            }
            // An assumption is everything past this is all some type of timed task.
            assert task.getType().equals("D") || task.getType().equals("E");

            /* Add the task only if the task deadline or duration falls before the given deadline. */
            TimedTask timedTask = (TimedTask) task;
            if (LocalDateTime.now().isBefore(timedTask.getDueDate())) {
                tasks.add(task);
            }
        }
        return FormattedPrinting.printUpcomingDeadlinesEvents(tasks);
    }

    /**
     * Returns feedback message if the command passed by the user is unrecognisable.
     * A list of all recognisable commands are also provided.
     */
    public String feedbackUnrecognisedCommand() {
        StringBuilder message = new StringBuilder("""
                    I do not recognise this command, please check again!
                    Available commands are:
                    """);
        for (CommandType type : CommandType.values()) {
            if (type == CommandType.UNKNOWN) {
                continue;
            }
            message.append(type.name().toLowerCase()).append("\n");
        }
        return message.toString();
    }

}
