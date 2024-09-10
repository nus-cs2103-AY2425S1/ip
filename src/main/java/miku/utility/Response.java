package miku.utility;

import java.util.Scanner;

import miku.task.Task;

/**
 * The UI class that in charge of printing out the UI.
 */
public class Response {
    private String response = "";
    private Scanner scanner;
    private String logo = " __  __   __   _   _   _    _\n"
            + "|  \\/  | |  | | | / / | |  | |\n"
            + "| |\\/| | |  | | |/ /  | |  | |\n"
            + "| |  | | |  | | |\\ \\  | |__| |\n"
            + "|_|  |_| |__| |_| \\_\\  \\____/";

    public Response() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the greeting message.
     */
    public static String greet() {
        return "Hello! I'm Miku\n"
                + "What song do you want to listen to today?";
    }

    /**
     * Prints the farewell message.
     */
    public String farewell() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * String output of deleting an item, called before deleting.
     *
     * @param num the index of the task
     */
    public String deleteItemResponse(int num, TaskList tasks) {
        return "はい、わかりました\nI have removed the following task: " + tasks.get(num - 1).stringValue()
                + "\nいまは " + (tasks.size() - 1) + " tasks in the list";
    }

    /**
     * String output of add an item.
     *
     * @return Response of adding an item
     */
    public String addItemResponse(Task task, TaskList tasks) {
        return "Got it . I've added this task:\n" + task.stringValue()
                + "\nいまは " + tasks.size() + " tasks in the list";
    }

    /**
     * Get the current response
     *
     * @return the response string
     */
    public String getResponse() {
        return response;
    }

    /**
     * Set the response to a string.
     *
     * @param string New response value
     */
    public void setResponse(String string) {
        response = string;
    }

    /**
     * Prints all tasks stored in the list.
     */
    public String printListResponse(TaskList tasks) {
        String string = "";
        System.out.println(tasks.size());
        System.out.println(string);
        for (int i = 0; i < tasks.size(); i++) {
            string += (String.valueOf(i + 1) + ". " + tasks.get(i).stringValue() + "\n");
        }
        return string;
    }

    /**
     * Searches and prints all the matched tasks.
     *
     * @param string the matching key phrase.
     */
    public String searchList(String string, TaskList tasks) {
        int counter = 1;
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDesc().contains(string)) {
                result += (String.valueOf(counter) + ". " + tasks.get(i).stringValue() + "\n");
                counter += 1;
            }
        }
        return result;
    }
    public String setPrioritySuccessful(int index, Priority priority) {
        return "Successfully set item " + (index + 1) + "'s priority to " + priority;
    }
}
