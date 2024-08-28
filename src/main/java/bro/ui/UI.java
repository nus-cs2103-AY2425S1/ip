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

    public void showWelcome() {
        reply(GREETING_MESSAGE);
    }

    public void showGoodbye() {
        reply(GOODBYE_MESSAGE);
    }

    public String readCommand() throws BroException {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            throw new BroException("Input cannot be empty.");
        }
    }

    public void showCreateTaskSuccess(Task task, int numberOfTasks) {
        addTaskReply(task, numberOfTasks);
    }

    public void showMarkTaskSuccess(Task task) {
        reply("Nice bro! I've marked this task as done:\n" + task);
    }

    public void showUnmarkTaskSuccess(Task task) {
        reply("Ok bro! I've marked this task as undone:\n" + task);
    }

    public void showDeleteTaskSuccess(Task task) {
        reply("Noted. Removed this task:\n" + task);
    }

    public void showTaskFind(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            reply("No tasks found.");
            return;
        }
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (Task task : tasks) {
            message.append(task.toString()).append("\n");
        }
        reply(message.toString());
    }

    public static void showLine() {
        String line = """
                ____________________________________________________________
                """;
        System.out.print(line);
    }

    public void showError(String errorMessage) {
        reply(errorMessage);
    }

    // Prints an add task reply
    public static void addTaskReply(Task task, int numberOfTasks) {
        String replyStr = String.format("""
                ____________________________________________________________
                Got it. I've added this task:
                %s
                You now have %d tasks in the list.
                bro.Bro
                ____________________________________________________________
                """, task.toString(), numberOfTasks);
        System.out.print(replyStr);
    }

    public void printTasks(TaskList taskList) {
        taskList.printAllTasks();
    }

    // Prints an adds to list on standard output
    private static void reply(String content) {
        String replyStr = String.format("""
                %s
                bro.
                """, content);
        System.out.print(replyStr);
        showLine();
    }
}
