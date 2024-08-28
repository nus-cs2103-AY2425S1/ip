import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;


/**
 * Main class for the Weeny task management application.
 * Handles user input and task operations.
 */
public class Weeny {

    public static void main(String[] args) {
        // Check if data directory and TaskList.txt exist, create if not
        Path dataDir = Paths.get("data");
        Path taskFile = dataDir.resolve("TaskList.txt");

        try {
            if (Files.notExists(dataDir)) {
                Files.createDirectory(dataDir);
            }

            if (Files.notExists(taskFile)) {
                Files.createFile(taskFile);
            }
        } catch (IOException e) {
            System.err.println("Error creating directory or file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Read TaskList file to resume TaskList
        List<Task> tasks = readFileIn("./data/TaskList.txt");
        boolean isFarewell = false;

        printLine();
        System.out.println("Hello! I'm Weeny\nWhat can I do for you?");
        printLine();

        Scanner userInput = new Scanner(System.in);

        while (!isFarewell) {
            String input = userInput.nextLine();
            String command = extractFirstWord(input);

            try {
                switch (command) {
                case "list":
                    printLine();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                    printLine();
                    break;

                case "bye":
                    isFarewell = true;
                    break;

                case "mark":
                    int markIndex = extractEndNumber(input) - 1;
                    validateIndex(markIndex, tasks.size(), "mark");
                    tasks.get(markIndex).setMark();
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(markIndex).toString());
                    printLine();
                    break;

                case "unmark":
                    int unmarkIndex = extractEndNumber(input) - 1;
                    validateIndex(unmarkIndex, tasks.size(), "unmark");
                    tasks.get(unmarkIndex).setUnmark();
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(unmarkIndex).toString());
                    printLine();
                    break;

                case "todo":
                    validateTodoInput(input);
                    printLine();
                    Task todoTask = new Todo(input.substring(5).trim());
                    tasks.add(todoTask);
                    printTaskAddedMessage(todoTask, tasks.size());
                    printLine();
                    break;

                case "event":
                    validateEventInput(input);
                    Task eventTask = new Events(extractEventName(input),
                            extractEventTimes(input)[0],
                            extractEventTimes(input)[1]);
                    tasks.add(eventTask);
                    printTaskAddedMessage(eventTask, tasks.size());
                    printLine();
                    break;

                case "deadline":
                    validateDeadlineInput(input);
                    Task deadlineTask = new Deadlines(extractDeadlineName(input),
                            extractDeadlineTime(input));
                    tasks.add(deadlineTask);
                    printTaskAddedMessage(deadlineTask, tasks.size());
                    printLine();
                    break;

                case "delete":
                    int deleteIndex = extractEndNumber(input) - 1;
                    validateIndex(deleteIndex, tasks.size(), "delete");
                    Task removedTask = tasks.remove(deleteIndex);
                    printLine();
                    System.out.println("Spooof! The task magically disappeared:");
                    System.out.println(removedTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    printLine();
                    break;

                default:
                    throw new UnsupportedOperationException("Oopsies we don't understand that command");
                }
            } catch (IllegalArgumentException | IndexOutOfBoundsException | UnsupportedOperationException e) {
                printLine();
                System.out.println("Error: " + e.getMessage());
                printLine();
            }
        }
        printLine();
        System.out.println("Bye. Hope to see you soon!");
        printLine();
        userInput.close();
    }

    /**
     * Prints a line separator.
     */
    public static void printLine() {
        System.out.println("______________________________________________");
    }

    /**
     * Return list of task from input file
     *
     * @param fileName The input file directory.
     * @return The list of task.
     */
    public static List<Task> readFileIn(String fileName) {
        List<Task> taskList= new ArrayList<>();
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(
                    Paths.get(fileName),
                    StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            // do something
            e.printStackTrace();
        }
        Iterator<String> itr = lines.iterator();
        while (itr.hasNext()) {
            String[] processTask = itr.next().split(" \\| ");
            String description = processTask[2];
            Task currentTask = null;
            if (processTask[0].equals("T")) {
                currentTask = new Todo(description);
            } else if (processTask[0].equals("D")) {
                currentTask = new Deadlines(description, processTask[3]);
            } else if (processTask[0].equals("E")) {
                int split = processTask[3].indexOf('-');
                String startTime = processTask[3].substring(0, split - 1) +
                        processTask[3].substring(processTask[3].length() - 3);
                String endTime = processTask[3].substring(0, split - 2) + " " +
                        processTask[3].substring(split + 1);
                currentTask = new Events(description, startTime, endTime);
            } else {
                // should not reach here
            }
            if (Integer.parseInt(processTask[1]) == 1) {
                currentTask.setMark();
            }
            taskList.add(currentTask);
        }
        return taskList;
    }

    /**
     * Extracts the first word from a given sentence.
     *
     * @param sentence The input string.
     * @return The first word of the sentence.
     */
    public static String extractFirstWord(String sentence) {
        int spaceIndex = sentence.indexOf(" ");
        return spaceIndex == -1 ? sentence : sentence.substring(0, spaceIndex);
    }

    /**
     * Extracts the last number in a command string.
     *
     * @param sentence The input string.
     * @return The number at the end of the sentence.
     */
    public static int extractEndNumber(String sentence) {
        int spaceIndex = sentence.indexOf(" ");
        return Integer.parseInt(sentence.substring(spaceIndex + 1).trim());
    }

    /**
     * Extracts the deadline time from a given command string.
     *
     * @param sentence The input string.
     * @return The extracted deadline time as a string.
     */
    public static String extractDeadlineTime(String sentence) {
        int index = sentence.indexOf("/by") + 4;
        return sentence.substring(index).trim();
    }

    /**
     * Extracts the event start and end times from a given command string.
     *
     * @param sentence The input string.
     * @return An array containing start and end times.
     */
    public static String[] extractEventTimes(String sentence) {
        int fromIndex = sentence.indexOf("/from") + 6;
        int toIndex = sentence.indexOf("/to");
        int toIndexPlus4 = toIndex + 4;
        return new String[] {
                sentence.substring(fromIndex, toIndex - 1).trim(),
                sentence.substring(toIndexPlus4).trim()
        };
    }

    /**
     * Validates that the index is within the valid range of task list.
     *
     * @param index The index to validate.
     * @param size The size of the task list.
     * @param action The action being performed (e.g., "mark", "delete").
     */
    private static void validateIndex(int index, int size, String action) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Just a reminder. You can't " + action + " tasks that don't exist!");
        }
    }

    /**
     * Validates the input for the "todo" command.
     *
     * @param input The input string.
     */
    private static void validateTodoInput(String input) {
        if (input.length() <= 5) {
            throw new IllegalArgumentException("Are you going to add nothing as your To-Do? >:(");
        }
    }

    /**
     * Validates the input for the "event" command.
     *
     * @param input The input string.
     */
    private static void validateEventInput(String input) {
        if (input.length() <= 6 || !input.contains("/from") || !input.contains("/to")) {
            throw new IllegalArgumentException("What kind of event has no name or time? >:(");
        }
    }

    /**
     * Validates the input for the "deadline" command.
     *
     * @param input The input string.
     */
    private static void validateDeadlineInput(String input) {
        if (input.length() <= 9 || !input.contains("/by")) {
            throw new IllegalArgumentException("What is a deadline without a date or a task? >:(");
        }
    }

    /**
     * Extracts the name of the event from the input string.
     *
     * @param input The input string.
     * @return The event name.
     */
    private static String extractEventName(String input) {
        return input.substring(6, input.indexOf("/from") - 1).trim();
    }

    /**
     * Extracts the name of the deadline task from the input string.
     *
     * @param input The input string.
     * @return The deadline task name.
     */
    private static String extractDeadlineName(String input) {
        return input.substring(9, input.indexOf("/by") - 1).trim();
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param size The current size of the task list.
     */
    private static void printTaskAddedMessage(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }


}
