package yappingbot;
import yappingbot.commands.Commands;
import yappingbot.commands.Parser;
import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotSaveFileNotFoundException;
import yappingbot.exceptions.YappingBotUnknownCommandException;
import yappingbot.storage.Storage;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.TaskList;
import yappingbot.tasks.TaskTypes;
import yappingbot.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class YappingBot {
    private TaskList userList;
    private final String savefilePath;

    private YappingBot(String savefilePath) {
        this.savefilePath = savefilePath;
    }

    private void init(Storage storage) {
        try {
            ArrayList<String> s = storage.loadListFromFile();
            userList = TaskList.generateFromRaw(s);
        } catch (YappingBotSaveFileNotFoundException e) {
            Ui.printError(e);
            userList = new TaskList();
        }
    }

    private void saveAndExit(Storage storage) {
        try {
            storage.saveListToFile(userList.toRawFormat());
        } catch (YappingBotException e) {
            Ui.printError(String.format(ReplyTextMessages.SAVE_FILE_ERROR_1s, e.getMessage()));
        }
    }

    private void mainLoop() {
        Scanner userInputScanner = new Scanner(System.in);
        Parser parser = new Parser();
        while (userInputScanner.hasNextLine()) {
            String userInput = userInputScanner.nextLine();
            String[] userInputSlices = userInput.split(" ");
            try {
                int taskListIndexPtr; // task list pointer
                switch (parser.parseCommand(userInputSlices[0])) {
                case EXIT:
                    return;
                case LIST:
                    Commands.printUserList(userList);
                    break;
                case MARK:
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    Commands.changeTaskListStatus(taskListIndexPtr, true, userList);
                    break;
                case UNMARK:
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    Commands.changeTaskListStatus(taskListIndexPtr, false, userList);
                    break;
                case DELETE:
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    Commands.deleteTask(taskListIndexPtr, userList);
                    break;
                case TODO:
                    userList.add(Commands.createNewTask(userInputSlices, TaskTypes.TODO, userList));
                    break;
                case EVENT:
                    userList.add(Commands.createNewTask(userInputSlices, TaskTypes.EVENT, userList));
                    break;
                case DEADLINE:
                    userList.add(Commands.createNewTask(userInputSlices, TaskTypes.DEADLINE, userList));
                    break;
                case UNKNOWN:
                default:
                    throw new YappingBotUnknownCommandException();
                }
            } catch (YappingBotException e) {
                Ui.printError(e);
            }
        }
    }

    private void start() {
        Storage storage = new Storage(savefilePath);
        init(storage);
        Ui.print(ReplyTextMessages.GREETING_TEXT);
        mainLoop();
        Ui.print(ReplyTextMessages.EXIT_TEXT);
        saveAndExit(storage);
    }

    // end of class methods
    public static void main(String[] args) {
        String savefile;

        // ability to give savefile path
        if (args.length > 1) {
            savefile = args[1];
        } else {
            savefile = "./savefile";
        }

        YappingBot yp = new YappingBot(savefile);
        yp.start();
    }
}
