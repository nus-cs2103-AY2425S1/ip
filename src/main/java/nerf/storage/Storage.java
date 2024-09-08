package nerf.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;

import nerf.error.FilePermissionsException;

/**
 * Class for handling file I/O.
 */
public class Storage {
    /** path to save file */
    private final String saveDirectory;
    /** name of save file */
    private final String saveFileName;

    public Storage(String pathString) {
        int lastSlash = pathString.lastIndexOf("/");
        String dirString = pathString.substring(0,lastSlash);
        String fileString = pathString.substring(lastSlash);
        this.saveDirectory = dirString;
        this.saveFileName = fileString;
    }

    /**
     * Returns the path string of the save file.
     * 
     * @return path to save file.
     */
    private String getFullPath() {
        return this.saveDirectory + this.saveFileName;
    }

    /**
     * Returns list of tasks as strings found in save file.
     * If a file I/O-related error occurs, throw an exception.
     * 
     * @return list of tasks.
     * @throws FilePermissionsException If unable to read file.
     */
    public List<String> load() throws FilePermissionsException {
        checkSafeFileExist();
        try {
            List<String> fileContent = Files.readAllLines(Paths.get(getFullPath()));
            return fileContent;
        } catch (IOException e) {
            throw new FilePermissionsException("No permission to read save file");
        }
    }

    private void checkSafeFileExist() throws FilePermissionsException {
        File directory = new File(this.saveDirectory);
        File saveFile = new File(getFullPath());
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                throw new FilePermissionsException("No permission to write to directory");
            }
        }
    }

    /**
     * Saves tasks to save file.
     * 
     * @param taskList list of task in string format.
     */
    public void save(List<String> taskList) {
        try (FileWriter fileWriter = new FileWriter(getFullPath())) {
            for (String i:taskList){
                fileWriter.write(i+"\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file. Please restart the application");
        }
    }
}
