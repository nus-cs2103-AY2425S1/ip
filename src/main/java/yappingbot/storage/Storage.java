package yappingbot.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import yappingbot.exceptions.YappingBotSaveFileIoException;
import yappingbot.exceptions.YappingBotSaveFileNotFoundException;

public class Storage {

    private final String savefilePath;

    public Storage(String savefilePath) {
        this.savefilePath = savefilePath;
    }

    public ArrayList<String> loadListFromFile() throws YappingBotSaveFileNotFoundException {
        ArrayList<String> taskListRaw = new ArrayList<>();
        File saveFile;
        Scanner scanner;
        try {
            saveFile = new File(savefilePath);
            scanner = new Scanner(saveFile);
            while (scanner.hasNext()) {
                taskListRaw.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new YappingBotSaveFileNotFoundException();
        }
        return taskListRaw;
    }

    public void saveListToFile(ArrayList<String> userListRaw) throws YappingBotSaveFileIoException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(savefilePath))) {
            for (String t : userListRaw) {
                bw.write(t);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new YappingBotSaveFileIoException(e.getMessage());
        }
    }
}
