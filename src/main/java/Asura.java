import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Asura {
    public static String formatResponse(String msg) {
        String startBorder = "---------------------------------------\n";
        String endBorder = "\n---------------------------------------";
        String formattedMsg = startBorder + msg + endBorder;
        return formattedMsg.indent(3);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String introduction = """
                Hello! I'm Asura!
                What can I do for you?""";
        String goodbye = """
                Bye. Hope to see you again soon!""";
        List<Task> tasks = new ArrayList<>();

        System.out.println(formatResponse(introduction));
        String[] input = scanner.nextLine().split(" ");
        String prefix = input[0];

        while (!prefix.equals("bye")) {
            StringBuilder output = new StringBuilder();
            switch (prefix) {
                case "list":
                    output.append("Here are the tasks in your list:\n");
                    for (int i=0; i<tasks.size(); i++) {
                        output.append(i + 1).append(".").append(tasks.get(i).getStatusIcon()).append(tasks.get(i).description).append("\n");
                    }
                    System.out.println(formatResponse(output.toString()));
                    break;
                case "mark":
                    if (1 >= input.length) {
                        // if user inputted mark by itself
                        System.out.println(formatResponse("Oops! Something is wrong."));
                    } else {
                        int selection = Integer.parseInt(input[1]) - 1;
                        tasks.get(selection).markAsDone();
                        output.append("Nice! I've marked this task as done:").append("\n").append(tasks.get(selection).getStatusIcon()).append(tasks.get(selection).description);
                        System.out.println(formatResponse(output.toString()));
                    }
                    break;
                case "unmark":
                    if (1 >= input.length) {
                        // if user inputted mark by itself
                        System.out.println(formatResponse("Oops! Something is wrong."));
                    } else {
                        int selection = Integer.parseInt(input[1]) - 1;
                        tasks.get(selection).markAsNotDone();
                        output.append("OK, I've marked this task as not done yet:").append("\n").append(tasks.get(selection).getStatusIcon()).append(tasks.get(selection).description);
                        System.out.println(formatResponse(output.toString()));
                    }
                    break;
                default:
                    String taskString = String.join(" ", input);
                    tasks.add(new Task(taskString));
                    System.out.println(formatResponse("added: " + taskString));
            }

            input = scanner.nextLine().split(" ");
            prefix = input[0];
        }
        System.out.println(formatResponse(goodbye));
    }
}
