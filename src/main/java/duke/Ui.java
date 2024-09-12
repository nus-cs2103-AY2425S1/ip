package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import duke.tasks.Task;

/**
 * Represents the user interface, responsible reading and writing messages.
 */
public class Ui {
    private BlockingQueue<String> inputQueue;
    private BlockingQueue<String> outputQueue;
    private Formatter formatter = msg -> msg;

    /**
     * Constructor for a user interface.
     */
    public Ui(BlockingQueue<String> inputQueue, BlockingQueue<String> outputQueue) {
        assert inputQueue != null;
        assert outputQueue != null;

        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    /**
     * Connects an input and output queue to standard input and output.
     *
     * @param inputQueue The input queue to connect to standard input.
     * @param outputQueue The output queue to connect to standard output.
     */
    public static void useStdio(BlockingQueue<String> inputQueue, BlockingQueue<String> outputQueue) {
        // Connect the input queue to standard input.
        new Thread(() -> {
            Scanner in = new Scanner(System.in);
            while (true) {
                String inputMessage = in.nextLine();
                inputQueue.add(inputMessage);
            }
        }).start();

        // Connect the output queue to standard output.
        new Thread(() -> {
            while (true) {
                try {
                    String outputMessage = outputQueue.take();
                    System.out.println(outputMessage);
                } catch (InterruptedException e) {
                    System.out.println("Oh no! An IO error has occurred.");
                }
            }
        }).start();
    }

    /**
     * Sets the formatter for outputting messages.
     *
     * @param f The formatter to use.
     */
    public void useFormatter(Formatter f) {
        assert f != null;

        this.formatter = f;
    }

    /**
     * Formats and writes a message to standard output.
     *
     * @param msg The message to format.
     */
    public void write(String msg) {
        this.outputQueue.add(formatter.format(msg));
    }

    /**
     * Reads a message from standard input.
     *
     * @return The message read.
     */
    public String read() {
        try {
            return this.inputQueue.take();
        } catch (InterruptedException e) {
            return "";
        }
    }

    /**
     * Writes the greeting message.
     */
    public void greet() {
        this.write(" Hello! I'm Bob\n"
                + " What can I do for you?");
    }

    /**
     * Writes the exit message.
     */
    public void exit() {
        this.write(" Bye. Hope to see you again soon!");
    }

    /**
     * Writes the response message for a add command.
     *
     * @param task The added task.
     * @param numTasks The number of tasks after adding the task.
     */
    public void add(Task task, int numTasks) {
        this.write(String.format("""
                 Got it. I've added this task:
                    %s
                 Now you have %d tasks in the list.""",
                task.toString(), numTasks));
    }

    /**
     * Writes the response message for a delete command.
     *
     * @param task The deleted task.
     * @param numTasks The number of tasks after deleting the task.
     */
    public void delete(Task task, int numTasks) {
        this.write(String.format("""
                Noted. I've removed this task:
                    %s
                Now you have %d tasks in the list.
               """, task.toString(), numTasks));
    }

    /**
     * Writes the response message for a mark command.
     *
     * @param task The task marked.
     * @param isCompleted Whether the task was marked completed or not completed.
     */
    public void mark(Task task, boolean isCompleted) {
        if (isCompleted) {
            this.write(" Nice! I've marked this task as done:\n   " + task);
        } else {
            this.write(" OK, I've marked this task as not done yet:\n   " + task);
        }
    }

    /**
     * Writes response message for a list command.
     *
     * @param tasks The tasks to display.
     */
    public void list(ArrayList<Task> tasks) {
        StringBuilder listMsg = new StringBuilder(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            listMsg
                .append("\n ")
                .append(i + 1)
                .append(". ")
                .append(tasks.get(i));
        }
        this.write(listMsg.toString());
    }

    /**
     * Writes response message for a find command.
     *
     * @param tasks The tasks to display.
     */
    public void find(ArrayList<Task> tasks) {
        StringBuilder listMsg = new StringBuilder(" Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            listMsg
                .append("\n ")
                .append(i + 1)
                .append(". ")
                .append(tasks.get(i));
        }
        this.write(listMsg.toString());
    }

    /**
     * Writes the response message for an archive command.
     *
     * @param numArchived The number of tasks archived.
     */
    public void archive(int numArchived) {
        this.write(" Ok! I've archived " + numArchived + " tasks.");
    }

    /**
     * Writes the response message for a restore command.
     *
     * @param numRestored The number of tasks restored.
     */
    public void restore(int numRestored) {
        this.write(" Ok! I've restored " + numRestored + " tasks.");
    }
}
