import exceptions.*;
import stringconstants.ReplyTextMessages;
import tasks.*;

import java.io.*;
import java.util.*;

import static stringconstants.ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_INVALID_VALUES_1s;


public class YappingBot {
    // https://github.com/nus-cs2103-AY2425S1/forum/issues/22#issuecomment-2309939016
    private static final String LIST_SAVE_PATH = "savefile";
    private static final HashMap<String, Commands> COMMANDS_HASH_MAP;
    private static final Scanner userInputScanner;
    private static final ArrayList<Task> userList;
    static {
        // initialization
        userInputScanner = new Scanner(System.in);
        userList = new ArrayList<>();
        COMMANDS_HASH_MAP = new HashMap<>();
        COMMANDS_HASH_MAP.put("list", Commands.LIST);
        COMMANDS_HASH_MAP.put("mark", Commands.MARK);
        COMMANDS_HASH_MAP.put("unmark", Commands.UNMARK);
        COMMANDS_HASH_MAP.put("delete", Commands.DELETE);
        COMMANDS_HASH_MAP.put("todo", Commands.TODO);
        COMMANDS_HASH_MAP.put("event", Commands.EVENT);
        COMMANDS_HASH_MAP.put("deadline", Commands.DEADLINE);
        COMMANDS_HASH_MAP.put("bye", Commands.EXIT);
    }
    // end of class properties

