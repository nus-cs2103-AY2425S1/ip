import java.util.ArrayList;
import java.util.Scanner;

public class Grok {

    private static String padHorizontalLines(String msg) {
        String horizontalLine = "_".repeat(80);
        return String.join(
                "\n",
                horizontalLine, msg, horizontalLine
        );
    }

    private static String indent(String msg) {
        String indentSpaces = " ".repeat(4);
        return indentSpaces.concat(msg.replace("\n", "\n".concat(indentSpaces)));
    }

    private static String padMessage(String msg) {
        return indent(padHorizontalLines(msg));
    }
    public static void main(String[] args) {
        ArrayList<Task> commands = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println(padMessage("Hello! I'm Grok\nWhat ya wanna do to grok your way to success?"));
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()) {
                System.out.println(padMessage("Please enter your command."));
            } else if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                if (commands.isEmpty()) {
                    System.out.println(padMessage("You have yet to enter your items."));
                    continue;
                }

                StringBuilder listOfCommands = new StringBuilder();
                for (int i = 0; i < commands.size(); i++) {
                    listOfCommands.append(i + 1);
                    listOfCommands.append(". ");
                    listOfCommands.append(commands.get(i));
                    listOfCommands.append("\n");
                }

                System.out.println(padMessage(
                        listOfCommands.substring(0, Math.max(0, listOfCommands.length() - 2))
                ));
            } else if (userInput.contains("unmark")) {
                if (userInput.length() < 8) {
                    System.out.println(padMessage("Please indicate the index to unmark: e.g. unmark 1"));
                    continue;
                }

                int taskIndex;
                try {
                    taskIndex = Integer.parseInt(userInput.substring(7));
                } catch (NumberFormatException e) {
                    System.out.println(padMessage("Please enter the task index number to unmark."));
                    continue;
                }

                if (commands.isEmpty() || taskIndex <= 0 || taskIndex > commands.size()) {
                    System.out.println(padMessage("Please enter a valid task index to unmark."));
                    continue;
                }

                Task task = commands.get(taskIndex - 1);
                task.markUndone();
                System.out.println(padMessage("Ok, I've marked this task as not done yet:\n  " + task));
            } else if (userInput.contains("mark")) {
                if (userInput.length() < 6) {
                    System.out.println(padMessage("Please indicate the index to mark: e.g. mark 1"));
                    continue;
                }

                int taskIndex;
                try {
                    taskIndex = Integer.parseInt(userInput.substring(5));
                } catch (NumberFormatException e) {
                    System.out.println(padMessage("Please enter the task index number to mark."));
                    continue;
                }

                if (commands.isEmpty() || taskIndex <= 0 || taskIndex > commands.size()) {
                    System.out.println(padMessage("Please enter a valid task index to mark."));
                    continue;
                }

                Task task = commands.get(taskIndex - 1);
                task.markDone();
                System.out.println(padMessage("Nice! I've marked this task as done:\n  " + task));
            } else {
                System.out.println(padMessage("added: ".concat(userInput)));
                commands.add(new Task(userInput));
            }
        }


        System.out.println(padMessage("Bye. Hope to see you again soon!"));
    }
}