package justbot.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import justbot.exception.JustbotException;
import justbot.task.Task;
import justbot.task.TaskList;

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
    public String getJustBotExceptionMessage(JustbotException e) {
        return e.getMessage();
    }

    /**
     * Displays a message when the user provides an invalid command.
     * Lists the valid commands for the user to input.
     */
    public String invalidCommandMessage() {
        String result = "Hey man you provided me with an invalid command. Here is a list of my commands:\n"
                + "\n"
                + "1. list\n"
                + "2. mark [task number]\n"
                + "3. unmark [task number]\n"
                + "4. delete [task number]\n"
                + "5. todo [task description]\n"
                + "6. deadline [task description] /by [dd/MM/yyyy HH:mm]\n"
                + "7. event [task description] /from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]\n"
                + "8. find [task description]\n"
                + "What can I do for you?\n";
        return result;
    }

    /**
     * Displays an introduction message when the bot starts.
     * Lists the valid commands for the user to input.
     */
    public String botIntro() {
        String result = "Nice to meet you man! Here is a list of my commands:\n"
                + "\n"
                + "1. list\n"
                + "2. mark [task number]\n"
                + "3. unmark [task number]\n"
                + "4. delete [task number]\n"
                + "5. todo [task description]\n"
                + "6. deadline [task description] /by [dd/MM/yyyy HH:mm]\n"
                + "7. event [task description] /from [dd/MM/yyyy HH:mm] /to [dd/MM/yyyy HH:mm]\n"
                + "8. find [task description]\n"
                + "What can I do for you?\n";
        return result;
    }

    /**
     * Displays the list of tasks currently in the task list.
     *
     * @param taskList The TaskList containing the tasks to be displayed.
     */
    public String listMessage(TaskList taskList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            int taskListCount = i + 1;
            Task currTask = taskList.get(i);
            result.append(taskListCount).append(". ").append(currTask.toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param taskList The TaskList containing the tasks.
     * @param keywords The string to check for in task description.
     */
    public String findMessage(TaskList taskList, String... keywords) {
        StringBuilder result = new StringBuilder();

        result.append("Hey man, here are the matching tasks in your list:\n\n");

        List<String> keywordList = new ArrayList<>();

        int numberOfKeywords = keywords.length;;

        for (String keyword : keywords) {
            keywordList.add(keyword);
        }

        System.out.println(keywordList);

        int count = 1;

        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);

            for (int j = 0; j < keywordList.size(); j++) {
                String keyword = keywordList.get(j);
                System.out.println(keyword);

                if (currTask.getTaskDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    result.append(count).append(". ").append(currTask).append("\n");
                    count++;
                    break;
                }
            }

            if (keywordList.isEmpty()) {
                break;
            }

        }

        if (count == 1) {
            result.append("No matching tasks found.\n");
        }

        return result.toString();
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param taskList The TaskList containing the tasks.
     * @param taskNumber The 1-based index of the task that was marked.
     */
    public String markMessage(TaskList taskList, int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = taskList.get(taskIndex);
        String result = "Nice! I have marked this task as done:\n" + currTask.toString();
        return result;
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param taskList The TaskList containing the tasks.
     * @param taskNumber The 1-based index of the task that was unmarked.
     */
    public String unmarkMessage(TaskList taskList, int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = taskList.get(taskIndex);
        String result = "OK, I've marked this task as not done yet:\n" + currTask.toString();
        return result;
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param taskList The TaskList containing the tasks.
     * @param task The task that was added.
     */
    public String addTaskMessage(TaskList taskList, Task task) {
        int numberOfTasks = taskList.size();
        String result = "Got it. I've added this task:\n"
                + task.toString()
                + "\n"
                + "Now you have "
                + numberOfTasks
                + " tasks in your list.";
        return result;
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param taskList The TaskList containing the tasks.
     * @param taskNumber The 1-based index of the task that was deleted.
     */
    public String deleteTaskMessage(TaskList taskList, int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task currTask = taskList.get(taskIndex);
        int numberOfTasks = taskList.size() - 1;
        String result = "Noted. I've removed this task:\n"
                + currTask.toString() + "\n"
                + "Now you have "
                + numberOfTasks
                + " tasks in your list.";
        return result;
    }

    /**
     * Displays a bye message when the bot is about to exit.
     */
    public String byeMessage() {
        return "Hey man, I'll miss you. See you soon!";
    }
}
