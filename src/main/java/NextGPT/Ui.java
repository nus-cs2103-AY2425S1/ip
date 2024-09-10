package nextgpt;

import java.util.List;
import java.util.Scanner;
import nextgpt.task.Task;

/** Interface that users interacts with. */
public class Ui {

    Scanner sc;
    public Ui(){
        this.sc = new Scanner(System.in);
    }


    /**
     * Prints exit message to user.
     */
    public String bye() {
        this.sc.close();
        return  "Bye. Hope to see you soon!\n";
    }

    /**
     * Alerts a user the task provided has been marked as done.
     *
     * @param task Task that was marked as done.
     */
    public String mark(Task task) {
        return "Nice! I've marked this task as done:\n " + task + "\n";
    }

    /**
     * Alerts a user the task provided has been unmarked.
     *
     * @param task Task that was marked as done.
     */
    public String unmark(Task task) {
        return "Ok, I've marked this task as not done yet:\n " + task + "\n";
    }


    /**
     * Alerts a user the task provided has been deleted and informs the user how many tasks are left.
     *
     * @param task Task that was marked as done.
     * @param listSize Size of task list remaining.
     */
    public String delete(Task task, int listSize) {
        return "Noted. I've removed this task from the list:\n " + task +
                "\nNow you have " + listSize + " tasks in the list.\n";
    }

    /**
     * Alerts user task has been added to the task list.
     *
     * @param task Task that was added to task list.
     * @param listSize Size of task list.
     */
    public String addTask(Task task, int listSize) {
        return " added: " +
                task + "\n Now you have " + listSize + " tasks in the list\n";
    }

    /**
     * Displays current task list to user.
     *
     * @param taskList TaskList currently saved.
     */
    public String showList(TaskList taskList) {
        String list = "";

        for (int i = 0; i < taskList.size(); i++) {
            list += i + 1 + "." + taskList.get(i) + "\n";
        }

        return list;
    }

    /**
     * Prints loading error message if there is no saved list locally.
     *
     */
    public String showLoadingError() {
        return "It seems like you do not have a saved task list. I will be creating an empty one for you.";
    }


}
