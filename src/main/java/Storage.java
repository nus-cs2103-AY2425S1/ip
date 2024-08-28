import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class Storage {

    private String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(TaskList taskList) throws IOException {
        ArrayList<Task> tasks = taskList.getTasks();
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            if (task != null) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        }
        writer.close();

    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException, HueException{
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                System.out.println("Data file not found. Starting with an empty task list.");
                return tasks;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                loadTasksFromData(tasks, parts);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Data file not found. Starting with an empty task list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Data file is corrupted or not in the expected format.");
        }

        return tasks;
    }

    public void loadTasksFromData(ArrayList<Task> tasks, String[] data) throws HueException{
        String type = data[0];
        boolean isDone = data[1].equals("1");
        String descritpion = data[2];

        switch (type) {
        case "T":
            Task todoTask = new Todo(descritpion);
            if (isDone) {
                todoTask.markDone();
            }
            tasks.add(todoTask);
            break;
        case "D":
            String by = data[3];
            Task deadlineTask = new Deadline(descritpion, by);
            if (isDone) {
                deadlineTask.markDone();
            }
            tasks.add(deadlineTask);
            break;
        case "E":
            String from = data[3];
            String to = data[4];
            Task eventTask = new Event(descritpion, from, to);
            if (isDone) {
                eventTask.markDone();
            }
            tasks.add(eventTask);
            break;
        default:
            throw new HueException("Unknown task type in file:" + type);
        }



    }





}
