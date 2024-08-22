import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Jade {
    private static final String INDENT = "     "; // 5 spaces for indentation
    private static final String TOP_LINE = "    " + "_".repeat(60) + "\n";
    private static final String BOT_LINE = "\n" + "    " + "_".repeat(60);
    private static final String GREET = INDENT + "Hello! I'm Jade!\n"
            + INDENT + "What can I do for you?";
    private static final String EXIT = INDENT + "Bye. Hope to see you again soon!";
    private static final String LIST_MESSAGE = INDENT + "Here are the tasks in your list:";

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println(TOP_LINE + GREET + BOT_LINE);

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        String message;
        String[] taskTypes = {"todo ", "deadline ", "event "};

        while (!command.equals("bye")) {
            boolean startsWithTaskTypes = Arrays.stream(taskTypes).anyMatch(command::startsWith);

            if (command.equals("list")) {
                System.out.println(TOP_LINE + LIST_MESSAGE);
                for (int i = 0; i < tasks.size(); i++) {
                    if (i < tasks.size() - 1) {
                        System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
                    } else {
                        // last task
                        System.out.println(INDENT + (i + 1) + "." + tasks.get(i)
                                + BOT_LINE);
                    }
                }
                command = sc.nextLine();
            } else if (command.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(command.substring(5)) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsDone();
                    message = INDENT + "Nice! I've marked this task as done:"
                            + "\n" + INDENT + "  " + tasks.get(taskIndex);
                } else {
                    message = INDENT + "Hmm, no such task. Try again.";
                }
                System.out.println(TOP_LINE + message + BOT_LINE);
                command = sc.nextLine();
            } else if (command.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(command.substring(7)) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsNotDone();
                    message = INDENT + "OK, I've marked this task as not done yet:"
                            + "\n" + INDENT + "  " + tasks.get(taskIndex);
                } else {
                    message = INDENT + "Hmm, no such task. Try again.";
                }
                System.out.println(TOP_LINE + message + BOT_LINE);
                command = sc.nextLine();
            } else if (startsWithTaskTypes) {
                if (command.startsWith("todo ")) {
                    Task newTodo = new Todo(command.substring(5));
                    tasks.add(newTodo);
                } else if (command.startsWith("deadline ")) {
                    String[] parts = command.substring(9).split(" /by ", 2);
                    if (parts.length < 2) {
                        message = INDENT + "Please provide a deadline in the format:"
                                + "\n" + INDENT + "  " + "deadline <task> /by <time>";

                        System.out.println(TOP_LINE + message + BOT_LINE);
                        command = sc.nextLine();
                        continue;
                    } else {
                        Task newDeadline = new Deadline(parts[0], parts[1]);
                        tasks.add(newDeadline);
                    }
                } else if (command.startsWith("event ")) {
                    String[] parts = command.substring(6).split(" /from ", 2);
                    if (parts.length < 2) {
                        message = INDENT + "Please provide an event in the format: "
                                + "\n" + INDENT + "  " + "event <task> /from <start time> /to <end time>";

                        System.out.println(TOP_LINE + message + BOT_LINE);
                        command = sc.nextLine();
                        continue;
                    } else {
                        String[] timeParts = parts[1].split(" /to ", 2);
                        if (timeParts.length < 2) {
                            message = INDENT + "Please provide an end time in the format:"
                                    + "\n" + INDENT + "  " + "event <task> /from <start time> /to <end time>";

                            System.out.println(TOP_LINE + message + BOT_LINE);
                            command = sc.nextLine();
                            continue;
                        } else {
                            Task newEvent = new Event(parts[0], timeParts[0], timeParts[1]);
                            tasks.add(newEvent);
                        }
                    }
                }

                int taskIndex = tasks.size() - 1;
                message = INDENT + "Got it. I've added this task:"
                        + "\n" + INDENT + "  " + tasks.get(taskIndex);
                if (taskIndex == 0) {
                    message += "\n" + INDENT + "Now you have 1 task in the list.";
                } else {
                    message += "\n" + INDENT + String.format("Now you have %d tasks in the list.", taskIndex + 1);
                }

                System.out.println(TOP_LINE + message + BOT_LINE);
                command = sc.nextLine();
            } else {
                message = INDENT + "Please specify the type of task: todo, deadline, or event.";
                System.out.println(TOP_LINE + message + BOT_LINE);
                command = sc.nextLine();
            }
        }

        System.out.println(TOP_LINE + EXIT + BOT_LINE);
    }
}
