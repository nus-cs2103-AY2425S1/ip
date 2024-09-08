package opus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load(){
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try{
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                switch (taskType) {
                    case "T":
                        tasks.add(new ToDo(parts[2]));
                        break;
                    case "D":
                        tasks.add(new Deadline(parts[2], parts[3]));
                        break;
                    case "E":
                        tasks.add(new Event(parts[2], parts[3], parts[4]));
                        break;
                }
                if (isDone) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
            scanner.close();
            return tasks;
        }
        catch (IOException e) {
            return tasks;
        }
    }

    public void save(ArrayList<Task> tasks){
        File file = new File(filePath);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs(); 
        }

        try{
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + "\n");
            }
            writer.close();
        }
        catch (IOException e){
            return;
        }
    }
}
