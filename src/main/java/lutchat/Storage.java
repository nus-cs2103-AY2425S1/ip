package lutchat;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();


        File file = new File(filePath);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
            return taskList;
        }

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            String[] parts = data.split(" \\| ");
            if (parts.length < 3) {
                System.out.println("Skipping corrupted data: " + data);
                continue;
            }

            String taskType = parts[0];
            boolean done = parts[1].equals("1");
            String desc = parts[2];

            Task task = null;
            switch (taskType) {
                case "T":
                    task = new Todo(desc);
                    break;
                case "D":
                    if (parts.length < 4) {
                        System.out.println("Skipping corrupted line: " + data);
                        continue;
                    }
                    task = new Deadline(desc, parts[3]);
                    break;
                case "E":
                    if (parts.length < 5) {
                        System.out.println("Skipping corrupted line: " + data);
                        continue;
                    }
                    task = new Event(desc, parts[3], parts[4]);
                    break;
                default:
                    System.out.println("Skipping unknown task type: " + taskType);
                    continue;
            }

            if (task != null) {
                if (done) {
                    task.markAsDone();
                }
                taskList.add(task);
            }
        }
        sc.close();
        return taskList;
    }

    public void save(TaskList taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList.getTasks()) {
                writer.write(task.toFileFormat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oh no! An error occurred while saving tasks...");
        }
    }
}
