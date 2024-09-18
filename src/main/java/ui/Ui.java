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
     *
     * @return String greeting message
     */
    public static String greet() {
        return "Ehh wassup, hows life?\n";
    }

    /**
     * Says goodbye to the user.
     *
     * @return String goodbye message
     */
    public static String exit() {
        return "I zao first, see you soon!";
    }

    /**
     * Prints out the list of tasks that the chatbot is keeping track of and
     * ask them for input after that.
     *
     * @return String list of task
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
     * @param task task that is being added to the list
     * @return String successful adding of task
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
     * @param task task that is being marked on the list
     * @return String successful marking of task
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
     * @param task task that is being unmark on the list
     * @return String successful unmarking of task
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
     * @param task task that is being deleted on the list
     * @return String successful deleting of task
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
     * @param targetString string that user is searching for
     * @return String list of task that matches keyword
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
     * Prints out the message when the user mark/unmark/delete/priority a task number
     * that is longer than the length of the task list.
     *
     * @return String message that show the task specified by user does not exist
     *
     */
    public static String noSuchTaskMessage() {
        return "Where got such thing?";
    }

    /**
     * Prints out the message when the user changes a task priority.
     *
     * @param priority new priority of task
     * @param taskNumber index of task that is changed
     * @return String message that show the priority has succesfully change
     */
    public static String changePriorityMessage(String priority, int taskNumber) {
        Storage.store(TaskList.getAllTask());
        return "Ok, change " + (taskNumber + 1) + " to " + priority + " priority!";
    }
}
