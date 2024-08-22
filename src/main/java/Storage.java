import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> load() throws MoiMoiException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner sc = new Scanner(new File(this.path));
            while (sc.hasNextLine()) {
                String[] taskInfo = sc.nextLine().split(" \\| ");
                tasks.add(this.createTask(taskInfo));
            }
        } catch (FileNotFoundException e) {
            this.createFile();
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws StorageIOException {
        try {
            FileWriter fileWriter = new FileWriter(this.path);
            for (Task task : tasks) {
                fileWriter.write(task.stringStorage() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new StorageIOException();
        }
    }

    private Task createTask(String[] taskInfo) throws StorageCorruptedException {
        try {
            Task task;
            switch (taskInfo[0]) {
            case "T":
                task = new Todo(taskInfo[2]);
                break;
            case "D":
                task = new Deadline(taskInfo[2], taskInfo[3]);
                break;
            case "E":
                task = new Event(taskInfo[2], taskInfo[3], taskInfo[4]);
                break;
            default:
                throw new StorageCorruptedException();
            }

            String taskStatusIcon = taskInfo[1];
            if (taskStatusIcon.equals("X")) {
                task.mark();
            } else if (!taskStatusIcon.equals(" ")) {
                throw new StorageCorruptedException();
            }

            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new StorageCorruptedException();
        }
    }

    private void createFile() throws StorageIOException {
        try {
            File file = new File(this.path);
            File folder = new File(file.getParent());
            folder.mkdir();
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageIOException();
        }
    }

}
