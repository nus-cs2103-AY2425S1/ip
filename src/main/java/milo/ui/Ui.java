package milo.ui;

import java.util.Scanner;

import milo.tasks.Task;
import milo.tasks.TaskList;
import milo.tasks.TaskTypes;

/**
 * Milo Ui system
 * sets how Milo interacts with user
 * as well as passing on user input to Parser
 */
public class Ui {
    private final Scanner myScanner = new Scanner(System.in);
    private final String cat0 = """
                  ╱|、
                (˚ˎ 。7 \s
                 |、˜〵         \s
                 じしˍ,)ノ
                """;
    private final String cat1 = """
                 ∧,,,∧
                ( ̳• · •̳)
                /    づ♡
                """;

    /**
     * Milo greets user
     */
    public String greetUser() {
        return "Hello! I'm Milo.\nWhat can I do for you?\n" + cat0;
    }

    /**
     * Milo gets user input
     */
    public String getUserInput() {
        return myScanner.nextLine();
    }

    /**
     * Milo says bye to user
     */
    public String byeUser() {
        return "Bye. Hope to see you again soon!\n" + cat1;
    }

    /**
     * Milo prints all tasks in the array list
     *
     * @param todoList list of tasks
     */
    public String printList(TaskList todoList) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the tasks in your list:\n");
        for (int i = 0; i < todoList.getNumberOfTasks(); i++) {
            response.append(i + 1).append(".").append(todoList.get(i).toString()).append("\n");
        }
        return response.toString();
    }

    /**
     * Milo prints feedback message on marking task as done
     *
     * @param curTask object that system marked as done
     */
    public String printMark(Task curTask) {
        return "Nice! I've marked this task as done:\n" +
                "  " + curTask.toString() + "\n";
    }

    /**
     * Milo prints feedback message on marking task as not done
     *
     * @param curTask object that system marked as not done
     */
    public String printUnmark(Task curTask) {
        return "Ok, I've marked this as not done yet:\n" +
                "  " + curTask.toString() + "\n";
    }

    /**
     * Milo prints feedback message on deleting task
     *
     * @param curTask object that system deleted
     * @param numberOfTasks left in the todo list
     */
    public String printDelete(Task curTask, int numberOfTasks) {
        StringBuilder response = new StringBuilder();
        response.append("Noted. I've removed this task:\n");
        response.append("  ").append(curTask.toString()).append("\n");
        if (numberOfTasks == 1) {
            response.append("Now you have ").append(numberOfTasks).append(" task in the list.\n");
        } else {
            response.append("Now you have ").append(numberOfTasks).append(" tasks in the list.\n");
        }
        return response.toString();
    }

    /**
     * Milo prints task details when added
     *
     * @param curTask object added to the list
     * @param numberOfTasks left in todo list
     */
    public String printTask(Task curTask, int numberOfTasks) {
        StringBuilder response = new StringBuilder();
        response.append("Got it. I've added this task:\n");
        response.append("  ").append(curTask.toString()).append("\n");
        if (numberOfTasks == 1) {
            response.append("Now you have ").append(numberOfTasks).append(" task in the list.\n");
        } else {
            response.append("Now you have ").append(numberOfTasks).append(" tasks in the list.\n");
        }
        return response.toString();
    }

    /**
     * Milo prints matching tasks to the keyword input
     *
     * @param todoList list of matching tasks
     * @param tasksFound number of matching tasks found
     */
    public String printFoundTask(TaskList todoList, int tasksFound) {
        StringBuilder response = new StringBuilder();
        response.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasksFound; i++) {
            response.append(i + 1).append(".").append(todoList.get(i).toString()).append("\n");
        }
        return response.toString();
    }

    /**
     * Milo prints error message
     *
     * @param taskType type of the task that caused the error
     * @param desc description of the error
     */
    public String printError(TaskTypes.TaskType taskType, String desc) {
        String oops = "OOPS!!! ";
        StringBuilder response = new StringBuilder();
        switch (taskType) {
            case TODO, EVENT, DEADLINE:
                response.append(oops).append(desc).append("\n");
                break;
            case INVALID:
                String invalidMessage = oops + "I'm sorry, but I don't know what that means ;-;\n";
                response.append(invalidMessage);
                break;
            case DATE:
                String invalidDateMessage = oops + "Date is not properly formatted.\nIt should be formatted as: YYYY-MM-DD\n";
                response.append(invalidDateMessage);
                break;
            default:
                String defaultMessage = oops + "I'm sorry, I don't know what that means ;-;\n";
                response.append(defaultMessage);
        }
        return response.toString();
    }
}
