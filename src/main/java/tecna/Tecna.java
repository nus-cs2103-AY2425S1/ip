package tecna;

import java.util.Scanner;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Tecna {
    private Storage storage;
    private TaskList taskList;
    private CommandScanner commandScanner;
    private Ui ui;
    /**
     * A constructor of tecna.Tecna chatbot
     */
    public Tecna() {
        this.taskList = new TaskList();
        this.commandScanner = new CommandScanner();
        this.ui = new Ui();
    }

    public Tecna(String taskData) {
        this.storage = new Storage(taskData);
        this.commandScanner = new CommandScanner();
        this.ui = new Ui();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (IOException ioException) {
            this.ui.printError("Oops! I cannot open the data file!");
        } catch (ParseException parseException) {
            this.ui.printError("Oops! The data file is of the wrong format!");
        } catch (TaskParseException taskParseException) {
            this.ui.printError(taskParseException.getMessage());
        } catch (JsonLoadingException jsonLoadingException) {
            this.ui.printError(jsonLoadingException.getMessage());
        }
    }

    /**
     * Exits the chatbot by printing the goodbye lines
     */
    public void exitChatBot() {
        storage.setFilePath("src/main/data/tecna1.json");
        storage.save(this.taskList);
        ui.printSectionLine();
        ui.printGoodbyeMsg();
        ui.printSectionLine();
    }

    /**
     * Repeats the input entered by the user
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
     * Receives requests entered by the user.
     * Accepts string input and processes accordingly.
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
                int index = commandScanner.markIndex();
                taskList.mark(index);
                ui.printMarkMsg(taskList.getTask(index));
                break;
            case UNMARK:
                index = commandScanner.markIndex();
                taskList.unmark(index);
                ui.printUnmarkMsg(taskList.getTask(index));
                break;
            case DELETE:
                index = commandScanner.markIndex();
                this.taskList.deleteItem(index);
                break;
            case TODO:
            case EVENT:
            case DEADLINE:
            case TODO_WRONG_FORMAT:
                try {
                    this.taskList.addItem(commandScanner.getInput());
                } catch (InvalidRequestException ive) {
                    ui.printInvalidCmdError();
                } catch (TodoWrongFormatException tde) {
                    ui.printError(tde.getMessage());
                }
                break;
            }
            ui.printSectionLine();
            command = this.commandScanner.getRequest();
        }
        this.exitChatBot();
        commandScanner.close();

    }

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
