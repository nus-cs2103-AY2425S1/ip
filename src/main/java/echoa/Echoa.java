package echoa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Echoa is a class that simulates a task checker.
 */

public class Echoa {

    private final Ui ui;
    public final Storage storage;
    private final Parser parser;
    private TaskList taskList;
    boolean isStarted = false;

    private String response;

    Echoa(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.taskList = new TaskList();
    }

    /**
     * The method starts up a scanner and allows inputs from the command line by the user.
     * It also handles any exceptions and errors thrown within the program
     *
     */
    public void start(String input) {
        if (!isStarted) {
            response = "Hello, I'm Echoa.\n" +
                       "How can I help you today?\n";
            isStarted = true;
            return;
        }

        taskList = new TaskList();

        try {
            storage.setUpFile();
            storage.loadInformation(taskList);
        } catch (FileNotFoundException e) {
            response = "File not found.\n";
        }

        response = ui.getStartMessage();

        try {
            String command = parser.parseCommand(input);
            String task = input.replace(command, "").trim();

            switch (command) {
            case "list":
                response = ui.getListOfTasksMessage(taskList);
                break;
            case "find":
                TaskList tasks = parser.parseFindTask(taskList, task);
                response = ui.getListOfTasksMessage(tasks);
                break;
            case "mark":
                int markIndex = parser.parseIndex(task);
                taskList.markTaskAsDone(markIndex);
                response = ui.getMarkTaskMessage(taskList, markIndex);
                storage.handleChange(taskList);
                break;
            case "unmark":
                int unmarkIndex = parser.parseIndex(task);
                taskList.markTaskAsUndone(unmarkIndex);
                response = ui.getUnmarkTaskMessage(taskList, unmarkIndex);
                storage.handleChange(taskList);
                break;
            case "delete":
                int deleteIndex = parser.parseIndex(task);
                response = ui.getDeleteTaskMessage(taskList, deleteIndex);
                taskList.deleteTask(deleteIndex);
                storage.handleChange(taskList);
                break;
            case "todo":
                Object[] todo = parser.parseToDoTask(task);
                String todoDescription = (String) todo[0];
                taskList.addTask(new ToDo(todoDescription));
                response = ui.getAddTaskMessage(taskList);
                storage.handleChange(taskList);
                break;
            case "deadline":
                Object[] deadline = parser.parseDeadlineTask(task);
                String deadlineDescription = (String) deadline[0];
                LocalDateTime dateAndTime = (LocalDateTime) deadline[1];
                taskList.addTask(new Deadline(deadlineDescription, dateAndTime));
                response = ui.getAddTaskMessage(taskList);
                storage.handleChange(taskList);
                break;
            case "event":
                Object[] event = parser.parseEventTask(task);
                String eventDescription = (String) event[0];
                LocalDateTime startDateAndTime = (LocalDateTime) event[1];
                LocalDateTime endDateAndTime = (LocalDateTime) event[2];
                taskList.addTask(new Event(eventDescription, startDateAndTime, endDateAndTime));
                response = ui.getAddTaskMessage(taskList);
                storage.handleChange(taskList);
                break;
            case "bye":
                response = ui.getEndMessage();
                break;
            case "":
                throw new InvalidInstructionException("Blank");
            default:
                throw new InvalidInstructionException(command);
            }
        }
        catch (IOException e) {
            response = "An error has occurred to the IO.";
        } catch (EchoaException e) {
            response = ui.getExceptionMessage(e);
        }
    }

    public String getResponse() {
        return this.response;
    }
}
