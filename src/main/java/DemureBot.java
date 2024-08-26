import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class DemureBot {
    private static TaskList list;
    private static Storage storage;
    private static Ui ui;

    /**
     * Checks if user command is valid and executes the command.
     *
     * @param command User command.
     * @param list List of tasks the user has.
     * @throws DemureBotException If the user command is invalid.
     */
    private static void check(String command, TaskList list) throws DemureBotException {
        if (command.startsWith("mark")) {
            String remainder = command.substring(4).trim();
            try {
                int index = Integer.parseInt(remainder) - 1;
                Task task = list.getTask(index);
                task.markAsDone();
                DemureBot.ui.displayMarkTask(task);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a positive integer after mark.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid index: " +
                    (Integer.parseInt(remainder)) +
                    "\n" +
                    "Please enter a number within 1 to number of current tasks.\n"
                );
            }
        } else if (command.startsWith("unmark")) {
            String remainder = command.substring(6).trim();
            try {
                int index = Integer.parseInt(remainder) - 1;
                Task task = list.getTask(index);
                task.unmark();
                DemureBot.ui.displayUnmarkTask(task);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a positive integer after unmark.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid index: " +
                    (Integer.parseInt(remainder)) +
                    "\n" +
                    "Please enter a number within 1 to number of current tasks.\n"
                );
            }
        } else if (command.startsWith("delete")) {
            String remainder = command.substring(6).trim();
            try {
                int index = Integer.parseInt(remainder) - 1;
                Task task = list.getTask(index);
                list.removeTask(index);
                DemureBot.ui.displayDeleteTask(task, list.getSize());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a positive integer after delete.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid index: " +
                    (Integer.parseInt(remainder)) +
                    "\n" +
                    "Please enter a number within 1 to number of current tasks.\n"
                );
            }
        } else if (command.startsWith("todo")) {
            String description = command.substring(4).trim();
            // check that there is a task description
            if (description.isEmpty()) {
                throw new DemureBotException("The description of a todo cannot be empty.\nAdd description after todo.\n");
            }
            Todo todo = new Todo(description, false);
            list.addTask(todo);
            DemureBot.ui.displayAddTask(todo, list.getSize());
        } else if (command.startsWith("deadline")) {
            Deadline deadline = getDeadline(command);
            list.addTask(deadline);
            DemureBot.ui.displayAddTask(deadline, list.getSize());
        } else if (command.startsWith("event")) {
            Event event = getEvent(command);
            list.addTask(event);
            DemureBot.ui.displayAddTask(event, list.getSize());
        } else {
            // throw invalid command exception
            throw new DemureBotException("Invalid command\nCreate a new task starting with the command todo, deadline or event.\n");
        }
    }

    /**
     * Returns a Deadline created from the user command.
     *
     * @param command User command.
     * @return Deadline created from the user command.
     * @throws DemureBotException If the user command is invalid.
     */
    private static Deadline getDeadline(String command) throws DemureBotException {
        String remainder = command.substring(8).trim();
        // check that there is a task description
        if (remainder.isEmpty() || remainder.startsWith("/by")) {
            throw new DemureBotException("The description of a deadline cannot be empty.\nAdd description after deadline.\n");
        }
        String[] splitBy = remainder.split("/by");
        // check that there is a deadline
        if (splitBy.length == 1) {
            throw new DemureBotException("The deadline of a deadline cannot be empty.\nAdd deadline after /by.\n");
        }
        String description = splitBy[0].trim();
        String by = formatDateTime(splitBy[1].trim());
        return new Deadline(description, by, false);
    }

    /**
     * Returns an Event created from the user command.
     *
     * @param command User command.
     * @return Event created from the user command.
     * @throws DemureBotException If the user command is invalid.
     */
    private static Event getEvent(String command) throws DemureBotException {
        String remainder = command.substring(5).trim();
        // check that there is a task description
        if (remainder.isEmpty() || remainder.startsWith("/from")) {
            throw new DemureBotException("The description of an event cannot be empty.\nAdd description after event.\n");
        }
        String[] splitFrom = remainder.split("/from");
        // check that there is a start time
        if (splitFrom.length == 1) {
            throw new DemureBotException("The start time of an event cannot be empty.\nAdd start time after /from.\n");
        }
        String description = splitFrom[0].trim();
        String[] splitTo = splitFrom[1].split("/to");
        // check that there is an end time
        if (splitTo.length == 1) {
            throw new DemureBotException("The end time of an event cannot be empty.\nAdd end time after /to.\n");
        }
        String from = formatDateTime(splitTo[0].trim());
        String to = formatDateTime(splitTo[1].trim());
        return new Event(description, from, to, false);
    }

    /**
     * Returns a formatted date/time string.
     *
     * @param dateTime Date/time string to be formatted.
     * @return Formatted date/time string.
     * @throws DemureBotException If the date/time string is invalid.
     */
    private static String formatDateTime(String dateTime) throws DemureBotException {
        try {
            LocalDateTime dateTimeParsed = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return dateTimeParsed.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm"));
        } catch (DateTimeParseException e) {
            throw new DemureBotException("Invalid date/time format. Please enter date and time in yyyy-MM-dd HHmm format.\n");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // check if user ended session
        boolean isFinished = false;

        DemureBot.storage = new Storage();
        DemureBot.ui = new Ui();

        // introduction to chatbot
        DemureBot.ui.displayStart();

        // check if data folder exists if not create it
        String folderPath = "./data";
        DemureBot.storage.checkFolder(folderPath);

        // check if saved data exists if not create it
        String filePath = "./data/tasks.txt";
        boolean savedDataExists = DemureBot.storage.checkFile(filePath);

        // load saved data if exists
        if (savedDataExists) {
            try {
                // fetch list of items to do
                DemureBot.list = new TaskList(DemureBot.storage.load(filePath));
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            } catch (DemureBotException e) {
                System.out.println("Error loading task: " + e.getMessage());
            }
        }

        if (list == null) {
            DemureBot.list = new TaskList(new ArrayList<>());
        }

        // while user hasn't ended session
        while (!isFinished) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                // end session
                isFinished = true;
            } else if (command.equals("list")) {
                // list all tasks
                for (int i = 0; i < list.getSize(); i++) {
                    Task task = list.getTask(i);
                    System.out.println((i + 1) + "." + task);
                }
            } else {
                try {
                    DemureBot.check(command, list);
                } catch (DemureBotException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // close scanner and end session
        scanner.close();
        DemureBot.ui.displayEnd();

        // save task list
        DemureBot.storage.save(filePath, DemureBot.list);
    }
}
