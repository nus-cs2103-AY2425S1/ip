package cook;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import exceptions.InvalidCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;

/**
 * Cook class to store main logic and program.
 */
public class Cook {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Cook class.
     */
    public Cook(File file) {
        this.storage = new Storage(file);
        this.tasks = new TaskList();
        this.ui = new Ui();

        HashMap<String, Integer> validCommandArgs = new HashMap<>();

        validCommandArgs.put("bye", 1);
        validCommandArgs.put("list", 1);
        validCommandArgs.put("mark", 2);
        validCommandArgs.put("unmark", 2);
        validCommandArgs.put("delete", 2);
        validCommandArgs.put("find", 2);
        validCommandArgs.put("todo", 2);
        validCommandArgs.put("deadline", 4);
        validCommandArgs.put("event", 6);

        this.parser = new Parser(validCommandArgs);
    }

    /**
     * Runs main logic.
     */
    public String getResponse(String input) {
        HashMap<String, String> argumentsHashMap;

        try {
            argumentsHashMap = this.parser.readInput(input);
        } catch (InvalidCommandException e) {
            return e.getMessage();
        }

        String command = argumentsHashMap.get("command");
        if (command.equals("bye")) {
            return "bye";
        } else if (command.equals("list")) {
            return this.tasks.toString();
        } else if (command.contains("mark") || command.equals("delete")) {
            int taskNumber;

            try {
                taskNumber = Integer.parseInt(argumentsHashMap.get(command));
            } catch (NumberFormatException e) {
                return "A task must be selected.";
            }

            boolean isMarking = command.equals("mark");
            boolean isSuccessful;

            try {
                if (command.equals("delete")) {
                    this.tasks.deleteTask(taskNumber);
                    return "The task has been deleted from the list.";
                } else {
                    // do nothing
                }
            } catch (IndexOutOfBoundsException e) {
                return "The task is not in the list.";
            }
            return "The task has been " + command + "ed.";
        } else if (command.equals("find")) {
            String keyword = argumentsHashMap.get("find");
            return this.tasks.findTask(keyword).toString();
        } else {
            if (command.equals("todo")) {
                String taskDesc = argumentsHashMap.get("todo");
                this.tasks.addTask(new ToDo(taskDesc));
            } else if (command.equals("deadline")) {
                String taskDesc = argumentsHashMap.get("deadline");
                LocalDateTime deadlineDateTime;

                try {
                    deadlineDateTime = LocalDateTime.parse(argumentsHashMap.get("/by"));
                } catch (DateTimeParseException e) {
                    return "Date & time must be in a valid format, e.g. YYYY-MM-DD HH:mm.";
                } catch (NullPointerException e) {
                    return "Tasks.Deadline command format: deadline [desc] /by [YYYY-MM-DD HH:mm].";
                }

                this.tasks.addTask(new Deadline(taskDesc, deadlineDateTime));
            } else if (command.equals("event")) {
                String taskDesc = argumentsHashMap.get("deadline");
                LocalDateTime startDateTime;
                LocalDateTime endDateTime;

                try {
                    startDateTime = LocalDateTime.parse(argumentsHashMap.get("/from"));
                    endDateTime = LocalDateTime.parse(argumentsHashMap.get("/to"));
                    if (startDateTime.isAfter(endDateTime)) {
                        return "The starting date & time cannot be "
                                + "after the ending date & time";
                    }
                } catch (DateTimeParseException e) {
                    return "Date & time must be in a valid format, e.g. YYYY-MM-DD HH:mm.";
                } catch (NullPointerException e) {
                    return "Tasks.Event command format: event [desc] "
                            + "/from [YYYY-MM-DD HH:mm] /to [YYYY-MM-DD HH:mm].";
                }

                this.tasks.addTask(new Event(taskDesc, startDateTime, endDateTime));
            }
            try {
                this.storage.createFile();
                this.storage.writeFile(this.tasks.toString());
                return "File saved.";
            } catch (IOException e) {
                return "File cannot be saved.";
            }
        }
    }

    /**
     * Prints response of Cook given an input from the user (CLI)
     */
    public void run() {
        String response = "";
        this.ui.welcome();
        while (true) {
            String input = this.ui.getInput();
            response = this.getResponse(input);
            if (response.equals("bye")) {
                break;
            } else {
                this.ui.say(response);
            }
        }
    }

    /**
     * Runs main.
     */
    public static void main(String[] args) {
        new Cook(new File("data", "tasks.txt")).run();
    }
}
