import java.util.Scanner;

public class Bob {
    private static final String line = "____________________________________________________________";
    private static final String lineIndent = "    ";
    private static final String indentation = "     ";
    private static TaskList taskList = new TaskList();
    private static final String[] greeting = { "Hello! I'm Bob", "What can I do for you?" };
    private static final String[] farewell = { " Bye. Hope to see you again soon!" };

    private static void prettyPrint(String[] texts) {
        String separator = Bob.lineIndent + Bob.line;
        System.out.println(separator);
        for (String text: texts) {
            System.out.print(Bob.indentation);
            System.out.println(text);
        }
        System.out.println(separator);
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

    private static String[] splitInput(String input, String[] splits) {
        String[] result = new String[splits.length];
        int[] splitIdxs = new int[splits.length + 1];
        splitIdxs[splits.length] = input.length() + 1;

        for (int i = 0; i < splits.length; i++) {
            int splitIdx = input.indexOf(splits[i]);
            splitIdxs[i] = splitIdx;
        }

        for (int i = 0; i < splits.length; i++) {
            String split = splits[i];
            int splitIdx = input.indexOf(split);
            String text = input.substring(splitIdx + split.length() + 1, splitIdxs[i+1] - 1);
            result[i] = text;
        }

        return result;
    }

    private static void todo(String input) {
        String[] inputs = Bob.splitInput(input, new String[] { "todo" });
        ToDo todo = Bob.taskList.todo(inputs[0]);
        Bob.prettyPrint(new String[] { "Got it. I've added this task:",
                " " + todo.toString(),
                "Now you have 5 tasks in the list." });
    }

    private static void deadline(String input) {
        String[] inputs = Bob.splitInput(input, new String[] { "deadline", "/by" });
        Deadline deadline = Bob.taskList.deadline(inputs[0], inputs[1]);
        Bob.prettyPrint(new String[] { "Got it. I've added this task:",
                " " + deadline.toString(),
                "Now you have 5 tasks in the list." });
    }

    private static void event(String input) {
        String[] inputs = Bob.splitInput(input, new String[] { "event", "/from", "/to" });
        Event event = Bob.taskList.event(inputs[0], inputs[1], inputs[2]);
        Bob.prettyPrint(new String[] { "Got it. I've added this task:",
                " " + event.toString(),
                "Now you have 5 tasks in the list." });
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
                case ("todo"):
                    Bob.todo(input);
                    continue;
                case ("deadline"): {
                    Bob.deadline(input);
                    continue;
                }
                case ("event"): {
                    Bob.event(input);
                    continue;
                }
                default:
                    System.out.println("ERROR: UNKNOWN COMMAND!");
            }
        }
    }
}
