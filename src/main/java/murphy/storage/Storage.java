package murphy.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import murphy.MurphyException;
import murphy.task.Task;
import murphy.task.TaskList;

public class Storage {
    private final String filepath;
    private final File file;

    public Storage(String filepath) throws MurphyException {
        this.filepath = filepath;
        File file = new File(filepath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new MurphyException("Error creating new save file: " + e.getMessage());
            }
        }
        this.file = file;
    }

    public Scanner load() throws MurphyException {
        try {
            return new Scanner(this.file);
        } catch (FileNotFoundException e) {
            throw new MurphyException("Error loading save file: " + e.getMessage());
        }
    }

    public void write(TaskList taskList) throws MurphyException {
        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(taskList.toSaveString());
            fw.close();
        } catch (IOException e) {
            throw new MurphyException("Error writing to save file: " + e.getMessage());
        }
    }
}
