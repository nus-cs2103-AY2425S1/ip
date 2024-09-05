package bimo;
import bimo.command.Command;

/**
 * Represents a chatbot class.
 */
public class Bimo {
    public static final String NAME = "bimo";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Returns an instance of a chatbot.
     *
     * @param filePath File path of file containing list of tasks.
     */
    public Bimo(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadFile());
        } catch (BimoException e) {
            tasks = new TaskList();
        }
    }
    /**
     * Returns a string object for the dialog box of chatbot.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
        } catch (BimoException e) {
            response = e.getMessage();
        }
        return response;
    }

    /**
     * Displays introduction to users.
     *
     * @param name Name of the chatbot.
     */
    public String greetUser(String name) {
        return String.format("Hello! I'm %s.", name)
                + " What can I do for you? \n \n" + getListOfCommands();
    }

    /**
     * Retrieves the list of  commands currently available.
     *
     * @return list of commands.
     */
    private String getListOfCommands() {
        return "Available commands:\n\n"
                + "1. todo <task>\n\n2. deadline <task> /by yyyy-mm-dd\n\n"
                + "3. event <task> /from yyyy-mm-dd /to yyyy-mm-dd\n\n"
                + "4. mark <task number>\n\n5. unmark <task number>\n\n"
                + "6. delete <task number>\n\n7. find <keyword>\n\n"
                + "8. bye\n";
    }
}
