import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    private static final String line = "____________________________________________________________\n";
    private static TaskList taskList = new TaskList();

    private static String greeting() {
        return Bob.lineFormat(" Hello! I'm Bob\n" +
                " What can I do for you?");
    }
    private static String farewell() {
        return  Bob.lineFormat(" Bye. Hope to see you again soon!");
    }
    private static String lineFormat(String input) {
        return Bob.line + input + "\n" + Bob.line;
    }
    private static void addTask(String command) {
        Bob.taskList.addTask(command);
        System.out.println(Bob.lineFormat("Added: " + command));
    }
    private static void listCommands() {
        System.out.print(Bob.lineFormat("Here are the tasks in your list:\n" + Bob.taskList.toString()));
    }

    public static void main(String[] args) {
        System.out.print(Bob.greeting());
        String input = "";
        String[] arguments;
        Scanner scanner = new Scanner(System.in);

        outerLoop:
        while (true) {
            System.out.println("Enter your command: ");
            input = scanner.nextLine();
            arguments = input.split(" ");

            switch (arguments[0]) {
                case ("bye"):
                    System.out.println(Bob.farewell());
                    break outerLoop;
                case ("list"):
                    Bob.listCommands();
                    continue;
                case ("mark"): {
                    int idx = Integer.parseInt(arguments[1]);
                    Bob.taskList.mark(idx);
                    String temp = "Nice! I've marked this task as done:\n"
                            + Bob.taskList.describeTask(idx);
                    System.out.println(Bob.lineFormat(temp));
                    continue;
                }
                case ("unmark"): {
                    int idx = Integer.parseInt(arguments[1]);
                    Bob.taskList.unmark(idx);
                    String temp = "OK, I've marked this task as not done yet:\n"
                            + Bob.taskList.describeTask(idx);
                    System.out.println(Bob.lineFormat(temp));
                    continue;
                }
                default:
                    Bob.addTask(input);
            }
        }
    }
}
