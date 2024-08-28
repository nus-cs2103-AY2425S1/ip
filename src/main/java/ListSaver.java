import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListSaver {
    private final String filePath;

    public ListSaver(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> getList() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                case "T":
                    taskList.add(new ToDos(parts[1], parts[2]));
                    break;
                case "D":
                    taskList.add(new Deadlines(parts[1], parts[2], parts[3]));
                    break;
                case "E":
                    taskList.add(new Events(parts[1], parts[2], parts[3], parts[4]));
                    break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IO Error found: " + e);
        }

        return taskList;
    }

    public void saveList(ArrayList<Task> taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList) {
                String taskLine = "";
                if (task instanceof ToDos) {
                    taskLine += "T | ";
                    taskLine += task.description;
                } else if (task instanceof Deadlines deadline) {
                    taskLine += "D | ";
                    taskLine += task.description;
                    taskLine += "| ";
                    taskLine += deadline.getDeadlineOfTask();
                } else if (task instanceof Events event) {
                    taskLine += "E | ";
                    taskLine += task.description;
                    taskLine += "| ";
                    taskLine += event.getStartOfEvent();
                    taskLine += "| ";
                    taskLine += event.getEndOfEvent();
                }
                if (task.getDone()) {
                    taskLine += " | 1\n";
                } else {
                    taskLine += " | 0\n";
                }
                writer.write(taskLine);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IO Error found: " + e);
        }
    }
}