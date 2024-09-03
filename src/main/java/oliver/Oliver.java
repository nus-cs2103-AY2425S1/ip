package oliver;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Oliver {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isRunning = true;

    public Oliver(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.storage.initialise();
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            System.out.println("Error occurred when reading data into list.");
            this.tasks = new TaskList();
        }
    }

    public void run() {
        System.out.println(ui.showWelcome());
        while (this.isRunning) {
            String input = ui.readInput();
            System.out.println(getResponse(input));
        }
    }

    public String getResponse(String input) {
        String command = Parser.parseCommand(input);
        if (input.equalsIgnoreCase("bye")) {
            storage.writeToFile(this.tasks);
            ui.close();
            this.isRunning = false;
            return ui.showBye();
        } else if (input.equalsIgnoreCase("list")) {
            return ui.showList(this.tasks);
        } else if (command.equalsIgnoreCase("mark")) {
            return handleMark(input);
        } else if (command.equalsIgnoreCase("unmark")) {
            return handleUnmark(input);
        } else if (command.equalsIgnoreCase("todo")) {
            return handleTodo(input);
        } else if (command.equalsIgnoreCase("deadline")) {
            return handleDeadline(input);
        } else if (command.equalsIgnoreCase("event")) {
            return handleEvent(input);
        } else if (command.equalsIgnoreCase("delete")) {
            return handleDelete(input);
        } else if (command.equalsIgnoreCase("find")) {
            return ui.showSearch(this.tasks, Parser.parseArgs(input));
        } else {
            return "Invalid command. Command was not recognised.";
        }
    }

    public static void main(String[] args) {
        new Oliver("./data/oliver.txt").run();
    }

    /**
     * Marks a task in the list as done.
     *
     * @param input the user input provided
     * @return string representation of Oliver's response
     */
    private String handleMark(String input) {
        try {
            int index = Integer.parseInt(Parser.parseArgs(input)) - 1;
            if (index > tasks.getSize() - 1 || index < 0) {
                return ui.showOutOfRangeError();
            } else {
                return ui.showMarked(tasks, index);
            }
        } catch (IndexOutOfBoundsException e) {
            return ui.showMissingArgsError();
        } catch (NumberFormatException e) {
            return ui.showInvalidArgsError();
        }
    }

    /**
     * Marks a task in the list as not done.
     *
     * @param input the user input provided
     * @return string representation of Oliver's response
     */
    private String handleUnmark(String input) {
        try {
            int index = Integer.parseInt(Parser.parseArgs(input)) - 1;
            if (index > tasks.getSize() - 1 || index < 0) {
                return ui.showOutOfRangeError();
            } else {
                return ui.showUnmarked(tasks, index);
            }
        } catch (IndexOutOfBoundsException e) {
            return ui.showMissingArgsError();
        } catch (NumberFormatException e) {
            return ui.showInvalidArgsError();
        }
    }

    /**
     * Adds a todo task to the list.
     *
     * @param input the user input provided
     * @return string representation of Oliver's response
     */
    private String handleTodo(String input) {
        try {
            ToDo t = new ToDo(Parser.parseArgs(input));
            tasks.add(t);
            return ui.showAdd(t, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            return ui.showMissingArgsError();
        }
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param input the user input provided
     * @return string representation of Oliver's response
     */
    private String handleDeadline(String input) { // Date is required, time is optional
        try {
            String[] parts = input.split("/by ");
            String[] dateAndTime = parts[1].split(" ");

            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
            // Time set to default if no time is provided in the input
            String timeStr = dateAndTime.length == 1 ? "0000" : dateAndTime[1];
            LocalDate date = LocalDate.parse(dateAndTime[0]);
            LocalTime time = LocalTime.parse(timeStr, timeFormatter);
            LocalDateTime dateTime = LocalDateTime.of(date, time);

            String action = parts[0].trim();
            Deadline d = new Deadline(action.split(" ", 2)[1], dateTime);
            tasks.add(d);
            return ui.showAdd(d, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            return ui.showMissingArgsError();
        } catch (DateTimeParseException e) {
            return "Invalid date or time. Please enter the date and time in the following format: " +
                    "YYYY-MM-DD HHmm\nNote that date is required but time is optional.";
        }
    }

    /**
     * Adds an event task to the list.
     *
     * @param input the user input provided
     * @return string representation of Oliver's response
     */
    private String handleEvent(String input) { // Both date and time are required
        try {
            String[] parts = input.split("/from |/to ");
            String action = parts[0].trim();
            String startDateAndTime = parts[1].trim();
            String endDateAndTime = parts[2].trim();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime start = LocalDateTime.parse(startDateAndTime, dateFormatter);
            LocalDateTime end = LocalDateTime.parse(endDateAndTime, dateFormatter);

            Event e = new Event(action.split(" ", 2)[1], start, end);
            tasks.add(e);
            return ui.showAdd(e, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            return ui.showMissingArgsError();
        } catch (DateTimeParseException e) {
            return "Invalid date or time. Please enter the date and time in the following format: YYYY-MM-DD HHmm";
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param input the user input provided
     * @return string representation of Oliver's response
     */
    private String handleDelete(String input) {
        try {
            int taskNum = Integer.parseInt(Parser.parseArgs(input));
            int index = taskNum - 1;
            if (index > tasks.getSize() - 1 || index < 0) {
                return ui.showOutOfRangeError();
            } else {
                Task removedTask = tasks.get(index);
                tasks.delete(taskNum);
                return ui.showDelete(removedTask, tasks.getSize());
            }
        } catch (IndexOutOfBoundsException e) {
            return ui.showMissingArgsError();
        } catch (NumberFormatException e) {
            return ui.showInvalidArgsError();
        }
    }
}
