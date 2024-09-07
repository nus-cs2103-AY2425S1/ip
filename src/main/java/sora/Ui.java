package sora;

import java.util.ArrayList;

import sora.Tasks.Task;
import sora.Tasks.TaskList;

/**
 * Ui stands for User Interface.
 * It deals with interactions with the user.
 */
public class Ui {
    protected static final String greeting = "Hello! I'm Sora!\nWhat can I do for you?\n";
    protected static final String farewell = "Bye. Hope to see you again soon!\n";
    protected static final String emptyCommand = "Please Enter a Command\n";
    protected static final String invalidCommand = "Sora doesn't understand! Please Try Again!\n";

    protected static String displayMessage(String message) {
        return message;
    }

    /**
     * Returns TaskList Content.
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

    protected String displayMarkedTask(TaskList taskList, String index) {
        try {
            Task task = taskList.markTask(index);
            return "Nice! Sora has marked this task as done:\n" + task;
        } catch (SoraException e) {
            return e.getMessage();
        }
    }

    protected String displayUnMarkedTask(TaskList taskList, String index) {
        try {
            Task task = taskList.unmarkTask(index);
            return "Nice! Sora has marked this task as not done:\n" + task;
        } catch (SoraException e) {
            return e.getMessage();
        }
    }

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

    protected String displayDeletedTask(TaskList taskList, String index) {
        try {
            Task deletedTask = taskList.deleteTask(index);
            return "Noted. Sora has removed this task:\n" + deletedTask;
        } catch (SoraException e) {
            return e.getMessage();
        }
    }

    protected String displayFoundTask(TaskList taskList, String s) {
        return taskList.findTask(s).toString();
    }
}
