import java.io.IOException;
import java.util.Scanner;

public class SumoDE {

    private Storage storage;
    private SumoTaskList tasks;
    private final Ui ui;

    public SumoDE (String filePath) {
        //handle Ui
        this.ui = new Ui();

        // handle Storage
        try {
            this.storage = new Storage(filePath,ui);
        } catch (IOException e) {
            // Note: this will only happen when file don't exist and we cannot create new file in the path.
            // New File will be created when file doesn't exist in first place.
            ui.unknownSaveError();
        }

        //handle SumoTaskList
        if (storage == null) {
            this.tasks = new SumoTaskList(); // we will use the version where we cannot save
        } else {
            try {
                this.tasks = new SumoTaskList(this.storage, ui);
            } catch (IOException e) {
                //unlikely will happen since we already successfully initialise storage
                ui.unknownSaveError();
                this.tasks = new SumoTaskList(); // we will use the version where we cannot save
            }
        }
    }

    public void run() {

        this.ui.greet();

        Scanner sc = new Scanner(System.in);
        boolean terminate = false;

        while (!terminate) {
            String input = sc.nextLine();

            // Splitting command and action
            String[] splitString = Parser.splitCommandAndAction(input);
            String commandString = splitString[0];
            String item = splitString[1];
            Command command;

            // find correct matching command
            try {
                command = Command.valueOf(commandString.toUpperCase());
                terminate = this.tasks.execute(command,item);
            }catch (IllegalArgumentException e) {
                ui.unknownCommand(commandString);
            }catch (WrongSyntaxForCommandException | UnknownCommandException
                    | NonExistentTaskException | AlreadyUnmarkedException
                    | AlreadyMarkedException e) {
                ui.handleError(e);
            } finally {
                if (!terminate) {
                    ui.next();
                }
            }
        }

        // loop ended, cleaning up
        ui.bye();
        sc.close();
    }

    public static void main(String[] args) {
        SumoDE sumoDE = new SumoDE("data\\taskSaved.txt");
        sumoDE.run();
    }
}
