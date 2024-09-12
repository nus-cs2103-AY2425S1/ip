package meowmeow;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents a parser which deals with figuring out what to do based on the user command
 */
public class Parser {
    private TaskList tasks;
    private Storage saver;
    private Ui ui;
    private String initInput;

    public Parser(TaskList list, Storage saver, Ui ui, String initInput) {
        this.tasks = list;
        this.saver = saver;
        this.ui = ui;
        this.initInput = initInput;
    }

    /**
     * Parses user input and determines what to do based on it.
     *
     * @throws IOException If an I/O error occurs during saving.
     */
    public void parse() throws IOException {
        while (!initInput.equals("bye")) {
            if (initInput.equals("list")) {
                // Print all tasks
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            } else if (initInput.startsWith("find ")) {
                //System.out.println(list.size());
                String keyword = initInput.substring(5);
                System.out.println(keyword);
                TaskList matchingTasks = new TaskList();
                for (Task task : list) {
                    System.out.println(task.getDescription());
                    if (task.getDescription().contains(keyword)) {
                        matchingTasks.add(task);
                    }
                }
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    System.out.println((i + 1) + "." + matchingTasks.get(i));
                }
            } else if (initInput.startsWith("mark ")) {
                // Mark a task as done
                int taskNumber = Integer.parseInt(initInput.substring(5)) - 1;
                if (taskNumber >= 0 && taskNumber < tasks.size()) {
                    tasks.get(taskNumber).markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks.get(taskNumber));
                    saver.saveData();
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (initInput.startsWith("unmark ")) {
                // Unmark a task (mark it as not done)
                int taskNumber = Integer.parseInt(initInput.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < tasks.size()) {
                    tasks.get(taskNumber).unMark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks.get(taskNumber));
                    saver.saveData();
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (initInput.startsWith("todo ")) {
                // Add a meowmeow.ToDo task
                String description = initInput.substring(5);
                ToDo todo = new ToDo(description);
                tasks.add(todo);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                saver.saveData();
            } else if (initInput.startsWith("deadline ")) {
                // Add a meowmeow.Deadline task
                String[] parts = initInput.substring(9).split(" /by ");
                if (parts.length <= 1) {
                    System.out.println("invalid deadline");
                } else {
                    String description = parts[0];
                    String by = parts[1];
                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saver.saveData();
                }
            } else if (initInput.startsWith("event ")) {
                // Add an meowmeow.Event task
                String[] parts = initInput.substring(6).split(" /from | /to ");
                if (parts.length <= 1) {
                    System.out.println("invalid event");
                } else {
                    String description = parts[0];
                    String from = parts[1];
                    String to = parts[2];
                    Event event = new Event(description, from, to);
                    tasks.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saver.saveData();
                }
            } else if (initInput.startsWith("delete ")) {
                // Delete a task
                int taskNumber = Integer.parseInt(initInput.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < tasks.size()) {
                    Task removedTask = tasks.remove(taskNumber);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saver.saveData();
                } else {
                    System.out.println("Invalid task number.");
                }
            } else {
                System.out.println("Sorry, I don't know what that means.");
            }
            initInput = Ui.getNext();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
