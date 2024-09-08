package bob;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class Bob {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private static List<Task> memory = new ArrayList<Task>();

    public Bob() {
    }

    public Bob(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        tasks = new TaskList(storage.load());
    }

    public String getResponse(String input) {
        String response = "";
        try {
            Command c = parser.parse(input);
            assert c != null : "Command returned by parser shouldn't be null";
            response = c.execute(tasks, storage);
            return response;
        } catch (InvalidCommandException e) {
            return "I don't recognise that command :( Try again.";
        } catch (NumberFormatException e) {
            return "Seems like at least one of the arguments to this command was\n"
                    + "not a number when it should have been."
                    + Bob.HELP_MESSAGE;
        } catch (StringIndexOutOfBoundsException e) {
            return "Seems like the command keyed wasn't appropriately used. You may have\n" +
                    "given insufficient information. Also check that the order in which\n" +
                    "the information was given is correct."
                    + Bob.HELP_MESSAGE;
        } catch (EmptyFieldException e) {
            return "Field(s) may not be blank."
                    + Bob.HELP_MESSAGE;
        } catch (DateTimeParseException e) {
            return "Sorry, I only accept datetime inputs of yyyy-MM-dd HHmm"
                    + Bob.HELP_MESSAGE;
        }
    }

    public String showWelcome() {
        return "Hello! I'm your chatbot, Bob.\n"
                + "How may I assist you?\n"
                + Bob.HELP_MESSAGE;
    }

    private static final String HELP_MESSAGE = "Key in \"I need help.\" for additional help.";

    public void run() {
        ui.showBar();
        ui.show(showWelcome());

        String response;

        while (!(response = ui.readInput()).equals("bye")) {
            String reply = getResponse(response);
            ui.show(reply);
        }
        ui.show("Bye.");
        ui.showBar();
    }

    public static void main(String[] args) {
        Bob bob;
        try {
            bob = new Bob("./data/bob.txt");
        } catch (FileNotFoundException e) {
            bob = new ErrorBob("Seems like I'm missing my memory");
        } catch (IOException e) {
            bob = new ErrorBob("I'm having trouble initialising my memory :(");
        }
        bob.run();
    }
}
