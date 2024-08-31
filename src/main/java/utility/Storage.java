package utility;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class Storage {
    private final File saveFile;

    public Storage(String filePathString) {
        this.saveFile = validateAndCreateFile(filePathString);
    }

    private File validateAndCreateFile(String filePathString) {
        File saveFile = new File(filePathString);
        File parentDir = saveFile.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            } catch (Exception e) {
                // Fallthrough
            }
        }
        return saveFile;
    }

    public TaskList loadTaskList() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
            return (TaskList) ois.readObject();
        } catch (Exception e) {
            return new TaskList();
        }
    }

    public void saveTaskList(TaskList taskList) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
            oos.writeObject(taskList);
        } catch (Exception e) {
            // Fallthrough
        }
    }

}
