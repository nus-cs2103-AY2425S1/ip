import java.util.Scanner;
import tasks.Task;
import tasks.Tasklist;

public class NathanBot {
    public static void main(String[] args) {
        String LINE = "____________________________________________________________\n";
        String GREET = """
                        Hello! I'm NathanBot
                        What can I do for you?
                       """;
        String EXIT = "Bye. Hope to see you again soon!\n";
        String BREAK_COMMAND = "bye";
        String DISPLAY_LIST= "list";
        Tasklist taskList = new Tasklist();
        System.out.println(LINE + GREET + LINE);
        
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine();
                if (input.equals(BREAK_COMMAND)) {
                    System.out.println(LINE + EXIT + LINE);
                    break;
                } else if (input.equals(DISPLAY_LIST)) {
                    System.out.println(LINE + taskList + LINE);
                } else {
                    taskList.addTask(new Task(input));
                    System.out.println(LINE + "added: " + input + "\n" + LINE);
                }
            }
        }
    }
}