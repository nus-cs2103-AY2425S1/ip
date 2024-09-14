import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Nebula {

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    private Storage storage;

    public Nebula(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (NebulaException e) {
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        System.out.println(ui.greeting());

        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String command = sc.nextLine();

            if(command.equals("bye")) {
                System.out.println(ui.goodbye());
                storage.saveTaskListToTextFile(TaskList.getTaskList());
                break;
            }

            else if(command.equals("list")) {
                System.out.println(ui.displayList());
            }

            else if(command.startsWith("mark")) {
                try {
                    validateCommand(command);
                } catch (NebulaException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                int taskNum = parser.splitCommandAndTaskNumber(command);
                System.out.println(tasks.markTask(taskNum));
            }

            else if(command.startsWith("unmark")) {
                try {
                    validateCommand(command);
                } catch (NebulaException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                int taskNum = parser.splitCommandAndTaskNumber(command);
                System.out.println(tasks.unmarkTask(taskNum));
            }

            else if(command.startsWith("delete")) {
                try {
                    validateCommand(command);
                } catch (NebulaException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                int taskNum = parser.splitCommandAndTaskNumber(command);
                System.out.println(tasks.deleteTask(taskNum));
            }

            else {
                try {
                    validateCommand(command);
                } catch (NebulaException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                TaskType taskType = parseTaskType(command);

                switch (taskType) {
                    case TODO:
                        String taskDescription = parser.splitCommandAndTaskDescription(command);
                        Task newTodo = new Todo(taskDescription);
                        String addedTodo = tasks.addTask(newTodo);
                        System.out.println(addedTodo);
                        break;

                    case DEADLINE:
                        String taskInformation = parser.splitCommandAndTaskDescription(command);
                        String taskDescriptionDeadline = parser.splitDeadlineCommand(taskInformation)[0];
                        String taskDeadline = parser.splitDeadlineCommand(taskInformation)[1];
                        Task newDeadline = new Deadline(taskDescriptionDeadline, taskDeadline);
                        String addedDeadline = tasks.addTask(newDeadline);
                        System.out.println(addedDeadline);
                        break;

                    case EVENT:
                        String taskInfo = parser.splitCommandAndTaskDescription(command);
                        String taskDescriptionEvent = parser.splitEventCommand(taskInfo)[0];
                        String startInfo = parser.splitEventCommand(taskInfo)[1];
                        String endInfo = parser.splitEventCommand(taskInfo)[2];

                        String taskStart = parser.splitCommandAndTaskDescription(startInfo);
                        String taskEnd = parser.splitCommandAndTaskDescription(endInfo);

                        Task newEvent = new Event(taskDescriptionEvent, taskStart, taskEnd);
                        String addedEvent = tasks.addTask(newEvent);
                        System.out.println(addedEvent);
                        break;

                    case UNKNOWN:
                        System.out.println("Unknown command type.");
                        break;
                }

            }

        }
    }

    /**
     * Entry point of the application. Initializes the UI, task list, and parser,
     * then processes user commands in a loop until "bye" is entered
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) throws IOException {
        new Nebula("./data/nebulaTaskList.txt").run();
    }

    /**
     * Validates the user's input for the correct format and content
     *
     * @param command  the user input command
     * @throws NebulaException if the command is invalid or improperly formatted
     */
    public static void validateCommand(String command) throws NebulaException {
        Ui ui = new Ui();

        if (command.isEmpty()) {
            throw new NebulaException("Please enter a command!");
        } else if (!(command.startsWith("todo") || command.startsWith("deadline")
                || command.startsWith("event") || command.startsWith("mark")
                || command.startsWith("unmark") || command.startsWith("delete")
                || command.startsWith("list") || command.startsWith("bye"))) {
            throw new NebulaException(ui.displayUnknownCommandException());
        } else if (command.startsWith("mark") || command.startsWith("unmark") || command.startsWith("delete")) {
            String[] parts = command.split(" ", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new NebulaException(ui.displayUnknownTaskNumberException());
            }
            try {
                int taskIndex = Integer.parseInt(parts[1].trim()) - 1; // Convert to 0-based index
                if (taskIndex < 0 || taskIndex >= TaskList.getTaskListLength()) {
                    throw new NebulaException(ui.displayNonexistentTaskNumberException());
                }
            } catch (NumberFormatException e) {
                throw new NebulaException(ui.displayUnknownTaskNumberException());
            }
        } else {
            String[] parts = command.split(" ", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new NebulaException(ui.displayUnknownMessageException());
            }

            String description = parts[1].trim();

            if (command.startsWith("deadline")) {
                if (!description.contains("/by")) {
                    throw new NebulaException(ui.displayUnknownDeadlineException());
                } else {
                    // Extract the date after "/by"
                    String[] parts2 = description.split("/by");
                    String dueDate = parts2[1].trim();

                    // Validate the due date format
                    if (!isValidDate(dueDate)) {
                        throw new NebulaException("Warning: Deadline date is not in the correct format (M/d/yyyy HHmm).");
                    }
                }
            } else if (command.startsWith("event")) {
                if (!description.contains("/from") || !description.contains("/to")) {
                    throw new NebulaException(ui.displayUnknownEventTimingException());
                } else {
                    // Extract dates from the description
                    String[] parts2 = description.split("/from");
                    String timingPart = parts2[1].trim();
                    String[] dates = timingPart.split("/to");

                    if (dates.length < 2) {
                        throw new NebulaException(ui.displayUnknownEventTimingException());
                    }
                    String startDate = dates[0].trim();
                    String endDate = dates[1].trim();

                    // Validate the start and end dates
                    if (!isValidDate(startDate) || !isValidDate(endDate)) {
                        throw new NebulaException("Warning: Event dates must be in yyyy-mm-dd format.");
                    }
                }
            }
        }
    }

    /**
     * Validates if the provided date string is in a valid date format
     *
     * @param dateStr the date string to be validated. It can be in either of two formats
     * @return A boolean representing whether the date string is in a valid format
     */
    private static boolean isValidDate(String dateStr) {
        try {
            LocalDateTime.parse(dateStr, DATE_TIME_FORMAT); // Attempt to parse the date with time
            return true;
        } catch (DateTimeParseException e) {
            try {
                LocalDate.parse(dateStr, DATE_FORMAT); // Attempt to parse the date without time
                return true;
            } catch (DateTimeParseException ex) {
                return false;
            }
        }
    }

/**
     * Determines the TaskType based on the command prefix
     *
     * @param command the input command string
     * @return the corresponding TaskType, or unknown TaskType if unrecognized
     */
    public static TaskType parseTaskType(String command) {
        if (command.startsWith("todo")) {
            return TaskType.TODO;
        } else if (command.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else if (command.startsWith("event")) {
            return TaskType.EVENT;
        } else {
            return TaskType.UNKNOWN;
        }
    }

}
