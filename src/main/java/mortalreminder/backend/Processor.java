package mortalreminder.backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import mortalreminder.commands.Command;
import mortalreminder.commands.CommandTypes;
import mortalreminder.io.FormattedPrinting;
import mortalreminder.tasks.Deadline;
import mortalreminder.tasks.Events;
import mortalreminder.tasks.Task;
import mortalreminder.tasks.TimedTask;
import mortalreminder.tasks.ToDo;

// javadocs was generated using ChatGPT with minor edits.
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
     * If the command is of type {@code BYE}, it will stop further processing.
     *
     * @param command         the {@link Command} to process.
     * @param taskList        the {@link TaskList} to modify based on the command.
     * @return {@code false} if the {@code BYE} command is issued, otherwise {@code true}.
     */
    public String handleCommand(Command command, TaskList taskList) {
        CommandTypes commandType = command.commandType();

        String commandDetails = Arrays.stream(command.commandDetails())
                .reduce((accumulator, element) -> accumulator + " " + element)
                .orElse("");

        switch (commandType) {
        case LIST:
            return FormattedPrinting.printList(taskList);

        case FIND:
            TaskList foundTasks = taskList.findTasks(commandDetails);
            return FormattedPrinting.printSimilarTasks(foundTasks);

        case MARK:
        case UNMARK:
        case DELETE:
            return markUnmarkOrDelete(commandDetails, taskList, commandType);

        case TODO:
        case DEADLINE:
        case EVENT:
            return createTask(commandDetails, taskList, commandType);

        case CLEAR_TASKS:
            Storage.clearListFile();
            return taskList.clearList();

        case UPCOMING_TASKS:
            return upcomingTasks(taskList);

        default:
            return FormattedPrinting.unknownCommand();
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
     * @param commandType    the {@link CommandTypes} indicating the action to perform.
     */
    public String markUnmarkOrDelete(String commandDetails, TaskList taskList, CommandTypes commandType) {
        String response = "";
        try {
            int index = Integer.parseInt(commandDetails) - 1;
            Task newTask = taskList.getTask(index);
            if (newTask != null) {
                if (commandType == CommandTypes.MARK) {
                    response = newTask.markDone();
                    Storage.refreshStorageFile(taskList);
                } else if (commandType == CommandTypes.UNMARK) {
                    response = newTask.markUndone();
                    Storage.refreshStorageFile(taskList);
                } else {
                    response = taskList.deleteTask(newTask);
                }
            }
        } catch (NumberFormatException e) {
            response = FormattedPrinting.unknownNumber();
        } catch (Exception e) {
            if (taskList.isEmpty()) {
                return FormattedPrinting.emptyList();
            }
            return FormattedPrinting.outOfListBounds(taskList);
        }
        return response;
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
     * @param commandType    the {@link CommandTypes} indicating the type of task to create.
     */
    public String createTask(String commandDetails, TaskList taskList, CommandTypes commandType) {
        try {
            Task newTask;
            if (commandType == CommandTypes.TODO) {
                newTask = new ToDo(commandDetails);
            } else if (commandType == CommandTypes.DEADLINE) {
                newTask = new Deadline(commandDetails);
            } else {
                newTask = new Events(commandDetails);
            }
            return taskList.addTask(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            return FormattedPrinting.invalidNumberOfDetails();
        } catch (DateTimeParseException e) {
            return FormattedPrinting.invalidDate();
        }
    }

    /**
     * Lists all upcoming tasks that have a due date in the future.
     * <p>
     * This method filters the tasks in the task list to find those that are either deadlines or events,
     * have a due date in the future, and are not yet marked as done. The upcoming tasks are then printed.
     *
     * @param taskList the {@link TaskList} containing the tasks to check for upcoming due dates.
     */
    public String upcomingTasks(TaskList taskList) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (Objects.equals(task.getType(), "D")
                    || Objects.equals(task.getType(), "E")) {
                TimedTask timedTask = (TimedTask) task;
                if (LocalDateTime.now().isBefore(timedTask.getDueDate()) && !task.getIsDone()) {
                    tasks.add(task);
                }
            }
        }
        return FormattedPrinting.upcomingDeadlinesEvents(tasks);
    }

}
