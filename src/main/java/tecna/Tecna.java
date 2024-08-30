package tecna;

import java.util.Scanner;

import java.io.IOException;

import org.json.simple.parser.ParseException;

/**
 * Runs the Tecna chatbot application.
 *
 * @author DennieDan.
 */
public class Tecna {
    private Storage storage;
    private TaskList taskList;
    private CommandScanner commandScanner;
    private Ui ui;

    public Tecna() {
        this.taskList = new TaskList();
        this.commandScanner = new CommandScanner();
        this.ui = new Ui();
    }

    /**
     * Constructs an instance of Tecna chatbot from a <b>json</b> file.
     *
     * @param taskData absolute path to the json file.
     */
    public Tecna(String taskData) {
        this.storage = new Storage(taskData);
        this.commandScanner = new CommandScanner();
        this.ui = new Ui();
        this.taskList = new TaskList();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (IOException ioException) {
            this.ui.printError("I cannot open the data file!");
        } catch (ParseException parseException) {
            this.ui.printError("The data file is of the wrong format!");
        } catch (JsonLoadingException jsonLoadingException) {
            this.ui.printError(jsonLoadingException.getMessage());
        }
    }

    /**
     * Exits the chatbot by printing the goodbye lines.
     */
    public void exitChatBot() {
        storage.setFilePath("src/main/data/tecna1.json");
        storage.save(this.taskList);
        ui.printSectionLine();
        ui.printGoodbyeMsg();
        ui.printSectionLine();
    }

    /**
     * Repeats the input entered by the user.
     */
    public void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        if (input.equalsIgnoreCase("bye")) {
            this.exitChatBot();
        } else {
            ui.printSectionLine();
            System.out.println(input);
            ui.printSectionLine();
        }
        sc.close();
    }

    /**
     * Receives requests entered by the user and controls other Tecna attributes to work accordingly.
     */
    public void getRequest() {
        CommandType command = this.commandScanner.getRequest();

        while (!command.equals(CommandType.BYE)) {
            ui.printSectionLine();
            switch (command) {
            case LIST:
                this.taskList.listItems();
                break;
            case MARK:
                int index = commandScanner.getInputIndex();
                taskList.mark(index);
                ui.printMarkMsg(taskList.getTask(index));
                break;
            case UNMARK:
                index = commandScanner.getInputIndex();
                taskList.unmark(index);
                ui.printUnmarkMsg(taskList.getTask(index));
                break;
            case DELETE:
                index = commandScanner.getInputIndex();
                this.taskList.deleteItem(index);
                break;
            case TODO:
            case EVENT:
            case DEADLINE:
                taskList.addItem(commandScanner.getInputTask());
                break;
            case TODO_WRONG_FORMAT:
                ui.printError("Wrong format! The command should be \"todo [task_description]\".");
                break;
            case DEADLINE_WRONG_FORMAT:
                ui.printError("Wrong format! The command should be \"deadline [task_description] /by [deadline in the form of yyyy-MM-dd HHmm]\".");
                break;
            case EVENT_WRONG_FORMAT:
                ui.printError("Wrong format! The command should be \"event [task_description] /from [start_time in the form of yyyy-MM-dd HHmm] /to [end time in the form of yyyy-MM-dd HHmm]\".");
                break;
            case INDEX_WRONG_FORMAT:
                ui.printError("The parameter of this command must be a number from 1 to " + taskList.getSize());
                break;
            case INVALID:
                ui.printInvalidCmdError();
                break;
            }
            ui.printSectionLine();
            command = this.commandScanner.getRequest();
        }
        this.exitChatBot();
        commandScanner.close();

    }

    /**
     * Greets the user by printing the logo.
     */
    public void greet() {
        ui.printLogo();
        ui.printHelloMsg();
        ui.printSectionLine();
    }

    public static void main(String[] args) {
        Tecna tecna = new Tecna("src/main/data/tecna.json");
        tecna.greet();
        tecna.getRequest();
        // tecna.echo();
    }
}
