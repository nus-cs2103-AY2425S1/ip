package sora;

import java.util.ArrayList;

import sora.Tasks.Task;
import sora.Tasks.TaskList;

/**
 * Ui stands for User Interface.
 * It deals with interactions with the user.
 */
public class Ui {
    protected static final String GREETING = "Hello! I'm Sora!\nWhat can I do for you?\n";
    protected static final String FAREWELL = "Bye. Hope to see you again soon!\n";
    protected static final String EMPTYCOMMAND = "Please Enter a Command\n";
    protected static final String INVALIDCOMMAND = "Sora doesn't understand! Please Try Again!\n";

    protected static String displayMessage(String message) {
        return message;
    }

    /**
     * Returns String of TaskList Content.
     * If TaskList is empty, a Unique Statement "No Tasks Found" will be returned.
     *
     * @param taskList TaskList.
     * @return TaskList Content.
     */
    protected String displayTaskList(TaskList taskList) {
        if (taskList.getTaskList().isEmpty()) {
            return "Seems like there are no tasks found!\n";
        }
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (Task t : taskList.getTaskList()) {
            sb.append(index + ". " + t.toString() + "\n");
            index++;
        }
        return sb.toString();
    }

    /**
     * Returns a String of Outcome of Marking Task.
     *
     * @param taskList TaskList.
     * @param parsedCommand Parsed Command with Index of the task to be marked as done. Starting from 1.
     * @return String of Outcome of Marking Task.
     */
    protected String displayMarkedTask(TaskList taskList, ArrayList<String> parsedCommand) {
        try {
            Task task = taskList.markTask(parsedCommand.get(1));
            return "Nice! Sora has marked this task as done:\n" + task;
        } catch (SoraException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Please Enter - Mark (int)\n";
        }
    }

    /**
     * Returns a String of Outcome of Unmarking Task.
     *
     * @param taskList TaskList.
     * @param parsedCommand Parsed Command with Index of the task to be marked as not done. Starting from 1.
     * @return String of Outcome of Unmarking Task.
     */
    protected String displayUnMarkedTask(TaskList taskList, ArrayList<String> parsedCommand) {
        try {
            Task task = taskList.unmarkTask(parsedCommand.get(1));
            return "Nice! Sora has marked this task as not done:\n" + task;
        } catch (SoraException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Please Enter - Unmark (int)\n";
        }
    }

    /**
     * Returns a String of Outcome of Adding Task.
     *
     * @param taskList TaskList.
     * @param mainCommand Type of task (Todo, Deadline, Event)
     * @param parsedCommand Parsed Command with Details of task
     * @return String of Outcome of Adding Task.
     */
    protected String displayAddedTask(TaskList taskList, String mainCommand, ArrayList<String> parsedCommand) {
        try {
            Task task = taskList.addTask(mainCommand, parsedCommand);
            return "Got it. Sora has added this task:\n"
                    + task
                    + "\nNow, you have " + taskList.getSize() + " tasks in your list.\n";
        } catch (SoraException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a String of Outcome of Deleting Task.
     *
     * @param taskList TaskList.
     * @param parsedCommand Parsed Command with Index of the task to be deleted. Starting from 1.
     * @return String of Outcome of Deleting Task.
     */
    protected String displayDeletedTask(TaskList taskList, ArrayList<String> parsedCommand) {
        try {
            Task deletedTask = taskList.deleteTask(parsedCommand.get(1));
            return "Noted. Sora has removed this task:\n" + deletedTask;
        } catch (SoraException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Please Enter - Delete (int)\n";
        }
    }

    /**
     * Returns a String of Outcome of Finding Task.
     *
     * @param taskList TaskList.
     * @param parsedCommand Parsed Command with User's Substring.
     * @return String of Outcome of Finding Task.
     */
    protected String displayFoundTask(TaskList taskList, ArrayList<String> parsedCommand) {
        try {
            return taskList.findTask(parsedCommand.get(1)).toString();
        } catch (IndexOutOfBoundsException e) {
            return "Please Enter - Find (String)\n";
        }
    }
}
