package mittens;

import mittens.commands.Command;
import mittens.parser.BadInputException;
import mittens.parser.CommandParser;
import mittens.storage.Storage;
import mittens.storage.StorageFileException;
import mittens.task.TaskList;
import mittens.ui.TextUi;
import mittens.ui.Ui;

/**
 * Represents the main class where the program is initialized and the main loop is run.
 */
public class Mittens {
    
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    
    /**
     * Creates a new Mittens object with the specified storage file path.
     * 
     * @param storageFilePath The file path to the storage file
     */
    public Mittens(String storageFilePath) {
        this.ui = new TextUi();
        this.storage = new Storage(storageFilePath);
        
        TaskList temp;
        try {
            temp = this.storage.load();
        } catch (StorageFileException e) {
            ui.showErrorMessage(e);
            ui.showRegularMessage("Would you like to continue with a new list instead? (y/n)");
            if (ui.getUserInput().equals("y")) {
                temp = new TaskList();
            } else {
                throw new RuntimeException("User chose to exit the program");
            }
        } catch (Exception e) {
            InitializationException newException = new InitializationException(e.getMessage());
            ui.showErrorMessage(newException);
            throw new RuntimeException("Error occurred during initialization");
        }
        
        this.taskList = temp;
    }

    /**
     * Runs the main loop of the program which accepts user input.
     */
    public void run() {
        CommandParser commandParser = new CommandParser();
        
        ui.showGreetingMessage();

        while (true) {
            String input = ui.getUserInput();
            
            try {
                Command command = commandParser.parse(input);
                command.execute(this.taskList, this.ui, this.storage);
                if (command.isExit()) {
                    break;
                }
            } catch (BadInputException e) {
                ui.showErrorMessage(e);
            }
        }
    }
    
    public static void main(String[] args) {
        Mittens mittens = new Mittens("data/data.txt");
        mittens.run();
    }
}
