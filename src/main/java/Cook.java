import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Cook {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Cook (File file) {
        this.storage = new Storage(file);
        this.tasks = new TaskList();
        this.ui = new Ui();
    }

    public void run() {
        HashMap<String, Integer> validCommands = new HashMap<>();

        validCommands.put("bye", 1);
        validCommands.put("list", 1);
        validCommands.put("mark", 2);
        validCommands.put("unmark", 2);
        validCommands.put("delete", 2);
        validCommands.put("todo", 2);
        validCommands.put("deadline", 4);
        validCommands.put("event", 6);

        this.parser = new Parser(validCommands);

        this.ui.welcome();

        while (true) {
            String input = this.ui.getInput();
            HashMap<String, String> argumentsHashMap;

            try {
                argumentsHashMap = this.parser.readInput(input);
            } catch (InvalidCommandException e) {
                 this.ui.say(e.getMessage());
                 continue;
            }

            String command = argumentsHashMap.get("command");
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                this.ui.say(this.tasks.toString());
            } else if (command.contains("mark") || command.equals("delete")) {
                int taskNo;

                try {
                    taskNo = Integer.parseInt(argumentsHashMap.get(command));
                } catch (NumberFormatException e) {
                    this.ui.say("A task must be selected.");
                    continue;
                }

                boolean toMark = command.equals("mark");
                boolean isSuccessful;

                try {
                    if (command.equals("delete")) {
                        this.tasks.deleteTask(taskNo);
                    } else {
                        isSuccessful = this.tasks.markTask(taskNo, toMark);
                    }
                } catch (IndexOutOfBoundsException e) {
                    this.ui.say("The task is not in the list.");
                    continue;
                }
            }
            else {
                switch (command) {
                case "todo" -> {
                    String taskDesc = argumentsHashMap.get("todo");
                    this.tasks.addTask(new ToDo(taskDesc));
                }
                case "deadline" -> {
                    String taskDesc = argumentsHashMap.get("deadline");
                    LocalDateTime deadlineDateTime;

                    try {
                        deadlineDateTime = LocalDateTime.parse(argumentsHashMap.get("/by"));
                    } catch (DateTimeParseException e) {
                        this.ui.say("Date & time must be in a valid format, e.g. YYYY-MM-DD HH:mm.");
                        continue;
                    } catch (NullPointerException e) {
                        this.ui.say("Deadline command format: deadline [desc] /by [YYYY-MM-DD HH:mm].");
                        continue;
                    }

                    this.tasks.addTask(new Deadline(taskDesc, deadlineDateTime));
                }
                case "event" -> {
                    String taskDesc = argumentsHashMap.get("deadline");
                    LocalDateTime startDateTime;
                    LocalDateTime endDateTime;

                    try {
                        startDateTime = LocalDateTime.parse(argumentsHashMap.get("/from"));
                        endDateTime = LocalDateTime.parse(argumentsHashMap.get("/to"));
                        if (startDateTime.isAfter(endDateTime)) {
                            this.ui.say("The starting date & time cannot be " +
                                    "after the ending date & time");
                        }
                    } catch (DateTimeParseException e) {
                        this.ui.say("Date & time must be in a valid format, e.g. YYYY-MM-DD HH:mm.");
                        continue;
                    } catch (NullPointerException e) {
                        this.ui.say("Event command format: event [desc] " +
                                "/from [YYYY-MM-DD HH:mm] /to [YYYY-MM-DD HH:mm].");
                        continue;
                    }

                    this.tasks.addTask(new Event(taskDesc, startDateTime, endDateTime));
                }
                }
                try {
                    this.storage.createFile();
                    this.storage.writeFile(this.tasks.toString());
                    this.ui.say("File saved.");
                } catch (IOException e) {
                    this.ui.say("File cannot be saved.");
                    continue;
                }
            }
        }
    }

    public static void main(String[] args) {
        new Cook(new File("data", "tasks.txt")).run();
    }
}
