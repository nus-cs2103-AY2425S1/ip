import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_NAME = "./data/store.txt";
        public void saveTasks(ArrayList<Task> store) {
        try {
            System.out.println("Saving tasks...");
            File file = new File(FILE_NAME);
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (Task task : store) {
                writer.write(task.toSaveString() + "\n");
            }
            writer.close();
            System.out.println("Tasks saved!");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("An error occurred, tasks not saved.");
        }
    }

    public ArrayList<Task> loadTasks() {
        try {
            ArrayList<Task> store = new ArrayList<>();
            System.out.println("Loading tasks...");
            File file = new File(FILE_NAME);
            file.getParentFile().mkdirs();
            file.createNewFile();
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] parts = data.split("\\|");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String task = parts[2];
                if (type.equals("T")) {
                    store.add(new TaskTodo(task));
                } else if (type.equals("D")) {
                    String by = parts[3];
                    store.add(new TaskDeadline(task, by));
                } else if (type.equals("E")) {
                    String from = parts[3];
                    String to = parts[4];
                    store.add(new TaskEvent(task, from, to));
                }
                if (isDone) {
                    store.get(store.size() - 1).toggleDone();
                }
            }
            reader.close();
            System.out.println("Tasks loaded!");
            return store;
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("An error occurred, tasks not loaded.");
            return new ArrayList<>();
        }
    }
}
