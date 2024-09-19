package echoa.main;

import echoa.command.Command;
import echoa.exception.EchoaException;

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
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
    }

    /**
     * Removes first word of the input line.
     *
     * @param input Input String.
     * @return String without the first word.
     */
    public static String removeFirstWord(String input) {
        if (input == null || input.trim().isEmpty()) {
            return input;
        }

        String[] parts = input.split("\\s+", 2);

        if (parts.length < 2) {
            return "";
        }

        return parts[1];
    }

    /**
     * Runs the program.
     *
     * @param input Command line input from user.
     */
    public void run(String input) {

        taskList = new TaskList();

        try {
            storage.setUpFile();
            storage.loadInformation(taskList);

            Command command = parser.parseCommand(input, ui, parser, taskList, storage);
            String line = removeFirstWord(input);
            command.execute(line);
            response = command.getMessage();
        } catch (EchoaException e) {
            response = ui.getExceptionMessage(e);
        } catch (Exception e) {
            response = ui.getExceptionMessage(e);
        }
    }
    public String getResponse() {
        return this.response;
    }
}
