import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    private static final String line = "____________________________________________________________\n";
    private static TaskList taskList = new TaskList();

    private static String greeting() {
        return Bob.lineFormat(" Hello! I'm Bob\n" +
                " What can I do for you?\n");
    }
    private static String farewell() {
        return  Bob.lineFormat(" Bye. Hope to see you again soon!\n");
    }
    private static String lineFormat(String input) {
        return Bob.line + input + Bob.line;
    }
    private static void addTask(String command) {
        Bob.taskList.addTask(command);
        System.out.println(Bob.lineFormat("Added: " + command + "\n"));
    }
    private static void listCommands() {
        System.out.print(Bob.lineFormat(Bob.taskList.toString()));
    }

    public static void main(String[] args) {
        System.out.println(Bob.greeting());
        String input = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your command: ");
            input = scanner.nextLine();

            if (input.compareTo("bye") == 0) {
                System.out.println(Bob.farewell());
                break;
            }
            if (input.compareTo("list") == 0) {
                Bob.listCommands();
                continue;
            }

            Bob.addTask(input);
        }
    }
}
