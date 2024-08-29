package NextGPT;

import java.util.List;
import java.util.Scanner;
import NextGPT.task.Task;

/** Interface that users interacts with. */
public class Ui {
    String line = "_______________________________________________________\n";

    Scanner sc;
    public Ui(){
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints opening message to user.
     */
    public void openingMessage() {
        String greeting = "_______________________________________________________\n" +
                "Hello! I'm NextGPT and I'll be your assistant chatbot.\n" +
                "What can I do for you today?\n" +
                "_______________________________________________________\n";
        System.out.println(greeting);
    }

    /**
     * Prints exit message to user.
     */
    public void bye() {
        this.sc.close();
        System.out.println(line + "Bye. Hope to see you soon!\n" + line);
    }

    /**
     * Alerts a user the task provided has been marked as done.
     *
     * @param task Task that was marked as done.
     */
    public void mark(Task task) {
        System.out.println(line + "Nice! I've marked this task as done:\n " + task + "\n" + line);
    }

    /**
     * Alerts a user the task provided has been unmarked.
     *
     * @param task Task that was marked as done.
     */
    public void unmark(Task task) {
        System.out.println(line + "Ok, I've marked this task as not done yet:\n " + task + "\n" + line);
    }


    /**
     * Alerts a user the task provided has been deleted and informs the user how many tasks are left.
     *
     * @param task Task that was marked as done.
     * @param listSize Size of task list remaining.
     */
    public void delete(Task task, int listSize) {
        System.out.println(line +
                "Noted. I've removed this task from the list:\n " + task +
                "\nNow you have " + listSize + " tasks in the list.\n" +
                line);
    }

    /**
     * Alerts user task has been added to the task list.
     *
     * @param task Task that was added to task list.
     * @param listSize Size of task list.
     */
    public void addTask(Task task, int listSize) {
        System.out.println(line + " added: " +
                task + "\n Now you have " + listSize + " tasks in the list\n" +
                line);
    }

    /**
     * Displays current task list to user.
     *
     * @param taskList TaskList currently saved.
     */
    public void showList(TaskList taskList) {
        System.out.println(line);

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i));
        }

        System.out.println(line);
    }

    /**
     * Prints loading error message if there is no saved list locally.
     *
     */
    public void showLoadingError() {
        System.out.println("It seems like you do not have a saved task list. I will be creating an empty one for you.");
    }

    /**
     * Prints error message.
     *
     * @param s Error message.
     */
    public void showError(String s) {
        System.out.println(line + s + "\n" + line);
    }

    /**
     * Reads next input from user.
     *
     * @return Returns String of input read from user.
     */
    public String readCommand() {

        return sc.nextLine();
    }



}
