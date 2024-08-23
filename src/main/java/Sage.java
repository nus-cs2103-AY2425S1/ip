import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Sage {
    public static final String NAME = "Sage";
    public static List<Task> tasksList = new ArrayList<>();

    public static void main(String[] args) throws SageException {
        Scanner scanner = new Scanner(System.in);
        textBox(String.format("Hello! I'm %s\nWhat can i do for you?", NAME));

        while (true) {
            try {
                String input = scanner.nextLine();
                String[] fullCommand = input.split(" ", 2);
                String command = fullCommand[0];

                if (input.equalsIgnoreCase("bye")) {
                    textBox("Bye. Hope to see you again soon!");
                    break;

                } else if (input.equals("list")) {
                    if (tasksList.isEmpty()) {
                        textBox("There are no task!");
                    } else {
                        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
                        for (int i = 0; i < tasksList.size(); i++) {
                            Task task = tasksList.get(i);
                            result.append(String.format("%d. %s\n", i + 1, task.toString()));
                        }
                        textBox(String.valueOf(result));
                    }

                } else if ((command.equals("mark") || command.equals("unmark")) && fullCommand.length > 1) {
                    boolean doneStatus = command.equals("mark");
                    StringBuilder confirmationMessage = new StringBuilder(doneStatus ? "Nice! I've marked this task as done:\n"
                            : "OK, I've marked this task as not done yet:\n");

                    int index;
                    try {
                        index = Integer.parseInt(fullCommand[1].trim()) - 1;
                    } catch (NumberFormatException e) {
                        throw new SageException("Invalid mark/unmark command. Index must be a number.");
                    }

                    if (index < 0 || index >= tasksList.size()) {
                        throw new SageException("Invalid index. Please try again.");
                    }

                    Task task = tasksList.get(index);
                    task.setDone(doneStatus);
                    confirmationMessage.append(task);
                    textBox(String.valueOf(confirmationMessage));

                } else if (command.equals("todo") && fullCommand.length > 1) {
                    String description = fullCommand[1].trim();
                    if (description.isEmpty())
                        throw new SageException("Invalid todo command. Please include a description.");

                    tasksList.add(new ToDo(description));
                    addedTextBox();

                } else if (command.equals("deadline") && fullCommand.length > 1) {
                    String[] deadlineCommand = fullCommand[1].split(" /by ", 2);
                    if (deadlineCommand.length < 2)
                        throw new SageException("Invalid deadline command. Please include a description and /by.");

                    String description = deadlineCommand[0].trim();
                    String by = deadlineCommand[1].trim();

                    tasksList.add(new Deadline(description, by));
                    addedTextBox();

                } else if (command.equals("event") && fullCommand.length > 1) {
                    String[] eventCommand = fullCommand[1].split(" /from | /to ", 3);
                    if (eventCommand.length < 3)
                        throw new SageException("Invalid event command. Please include description, /from, and /to.");

                    String description = eventCommand[0].trim();
                    String from = eventCommand[1].trim();
                    String to = eventCommand[2].trim();

                    tasksList.add(new Event(description, from, to));
                    addedTextBox();

                } else if (command.equals("delete") && fullCommand.length > 1) {
                    int index;
                    try {
                        index = Integer.parseInt(fullCommand[1].trim()) - 1;
                    } catch (NumberFormatException e) {
                        throw new SageException("Invalid delete command. Index must be a number.");
                    }
                    if (index < 0 || index >= tasksList.size()) {
                        throw new SageException("Invalid index. Please try again.");
                    }
                    Task deletedTask = tasksList.get(index);
                    tasksList.remove(index);
                    int noOfTasks = tasksList.size();
                    textBox(String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", deletedTask, noOfTasks));

                } else {
                    throw new SageException("Invalid command.");
                }
                
            } catch (SageException e) {
                textBox(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void textBox(String text) {
        String HORIZONTAL_LINE = "_________________________________________________";
        String indentedText = text.indent(4);
        System.out.println(HORIZONTAL_LINE + "\n" + indentedText + HORIZONTAL_LINE);
    }

    public static void addedTextBox() {
        int noOfTasks = tasksList.size();
        textBox(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", tasksList.get(noOfTasks - 1), noOfTasks));
    }
}
