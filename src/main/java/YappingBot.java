import exceptions.*;
import stringconstants.ReplyTextMessages;
import tasks.*;
import ui.Ui;

import java.io.*;
import java.util.*;

import static commands.Commands.*;


public class YappingBot {
    // https://github.com/nus-cs2103-AY2425S1/forum/issues/22#issuecomment-2309939016
    private final String savefilePath;
    private final ArrayList<Task> userList;
    private YappingBot(String savefilePath) {
        this.savefilePath = savefilePath;
        userList = new ArrayList<>();
        try {
            loadListFromFile();
        } catch (YappingBotException e) {
            System.out.println(Ui.quoteMultilineText(String.format(ReplyTextMessages.LOAD_FILE_ERROR_1s, e.getMessage())));
        }
    }

    private void loadListFromFile() throws YappingBotSaveFileNotFoundException, YappingBotInvalidSaveFileException {
        StringBuilder error_list = new StringBuilder();
        try {
            File saveFile = new File(savefilePath);
            Scanner scanner = new Scanner(saveFile);
            while (scanner.hasNext()) {
                String[] s = scanner.nextLine().split(":");
                Task t;
                try {
                    try {
                        switch (TaskTypes.valueOf(s[0])) {
                            case TODO:
                                t = new Todo();
                                t.deserialize(s);
                                break;
                            case DEADLINE:
                                t = new Deadline();
                                t.deserialize(s);
                                break;
                            case EVENT:
                                t = new Event();
                                t.deserialize(s);
                                break;
                            default:
                                throw new YappingBotInvalidSaveFileException(String.format(ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_INVALID_VALUES_1s, s[0]));
                        }
                    } catch (IllegalArgumentException e) {
                        // todo: add line number
                        throw new YappingBotInvalidSaveFileException(String.format(ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_INVALID_VALUES_1s, e.getMessage()));
                    }
                    userList.add(t);
                } catch (YappingBotException e) {
                    error_list.append(Ui.quoteMultilineText(e.getMessage()));
                }
            }
        } catch (FileNotFoundException e) {
            throw new YappingBotSaveFileNotFoundException();
        }
        if (!error_list.isEmpty()) {
            throw new YappingBotException(error_list.toString());
        }
    }
    private void start() {
        mainLoop();
        try {
            saveListToFile();
        } catch (YappingBotException e) {
            System.out.println(Ui.quoteMultilineText(String.format(ReplyTextMessages.SAVE_FILE_ERROR_1s, e.getMessage())));
        }
        System.out.println(Ui.quoteMultilineText(ReplyTextMessages.EXIT_TEXT));
    }
    private void saveListToFile() throws YappingBotSaveFileIOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(savefilePath))) {
            for (Task t : userList) {
                bw.write(t.serialize());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new YappingBotSaveFileIOException(e.getMessage());
        }
    }
    private void mainLoop() {
        Scanner userInputScanner;
        userInputScanner = new Scanner(System.in);
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

    // end of class methods
    public static void main(String[] args) {
        YappingBot yp = new YappingBot("savefile");
        yp.start();
    }
}
