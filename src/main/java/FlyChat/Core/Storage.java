package flychat.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Contains operations related to reading and writing of the save file.
 */
public class Storage {
    private File saveFile;

    /**
     * Finds the save file specified in the input filePath. If no save file is found, creates a
     * new save file.
     */
    public void findSaveFile(String filePath) {
        File storageFolder = new File("./data");
        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }

        try {
            saveFile = new File(filePath);
            saveFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Save file could not be created. Info will not be saved.");
        }
    }

    /**
     * Reads the save file and saves all tasks present in the save file to the task list.
     */
    public void loadSaveFile(TaskList loadTarget) {
        assert loadTarget != null : "TaskList loadTarget is null";
        assert saveFile.exists() : "Save file does not exist";

        try {
            Scanner saveReader = new Scanner(saveFile);
            while (saveReader.hasNextLine()) {
                loadTarget.addTaskFromFile(saveReader.nextLine());
            }
            saveReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            saveFile.delete();
            try {
                saveFile.createNewFile();
            } catch (IOException f) {
                System.out.println("Save file could not be created. Info will not be saved.");
            }
        }
    }

    /**
     * Overwrites the current save file with all tasks from the current task list.
     *
     * @param source String to be written into the save file.
     */
    public void writeToSave(String source) {
        assert source != null && !source.isEmpty() : "Source string is null or empty";
        assert saveFile.exists() : "Save file does not exist";

        //Replaces old file with a new file with updated contents
        try {
            File tmp = File.createTempFile("tmp", "");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tmp));
            writer.write(source);
            writer.close();
            if (saveFile.delete()) {
                tmp.renameTo(saveFile);
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file. Information may not be saved for the next session.");
        }
    }
}
