package casper;
import exception.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class CasperBot {
    private enum CommandType {
        CREATE, TASK;
    }
    private enum CreateCommand {
        EVENT, TODO, DEADLINE;
    }

    private enum TaskCommand {
        MARK, UNMARK, DELETE;
    }
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    public CasperBot(String filePath) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }

    public static void main(String[] args) throws CasperBotException {
        new CasperBot("chatbot.txt").run();
    }

    private void run() {
        try {
            this.storage.openFile(this.taskList);this.ui.printLine();
            this.ui.greeting();
            this.ui.printLine();
            echo();
        } catch (CasperBotIOException e) {
            this.ui.showErrorMessage(e.getMessage());
        }
    }

    private void echo() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {
            input = scanner.nextLine();
            String[] inputArray = this.parser.splitInputIntoTwo(input);
            this.ui.printLine();
            try {
                if (inputArray[0].equalsIgnoreCase("list")) {
                    this.ui.displayTaskList(taskList);
                }
                else if (isValidCommand(inputArray[0], CommandType.TASK)) {
                    try {
                        int index = Integer.parseInt(inputArray[1]) - 1;
                        TaskCommand taskCommand = TaskCommand.valueOf(inputArray[0].trim().toUpperCase());
                        Task task = this.taskList.getTask(index);
                        switch (taskCommand) {
                        case MARK:
                            task.markAsDone();
                            this.storage.updateDoneStatus(index, true);
                            this.ui.displayUpdateMessage(Ui.TaskCommand.MARK, task);
                            break;
                        case UNMARK:
                            task.markAsNotDone();
                            this.storage.updateDoneStatus(index, false);
                            this.ui.displayUpdateMessage(Ui.TaskCommand.UNMARK, task);
                            break;
                        case DELETE:
                            this.taskList.deleteTask(task);
                            this.storage.deleteFromFile(index);
                            this.ui.displayUpdateMessage(Ui.TaskCommand.DELETE, task);
                            break;
                        }
                        if (taskCommand == TaskCommand.DELETE) {
                            this.ui.printTaskListLength(this.taskList.getNumberOfTasks());
                        }
                    } catch (NumberFormatException e) {
                        throw new CasperBotNumberFormatException();
                    }
                }
                else if (isValidCommand(inputArray[0], CommandType.CREATE)) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    this.parser.parseBySlash(inputArray[1], hashMap);
                    CreateCommand command = CreateCommand.valueOf(inputArray[0].trim().toUpperCase());
                    try {
                        switch (command) {
                        case TODO:
                            String todoDescription = hashMap.get("description");
                            if (todoDescription.isEmpty()) {
                                throw new CasperBotMissingInputException("description", "ToDo");
                            }
                            ToDo newToDo = new ToDo(todoDescription, false);
                            this.taskList.addTask(newToDo);
                            this.storage.writeToFile(newToDo);
                            this.ui.addTaskMessage(newToDo);
                            break;
                        case DEADLINE:
                            String deadlineDescription = hashMap.get("description");
                            if (deadlineDescription.isEmpty()) {
                                throw new CasperBotMissingInputException("description", "Deadline");
                            }
                            String deadline = hashMap.get("by");
                            if (deadline == null || deadline.isEmpty()) {
                                throw new CasperBotMissingInputException("/by", "Deadline");
                            }
                            LocalDate dateOfDeadline = LocalDate.parse(deadline);
                            Deadline newDeadline = new Deadline(deadlineDescription, false, dateOfDeadline);
                            this.taskList.addTask(newDeadline);
                            this.storage.writeToFile(newDeadline);
                            this.ui.addTaskMessage(newDeadline);
                            break;
                        case EVENT:
                            String eventDescription = hashMap.get("description");
                            if (eventDescription.isEmpty()) {
                                throw new CasperBotMissingInputException("description", "Event");
                            }
                            String start = hashMap.get("from");
                            if (start == null || start.isEmpty()) {
                                throw new CasperBotMissingInputException("/from", "Event");
                            }
                            LocalDate dateOfStart = LocalDate.parse(start);
                            String end = hashMap.get("to");
                            if (end == null || end.isEmpty()) {
                                throw new CasperBotMissingInputException("/to", "Event");
                            }
                            LocalDate dateOfEnd = LocalDate.parse(end);
                            Event newEvent = new Event(eventDescription, false, dateOfStart, dateOfEnd);
                            this.taskList.addTask(newEvent);
                            this.storage.writeToFile(newEvent);
                            this.ui.addTaskMessage(newEvent);
                            break;
                        }
                        this.ui.printTaskListLength(this.taskList.getNumberOfTasks());
                    } catch (DateTimeParseException e) {
                        throw new CasperBotInvalidDateException();
                    }
                } else if (inputArray[0].equalsIgnoreCase("bye")) {
                    this.ui.exit();
                } else {
                    throw new CasperBotInvalidCommandException();
                }
            } catch (CasperBotException e) {
                this.ui.showErrorMessage(e.getMessage());
            } finally {
                this.ui.printLine();
            }
        }
        scanner.close();
    }

    private static boolean isValidCommand(String command, CommandType commandType) {
        try {
            switch (commandType) {
            case CREATE:
                CreateCommand.valueOf(command.trim().toUpperCase());
                break;
            case TASK:
                TaskCommand.valueOf(command.trim().toUpperCase());
                break;
            }
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
