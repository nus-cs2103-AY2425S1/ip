package snowy;

import snowy.common.Command;
import snowy.common.CommandResult;
import snowy.data.SnowyException;
import snowy.storage.Storage;
import snowy.tasklist.Task;
import snowy.tasklist.TaskList;
import snowy.ui.TextUi;
import snowy.parser.Parser;

public class Snowy {
    private static final String FILE_PATH = "../ip/src/main/data";
    private static final String FILENAME = "snowy.txt";
    private final Parser parser;
    private final TaskList tasklist;
    private final Storage storage;
    private final TextUi ui;

    public Snowy() {
        this.parser = new Parser();
        this.storage = new Storage(FILE_PATH, FILENAME);
        this.tasklist = new TaskList(storage);
        ui = new TextUi();
    }

    public String getResponse(String input) {
       try {
           Command command = parser.parseCommand(input);
           command.setData(tasklist);
           CommandResult result = command.execute();
           return result.getFeedback();
        } catch (SnowyException e) {
            return e.getMessage();
        }

        }
    }
//    public void run(String[] launchArgs) {
//        start(launchArgs);
//        runCommandLoopUntilExitCommand();
//        exit();
//    }
//
//    private void start(String[] launchArgs) {
//        Storage storage = new Storage(FILE_PATH, FILENAME);
//        this.ui = new TextUi();
//        this.taskList = new TaskList(storage);
//        this.parser = new Parser();
//        ui.showWelcomeMessage();
//    }
//
//    private void exit() {
//        System.exit(0);
//    }

//    private void runCommandLoopUntilExitCommand() {
//        Command command;
//        boolean isExit = false;
//
//        while (!isExit) {
//            try {
//                String userCommandText = ui.getUserInput();
//                command = parser.parseCommand(userCommandText);
//
//                if (command != null) {
//                    command.setData(taskList);
//                    CommandResult result = command.execute();
//                    ui.showTaskAddedMessage(result.getFeedback());
//                    isExit = command.isExit();
//                }
//
//            } catch (SnowyException e) {
//                ui.showErrorMessage(e.getMessage());
//            }
//        }
//    }
//}
