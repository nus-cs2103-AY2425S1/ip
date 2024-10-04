package sora;

import java.util.ArrayList;

import sora.Tasks.TaskList;

/**
 * Sora is the main class.
 * Sora is a ChatBot.
 */
public class Sora {
    /** Storage to save and write tasks to a file */
    private Storage storage;
    /** List of Tasks */
    private TaskList taskList;
    /** User Interface */
    private Ui ui;

    /**
     * Constructs a new Sora.
     */
    public Sora() {
        this.storage = new Storage("savedtasks.txt");
        this.taskList = new TaskList();
        this.ui = new Ui();
        try {
            this.storage.loadTaskList(this.taskList);
        } catch (SoraException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Derives Sora's Response to User's Command.
     *
     * @param input User's Command.
     * @return Sora's Response.
     */
    public String getResponse(String input) {
        String res;
        ArrayList<String> parsedCommand = Parser.parse(input);
        String mainCommand = parsedCommand.get(0).toLowerCase();
        switch (mainCommand) {
        case "help":
            res = Ui.HELP;
            break;
        case "welcome":
            res = Ui.GREETING;
            break;
        case "bye":
            res = Ui.FAREWELL;
            break;
        case "list":
            res = ui.displayTaskList(this.taskList);
            break;
        case "mark":
            res = ui.displayMarkedTask(this.taskList, parsedCommand);
            break;
        case "unmark":
            res = ui.displayUnMarkedTask(this.taskList, parsedCommand);
            break;
        case "todo":
            res = ui.displayAddedTask(this.taskList, "todo", parsedCommand);
            break;
        case "deadline":
            res = ui.displayAddedTask(this.taskList, "deadline", parsedCommand);
            break;
        case "event":
            res = ui.displayAddedTask(this.taskList, "event", parsedCommand);
            break;
        case "delete":
            res = ui.displayDeletedTask(this.taskList, parsedCommand);
            break;
        case "find":
            res = ui.displayFoundTask(this.taskList, parsedCommand);
            break;
        case "":
            res = Ui.EMPTYCOMMAND;
            break;
        default:
            res = Ui.INVALIDCOMMAND;
        }
        try {
            this.storage.saveTaskList(this.taskList);
        } catch (SoraException e) {
            res = e.getMessage();
        }
        return res;
    }
}
