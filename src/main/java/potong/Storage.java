package potong;

import potong.exceptions.IllegalInputPotongException;

import potong.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String DIR_PATH = "./src/main/data";
    private final String FILE_PATH = DIR_PATH + "/potong.txt";

    private TaskList tasklist;

    public Storage(TaskList tasklist) throws FileNotFoundException, IllegalInputPotongException {
        this.tasklist = tasklist;
        this.loadFile();
    }

    public void createDirectory() {
        File d = new File(this.DIR_PATH);
        d.mkdirs();
    }

    public void createFile() {
        this.createDirectory();
        File f = new File(this.FILE_PATH);
    }
    public void loadFile() throws FileNotFoundException, IllegalInputPotongException {
        ArrayList<Task> result = new ArrayList<>(100);
        File f = new File(this.FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String curr = s.nextLine();
            if (curr.isEmpty()) {
                break;
            }
            Task nextTask = Parser.createTask(curr);
            result.add(nextTask);
        }
        this.tasklist.initialise(result);
    }

    public void writeToFile(String textToAdd) throws IOException {
        this.createFile();
        FileWriter fw = new FileWriter(this.FILE_PATH);
        fw.write(textToAdd);
        fw.close();
    }
}
