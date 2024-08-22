package bot;

import bot.tasks.*;
import bot.utils.Formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bot {
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printBotMessage("Hello! I'm ChadGPT. What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            handleInput(sc.nextLine());
        }
    }

    private static void handleInput(String input) {
        Pattern regex = Pattern.compile("(\\w+)\\s*(.*)");
        Matcher matcher = regex.matcher(input);
        if (matcher.matches()) {
            String cmd = matcher.group(1);
            String args = matcher.group(2);

            switch (cmd) {
                case "list":
                    handleList();
                    break;
                case "todo", "deadline", "event":
                    handleAddTask(cmd, args);
                    break;
                case "mark":
                    handleMarkTask(args);
                    break;
                case "unmark":
                    handleUnmarkTask(args);
                    break;
                case "bye":
                    printBotMessage("Bye. Hope to see you again soon!");
                    System.exit(0);
                default:
                    // TODO? Possibly find a better way to handle this
                    printBotMessage("Command not found");
            }
        } else {
            // TODO: Throw exception
        }
    }

    private static void handleList() {
        printBotMessage("Here are the tasks in your list:\n" + Formatter.formatList(tasks));
    }

    private static void handleAddTask(String cmd, String args) {
        if (cmd.equals("todo")) {
            tasks.add(new Todo(args));
        } else if (cmd.equals("deadline")) {
            Pattern regex = Pattern.compile("(.*)\\s/by\\s(.*)");
            Matcher matcher = regex.matcher(args);
            if (matcher.matches()) {
                String task = matcher.group(1);
                String deadline = matcher.group(2);
                tasks.add(new Deadline(task, deadline));
            } else {
                // TODO: Handle error
            }
        } else {
            // Last command is guaranteed to be "event" by the switch statement
            Pattern regex = Pattern.compile("(.*)\\s/from\\s(.*)\\s/to\\s(.*)");
            Matcher matcher = regex.matcher(args);
            if (matcher.matches()) {
                String task = matcher.group(1);
                String from = matcher.group(2);
                String to = matcher.group(3);
                tasks.add(new Event(task, from, to));
            } else {
                // TODO: Handle error
            }
        }
        Task newTask = tasks.get(tasks.size()-1);
        String response = String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                newTask.toString(),
                tasks.size()
        );
        printBotMessage(response);
    }

    private static void handleMarkTask(String args) {
        int index = getTaskIndex(args);
        tasks.get(index).markAsDone();
        printBotMessage("Nice! I've marked this task as done:\n" + tasks.get(index));
    }

    private static void handleUnmarkTask(String args) {
        int index = getTaskIndex(args);
        tasks.get(index).markAsIncomplete();
        printBotMessage("OK, I've marked this task as not done yet:\n" + tasks.get(index));
    }

    private static int getTaskIndex(String input) {
        return Integer.parseInt(input.split(" ")[0]) - 1;
    }

    /**
     * Prints a formatted bot message
     *
     * @param msg the string message to be printed
     */
    private static void printBotMessage(String msg) {
        System.out.println(Formatter.formatBotMessage(msg));
    }
}
