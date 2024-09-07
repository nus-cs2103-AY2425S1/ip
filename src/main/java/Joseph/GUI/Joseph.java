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
    public static void main(String[] args) {
        final String NAME = "Joseph";
        UI ui = new UI();
        Parser parser = new Parser();
        Storage storage = new Storage("./data/joseph.txt");
        TaskList taskList = new TaskList(storage.loadTasks());

        ui.printWelcomeMessage(NAME);

        while (true) {
            String input = ui.readUserInput();
            try {
                Parser.Command command = parser.parseCommand(input);
                if (command == Parser.Command.EXIT) {
                    ui.printExitMessage();
                    storage.saveTasks(taskList.getTasks());
                    ui.close();
                    return;
                } else if (command == Parser.Command.HELP) {
                    ui.printHelpMessage();
                } else {
                    taskList.handleCommand(command, input, parser, ui, storage);
                }
            } catch (UnknownCommandException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        return "Joseph heard: " + input;
    }
}