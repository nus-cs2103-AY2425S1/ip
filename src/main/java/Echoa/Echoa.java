package Echoa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Echoa is a class that simulates a task checker.
 */

public class Echoa {

    private final Ui ui;
    public final Storage storage;
    private final Parser parser;
    private final TaskList taskList;

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
    public void start() throws IOException {

        storage.setUpFile();
        storage.loadInformation(taskList);

        Scanner scanner = new Scanner(System.in);

        ui.greetUserStart();

        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String command = parser.parseCommand(line);
                String task = line.replace(command, "").trim();

                switch (command) {
                    case "list":
                        taskList.listTasks();
                        break;
                    case "mark":
                        int markIndex = parser.parseIndex(line);
                        taskList.markTaskAsDone(markIndex);
                        ui.printMarkTaskMessage(taskList, markIndex);
                        storage.handleChange(taskList);
                        break;
                    case "unmark":
                        int unmarkIndex = parser.parseIndex(line);
                        taskList.markTaskAsUndone(unmarkIndex);
                        ui.printUnmarkTaskMessage(taskList, unmarkIndex);
                        storage.handleChange(taskList);
                        break;
                    case "delete":
                        int deleteIndex = parser.parseIndex(line);
                        taskList.deleteTask(deleteIndex);
                        ui.printDeleteTaskMessage(taskList, deleteIndex);
                        storage.handleChange(taskList);
                        break;
                    case "todo":
                        Object[] todo = parser.parseToDoTask(task);
                        String todoDescription = (String) todo[0];
                        taskList.addTask(new ToDo(todoDescription));
                        ui.printAddTaskMessage(taskList);
                        storage.handleChange(taskList);
                        break;
                    case "deadline":
                        Object[] deadline = parser.parseDeadlineTask(task);
                        String deadlineDescription = (String) deadline[0];
                        LocalDateTime dateAndTime = (LocalDateTime) deadline[1];
                        taskList.addTask(new Deadline(deadlineDescription, dateAndTime));
                        ui.printAddTaskMessage(taskList);
                        storage.handleChange(taskList);
                        break;
                    case "event":
                        Object[] event = parser.parseEventTask(task);
                        String eventDescription = (String) event[0];
                        LocalDateTime startDateAndTime = (LocalDateTime) event[1];
                        LocalDateTime endDateAndTime = (LocalDateTime) event[2];
                        taskList.addTask(new Event(eventDescription, startDateAndTime, endDateAndTime));
                        ui.printAddTaskMessage(taskList);
                        storage.handleChange(taskList);
                        break;
                    case "bye":
                        ui.greetUserEnd();
                        break;
                    case "":
                        throw new InvalidInstructionException("Blank");
                    default:
                        throw new InvalidInstructionException(command);
                }
            }
        } catch (InvalidDeadlineContentException e) {
            ui.printInvalidDeadlineContentException(e);
        } catch (InvalidEventContentException e) {
            ui.printInvalidEventContentException(e);
        } catch (InvalidInstructionException e) {
            ui.printInvalidInstructionExceptionMessage(e);
            ui.askUserToTryAgain();
        } catch (ListOutOfBoundsException e) {
            ui.printListOutOfBoundsException(e);
        }
        finally {
            scanner.close();
        }
    }

    /**
     * The main method is the entry point to the application.
     * It catches any file related exception and handles them.
     *
     * @param args Arguments inputted into the command line interface.
     */
    public static void main(String[] args) {

        String filePath = "./data/echoa.txt";
        Echoa echoa = new Echoa(filePath);

        try {
            echoa.start();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("An error has occurred when writing to the file");
        } catch (DateTimeParseException e) {
            System.out.println("Date or time is inputted in the wrong format!");
        }
    }
}
