import java.util.Scanner;


public class Mittens {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    
    public Mittens(String storageFilePath) {
        this.ui = new TextUi();
        this.storage = new Storage(storageFilePath);
        this.taskList = new TaskList();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        CommandParser commandParser = new CommandParser();
        
        try {
            this.taskList = this.storage.load();
        } catch (StorageFileException e) {
            e.echo();
            
            System.out.print("Would you like to continue with a new list instead? (y/n)\n> ");
            String input = scanner.nextLine();
            if (input.equals("y")) {
                this.taskList = new TaskList();
            } else {
                return;
            }
        } catch (Exception e) {
            InitializationException newException = new InitializationException(e.getMessage());
            newException.echo();
            return;
        }
        
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
