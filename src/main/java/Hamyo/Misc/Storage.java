package Hamyo.Misc;

import Hamyo.Tasks.Deadline;
import Hamyo.Tasks.Event;
import Hamyo.Tasks.Task;
import Hamyo.Tasks.TaskList;
import Hamyo.Tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage - deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private final String path;
    private File file;

    public Storage(String path) {
        this.path = path;
        try {
            File tempFile = new File("./savedTasks.txt");
            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }
            this.file = tempFile;
        } catch (IOException e) {
            UI.printException(e);
        }
    }

    public void loadData(TaskList tasks) throws HamyoException {
        try {
            Scanner scannedTasks = new Scanner(this.file);
            int currTask = 0;
            while (scannedTasks.hasNext()) {
                currTask++;
                String[] task = scannedTasks.nextLine().split(" \\| ");
                Task tempTask;
                switch (task[0]) {
                case "T":
                    tempTask = new ToDo(new String[]{task[2]});
                    break;
                case "D":
                    tempTask = new Deadline(new String[]{task[2], task[3]});
                    break;
                case "E":
                    tempTask = new Event(new String[]{task[2], task[3], task[4]});
                    break;
                default:
                    throw new HamyoException("Invalid case " + task[0] + ".");
                }
                switch (task[1]) {
                case "1":
                    tempTask.mark(false);
                    break;
                case "0":
                    break;
                default:
                    throw new HamyoException("Invalid boolean " + task[1] + ".");
                }
                tasks.add(tempTask);
            }
        } catch (HamyoException e) {
            throw new HamyoException("Possible File Corruption. " + e.getMessage());
        } catch (IOException e) {
            throw new HamyoException(e.getMessage());
        }
    }

    public void saveData(TaskList tasks) throws HamyoException {
        try {
            FileWriter fileWriter = new FileWriter(this.path);
            StringBuilder newData = new StringBuilder();
            for (Task task : tasks) {
                newData.append(task.toFileFormat()).append(System.lineSeparator());
            }
            fileWriter.write(newData.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new HamyoException(e.getMessage());
        }
    }
}
