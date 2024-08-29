package justbot.ui;

import justbot.exception.JustbotException;
import justbot.task.Task;
import justbot.task.TaskList;

import java.util.Scanner;

/**
 * Represents the user interface of Justbot.
 * Handles interactions with the user, including reading input and displaying messages.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui instance and initializes the scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command input by the user.
     *
     * @return The command input by the user, trimmed of leading and trailing whitespace.
     */
    public String readCommand() {
        System.out.print("Enter your command: ");
        return scanner.nextLine().trim();
    }

    /**
     * Displays a message when a JustbotException is encountered.
     *
     * @param e The JustbotException to display the message for.
     */
    public void getJustBotExceptionMessage(JustbotException e) {
        System.out.println("------------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("------------------------------------------");
    }

    /**
     * Displays a message when the user provides an invalid command.
     * Lists the valid commands for the user to input.
     */
    public void invalidCommandMessage() {
        System.out.println("------------------------------------------");
        System.out.println("Hey man you provided me with an invalid command. Here is a list of my commands:");
        System.out.println("1. list");
        System.out.println("2. mark [task number]");
        System.out.println("3. unmark [task number]");
        System.out.println("4. delete [task number]");
        System.out.println("5. todo [task description]");
        System.out.println("6. deadline [task description] /by [dd/MM/yyyy HH:mm]");
        System.out.println("7. event [task description] /from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
    }

    /**
     * Displays an introduction message when the bot starts.
     * Lists the valid commands for the user to input.
     */
    public void botIntro() {
        System.out.println("------------------------------------------");
        System.out.println("Hello I'm Justbot!");
        System.out.println("Here is a list of my commands:");
        System.out.println("1. list");
        System.out.println("2. mark [task number]");
        System.out.println("3. unmark [task number]");
        System.out.println("4. delete [task number]");
        System.out.println("5. todo [task description]");
        System.out.println("6. deadline [task description] /by [dd/MM/yyyy HH:mm]");
        System.out.println("7. event [task description] /from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
    }

    /**
     * Displays the list of tasks currently in the task list.
     *
     * @param taskList The TaskList containing the tasks to be displayed.
     */
    public void listMessage(TaskList taskList) {
        System.out.println("------------------------------------------");
        System.out.println("Here are the tasks in your list:\n");
        for(int i =0; i < taskList.size(); i++) {
            int taskListCount = i + 1;
            Task currTask = taskList.get(i);
            System.out.print(taskListCount + ". " + currTask.toString() + "\n");
        }
        System.out.println("------------------------------------------");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param taskList The TaskList containing the tasks.
     * @param taskNumber The 1-based index of the task that was marked.
     */
    public void markMessage(TaskList taskList, int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = taskList.get(taskIndex);
        System.out.println("------------------------------------------");
        System.out.println("Nice! I have marked this task as done:\n" + currTask.toString());
        System.out.println("------------------------------------------");
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param taskList The TaskList containing the tasks.
     * @param taskNumber The 1-based index of the task that was unmarked.
     */
    public void unmarkMessage(TaskList taskList, int taskNumber){
        int taskIndex = taskNumber - 1;
        Task currTask = taskList.get(taskIndex);
        System.out.println("------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:\n" + currTask.toString());
        System.out.println("------------------------------------------");
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param taskList The TaskList containing the tasks.
     * @param task The task that was added.
     */
    public void addTaskMessage(TaskList taskList, Task task) {
        System.out.println("------------------------------------------");
        int numberOfTasks = taskList.size();
        System.out.println( "Got it. I've added this task:\n" + task.toString() + "\n" + "Now you have " + numberOfTasks + " tasks in your list.");
        System.out.println("------------------------------------------");
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param taskList The TaskList containing the tasks.
     * @param taskNumber The 1-based index of the task that was deleted.
     */
    public void deleteTaskMessage(TaskList taskList, int taskNumber) {
        System.out.println("------------------------------------------");
        int taskIndex = taskNumber -1;
        Task currTask = taskList.get(taskIndex);
        int numberOfTasks = taskList.size() - 1;
        System.out.println( "Noted. I've removed this task:\n" + currTask.toString() + "\n" + "Now you have " + numberOfTasks + " tasks in your list.");
        System.out.println("------------------------------------------");
    }

    /**
     * Displays a bye message when the bot is about to exit.
     */
    public void byeMessage() {
        System.out.println("------------------------------------------");
        System.out.println("Hey man, I'll miss you. See you soon!");
        System.out.println("------------------------------------------");
    }
}
