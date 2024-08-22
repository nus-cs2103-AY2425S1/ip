import java.util.Scanner;
import tasks.Task;
import tasks.Tasklist;

public class NathanBot {
    static String LINE = "____________________________________________________________\n";
    public static void main(String[] args) {
        Tasklist taskList = new Tasklist();
        
        String GREET = """
                        Hello! I'm NathanBot
                        What can I do for you?
                       """;
        String EXIT = "Bye. Hope to see you again soon!\n";
        String BREAK_COMMAND = "bye";
        String DISPLAY_LIST_COMMAND= "list";
        String MARK_DONE_COMMAND = "mark ";
        String MARK_UNDONE_COMMAND = "unmark ";
        
        System.out.println(LINE + GREET + LINE);
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine();
                if (input.equals(BREAK_COMMAND)) {
                    System.out.println(LINE + EXIT + LINE);
                    break;
                } else if (input.equals(DISPLAY_LIST_COMMAND)) {
                    System.out.println(LINE + taskList + LINE);
                } else if (input.startsWith(MARK_DONE_COMMAND)) {
                    handleMarkCommand(input, MARK_DONE_COMMAND, taskList, true);
                } else if (input.startsWith(MARK_UNDONE_COMMAND)) {
                    handleMarkCommand(input, MARK_UNDONE_COMMAND, taskList, false);
                } else {
                    taskList.addTask(new Task(input));
                    System.out.println(LINE + "added: " + input + "\n" + LINE);
                }
            }
        }
    }

    private static void handleMarkCommand(String input, String command, Tasklist taskList, boolean isDone) {
        // suggested by Copilot
        try {
            int index = Integer.parseInt(input.substring(command.length()));
            if (isDone) {
                taskList.markAsDone(index - 1);
                System.out.println(LINE + "Nice! I've marked this task as done:\n  " + taskList.getTask(index - 1) + "\n" + LINE);
            } else {
                taskList.markAsUndone(index - 1);
                System.out.println(LINE + "OK, I've marked this task as not done yet:\n  " + taskList.getTask(index - 1) + "\n" + LINE);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(LINE + "Invalid task number.\n" + LINE);
        }
    }
}