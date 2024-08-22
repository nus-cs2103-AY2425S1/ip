import java.util.Scanner;

public class Bob {
    private static final String line = "____________________________________________________________";
    private static final String indentation = "    ";
    private static TaskList taskList = new TaskList();
    private static final String[] greeting = { "Hello! I'm Bob", "What can I do for you?" };
    private static final String[] farewell = { " Bye. Hope to see you again soon!" };

    private static void prettyPrint(String[] texts) {
        String separator = Bob.indentation + Bob.line;
        System.out.println(separator);
        for (String text: texts) {
            System.out.print(Bob.indentation);
            System.out.println(text);
        }
        System.out.println(separator);
    }

    private static void addTask(String desc) {
        Bob.taskList.addTask(desc);
        Bob.prettyPrint(new String[] { "Added: " + desc });
    }

    private static void listCommands() {
        String[] desc = { "Here are the tasks in your list:" };
        String[] tasks = Bob.taskList.describeTasks();
        String[] texts = new String[tasks.length + desc.length];

        for (int i = 0; i < desc.length; i++) { // Copy desc lines into texts array to be printed
            texts[i] = desc[i];
        }
        for (int i = 0; i < tasks.length; i++) { // Copy tasks lines into texts array to be printed
            texts[i + desc.length] = String.format("%d.", i+1) + tasks[i];
        }

        Bob.prettyPrint(texts);
    }

    private static void markTask(int idx) {
        Bob.taskList.mark(idx);
        Bob.prettyPrint(new String[] { "Nice! I've marked this task as done:", Bob.taskList.describeTask(idx) });
    }

    private static void unmarkTask(int idx) {
        Bob.taskList.unmark(idx);
        Bob.prettyPrint(new String[] { "OK, I've marked this task as not done yet:", Bob.taskList.describeTask(idx) });
    }

    public static void main(String[] args) {
        Bob.prettyPrint(Bob.greeting);
        String input = "";
        String[] arguments;
        Scanner scanner = new Scanner(System.in);

        outerLoop:
        while (true) {
            input = scanner.nextLine();
            arguments = input.split(" ");

            switch (arguments[0]) {
                case ("bye"):
                    Bob.prettyPrint(Bob.farewell);
                    break outerLoop;
                case ("list"):
                    Bob.listCommands();
                    continue;
                case ("mark"): {
                    int idx = Integer.parseInt(arguments[1]);
                    Bob.markTask(idx);
                    continue;
                }
                case ("unmark"): {
                    int idx = Integer.parseInt(arguments[1]);
                    Bob.unmarkTask(idx);
                    continue;
                }
                default:
                    Bob.addTask(input);
            }
        }
    }
}
