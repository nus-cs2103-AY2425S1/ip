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

    private static String addTaskMessage(Task t, ArrayList<Task> tasks) {
        return "Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println(padMessage("Hello! I'm Grok\nWhat ya wanna do to grok your way to success?"));
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()) {
                System.out.println(padMessage("Please enter your command."));
            } else if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println(padMessage("You have yet to enter your items."));
                    continue;
                }

                StringBuilder listOfCommands = new StringBuilder();
                for (int i = 0; i < tasks.size(); i++) {
                    listOfCommands
                            .append(i + 1)
                            .append(". ")
                            .append(tasks.get(i))
                            .append("\n");
                }

                System.out.println(padMessage(
                        listOfCommands.substring(0, Math.max(0, listOfCommands.length() - 1))
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

                if (tasks.isEmpty() || taskIndex <= 0 || taskIndex > tasks.size()) {
                    System.out.println(padMessage("Please enter a valid task index to unmark."));
                    continue;
                }

                Task task = tasks.get(taskIndex - 1);
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

                if (tasks.isEmpty() || taskIndex <= 0 || taskIndex > tasks.size()) {
                    System.out.println(padMessage("Please enter a valid task index to mark."));
                    continue;
                }

                Task task = tasks.get(taskIndex - 1);
                task.markDone();
                System.out.println(padMessage("Nice! I've marked this task as done:\n  " + task));
            } else if (userInput.contains("todo")) {
                if (userInput.length() < 6) {
                    System.out.println(padMessage("Todo command usage: todo (task description here)"));
                    continue;
                }
                Task newTask;
                try {
                    newTask = new Todo(userInput.substring(5));
                } catch (GrokInvalidUserInputException e) {
                    System.out.println(padMessage("OOPS! " + e.getMessage()));
                    continue;
                }

                tasks.add(newTask);
                System.out.println(padMessage(addTaskMessage(newTask, tasks)));
            } else if (userInput.contains("deadline")) {
                if (userInput.length() < 10 || !userInput.contains("/by")) {
                    System.out.println(padMessage(
                        "Deadline command usage: deadline (task description here) /by (due date and time)"
                    ));
                    continue;
                }

                String[] components = userInput.split("/by");
                String description = components[0].substring(9);
                String due = components[1];

                Task newTask;
                try {
                    newTask = new Deadline(description, due);;
                } catch (GrokInvalidUserInputException e) {
                    System.out.println(padMessage("OOPS! " + e.getMessage()));
                    continue;
                }

                tasks.add(newTask);
                System.out.println(padMessage(addTaskMessage(newTask, tasks)));
            } else if (userInput.contains("event")) {
                if (userInput.length() < 7 || !userInput.contains("/from") || !userInput.contains("/to")) {
                    System.out.println(padMessage(
                            "Event command usage: event (task description here) /from (start date and time) /to (end date and time)"
                    ));
                    continue;
                }

                String[] components = userInput.split("/from");
                String[] subcomponents = components[1].split("/to");
                String description = components[0];
                String from = subcomponents[0];
                String to = subcomponents[1];

                Task newTask;
                try {
                    newTask = new Event(description, from, to);
                } catch (GrokInvalidUserInputException e) {
                    System.out.println(padMessage("OOPS! " + e.getMessage()));
                    continue;
                }

                tasks.add(newTask);
                System.out.println(padMessage(addTaskMessage(newTask, tasks)));
            } else {
                System.out.println(padMessage("OOPS! Sorry, I don't recognize your input :(\n"));
            }
        }


        System.out.println(padMessage("Bye. Hope to see you again soon!"));
    }
}