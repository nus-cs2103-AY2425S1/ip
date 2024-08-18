import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BobbyBot {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String chatBotName = "BobbyBot";
    public static void main(String[] args) {
        try (Scanner myScanner = new Scanner(System.in)) {
            runBot(myScanner);
        } catch (DukeException e) {
            printInput(e.getMessage());
        }
    }

    private static void runBot(Scanner myScanner) throws DukeException {
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
                        if (input.split(" ").length != 2) {
                            throw new DukeException("Please specify one task number.");
                        }
                        String secondParam = input.split(" ")[1];
                        int index = validateIndex(secondParam);
                        tasks.get(index).setIsDone(true);
                        printInput("Nice! I've marked this task as done:",  "\t" + tasks.get(index));
                    } else if (input.startsWith("unmark")) {
                        if (input.split(" ").length != 2) {
                            throw new DukeException("Please specify one task number.");
                        }
                        String secondParam = input.split(" ")[1];
                        int index = validateIndex(secondParam);
                        tasks.get(index).setIsDone(false);
                        printInput("OK, I've marked this task as not done yet:",  "\t" + tasks.get(index));
                    } else if (input.startsWith("todo")) {
                        String inputTrimmed = input.replace("todo", "").trim();
                        if (inputTrimmed.isEmpty()) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        TaskCreator addTodo = () -> {
                            Task todo = new ToDo(inputTrimmed);
                            tasks.add(todo);
                            return todo;
                        };
                        addTask(addTodo);
                    } else if (input.startsWith("deadline")) {
                        TaskCreator addDeadline = () -> {
                            String inputTrimmed = input.replace("deadline", "").trim();
                            Pattern r = Pattern.compile("(.*) /by (.*)");
                            Matcher m = r.matcher(inputTrimmed);
                            if (m.find()) {
                                String description = m.group(1).trim();
                                String by = m.group(2).trim();
                                Task deadline = new Deadline(description, by);
                                tasks.add(deadline);
                                return deadline;
                            } else {
                                throw new DukeException("Please specify a deadline.");
                            }
                        };
                        addTask(addDeadline);
                    } else if (input.startsWith("event")) {
                        TaskCreator addEvent = () -> {
                            String inputTrimmed = input.replace("event", "").trim();
                            Pattern r = Pattern.compile("(.*) /from (.*) /to (.*)");
                            Matcher m = r.matcher(inputTrimmed);
                            if (m.find()) {
                                String description = m.group(1).trim();
                                String from = m.group(2).trim();
                                String to = m.group(3).trim();
                                Task event = new Event(description, from, to);
                                tasks.add(event);
                                return event;
                            } else {
                                throw new DukeException("Please specify a from and to time.");
                            }
                        };
                        addTask(addEvent);
                    } else if (input.startsWith("delete")) {
                        if (input.split(" ").length != 2) {
                            throw new DukeException("Please specify one task number.");
                        }
                        String secondParam = input.split(" ")[1];
                        int index = validateIndex(secondParam);
                        removeTask(index);
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    break;
            }
        }
    }

    private static int validateIndex(String indexParam) throws DukeException {
        if (!indexParam.matches("\\d+")) {
            throw new DukeException("Please specify a valid number.");
        }
        int index = Integer.parseInt(indexParam) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Please specify a task number that is in range.");
        }
        return index;
    }

    private static void addTask(TaskCreator task) throws DukeException {
        printInput(
                "Got it. I've added this task:",
                "\t" + task.createTask(),
                "Now you have " + tasks.size() + " task(s) in the list."
        );
    }

    private static void removeTask(int index) {
        Task removedTask = tasks.remove(index);
        printInput(
                "Noted. I've removed this task:",
                "\t" + removedTask,
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
