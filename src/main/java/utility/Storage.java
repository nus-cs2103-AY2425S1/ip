package utility;

import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String storagePath;

    public Storage(String storagePath) {
        this.storagePath = storagePath;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        File saveFile = new File(storagePath);
        if (!saveFile.exists()) {
            File dir = new File("./data");
            boolean dirCreated = dir.mkdir();
            if (dirCreated && !saveFile.createNewFile()) {
                throw new FileNotFoundException("Could not create file " + saveFile.getAbsolutePath());
            }
        }
        FileWriter fw = new FileWriter(storagePath);
        for (Task task : tasks) {
            fw.write(task.getSaveFormat() + "\n");
        }
        fw.close();
    }

    public ArrayList<Task> load() throws IOException {
        File saveFile = new File(storagePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!saveFile.exists()) {
            return tasks;
        }
        Scanner sc = new Scanner(saveFile);

        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            switch (line.charAt(0)) {
            case 'T':
                tasks.add(Todo.load(line));
                break;
            case 'D':
                tasks.add(DeadLine.load(line));
                break;
            case 'E':
                tasks.add(Event.load(line));
                break;
            }
        }
        sc.close();
        return tasks;
    }
}
