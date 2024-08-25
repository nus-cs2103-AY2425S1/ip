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

    public Oliver(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.storage.initialise();
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            System.out.println("\tError occurred when reading data into list.");
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            String input = ui.readInput();
            String command = Parser.parseCommand(input);

            if (input.equalsIgnoreCase("bye")) {
                storage.writeToFile(this.tasks);
                ui.showBye();
                ui.close();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                ui.showList(this.tasks);
            } else if (command.equalsIgnoreCase("mark")) {
                handleMark(input);
            } else if (command.equalsIgnoreCase("unmark")) {
                handleUnmark(input);
            } else if (command.equalsIgnoreCase("todo")) {
                handleTodo(input);
            } else if (command.equalsIgnoreCase("deadline")) {
                handleDeadline(input);
            } else if (command.equalsIgnoreCase("event")) {
                handleEvent(input);
            } else if (command.equalsIgnoreCase("delete")) {
                handleDelete(input);
            } else if (command.equalsIgnoreCase("find")) {
                ui.showSearch(this.tasks, Parser.parseArgs(input));
            } else {
                System.out.println("\tInvalid command. Command was not recognised.");
            }
        }
    }

    public static void main(String[] args) {
        new Oliver("./data/oliver.txt").run();
    }

    /**
     * Marks a task in the list as done.
     *
     * @param input the user input provided
     */
    private void handleMark(String input) {
        try {
            int index = Integer.parseInt(Parser.parseArgs(input)) - 1;
            if (index > tasks.getSize() - 1 || index < 0) {
                ui.showOutOfRangeError();
            } else {
                ui.showMarked(tasks, index);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showMissingArgsError();
        } catch (NumberFormatException e) {
            ui.showInvalidArgsError();
        }
    }

    /**
     * Marks a task in the list as not done.
     *
     * @param input the user input provided
     */
    private void handleUnmark(String input) {
        try {
            int index = Integer.parseInt(Parser.parseArgs(input)) - 1;
            if (index > tasks.getSize() - 1 || index < 0) {
                ui.showOutOfRangeError();
            } else {
                ui.showUnmarked(tasks, index);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showMissingArgsError();
        } catch (NumberFormatException e) {
            ui.showInvalidArgsError();
        }
    }

    /**
     * Adds a todo task to the list.
     *
     * @param input the user input provided
     */
    private void handleTodo(String input) {
        try {
            ToDo t = new ToDo(Parser.parseArgs(input));
            tasks.add(t);
            ui.showAdd(t, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            ui.showMissingArgsError();
        }
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param input the user input provided
     */
    private void handleDeadline(String input) { // Date is required, time is optional
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
            ui.showAdd(d, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            ui.showMissingArgsError();
        } catch (DateTimeParseException e) {
            System.out.println("\tInvalid date or time. Please enter the date and time in the following format: YYYY-MM-DD HHmm");
            System.out.println("\tNote that date is required but time is optional.");
        }
    }

    /**
     * Adds an event task to the list.
     *
     * @param input the user input provided
     */
    private void handleEvent(String input) { // Both date and time are required
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
            ui.showAdd(e, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            ui.showMissingArgsError();
        } catch (DateTimeParseException e) {
            System.out.println("\tInvalid date or time. Please enter the date and time in the following format: YYYY-MM-DD HHmm" + e);
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param input the user input provided
     */
    private void handleDelete(String input) {
        try {
            int index = Integer.parseInt(Parser.parseArgs(input)) - 1;
            if (index > tasks.getSize() - 1 || index < 0) {
                ui.showOutOfRangeError();
            } else {
                Task removedTask = tasks.get(index);
                tasks.delete(index);
                ui.showDelete(removedTask, tasks.getSize());
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showMissingArgsError();
        } catch (NumberFormatException e) {
            ui.showInvalidArgsError();
        }
    }
}
