package kotori.storage;

import kotori.taskList.Task;
import kotori.taskList.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import kotori.Ui.Ui;

/**
 * This class represent the Storage of the chatbot.
 * */

public class Storage {
    private File file;

    public Storage (String directoryName, String fileName) {

        this.file = makeFile(directoryName, fileName);
    }

    /**
     * Loads from the memory file and create a TaskList Object.
     *
     * @return The TaskList loaded from the hard disk file.
     * */
    public TaskList load() {
        try {
            try {
                TaskList result = new TaskList();
                Scanner s = new Scanner(file);
                while (s.hasNextLine()) {
                    String input = s.nextLine();
                    String[] elements = input.split(" \\| ");
                    if (elements.length < 1) {
                        throw new CorruptedFileException("");
                    } else {
                        result.add(Task.read(elements));
                    }
                }
                return result;
            } catch (DateTimeParseException e) {
                throw new CorruptedFileException("");
            }
        } catch (FileNotFoundException e) {
            Ui.printMessage("There is no existing memory so I create a new one~ ^_^");
            return new TaskList();
        } catch (CorruptedFileException e) {
            Ui.printMessage("The memory file is corrupted so I create a new one~ ^_^");
            return new TaskList();
        }
    }


    /**
     * Update the hard disk file with the current TaskList.
     *
     * @param tasks the current TaskList.
     * */
    public void updateFile(TaskList tasks) {
        try {
        FileWriter writer = new FileWriter(file);
        String content = "";
        for (int i = 0; i < tasks.size(); i++) {
            content += tasks.get(i).getStorageMessage() + "\n";
        }
        writer.write(content);
        writer.close();
    } catch (FileNotFoundException e) {
        Ui.printMessages("Sorry~ I can not find the storage file",
                "Please ensure there is a file with path data/Kotori.Kotori.txt");
    } catch (IOException e) {
        Ui.printMessage(String.format("There is something wrong about: %s", e.getMessage()));
    }


}

/**
 * makes a file with specific directory name and file name
 *
 * @param directoryName name of directory.
 * @param fileName name of the file
 * @return the file produced
 * @throws Error if the file does not exist and
 * can not create one.
 * */

private static File makeFile (String directoryName, String fileName) {
    File directory = new File(directoryName);
    if (!directory.exists()) {
        directory.mkdirs();
    }
    File file = new File(directory,fileName);
    if (!file.exists()) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new Error("A fatal error has occurs in creating the file");
        }
    }
    return file;
}

}

