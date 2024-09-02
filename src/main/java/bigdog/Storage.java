import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> taskList) throws BigdogException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            for (Task task: taskList) {
                String line = getString(task);
                writer.write(line + "\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Storage Error: You do not have a .txt file to store my memory! File Path: " + this.filePath);
        } catch (IOException e) {
            System.out.println("Storage Error: IO Error " + e);
        }
    }

    private static String getString(Task task) throws BigdogException {
        String line = "";
        if (task.isMarked()) {
            line += "X | ";
        } else {
            line += "O | ";
        }
        if (task instanceof Todo) {
            line += "T | ";
            line += task.getDescription();
        } else if (task instanceof Deadline) {
            line += "D | ";
            line += task.getDescription();
        } else if (task instanceof Event){
            line += "E | ";
            line += task.getDescription();
        } else {
            throw new BigdogException("Storage Error: Oops no task detected!");
        }
        return line;
    }

    public ArrayList<Task> load() throws BigdogException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.charAt(0) == 'X') {
                    list.add(Task.of(line.substring(4), true));
                } else if (line.charAt(0) == 'O') {
                    list.add(Task.of(line.substring(4), false));
                } else {
                    throw new BigdogException("Storage Error: data file is corrupted!");
                }
            }
            reader.close();
            return list;
        } catch (FileNotFoundException e) {
            throw new BigdogException("Storage Error: You do not have a .txt file to read! File Path: " + this.filePath);
        } catch (IOException e) {
            throw new BigdogException("Storage Error: IO Error: " + e.getMessage());
        }
    }
    
}
