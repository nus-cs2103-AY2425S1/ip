package MeowMeow;

import java.io.IOException;

public class Parser {
    private TaskList list;
    private Saving saver;
    private Ui ui;
    private String initInput;

    public Parser(TaskList list, Saving saver, Ui ui, String initInput) {
        this.list = list;
        this.saver = saver;
        this.ui = ui;
        this.initInput = initInput;
    }

    public void parse() throws IOException {
        while (!initInput.equals("bye")) {
            if (initInput.equals("list")) {
                // Print all tasks
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
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
                if (taskNumber >= 0 && taskNumber < list.size()) {
                    list.get(taskNumber).markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + list.get(taskNumber));
                    saver.saveData();
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (initInput.startsWith("unmark ")) {
                // Unmark a task (mark it as not done)
                int taskNumber = Integer.parseInt(initInput.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < list.size()) {
                    list.get(taskNumber).unMark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + list.get(taskNumber));
                    saver.saveData();
                } else {
                    System.out.println("Invalid task number.");
                }
            } else if (initInput.startsWith("todo ")) {
                // Add a meowmeow.ToDo task
                String description = initInput.substring(5);
                ToDo todo = new ToDo(description);
                list.add(todo);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
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
                    list.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
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
                    list.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    saver.saveData();
                }
            } else if (initInput.startsWith("delete ")) {
                // Delete a task
                int taskNumber = Integer.parseInt(initInput.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < list.size()) {
                    Task removedTask = list.remove(taskNumber);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removedTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    saver.saveData();
                } else {
                    System.out.println("Invalid task number.");
                }
            } else {
                System.out.println("Sorry, I don't know what that means.");
            }
            initInput = Ui.next();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
