package bob;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

import bob.command.Command;
import bob.exceptions.EmptyArgumentException;
import bob.exceptions.InvalidInputException;
import bob.exceptions.InvalidTaskNumberException;
import bob.exceptions.MissingArgumentException;
import bob.tasks.TaskList;

/**
 * Bob is the ChatBot
 */
public class Bob {

    private final TaskList myTasks;
    private final String filePath;

    /**
     * Constructor for Bob
     * Initializes Bob and retrieves data from the saved file
     *
     * @param filePath File path to the file of the saved data
     */
    public Bob(String filePath) {
        this.myTasks = new TaskList();
        this.filePath = filePath;
        this.initialize();
    }

    /**
     * Reads the data from the saved file stored at the file path and updates the TaskList
     */
    private void initialize() {
        Storage.readData(this.myTasks, this.filePath);
    }

    public static void main(String[] args) {

        Bob myBot = new Bob("./userdata.txt");
        myBot.startChatBot();

    }

    /**
     * Starts the chatbot
     */
    public void startChatBot() {
        UI.printGreetings();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userCommand = UI.readCommand();
                UI.printLine();
                Command command = Parser.parseCommand(userCommand);
                command.execute(myTasks);
                isRunning = command.isRunning();
                Storage.writeData(myTasks, this.filePath);
            } catch (EmptyArgumentException | MissingArgumentException
                     | InvalidTaskNumberException e) {
                UI.printMessage(e.getMessage());
            } catch (DateTimeParseException e) {
                UI.printMessage("Invalid date/time has been entered. "
                        + "Please key in with a DD/MM/YYYY format or DD/MM/YYYY HHMM format");
            } catch (InvalidInputException | InputMismatchException e) {
                UI.printMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } finally {
                UI.printLine();
            }
        }
    }

    public String getResponse(String input) {
        try {
            Storage.writeData(myTasks, this.filePath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            System.setOut(ps);
            Command command = Parser.parseCommand(input);
            command.execute(myTasks);
            System.out.flush();
            return baos.toString();
        } catch (EmptyArgumentException | MissingArgumentException
                | InvalidTaskNumberException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Invalid date/time has been entered. "
                    + "Please key in with a DD/MM/YYYY format or DD/MM/YYYY HHMM format";
        } catch (InvalidInputException | InputMismatchException e) {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}

