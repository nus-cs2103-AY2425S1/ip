import exceptions.*;
import storage.Storage;
import stringconstants.ReplyTextMessages;
import tasks.*;
import ui.Ui;
import java.util.*;

import static commands.Commands.*;


public class YappingBot {
    private ArrayList<Task> userList;
    private String savefilePath;
    // https://github.com/nus-cs2103-AY2425S1/forum/issues/22#issuecomment-2309939016
    private YappingBot(String savefilePath) {
        this.savefilePath = savefilePath;
    }

    private void init(Storage storage) {
        try {
            userList = storage.loadListFromFile();
        } catch (YappingBotException e) {
            System.out.println(Ui.quoteMultilineText(String.format(ReplyTextMessages.LOAD_FILE_ERROR_1s, e.getMessage())));
            userList = new ArrayList<>();
        }
    }
    private void saveAndExit(Storage storage) {
        try {
            storage.saveListToFile(userList);
        } catch (YappingBotException e) {
            System.out.println(Ui.quoteMultilineText(String.format(ReplyTextMessages.SAVE_FILE_ERROR_1s, e.getMessage())));
        }
        System.out.println(Ui.quoteMultilineText(ReplyTextMessages.EXIT_TEXT));
    }
    private void mainLoop() {
        Scanner userInputScanner = new Scanner(System.in);
        System.out.println(Ui.quoteMultilineText(ReplyTextMessages.GREETING_TEXT));
        programmeLoop: // to break out of loop
        while (userInputScanner.hasNextLine()) {
            String userInput = userInputScanner.nextLine();
            String[] userInputSlices = userInput.split(" ");
            try {
                int taskListIndexPtr; // task list pointer
                switch (parseCommand(userInputSlices[0])) {
                    case EXIT:
                        break programmeLoop;
                    case LIST:
                        printUserList(userList);
                        break;
                    case MARK:
                        taskListIndexPtr = parseTaskNumberSelected(userInputSlices[1], userList);
                        changeTaskListStatus(taskListIndexPtr, true, userList);
                        break;
                    case UNMARK:
                        taskListIndexPtr = parseTaskNumberSelected(userInputSlices[1], userList);
                        changeTaskListStatus(taskListIndexPtr, false, userList);
                        break;
                    case DELETE:
                        taskListIndexPtr = parseTaskNumberSelected(userInputSlices[1], userList);
                        deleteTask(taskListIndexPtr, userList);
                        break;
                    case TODO:
                        userList.add(addTaskToList(userInputSlices, TaskTypes.TODO, userList));
                        break;
                    case EVENT:
                        userList.add(addTaskToList(userInputSlices, TaskTypes.EVENT, userList));
                        break;
                    case DEADLINE:
                        userList.add(addTaskToList(userInputSlices, TaskTypes.DEADLINE, userList));
                        break;
                    case UNKNOWN:
                    default:
                        throw new YappingBotUnknownCommandException();
                }
            } catch (YappingBotException e){
                System.out.println(Ui.quoteMultilineText(e.getMessage()));
            }
        }
    }
    private void start() {
        Storage storage = new Storage(savefilePath);
        init(storage);
        mainLoop();
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
