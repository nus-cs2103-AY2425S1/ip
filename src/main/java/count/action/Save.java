package count.action;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import count.TaskList;

import count.exception.CountException;

/**
 * The Save class is used to save the current TaskList as Count.txt
 * in the Count object's filePath
 */
public class Save extends Action {
    TaskList ls;
    String filePath;

    /**
     * Constructor for Save
     * @param ls TaskList being saved
     * @param filePath String file path the Save class will save to
     */
    public Save(TaskList ls, String filePath) {
        this.ls = ls;
        this.filePath = filePath;
    }

    /**
     * The save method saves the TaskList as Count.txt, overwriting any existing Count.txt
     * @return String for Count's UI to print
     * @throws CountException if the file creation fails
     */
    @Override
    public String run() throws CountException {
        String output = "";
        for (int i = 0; i < ls.getList().size(); i++) {
            output += (i + 1) + "." + ls.getList().get(i).toString() + "\n";
        }

        try {
            File file = new File(this.filePath);
            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();
            FileWriter writer = new FileWriter(this.filePath);
            writer.write(output);
            writer.close();

        } catch (IOException e) {
            throw new CountException("File Creation failed");
        }
        return "Your list has been saved over at Count.txt";
    }
}

