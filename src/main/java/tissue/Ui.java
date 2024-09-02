package tissue;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import tissue.parse.Parser;
import tissue.task.Deadline;
import tissue.task.Event;
import tissue.task.Task;
import tissue.task.ToDo;


/**
 * The Ui wrapper class for the console.
 */
public class Ui {
    private static final String LINE =
            "--------------------------------------------------------------";
    private static final String INDENT = "       ";
    private final Parser parser;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * The Ui constructor.
     */
    public Ui(Parser parser, TaskList taskList, Storage storage) {
        this.parser = parser;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * The main function to call to run the ui.
     */
    public void run() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Tissue");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        String in = parser.retrieveNextString();

        while (!in.equals("bye")) {
            System.out.println(LINE);

            if (in.equals("list")) {
                System.out.println(INDENT + "Here are the tasks in your list:");
                System.out.println(taskList);

            } else if (in.equals("mark")) {
                Task task = taskList.retrieveTask(parser.retrieveNextInt() - 1).markTask();
                System.out.println(INDENT + "Nice! I've marked this task as done:");
                System.out.println(INDENT + "  " + task);
            } else if (in.equals("unmark")) {

                Task task = taskList.retrieveTask(parser.retrieveNextInt() - 1).unmarkTask();
                System.out.println(INDENT + "OK, I've marked this task as not done yet:");
                System.out.println(INDENT + "  " + task);

            } else if (in.equals("delete")) {
                int line = parser.retrieveNextInt();
                Task task = taskList.deleteTask(line);
                storage.delete(line);
                System.out.println(INDENT + "Noted. I've removed this task:");
                System.out.println(INDENT + "  " + task);
                System.out.println(
                        INDENT + "Now you have " + taskList.size() + " tasks in the list.");
            } else if (in.equals("find")) {
                ArrayList<Task> matches = taskList.searchKeyword(parser.retrieveNextString());
                System.out.println(INDENT + "Here are the matching tasks!");
                for (Task task : matches) {
                    System.out.println(INDENT + task);
                }

            } else {
                storeTask(in);
            }

            System.out.println(LINE);
            in = parser.retrieveNextString();
        }
        parser.close();
        System.out.println(LINE);
        System.out.print(INDENT);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Reads an input string which stored into its respective task.
     *
     * @param in The input string
     */
    private void storeTask(String in) {
        if (in.equals("todo")) {

            String item = parser.retrieveNextString();
            if (item.isEmpty()) {
                System.out.println("Description of TODO cannot be empty.");
            } else {
                Task task = new ToDo(false, item);
                storage.save(task);
                taskList.add(task);
                System.out.println(INDENT + "Got it. I've added this task:");
                System.out.println(INDENT + "  " + task);
                System.out.println(
                        INDENT + "Now you have " + taskList.size() + " tasks in the list.");
            }

        } else if (in.equals("deadline")) {
            String item = parser.scanUntil("/by");
            String by = parser.retrieveNextString().strip();
            try {
                Task task = new Deadline(false, item, by);
                storage.save(task);
                taskList.add(task);
                System.out.println(INDENT + "Got it. I've added this task:");
                System.out.println(INDENT + "  " + task);
                System.out.println(
                        INDENT + "Now you have " + taskList.size() + " tasks in the list.");
            } catch (DateTimeParseException d) {
                System.out.println("OOPS! Please enter a date in the format YYYY-MM-DD");
            }

        } else if (in.equals("event")) {
            String item = parser.scanUntil("/from");
            String from = parser.scanUntil("/to");
            String to = parser.retrieveNextString().strip();
            Task task = new Event(false, item, from, to);
            storage.save(task);
            taskList.add(task);
            System.out.println(INDENT + "Got it. I've added this task:");
            System.out.println(INDENT + "  " + task);
            System.out.println(INDENT + "Now you have " + taskList.size() + " tasks in the list.");
        } else {

            System.out.println(
                    "Invalid input. Possible inputs are deadline, todo, event, list, mark, and unmark.");
        }
    }
}
