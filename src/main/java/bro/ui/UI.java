package bro.ui;

import bro.BroException;
import bro.task.Task;
import bro.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

// This class encapsulates user interactions with bro.Bro
public class UI {
    final static String GREETING_MESSAGE = """
                 Hello! I'm bro.Bro
                 What can I do for you?""";
    final static String GOODBYE_MESSAGE = "Goodbye.";

    private final Scanner scanner;

    public UI(){
        this.scanner = new Scanner(System.in);
    }

    public String showWelcome() {
        return reply(GREETING_MESSAGE);
    }

    public String showGoodbye() {
        return reply(GOODBYE_MESSAGE);
    }

    public String readCommand() throws BroException {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            throw new BroException("Input cannot be empty.");
        }
    }

    public String showCreateTaskSuccess(Task task, int numberOfTasks) {
        return addTaskReply(task, numberOfTasks);
    }

    public String showMarkTaskSuccess(Task task) {
        return reply("Nice bro! I've marked this task as done:\n" + task);
    }

    public String showUnmarkTaskSuccess(Task task) {
        return reply("Ok bro! I've marked this task as undone:\n" + task);
    }

    public String showDeleteTaskSuccess(Task task) {
        return reply("Noted. Removed this task:\n" + task);
    }

    public String showTaskFind(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            return reply("No tasks found.");
        }
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (Task task : tasks) {
            message.append(task.toString()).append("\n");
        }
        return reply(message.toString());
    }

    public static void showLine() {
        String line = """
                ____________________________________________________________
                """;
        System.out.print(line);
    }

    public String showError(String errorMessage) {
        return reply(errorMessage);
    }

    // Prints an add task reply
    public static String addTaskReply(Task task, int numberOfTasks) {
        String replyStr = String.format("""
                ____________________________________________________________
                Got it. I've added this task:
                %s
                You now have %d tasks in the list.
                bro.Bro
                ____________________________________________________________
                """, task.toString(), numberOfTasks);
        System.out.print(replyStr);
        return replyStr;
    }

    public String printTasks(TaskList taskList) {
        return taskList.printAllTasks();
    }

    // Prints an adds to list on standard output
    private String reply(String content) {
        String replyStr = String.format("""
                %s
                bro.
                """, content);
        System.out.print(replyStr);
        showLine();
        return replyStr;
    }
}
