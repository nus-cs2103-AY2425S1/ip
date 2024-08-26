import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TrackBotStorage {
    String filePath;
    File file;

    public TrackBotStorage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        createNewFile();
    }

    public void createNewFile() throws IOException {
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void loadContents(List<Task> list) throws FileNotFoundException {
        if (!file.exists()) {
            System.out.println("Data file does not exist. A new file will be created.");
            return;
        }

        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parts = line.split(" \\| ");
            String taskType = parts[0];
            boolean isDone = parts[1].equals("1");
            Task task;

            switch (taskType) {
                case "T":
                    task = new ToDo(parts[2]);
                    break;
                case "D":
                    task = new Deadline(parts[2], parts[3]);
                    break;
                case "E":
                    task = new Event(parts[2], parts[3], parts[4]);
                    break;
                default:
                    continue;
            }
            if (isDone) {
                task.mark();
            }
            list.add(task);
        }
        s.close();
    }

    public void saveContents(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task : list) {
            fw.write(task.toStorageFormat() + System.lineSeparator());
        }
        fw.close();
    }

    public void printFileContents() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
        s.close();
    }


    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

}
