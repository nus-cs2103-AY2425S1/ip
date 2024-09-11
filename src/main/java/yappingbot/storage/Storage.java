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

/**
 * Storage class to keep track, load, and save data to disk.
 */
public class Storage {

    private final String savefilePath;

    /**
     * Creates a Storage class.
     *
     * @param savefilePath the absolute or relative path this Storage object will interface the
     *                     disk with.
     */
    public Storage(String savefilePath) {
        assert savefilePath != null;
        this.savefilePath = savefilePath;
    }

    /**
     * Loads any file pointed by the savefile path, line by line, into an ArrayList of Strings.
     *
     * @return ArrayList of Strings of each line retrieved from the file.
     * @throws YappingBotSaveFileNotFoundException Exception if file is missing or not accessible.
     */
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

    /**
     * Saves ArrayList of Strings as a file pointed by the savefile path, line by line.
     * Overrides file if exits.
     *
     * @param userListRaw ArrayList of Strings to be saved.
     * @throws YappingBotSaveFileIoException Exception if file is not accessible.
     */
    public void saveListToFile(ArrayList<String> userListRaw) throws YappingBotSaveFileIoException {
        assert userListRaw != null;
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
