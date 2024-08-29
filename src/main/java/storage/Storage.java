package storage;

import exceptions.YappingBotException;
import exceptions.YappingBotInvalidSaveFileException;
import exceptions.YappingBotSaveFileIOException;
import exceptions.YappingBotSaveFileNotFoundException;
import stringconstants.ReplyTextMessages;
import tasks.*;
import ui.Ui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String savefilePath;
    public Storage(String savefilePath) {
        this.savefilePath = savefilePath;
    }

    public ArrayList<Task> loadListFromFile() throws YappingBotSaveFileNotFoundException, YappingBotInvalidSaveFileException {
        ArrayList<Task> userList = new ArrayList<>();
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
        return userList;
    }
    public void saveListToFile(ArrayList<Task> userList) throws YappingBotSaveFileIOException {
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
