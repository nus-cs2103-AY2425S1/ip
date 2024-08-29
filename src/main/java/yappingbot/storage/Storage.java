package yappingbot.storage;

import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.exceptions.YappingBotSaveFileIOException;
import yappingbot.exceptions.YappingBotSaveFileNotFoundException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.*;
import yappingbot.ui.MultilineStringBuilder;
import yappingbot.ui.Ui;;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String savefilePath;

    public Storage(String savefilePath) {
        this.savefilePath = savefilePath;
    }

    private Task parseTask(String[] s) throws YappingBotInvalidSaveFileException {
        Task t;
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
        } catch (Exception e) {
            // todo: add line number
            throw new YappingBotInvalidSaveFileException(String.format(ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_INVALID_VALUES_1s, e.getMessage()));
        }
        return t;
    }

    public TaskList loadListFromFile() throws YappingBotSaveFileNotFoundException {
        TaskList userList = new TaskList();
        File saveFile;
        Scanner scanner;
        try {
            saveFile = new File(savefilePath);
            scanner = new Scanner(saveFile);
        } catch (FileNotFoundException e) {
            throw new YappingBotSaveFileNotFoundException();
        }

        ArrayList<Exception> errorLists = new ArrayList<>();
        while (scanner.hasNext()) {
            String[] s = scanner.nextLine().split(":");
            try {
                userList.add(parseTask(s));
            } catch (YappingBotException e) {
               errorLists.add(e);
            }
        }
        if (!errorLists.isEmpty()) {
            MultilineStringBuilder msb = new MultilineStringBuilder();
            for (Exception e : errorLists) {
                msb.addLine(e.getMessage());
            }
            Ui.printError(String.format(ReplyTextMessages.LOAD_FILE_ERROR_1s, msb));
        }
        return userList;
    }
    public void saveListToFile(TaskList userList) throws YappingBotSaveFileIOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(savefilePath))) {
            for (Task t : userList) {
                bw.write(t.serialize());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new YappingBotSaveFileIOException(e.getMessage());
        }
    }
}
