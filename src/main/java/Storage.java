import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private static final String DATA_FILE_PATH = "src/data/tasks.txt";

    /**
     * Saves the tasks to a file.
     */
    public void saveTask(ArrayList<Task> list) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH))) {
            for(Task task : list) {
                writer.write(getTaskData(task));
                writer.newLine();
            }
        } catch(IOException e) {
            System.out.println("Toothless:\nOh no! There is an error saving quests to file.");
        }
    }

    /**
     * Returns the data of the task to save in the text file.
     * @param task The task to be saved.
     * @return The data of the task to be saved in the text file.
     */
    private String getTaskData(Task task) {
        String taskType = task instanceof ToDos ? "T" :
                task instanceof Deadline ? "D" :
                        task instanceof Events ? "E" : "";
        String baseInfo = taskType + " | " + (task.isDone ? "1" : "0") + " | " + task.description;

        if (task instanceof Deadline) {
            return baseInfo + " | " + ((Deadline) task).deadline;
        } else if (task instanceof Events) {
            Events event = (Events) task;
            return baseInfo + " | " + event.eventStart + " | " + event.eventEnd;
        }
        return baseInfo;
    }

    /**
     * Loads the tasks from a file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> list = new ArrayList<>();
        list.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseDataToTask(line);
                if (task != null) {
                    list.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Toothless:\nOh no! There is an error loading quests from file.");
        }

        return list;
    }

    /**
     * Parses the data from the file to a task.
     * @param taskData The data of the task.
     * @return The task parsed from the data.
     */
    private Task parseDataToTask(String taskData) {
        String[] splitData = taskData.split(" \\| ");

        String taskType = splitData[0];
        boolean isDone = splitData[1].equals("1");
        String description = splitData[2];

        switch(taskType) {
            case "T":
                return new ToDos(description, isDone);
            case "D":
                String deadline = splitData[3];
                return new Deadline(description, LocalDateTime.parse(deadline), isDone);
            case "E":
                String eventStart = splitData[3];
                String eventEnd = splitData[4];
                return new Events(description, LocalDateTime.parse(eventStart), LocalDateTime.parse(eventEnd), isDone);
            default:
                return null;
        }
    }
}
