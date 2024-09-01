package FlyChat.Core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File saveFile;

    public void findSaveFile(String filePath) {
        try {
            saveFile = new File(filePath);
            saveFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Save file could not be created. Info will not be saved.");
        }
    }

    public void loadSaveFile(TaskList loadTarget) {
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

    public void writeToSave(String source) {
        //Replaces old file with a new file with updated contents
        try {
            File tmp = File.createTempFile("tmp", "");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tmp));
            writer.write(source);
            writer.close();
            if(saveFile.delete()) {
                tmp.renameTo(saveFile);
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file. Information may not be saved for the next session.");
        }
    }
}
