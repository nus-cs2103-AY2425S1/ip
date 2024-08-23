import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Asura {
    public static String formatResponse(String msg) {
        String startBorder = "---------------------------------------\n";
        String endBorder = "\n---------------------------------------";
        String formattedMsg = startBorder + msg + endBorder;
        return formattedMsg.indent(3);
    }

    public static void main(String[] args) throws AsuraException {
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
                        output.append(i + 1).append(".").append(tasks.get(i).toString()).append("\n");
                    }
                    System.out.println(formatResponse(output.toString()));
                    break;
                case "mark":
                    if (1 >= input.length) {
                        // if user inputted mark by itself
                        throw new AsuraException("Please indicate a task to mark.");
                    } else {
                        int selection = Integer.parseInt(input[1]) - 1;
                        tasks.get(selection).markAsDone();
                        output.append("Nice! I've marked this task as done:").append("\n").append(tasks.get(selection).toString());
                        System.out.println(formatResponse(output.toString()));
                    }
                    break;
                case "unmark":
                    if (1 >= input.length) {
                        // if user inputted mark by itself
                        throw new AsuraException("Please indicate a task to unmark.");
                    } else {
                        int selection = Integer.parseInt(input[1]) - 1;
                        tasks.get(selection).markAsNotDone();
                        output.append("OK, I've marked this task as not done yet:").append("\n").append(tasks.get(selection).toString());
                        System.out.println(formatResponse(output.toString()));
                    }
                    break;
                case "todo":
                    if (1 >= input.length) {
                        throw new AsuraException("The description todo cannot be empty.");
                    }
                    String taskString = String.join(" ", Arrays.asList(input).subList(1, input.length));
                    Todo newTodo = new Todo(taskString);
                    tasks.add(newTodo);
                    output.append("Got it. I've added this task:\n").append(newTodo.toString()).append("\n").append("Now you have ").append(tasks.size()).append(" tasks in your list.\n");
                    System.out.println(formatResponse(output.toString()));
                    break;
                case "deadline":
                    List<String> inputArray = Arrays.asList(input);
                    int byIndex = inputArray.indexOf("/by");
                    try {
                        List<String> descriptionArray = inputArray.subList(1, byIndex);
                        if (descriptionArray.isEmpty()) {
                            throw new AsuraException("The description todo cannot be empty.");
                        }
                        List<String> dateArray = inputArray.subList(byIndex + 1, inputArray.size());
                        if (dateArray.isEmpty()) {
                            throw new AsuraException("The date cannot be empty.");
                        }
                        Deadline newDeadline = new Deadline(String.join(" ", descriptionArray), String.join(" ", dateArray));
                        tasks.add(newDeadline);
                        output.append("Got it. I've added this task:\n").append(newDeadline.toString()).append("\n").append("Now you have ").append(tasks.size()).append(" tasks in your list.\n");
                        System.out.println(formatResponse(output.toString()));
                    } catch (Exception e) {
                        throw new AsuraException(e.getMessage());
                    }
                    break;
                case "event":
                    inputArray = Arrays.asList(input);
                    int fromIndex = inputArray.indexOf("/from");
                    int toIndex = inputArray.indexOf("/to");
                    try {
                        List<String> descriptionArray = inputArray.subList(1, fromIndex);
                        if (descriptionArray.isEmpty()) {
                            throw new AsuraException("The description todo cannot be empty.");
                        }
                        List<String> fromArray = inputArray.subList(fromIndex + 1, toIndex);
                        if (fromArray.isEmpty()) {
                            throw new AsuraException("The from date cannot be empty.");
                        }
                        List<String> toArray = inputArray.subList(toIndex + 1, inputArray.size());
                        if (toArray.isEmpty()) {
                            throw new AsuraException("The to date cannot be empty.");
                        }
                        Event newEvent = new Event(String.join(" ", descriptionArray), String.join(" ", fromArray), String.join(" ", toArray));
                        tasks.add(newEvent);
                        output.append("Got it. I've added this task:\n").append(newEvent.toString()).append("\n").append("Now you have ").append(tasks.size()).append(" tasks in your list.\n");
                        System.out.println(formatResponse(output.toString()));
                    }
                    catch (Exception e) {
                        throw new AsuraException(e.getMessage());
                    }
                    break;
                default:
                    throw new AsuraException("Invalid input");
            }

            input = scanner.nextLine().split(" ");
            prefix = input[0];
        }
        System.out.println(formatResponse(goodbye));
    }
}
