package ui;

import exception.TakoException;
import task.TaskList;
import task.Task;
import storage.Storage;
import parser.Parser;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Prompts users for input and also to output responses to them.
 */
public class Ui {

    /**
     * Greets the user when the chatbot just starts and ask them for an input.
     */
    public static String greet() {
        return "Ehh wassup, hows life?\n";
    }

    /**
     * Says goodbye to the user.
     */
    public static String exit() {
        return "I zao first, see you soon!";
    }

    /**
     * Prints out the list of tasks that the chatbot is keeping track of and
     * ask them for input after that.
     */
    public static String printList() {
        assert TaskList.length() >= 0;
        String output = "So many things, sien la:\n";
        for (int i = 0; i < TaskList.length(); i++) {
            output += TaskList.listTask(i) + "\n";
        }
        return output;
    }

    /**
     * Prints out the message when the user added a task to the list and
     * ask them for input after that.
     *
     * @param task task that is being added to the list.
     */
    public static String addTaskMessage(Task task) {
        String output = "Got it. Thanks ah!\n" +
                          task.toString() + "\n" +
                          "You now got " + TaskList.length() + " things to do!";
        Storage.store(TaskList.getAllTask());
        return output;
    }

    /**
     * Prints out the message when the user mark a task on the list and
     * ask them for input after that.
     *
     * @param task task that is being marked on the list.
     */
    public static String markTaskMessage(Task task) {
        String output = "Mark alr! \n" +
                           task.toString();
        Storage.store(TaskList.getAllTask());
        return output;
    }

    /**
     * Prints out the message when the user unmark a task on the list and
     * ask them for input after that.
     *
     * @param task task that is being unmark on the list.
     */
    public static String unmarkTaskMessage(Task task) {
        String output = "Unmark alr! \n" +
                           task.toString();
        Storage.store(TaskList.getAllTask());
        return output;
    }

    /**
     * Prints out the message when the user delete a task on the list and
     * ask them for input after that.
     *
     * @param task task that is being deleted on the list.
     */
    public static String deleteTaskMessage(Task task) {
        String output = "Delete liao!\n" +
                           task.toString() + "\n" +
                           "Now, you have " + TaskList.length() + " things left!";
        Storage.store(TaskList.getAllTask());
        return output;
    }

    /**
     * Prints out the tasks that matches the keywords that user inputs.
     *
     *
     * @param targetString string that user is searching for.
     */
    public static String findTargetString(String targetString) {
        ArrayList<Task> filteredTask = new ArrayList<>();
        for (int i = 0; i < TaskList.length(); i++) {
            if (TaskList.get(i).getDescription().contains(targetString)) {
                filteredTask.add(TaskList.get(i));
            }
        }
        String output = "I can only find all these ah:\n";
        for (int i = 0; i < filteredTask.size(); i++) {
            output += filteredTask.get(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Prints out the message when the user mark/unmark/delete a task number
     * that is longer than the length of the task list.
     *
     */
    public static String noSuchTaskMessage() {
        return "Where got such thing?";
    }

    public static String changePriorityMessage(String priority, int taskNumber) {
        Storage.store(TaskList.getAllTask());
        return "Ok, change " + (taskNumber + 1) + " to " + priority + " priority!";
    }
}
