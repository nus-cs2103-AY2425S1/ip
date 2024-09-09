package blue;

import java.util.List;
import java.util.Scanner;

import blue.exceptions.EmptyDescriptionException;
import blue.exceptions.InputErrorException;
import blue.exceptions.WrongNumberOfItemException;
import blue.task.Task;
import blue.task.TaskList;

/**
 * The {@code Parser} class handles the processing of user commands in the Blue application.
 * It interprets user inputs and triggers the appropriate actions on the {@link TaskList}.
 */
public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark ";
    private static final String COMMAND_UNMARK = "unmark ";
    private static final String COMMAND_DELETE = "delete ";
    private static final String COMMAND_FIND = "find ";

    private Scanner scanner;

    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    public String parse(String input, TaskList taskList) {

        // Ensure taskList is not null
        assert taskList != null : "TaskList should not be null";


        if (input.equalsIgnoreCase(COMMAND_BYE)) {
            return UI.farewell();
        }

        if (input.equalsIgnoreCase(COMMAND_LIST)) {
            return taskList.printList();
        }

        if (input.startsWith(COMMAND_MARK)) {
            return handleMarkCommand(input, taskList);
        }

        if (input.startsWith(COMMAND_UNMARK)) {
            return handleUnmarkCommand(input, taskList);
        }

        if (input.startsWith(COMMAND_DELETE)) {
            return handleDeleteCommand(input, taskList);
        }

        if (input.startsWith(COMMAND_FIND)) {
            return handleFindCommand(input, taskList);
        }

        return handleAddTaskCommand(input, taskList);
    }

    private String handleMarkCommand(String input, TaskList taskList) {
        try {
            int taskNumber = Integer.parseInt(input.substring(COMMAND_MARK.length()));
            taskList.mark(taskNumber);
            return UI.displayAfterMark(taskList.getTask(taskNumber - 1));
        } catch (NumberFormatException | WrongNumberOfItemException e) {
            return "Invalid task number or format.";
        }
    }

    private String handleUnmarkCommand(String input, TaskList taskList) {
        try {
            int taskNumber = Integer.parseInt(input.substring(COMMAND_UNMARK.length()));
            taskList.unmark(taskNumber);
            return UI.displayAfterUnMark(taskList.getTask(taskNumber - 1));
        } catch (NumberFormatException | WrongNumberOfItemException e) {
            return "Invalid task number or format.";
        }
    }

    private String handleDeleteCommand(String input, TaskList taskList) {
        try {
            int taskNumber = Integer.parseInt(input.substring(COMMAND_DELETE.length()));
            Task deletedTask = taskList.getTask(taskNumber - 1);
            taskList.delete(taskNumber);
            return UI.displayAfterDelete(deletedTask, taskList.getNumberOfTask());
        } catch (NumberFormatException | WrongNumberOfItemException e) {
            return "Invalid task number or format.";
        }
    }

    private String handleFindCommand(String input, TaskList taskList) {
        String keyword = input.substring(COMMAND_FIND.length());
        List<Task> matchingTasks = taskList.find(keyword);

        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        return result.toString();
    }

    private String handleAddTaskCommand(String input, TaskList taskList) {
        try {
            taskList.addToList(input);
            return "Task added: " + input + ". Now you have " + taskList.getNumberOfTask() + " tasks.";
        } catch (EmptyDescriptionException | InputErrorException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
    }
}