package meowmeow;

import java.io.IOException;

/**
 * Represents a parser which deals with figuring out what to do based on the user command
 */
public class Parser {
    private TaskList tasks;
    private Storage saver;
    private Ui ui;
    //private String initInput;

    public Parser(TaskList list, Storage saver, Ui ui) {
        this.tasks = list;
        this.saver = saver;
        this.ui = ui;
        //this.initInput = initInput;
    }

    /**
     * Parses user input and determines what to do based on it.
     *
     * @throws IOException If an I/O error occurs during saving.
     */
    public String parse(String initInput) throws IOException, InterruptedException {
        StringBuilder output = new StringBuilder();

        //while (!initInput.equals("bye")) {
            if (initInput.equals("list")) {
                // Build the task list output
                for (int i = 0; i < tasks.size(); i++) {
                    output.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
                }
            } else if (initInput.startsWith("find ")) {
                String keyword = initInput.substring(5);
                TaskList matchingTasks = new TaskList();
                for (Task task : tasks) {
                    if (task.getDescription().contains(keyword)) {
                        matchingTasks.add(task);
                    }
                }
                output.append("Here are the matching tasks in your list:\n");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    output.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
                }
            } else if (initInput.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(initInput.substring(5)) - 1;
                if (taskNumber >= 0 && taskNumber < tasks.size()) {
                    tasks.get(taskNumber).markDone();
                    output.append("Nice! I've marked this task as done:\n");
                    output.append("  ").append(tasks.get(taskNumber)).append("\n");
                    saver.saveData();
                } else {
                    output.append("Invalid task number.\n");
                }
            } else if (initInput.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(initInput.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < tasks.size()) {
                    tasks.get(taskNumber).unMark();
                    output.append("OK, I've marked this task as not done yet:\n");
                    output.append("  ").append(tasks.get(taskNumber)).append("\n");
                    saver.saveData();
                } else {
                    output.append("Invalid task number.\n");
                }
            } else if (initInput.startsWith("todo ")) {
                String description = initInput.substring(5);
                ToDo todo = new ToDo(description);
                tasks.add(todo);
                output.append("Got it. I've added this task:\n");
                output.append("  ").append(todo).append("\n");
                output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                saver.saveData();
            } else if (initInput.startsWith("deadline ")) {
                String[] parts = initInput.substring(9).split(" /by ");
                if (parts.length <= 1) {
                    output.append("Invalid deadline.\n");
                } else {
                    String description = parts[0];
                    String by = parts[1];
                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    output.append("Got it. I've added this task:\n");
                    output.append("  ").append(deadline).append("\n");
                    output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                    saver.saveData();
                }
            } else if (initInput.startsWith("event ")) {
                String[] parts = initInput.substring(6).split(" /from | /to ");
                if (parts.length <= 1) {
                    output.append("Invalid event.\n");
                } else {
                    String description = parts[0];
                    String from = parts[1];
                    String to = parts[2];
                    Event event = new Event(description, from, to);
                    tasks.add(event);
                    output.append("Got it. I've added this task:\n");
                    output.append("  ").append(event).append("\n");
                    output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                    saver.saveData();
                }
            } else if (initInput.startsWith("delete ")) {
                int taskNumber = Integer.parseInt(initInput.substring(7)) - 1;
                if (taskNumber >= 0 && taskNumber < tasks.size()) {
                    Task removedTask = tasks.remove(taskNumber);
                    output.append("Noted. I've removed this task:\n");
                    output.append("  ").append(removedTask).append("\n");
                    output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                    saver.saveData();
                } else {
                    output.append("Invalid task number.\n");
                }
            } else if (initInput.startsWith("bye")) {
                output.append("Bye. Hope to see you again soon!\n");
            } else {
                output.append("Sorry, I don't know what that means.\n");
            }
            //initInput = Ui.getNext();
        return output.toString();
    }
}
