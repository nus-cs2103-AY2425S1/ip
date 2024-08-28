package astra;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import astra.task.Task;

public class Storage {
    private static final String FILENAME = "/tasks.txt";
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> load() throws AstraException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(path + FILENAME);
        try {
            Scanner inp = new Scanner(f);
            while (inp.hasNextLine()) {
                String line = inp.nextLine();
                tasks.add(Task.fromText(line));
            }
            inp.close();
        } catch (FileNotFoundException e) {
            return tasks;
        } catch (Exception e) {
            throw new AstraException("Data file corrupted, failed to read all tasks.");
        }

        return tasks;
    }

    public void save(TaskList tasks) {
        String text = tasks.toText();

        try {
            File dir = new File(path);
            dir.mkdirs();
            FileWriter fw = new FileWriter(path + FILENAME);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
