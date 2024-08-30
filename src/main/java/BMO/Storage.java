package BMO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import BMO.task.Task;

import java.io.FileWriter;

public class Storage {
    private FileWriter writer = null;

    public Storage(String filePath) throws IOException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new IOException("File not found");
        }
        this.writer = new FileWriter(filePath, true);
    }

    public FileWriter getWriter() {
        return this.writer;
    }

    public void closeWriter() throws IOException {
        this.writer.close();
    }

    public void updateStorageFile(TaskList tasks) throws IOException {
        this.writer.close();
        FileWriter writer = new FileWriter("ip/data/BMO.txt");
        this.writer = writer;
        for (Task task : tasks.getTasks()) {
            this.writer.write(task.getSavedFormat());
        }
    }

    public void readStorageFile(TaskList tasks, String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
             String line;
             while ((line = reader.readLine()) != null) {
                String[] taskDetails = line.split(" \\| ");
                switch (taskDetails[0]) {
                case "T":
                    tasks.addTodo(taskDetails[2]);
                    break;
                case "D":
                    tasks.addDeadline(taskDetails[2], taskDetails[3]);
                    break;
                case "E":
                    tasks.addEvent(taskDetails[2], taskDetails[3], taskDetails[4]);
                    break;
                default:
                    break;
                }
           
                if (taskDetails[1].equals("1")) {
                    tasks.getTask(tasks.getSize() - 1).mark();
                }
                this.updateStorageFile(tasks);
             }
        } catch (IOException e) {
            throw new IOException("File not found");
        }
    }

    public void saveTask(Task task) throws IOException {
        this.writer.write(task.getSavedFormat());
    }
}