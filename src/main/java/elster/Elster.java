package elster;

import java.nio.file.Path;

import elster.commands.*;

/**
 * Elster class that handles the logic for processing user inputs.
 */
public class Elster {
    private TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructor for Elster class. Initialises the Ui, Storage and Tasklist components.
     *
     * @param filePath file path of the save file.
     */
    public Elster(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            taskList = new TaskList();
            storage.loadFromFile(this.taskList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Method called when the GUI is initialised.
     * @return welcome message.
     */
    public String guiInitiliasation() {
        return ui.welcomeMessage();
    }

    /**
     * Run function of the Elster instance, handles the loading of the save file from storage as
     * well as general logic of the chatbot.
     */
    public String parse(String input) {
        input = input.strip().toLowerCase();
        Command command = null;
        if (input.startsWith("bye")) {
            command = ByeCommand.of(ui, taskList, storage, input);

        } else if (input.startsWith("list")) {
            command = ListCommand.of(ui, taskList, storage, input);

        } else if (input.startsWith("deadline")) {
            command = DeadlineCommand.of(ui, taskList, storage, input);

        } else if (input.startsWith("event")) {
            command = EventCommand.of(ui, taskList, storage, input);

        } else if (input.startsWith("delete")) {
            command = DeleteCommand.of(ui, taskList, storage, input);

        } else if (input.startsWith("todo")) {
            command = TodoCommand.of(ui, taskList, storage, input);

        } else if (input.startsWith("mark")) {
            command = MarkCommand.of(ui, taskList, storage, input);

        } else if (input.startsWith("unmark")) {
            command = UnmarkCommand.of(ui, taskList, storage, input);

        } else if (input.startsWith("find")) {
            command = FindCommand.of(ui, taskList, storage, input);

        } else if (input.startsWith("tag")) {
            command = TagCommand.of(ui, taskList, storage, input);

        } else if (input.startsWith("untag")) {
            command = UntagCommand.of(ui, taskList, storage, input);
        }
        if (command != null) {
            return command.execute();
        } else {
            return ui.nonsenseErrorMessage();
        }
    }
}
