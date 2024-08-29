import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;


public class FileSaver {
    private final String filePath;

    public FileSaver(String filePath) {
        this.filePath = filePath;
    }

    public void saveToFile(ArrayList<Task> taskList) throws BigdogException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            for (Task task: taskList) {
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
                    throw new BigdogException("Oops no task detected!");
                }
                writer.write(line + "\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("You do not have a .txt file to store my memory! File Path: " + this.filePath);
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
    }

    public void loadFromFile(ArrayList<Task> taskList) throws BigdogException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.charAt(0) == 'X') {
                    taskList.add(Task.of(line.substring(4), true));
                } else if (line.charAt(0) == 'O') {
                    taskList.add(Task.of(line.substring(4), false));
                } else {
                    throw new BigdogException("data file is corrupted!");
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("You do not have a .txt file to read! File Path: " + this.filePath);
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
    }


}

