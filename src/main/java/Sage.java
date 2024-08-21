import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Sage {
    public static final String NAME = "Sage";
    public static final String HORIZONTAL_LINE = "_________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasksList = new ArrayList<>();

        segmentedText(String.format("Hello! I'm %s \nWhat can i do for you?", NAME));

        while (true) {
            String input = scanner.nextLine();
            String[] command = input.split(" ", 2);

            if (input.equalsIgnoreCase("bye")) {
                segmentedText("Bye. Hope to see you again soon!");
                break;

            } else if (input.equals("list")) {
                if (tasksList.isEmpty()) {
                    segmentedText("There are no task!");
                } else {
                    StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
                    for (int i = 0; i < tasksList.size(); i++) {
                        Task task = tasksList.get(i);
                        result.append(String.format("%d. [%s] %s\n", i + 1, task.getStatusIcon(), task.getDescription()));
                    }
                    segmentedText(String.valueOf(result));
                }

            } else if ((command[0].equals("mark") || command[0].equals("unmark")) && command.length > 1) {
                boolean doneStatus = command[0].equals("mark");
                StringBuilder confirmationMessage = new StringBuilder(doneStatus ? "Nice! I've marked this task as done:\n"
                        : "OK, I've marked this task as not done yet:\n");
                try {
                    int index = Integer.parseInt(command[1]) - 1;
                    Task task = tasksList.get(index);
                    task.setDone(doneStatus);
                    confirmationMessage.append(String.format("[%s] %s\n", task.getStatusIcon(), task.getDescription()));
                    segmentedText(String.valueOf(confirmationMessage));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid command");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid index");
                }

            } else {
                tasksList.add(new Task(input));
                segmentedText("added: " + input);
            }
        }
    }

    public static void segmentedText(String text) {
        String indentedText = text.indent(4);
        System.out.println(HORIZONTAL_LINE + "\n" + indentedText + HORIZONTAL_LINE);
    }
}