    // class methods
    private static String quoteSinglelineText(String line) {
        if (line == null || line.trim().isEmpty()) {
            return "\n |";
        } else {
            return String.format("\n |  %s\n", line);
        }
    }
    private static void quoteSinglelineText(String line, StringBuilder sb) {
        if (line.trim().isEmpty()) {
            sb.append("\n |");
        } else {
            sb.append("\n |  ");
            sb.append(line);
        }
    }
    private static String quoteMultilineText(String text) {
        // annotates text with pipe to denote speech from bot
        if (text == null) {
            return quoteSinglelineText("");
        }
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String l : lines) {
            quoteSinglelineText(l, sb);
        }
        sb.append("\n"); // pad the end with another newline
        return sb.toString();
    }
    private static void printUserList() {
        if (userList.isEmpty()) {
            System.out.println(quoteSinglelineText("List is empty!"));
            return;
        }

        StringBuilder sb = new StringBuilder();
        quoteSinglelineText(ReplyTextMessages.LIST_TEXT, sb);
        for (int i = 0; i < userList.size(); i++) {
            Task t = userList.get(i);
            quoteSinglelineText(
                    String.format(
                            "%2d.%s",
                            i+1,
                            String.format(
                                    ReplyTextMessages.TASK_PRINT_TEXT_3s,
                                    t.getTaskTypeSymbol(),
                                    t.getTaskDoneCheckmark(),
                                    t
                            )
                    ),
                    sb
            );
        }
        sb.append("\n");
        System.out.println(sb);
    }
    private static int parseTaskNumberSelected(String userInputSlice) throws YappingBotOOBException, YappingBotInvalidTaskNumberException {
        int i = -1;
        try {
            i = Integer.parseInt(userInputSlice) - 1;
        } catch (NumberFormatException ex) {
            throw new YappingBotInvalidTaskNumberException(userInputSlice);
        }

        // OOB
        if (i < 0 || i >= userList.size()) {
            throw new YappingBotOOBException(i);
        }

        return i;
    }
    private static void changeTaskListStatus(int i, boolean isTaskDone) {
        Task t = userList.get(i);
        t.setTaskDone(isTaskDone);
        StringBuilder sb = new StringBuilder();
        if (isTaskDone) {
            quoteSinglelineText(ReplyTextMessages.MARKED_TASK_AS_DONE_TEXT, sb);
        } else {
            quoteSinglelineText(ReplyTextMessages.UNMARKED_TASK_AS_DONE_TEXT, sb);
        }
        quoteSinglelineText(
                String.format(
                        ReplyTextMessages.TASK_PRINT_TEXT_3s,
                        t.getTaskTypeSymbol(),
                        t.getTaskDoneCheckmark(),
                        t
                ),
                sb
        );
        sb.append("\n");
        System.out.println(sb);
    }
    private static void deleteTask(int i) {
        Task t = userList.get(i);
        userList.remove(i);
        StringBuilder sb = new StringBuilder();
        quoteSinglelineText(ReplyTextMessages.DELETED_TEXT, sb);
        quoteSinglelineText(
                String.format(ReplyTextMessages.TASK_PRINT_TEXT_3s,
                        t.getTaskTypeSymbol(),
                        t.getTaskDoneCheckmark(),
                        t),
                sb
        );
        quoteSinglelineText(String.format(ReplyTextMessages.LIST_SUMMARY_TEXT_1d, userList.size()), sb);
        System.out.println(sb);
    }
    private static void addTaskToList(String[] userInputSpliced, TaskTypes taskTypes) throws YappingBotIncorrectCommandException {
        Task newTask;
        String taskName = null;
        String command = null;
        StringBuilder sb = new StringBuilder();
        switch (taskTypes) {
            case TODO:
                if (userInputSpliced.length <= 1) {
                    throw new YappingBotIncorrectCommandException(ReplyTextMessages.TODO_USAGE, userInputSpliced[0]);
                }
                // pattern: ^[COMMAND] ( titles )$
                for (String s : userInputSpliced) {
                    if (command == null) {
                        command = s;
                    } else {
                        sb.append(s);
                        sb.append(" ");
                    }
                }
                taskName = sb.toString();
                newTask = new Todo(taskName.trim(), false);
                break;
            case DEADLINE:
                if (userInputSpliced.length <= 1) {
                    throw new YappingBotIncorrectCommandException(ReplyTextMessages.DEADLINE_USAGE, userInputSpliced[0]);
                }
                // pattern: ^[COMMAND] (titles) /by (date)$
                String deadline;
                for (String s : userInputSpliced) {
                    if (command == null) {
                        command = s;
                        continue;
                    } else if (taskName == null && s.equals("/by")) {
                        taskName = sb.toString();
                        sb = new StringBuilder();
                        continue;
                    }
                    sb.append(s);
                    sb.append(" ");
                }
                deadline = sb.toString();
                if (deadline.isEmpty() || taskName == null) {
                    throw YappingBotIncorrectCommandException.withUserInputArray(ReplyTextMessages.DEADLINE_USAGE, userInputSpliced);
                }
                newTask = new Deadline(taskName.trim(), false, deadline.trim());
                break;
            case EVENT:
                if (userInputSpliced.length <= 1) {
                    throw new YappingBotIncorrectCommandException(ReplyTextMessages.EVENT_USAGE, userInputSpliced[0]);
                }
                // pattern: ^[COMMAND] (titles) /from (date) /to ([date])$
                String fromTime = null;
                String toTime = null;
                // todo: regexify this to use capture groups
                for (String s : userInputSpliced) {
                    if (command == null) {
                        command = s;
                        continue;
                    } else if (taskName == null && (s.equals("/from") || s.equals("/to"))) {
                        taskName = sb.toString();
                        sb = new StringBuilder();
                        continue;
                    } else {
                        if (fromTime == null && s.equals("/to")) {
                            fromTime = sb.toString();
                            sb = new StringBuilder();
                            continue;
                        } else if (toTime == null && s.equals("/from")) {
                            toTime = sb.toString();
                            sb = new StringBuilder();
                            continue;
                        }
                    }
                    sb.append(s);
                    sb.append(" ");
                }
                if (toTime == null) {
                    toTime = sb.toString();
                } else if (fromTime == null) {
                    fromTime = sb.toString();
                }
                //noinspection ReassignedVariable
                if (fromTime == null || toTime == null || taskName == null) {
                    throw YappingBotIncorrectCommandException.withUserInputArray(ReplyTextMessages.EVENT_USAGE, userInputSpliced);
                }
                newTask = new Event(taskName.trim(), false, fromTime.trim(), toTime.trim());
                break;
            default:
                throw YappingBotIncorrectCommandException.withUserInputArray(ReplyTextMessages.EVENT_USAGE, userInputSpliced);
        }
        userList.add(newTask);
        sb = new StringBuilder();
        quoteSinglelineText(ReplyTextMessages.ADDED_TEXT, sb);
        quoteSinglelineText(
                String.format(ReplyTextMessages.TASK_PRINT_TEXT_3s,
                        newTask.getTaskTypeSymbol(),
                        newTask.getTaskDoneCheckmark(),
                        newTask),
                sb
        );
        quoteSinglelineText(String.format(ReplyTextMessages.LIST_SUMMARY_TEXT_1d, userList.size()), sb);
        System.out.println(sb);
    }
    private static Commands parseCommand(String commandString) throws YappingBotUnknownCommandException {
        if (commandString.toLowerCase().trim().isEmpty()) {
            throw new YappingBotUnknownCommandException();
        } else {
            if (COMMANDS_HASH_MAP.containsKey(commandString)) {
                return COMMANDS_HASH_MAP.get(commandString);
            } else {
                throw new YappingBotUnknownCommandException(commandString);
            }
        }
    }
    private static void loadListFromFile() throws YappingBotSaveFileNotFoundException, YappingBotInvalidSaveFileException{
        StringBuilder error_list = new StringBuilder();
        try {
            File saveFile = new File(LIST_SAVE_PATH);
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
                                throw new YappingBotInvalidSaveFileException(String.format(INVALID_SAVE_FILE_EXCEPTION_INVALID_VALUES_1s, s[0]));
                        }
                    } catch (IllegalArgumentException e) {
                        // todo: add line number
                        throw new YappingBotInvalidSaveFileException(String.format(INVALID_SAVE_FILE_EXCEPTION_INVALID_VALUES_1s, e.getMessage()));
                    }
                    userList.add(t);
                } catch (YappingBotException e) {
                    error_list.append(quoteMultilineText(e.getMessage()));
                }
            }
        } catch (FileNotFoundException e) {
            throw new YappingBotSaveFileNotFoundException();
        }
        if (!error_list.isEmpty()) {
            throw new YappingBotException(error_list.toString());
        }
    }
    private static void saveListToFile() throws YappingBotSaveFileIOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LIST_SAVE_PATH))) {
            for (Task t : userList) {
                bw.write(t.serialize());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new YappingBotSaveFileIOException(e.getMessage());
        }
    }
    // end of class methods

    public static void main(String[] args) {
        // start
        try {
            loadListFromFile();
        } catch (YappingBotException e) {
            System.out.println(quoteMultilineText(String.format(ReplyTextMessages.LOAD_FILE_ERROR_1s, e.getMessage())));
        }

        System.out.println(quoteMultilineText(ReplyTextMessages.GREETING_TEXT));
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
                        printUserList();
                        break;
                    case MARK:
                        taskListIndexPtr = parseTaskNumberSelected(userInputSlices[1]);
                        changeTaskListStatus(taskListIndexPtr, true);
                        break;
                    case UNMARK:
                        taskListIndexPtr = parseTaskNumberSelected(userInputSlices[1]);
                        changeTaskListStatus(taskListIndexPtr, false);
                        break;
                    case DELETE:
                        taskListIndexPtr = parseTaskNumberSelected(userInputSlices[1]);
                        deleteTask(taskListIndexPtr);
                        break;
                    case TODO:
                        addTaskToList(userInputSlices, TaskTypes.TODO);
                        break;
                    case EVENT:
                        addTaskToList(userInputSlices, TaskTypes.EVENT);
                        break;
                    case DEADLINE:
                        addTaskToList(userInputSlices, TaskTypes.DEADLINE);
                        break;
                    case UNKNOWN:
                    default:
                        throw new YappingBotUnknownCommandException();
                }
            } catch (YappingBotException e){
                System.out.println(quoteMultilineText(e.getMessage()));
            }
        }
        // exit
        try {
            saveListToFile();
        } catch (YappingBotException e) {
            System.out.println(quoteMultilineText(String.format(ReplyTextMessages.SAVE_FILE_ERROR_1s, e.getMessage())));
        }
        System.out.println(quoteMultilineText(ReplyTextMessages.EXIT_TEXT));
    }
}
