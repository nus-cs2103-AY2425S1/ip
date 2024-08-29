import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Main class for the Weeny task management application.
 * Handles user input and task operations.
 */
public class Weeny {

    public static void main(String[] args) {
        Ui ui = new Ui(); // Create Ui object
        Storage storage = new Storage(); // Create storage object
        Parser parser = new Parser(); // Create parser object
        TaskList taskList = new TaskList(); // Create tasklist object

        // Check if data directory and TaskList.txt exist, create if not
        File dataDir = new File("./data");
        File taskFile = new File(dataDir, "TaskList.txt");
        try {
            if (dataDir.mkdir()) {
                // Create directory successful
            }
            if (taskFile.createNewFile()) {
                // Create file successful
            }
        } catch (IOException e) {
            System.err.println("Error creating directory or file" + e.getMessage());
            e.printStackTrace();
        }


        // Read TaskList file to resume TaskList
        List<Task> tasks = readFileIn("./data/TaskList.txt");
        boolean isFarewell = false;

        ui.showWelcomeMessage();

        while (!isFarewell) {
            String input = ui.readUserInput();
            String command = parser.extractFirstWord(input);

            try {
                switch (command) {
                case "list":
                    ui.showTaskList(tasks);
                    break;

                case "bye":
                    isFarewell = true;
                    break;

                case "mark":
                    int markIndex = parser.extractEndNumber(input) - 1;
                    validateIndex(markIndex, tasks.size(), "mark");
                    tasks.get(markIndex).setMark();
                    ui.showMarkMessage(tasks.get(markIndex));
                    break;

                case "unmark":
                    int unmarkIndex = parser.extractEndNumber(input) - 1;
                    validateIndex(unmarkIndex, tasks.size(), "unmark");
                    tasks.get(unmarkIndex).setUnmark();
                    ui.showUnmarkMessage(tasks.get(unmarkIndex));
                    break;

                case "todo":
                    validateTodoInput(input);
                    printLine();
                    Task todoTask = new Todo(input.substring(5).trim());
                    tasks.add(todoTask);
                    ui.printTaskAddedMessage(todoTask, tasks.size());
                    printLine();
                    break;

                case "event":
                    validateEventInput(input);
                    Task eventTask = new Events(parser.extractEventName(input),
                            parser.extractEventTimes(input)[0],
                            parser.extractEventTimes(input)[1]);
                    tasks.add(eventTask);
                    ui.printTaskAddedMessage(eventTask, tasks.size());
                    printLine();
                    break;

                case "deadline":
                    validateDeadlineInput(input);
                    Task deadlineTask = new Deadlines(parser.extractDeadlineName(input),
                            parser.extractDeadlineTime(input));
                    tasks.add(deadlineTask);
                    ui.printTaskAddedMessage(deadlineTask, tasks.size());
                    printLine();
                    break;

                case "delete":
                    int deleteIndex = parser.extractEndNumber(input) - 1;
                    validateIndex(deleteIndex, tasks.size(), "delete");
                    Task removedTask = tasks.remove(deleteIndex);
                    ui.showTaskDeletedMessage(removedTask, tasks.size());
                    break;

                default:
                    throw new UnsupportedOperationException("Oopsies we don't understand that command");
                }
            } catch (IllegalArgumentException | IndexOutOfBoundsException | UnsupportedOperationException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbyeMessage();


        // Write to TaskList.txt
        writeFileIn("./Data/TaskList.txt", tasks);
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
                // Split string into startDate and endDate string
                int split = processTask[3].indexOf('-');
                if (split == -1) {
                    throw new IllegalArgumentException("Invalid event time format " + processTask[3]);
                }
                String startDatestring = processTask[3].substring(0, split).trim();
                String endDatestring = processTask[3].substring(split + 1).trim();
                currentTask = new Events(description, startDatestring, endDatestring);
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

    public static void writeFileIn(String path, List<Task> tasks) {
        Iterator<Task> taskIterator = tasks.iterator();
        try {
            FileWriter fileWriter = new FileWriter(path);
            while (taskIterator.hasNext()) {
                fileWriter.write(taskIterator.next().toOutput() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error in writing");
            e.printStackTrace();
        }
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
}
