package Joseph.GUI;

import Joseph.Exceptions.UnknownCommandException;
import Joseph.Tasks.TaskList;
import Joseph.UI;
import Joseph.Parser;
import Joseph.Storage;

/**
 *  The main class of the chatbot.
 */
public class Joseph {
    private final Parser parser;
    private final Storage storage;
    private final TaskList taskList;
    private final UI ui;

    public Joseph() {
        this.ui = new UI();
        this.parser = new Parser();
        this.storage = new Storage("./data/joseph.txt");
        this.taskList = new TaskList(storage.loadTasks());
    }

    public String getResponse(String input) {
        try {
            Parser.Command command = parser.parseCommand(input);
            if (command == Parser.Command.EXIT) {

                storage.saveTasks(taskList.getTasks());
                return ui.getExitMessage();
            } else if (command == Parser.Command.HELP) {
                return ui.getHelpMessage();
            } else {
                return taskList.handleCommand(command, input, parser, ui, storage);
            }
        } catch (UnknownCommandException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }
}