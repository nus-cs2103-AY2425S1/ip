package katheryne;

import java.io.FileNotFoundException;

import katheryne.exceptions.InvalidInputException;
import katheryne.exceptions.MissingInformationException;



/**
 * Katheryne Class is the main class that will run based on different commands
 * given by users. It has attribute of storage for loading and saving of tasks;
 * taskList to manage the tasks on record; and ui to handle interaction with users.
 */
public class Katheryne {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    /**
     * Constructor for Katheryne class
     */
    public Katheryne(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = storage.load();
        } catch (FileNotFoundException e) {
            System.out.println(Message.MESSAGE_LOADING_ERROR + e.getMessage());
            taskList = new TaskList();
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Starts the interaction with user
     */
    public static void main(String[] args) throws MissingInformationException {
        Katheryne k = new Katheryne("./data/Katheryne.txt");
        k.run();
    }

    /**
     * Handles user input and flow of output
     */
    public void run() {
        boolean isFinish = false;
        while (!isFinish) {
            String str = ui.getLine();
            try {
                handleCommand(str);
                if (ui.getCommand(str).equals("bye")) {
                    isFinish = true;
                }
            } catch (InvalidInputException | MissingInformationException e) {
                continue;
            }
        }
    }

    /**
     * Handle the response generation of Katheryne to the user input.
     *
     * @param str The user input.
     * @return The response to the user input.
     */

    private String handleCommand(String str) throws MissingInformationException, InvalidInputException {
        Command c = new Command(ui, taskList);
        String commandWord = ui.getCommand(str);

        String response;
        switch (commandWord) {
        case "list" -> response = c.executeList();
        case "mark" -> {
            response = c.executeMark(str);
            storage.save(taskList);
        }
        case "unmark" -> {
            response = c.executeUnmark(str);
            storage.save(taskList);
        }
        case "todo" -> {
            response = c.executeAddToDo(str);
            storage.save(taskList);
        }
        case "event" -> {
            response = c.executeAddEvent(str);
            storage.save(taskList);
        }
        case "deadline" -> {
            response = c.executeAddDeadline(str);
            storage.save(taskList);
        }
        case "bye" -> response = ui.getBye();
        case "delete" -> {
            response = c.executeDelete(str);
            storage.save(taskList);
        }
        case "find" -> response = c.executeFind(str);
        default -> {
            String msg = "I'm sorry, Katheryne is unable to comprehend your request.";
            throw new InvalidInputException(msg);
        }
        }
        return response;
    }

    /**
     * Returns the response to the user input.
     *
     * @param input The user input.
     * @return The response of the knight to the user input.
     */

    public String getResponse(String input) throws MissingInformationException, InvalidInputException {
        try {
            return handleCommand(input);
        } catch (InvalidInputException e) {
            return String.format("Katheryne: %s", e.getMessage());
        } catch (MissingInformationException e) {
            return String.format("Katheryne: %s", e.getMessage());
        }
    }
}




