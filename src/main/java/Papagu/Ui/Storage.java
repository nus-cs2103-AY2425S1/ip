package Papagu.Ui;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalTime;

/**
 * Deals with loading and saving tasks to file(Tasks.txt)
 */
public class Storage {
    private String filePath;
    private TaskList taskList;
    private File file;

    /**
     * Constructor for Storage
     *
     * @param filePath File path of the file to be saved to
     * @param taskList TaskList object to be saved
     * @param file File object to be saved to
     */
    public Storage(String filePath, TaskList taskList, File file) {
        this.filePath = filePath;
        this.taskList = taskList;
        this.file = file;
    }

    /**
     * For remaking the file object each time a new task is created
     */
    public void save() {
        try {
            this.file.delete();
            File newFile = new File(this.filePath);
            newFile.getParentFile().mkdirs();
            for (int i = 0; i < taskList.getTaskCount(); i++) {
                FileWriter fileWriter = new FileWriter(newFile, true);
                fileWriter.write(taskList.getTask(i).toFile() + "\n");
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}