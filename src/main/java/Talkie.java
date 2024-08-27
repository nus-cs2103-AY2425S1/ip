import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Talkie {

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Talkie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(this.storage.loadData());
        } catch (TalkieException e) {
            ui.showTalkieException(e);
            tasks = new TaskList();
        }
    }

    // Creates Deadline Task
    public void createDeadline(String input) throws TalkieMissingArgumentException {
        String[] parts = input.split(" ", 2); // Split into type and the rest of the input

        try {
            if (parts.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

                String details = parts[1]; // rest of the input (eg. from, to details)
                String[] deadlineParts = details.split("/by ");
                String description = deadlineParts[0].trim();
                String by = deadlineParts[1].trim();

                LocalDateTime time = LocalDateTime.parse(by, formatter);

                Task newDeadline = new Deadline(description, time);
                this.tasks.addTask(newDeadline);
                ui.addMessage(newDeadline, this.tasks.size());
            } else {
                throw new TalkieMissingArgumentException(parts[0],
                        "The 'description' and 'by' of deadline cannot be empty.");
            }
        } catch (DateTimeParseException e) {
            ui.wrongDateTimeFormatMessage();
        }
    }

    // Creates Event Task
    public void createEvent(String input) throws TalkieMissingArgumentException {
        String[] parts = input.split(" ", 2); // Split into type and the rest of the input

        try {
            if (parts.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                String details = parts[1]; // rest of the input (eg. from, to details)
                String[] eventParts = details.split("/from | /to ");

                String description = eventParts[0].trim();
                String from = eventParts[1].trim();
                String to = eventParts[2].trim();

                LocalDateTime startTime = LocalDateTime.parse(from, formatter);
                LocalDateTime endTime = LocalDateTime.parse(to, formatter);

                if (startTime.isAfter(endTime)) {
                    System.out.println("The end time must be after the start time!");
                    return;
                }

                Task newEvent = new Event(description, startTime, endTime);
                this.tasks.addTask(newEvent);
                ui.addMessage(newEvent, this.tasks.size());
            } else {
                throw new TalkieMissingArgumentException(parts[0],
                        "The 'description', 'from' and 'to' of event cannot be empty.");
            }
        } catch (DateTimeParseException e) {
           ui.wrongDateTimeFormatMessage();
        }

    }

    // Creates ToDo Task
    public void createToDo(String input) throws TalkieMissingArgumentException{
        String[] parts = input.split(" ", 2); // Split into type and the rest of the input

        if (parts.length == 2) {
            String details = parts[1]; // rest of the input (eg. from, to details)
            Task newToDo = new ToDo(details.trim());
            this.tasks.addTask(newToDo);
            ui.addMessage(newToDo, this.tasks.size());
        } else {
            throw new TalkieMissingArgumentException(parts[0], "The 'description' of todo cannot be empty.");
        }
    }

    // Deletes a task
    public void deleteTask(String input)
            throws TalkieMissingArgumentException, TalkieNoTaskFoundException, TalkieInvalidArgumentException{
        String[] temp = input.split(" ");

        // Check if user included an argument
        if (temp.length == 1) {
            throw new TalkieMissingArgumentException(temp[0], "The 'delete' command requires an integer as argument");

        // Check if user included the correct int argument
        } else if (Talkie.isInteger(temp[1])) {
            int index = Integer.parseInt(input.split(" ")[1]);

            // Check if the task is in the list
            if (index <= this.tasks.size()) {
                Task task = this.tasks.deleteTask(index);
                ui.deleteMessage(task, tasks.size());
            } else {
                throw new TalkieNoTaskFoundException();
            }
        } else {
            throw new TalkieInvalidArgumentException(temp[0], "The 'delete' command requires an integer as argument");
        }
    }


    // Marks a task
    public void markTask(String input)
            throws TalkieInvalidArgumentException, TalkieMissingArgumentException, TalkieNoTaskFoundException {
        String[] temp = input.split(" ");

        // Check if the user included an argument
        if (temp.length == 1) {
            throw new TalkieMissingArgumentException(temp[0], "The 'mark' command requires an integer as argument");

        // Check if user included the correct int argument
        } else if (Talkie.isInteger(temp[1])) {
            int index = Integer.parseInt(input.split(" ")[1]);

            // Check if the task is in the list
            if (index <= this.tasks.size()) {
                Task task = this.tasks.getTask(index);
                task.markAsDone();
                ui.markMessage(task);
            } else {
                throw new TalkieNoTaskFoundException();
            }

        } else {
            throw new TalkieInvalidArgumentException(temp[0], "The 'mark' command requires an integer as argument");
        }
    }

    // Unmarks a Task
    public void unmarkTask(String input)
            throws TalkieInvalidArgumentException, TalkieMissingArgumentException, TalkieNoTaskFoundException {
        String[] temp = input.split(" ");

        // Check if the user included an argument
        if (temp.length == 1) {
            throw new TalkieMissingArgumentException(temp[0], "The 'unmark' command requires an integer as argument");

        // Check if the user included the correct int argument
        } else if (Talkie.isInteger(temp[1])) {
            int index = Integer.parseInt(input.split(" ")[1]);

            // Check if the task index is valid in the task list
            if (index <= this.tasks.size()) {
                Task task = this.tasks.getTask(index);
                task.markAsNotDone();
                ui.unMarkMessage(task);
            } else {
                throw new TalkieNoTaskFoundException();
            }

        } else {
            throw new TalkieInvalidArgumentException(temp[0], "The 'unmark' command requires an integer as argument");
        }
    }

    // Check if the input string is a number (Helper method for unmark and mark)
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Exits the program
    public void exit() {
        try {
            this.storage.saveData(this.tasks);
            ui.byeMessage();
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong when saving the data!");
        }
    }

    // Runs the main program
    public void runTalkie() {

        ui.welcomeMessage();

        boolean isFinished = false;
        while (!isFinished) {

            try {
                String input = this.ui.readCommand();
                if (input.equalsIgnoreCase("bye")) {
                    this.exit();
                    isFinished = true;

                } else if (input.equalsIgnoreCase("list")) {
                    ui.listTasks(this.tasks);

                } else if (input.startsWith("delete")) {
                    this.deleteTask(input);

                } else if (input.startsWith("mark")) {
                    this.markTask(input);

                } else if (input.startsWith("unmark")) {
                    this.unmarkTask(input);

                } else if (input.startsWith("todo")) {
                    this.createToDo(input);

                } else if (input.startsWith("deadline")) {
                    this.createDeadline(input);

                } else if (input.startsWith("event")) {
                    this.createEvent(input);

                } else {
                    throw new TalkieUnknownCommandException(input);
                }
            } catch (TalkieException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        // Start of Talkie
        new Talkie("./data/Talkie.txt").runTalkie();
    }
}
