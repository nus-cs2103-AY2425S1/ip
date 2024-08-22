import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Sage {
    public static final String NAME = "Sage";
    public static List<Task> tasksList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        textBox(String.format("Hello! I'm %s\nWhat can i do for you?", NAME));

        while (true) {
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
                try {
                    int index = Integer.parseInt(fullCommand[1].replaceAll("\\s+","")) - 1;
                    Task task = tasksList.get(index);

                    task.setDone(doneStatus);
                    confirmationMessage.append(task);
                    textBox(String.valueOf(confirmationMessage));
                } catch (NumberFormatException e) {
                    textBox("Invalid command");
                } catch (IndexOutOfBoundsException e) {
                    textBox("Invalid index");
                }

            } else if (command.equals("todo") && fullCommand.length > 1) {
                tasksList.add(new ToDo(fullCommand[1]));
                addedTextBox();

            } else if (command.equals("deadline") && fullCommand.length > 1) {
                try {
                    String[] deadlineCommand = fullCommand[1].split(" /by ", 2);
                    String description = deadlineCommand[0];
                    String by = deadlineCommand[1].replaceAll("\\s+","");

                    tasksList.add(new Deadline(description, by));
                    addedTextBox();
                } catch (IndexOutOfBoundsException e) {
                    textBox("Invalid command. Do include description and /by");
                }

            } else if (command.equals("event") && fullCommand.length > 1) {
                try {
                    String[] eventCommand = fullCommand[1].split(" /from | /to ", 3);
                    String description = eventCommand[0];
                    String from = eventCommand[1];
                    String to = eventCommand[2];

                    tasksList.add(new Event(description, from, to));
                    addedTextBox();
                } catch (IndexOutOfBoundsException e) {
                    textBox("Invalid command. Do include description, /from and /to");
                }

            } else {
                textBox("Invalid command");
            }
        }
    }

    public static void textBox(String text) {
        String HORIZONTAL_LINE = "_________________________________________________";
        String indentedText = text.indent(4);
        System.out.println(HORIZONTAL_LINE + "\n" + indentedText + HORIZONTAL_LINE);
    }

    public static void addedTextBox() {
        int noOfTasks = tasksList.size();
        textBox(String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", tasksList.get(noOfTasks - 1), noOfTasks));
    }
}
