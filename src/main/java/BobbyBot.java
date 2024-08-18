import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BobbyBot {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String chatBotName = "BobbyBot";

        Scanner myScanner = new Scanner(System.in);
        printInput("Hello! I'm " + chatBotName, "What can I do for you?");
        while (true) {
            final String input = myScanner.nextLine();
            switch (input) {
                case "list":
                    listTasks(tasks.toArray(new Task[0]));
                    break;
                case "bye":
                    printInput("Bye. Hope to see you again soon!");
                    return;
                default:
                    if (input.startsWith("mark")) {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        tasks.get(index).setIsDone(true);
                        printInput("Nice! I've marked this task as done:",  "\t" + tasks.get(index));
                    } else if (input.startsWith("unmark")) {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        tasks.get(index).setIsDone(false);
                        printInput("OK, I've marked this task as not done yet:",  "\t" + tasks.get(index));
                    } else if (input.startsWith("todo")) {
                        String inputTrimmed = input.replace("todo", "").trim();
                        Supplier<String> addTodo = () -> {
                            Task todo = new ToDo(inputTrimmed);
                            tasks.add(todo);
                            return todo.toString();
                        };
                        addTask(addTodo);
                    } else if (input.startsWith("deadline")) {
                        Supplier<String> addDeadline = () -> {
                            String inputTrimmed = input.replace("deadline", "").trim();
                            Pattern r = Pattern.compile("(.*) /by (.*)");
                            Matcher m = r.matcher(inputTrimmed);
                            if (m.find()) {
                                String description = m.group(1).trim();
                                String by = m.group(2).trim();
                                Task deadline = new Deadline(description, by);
                                tasks.add(deadline);
                                return deadline.toString();
                            }
                            return "";
                        };
                        addTask(addDeadline);
                    } else if (input.startsWith("event")) {
                        Supplier<String> addEvent = () -> {
                            String inputTrimmed = input.replace("event", "").trim();
                            Pattern r = Pattern.compile("(.*) /from (.*) /to (.*)");
                            Matcher m = r.matcher(inputTrimmed);
                            if (m.find()) {
                                String description = m.group(1).trim();
                                String from = m.group(2).trim();
                                String to = m.group(3).trim();
                                Task event = new Event(description, from, to);
                                tasks.add(event);
                                return event.toString();
                            }
                            return "";
                        };
                        addTask(addEvent);
                    }
                    break;
            }
        }
    }

    private static void addTask(Supplier<String> task) {
        printInput(
                "Got it. I've added this task:",
                "\t" + task.get(),
                "Now you have " + tasks.size() + " task(s) in the list."
        );
    }

    private static void listTasks(Task[] tasks) {
        String[] formattedMessages = IntStream.
                range(0, tasks.length).
                filter(i -> tasks[i] != null).
                mapToObj(i -> i + 1 + ". " + tasks[i]).
                toArray(String[]::new);
        String[] combined = new String[formattedMessages.length + 1];
        combined[0] = "Here are the tasks in your list:";
        System.arraycopy(formattedMessages, 0, combined, 1, formattedMessages.length);
        printInput(combined);
    }

    private static void printInput(String ...input) {
        System.out.print("\t");
        printLine();
        for (String i : input) {
            System.out.println("\t" + i);
        }
        System.out.print("\t");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
