import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> listOfTasks) throws IOException {
        File file = new File(this.filePath);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (Task task : listOfTasks) {
            String typeOfTaskString = task.typeOfTaskString();
            String statusString = task.statusString();
            String taskString = task.savedTaskString();

            writer.write(typeOfTaskString + "| " + statusString + "| " + taskString);
            writer.newLine();
        }
        writer.close();
    }

    public ArrayList<Task> load() throws IOException, EdithException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        File file = new File(this.filePath);
        File directory = new File(file.getParent());

        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
            return listOfTasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] taskData = line.split("\\|");
            String typeOfTaskString = taskData[0].trim();
            String statusString = taskData[1].trim();
            String taskString = taskData[2].trim();
            Task task;

            switch (typeOfTaskString) {
            case "[T]":
                task = new ToDo(taskString);
                break;
            case "[D]":
                String[] deadlineParts = taskString.split(" /by ");
                String deadlineTask = deadlineParts[0].trim();
                String dueDate = deadlineParts[1].trim();
                task = new Deadline(deadlineTask, dueDate);
                break;
            case "[E]":
                String[] eventParts = taskString.split(" /from | /to ");
                String eventTask = eventParts[0].trim();
                String startTime = eventParts[1].trim();
                String endTime = eventParts[2].trim();
                task = new Event(eventTask, startTime, endTime);
                break;
            default:
                throw new EdithException("Unknown task type found in saved task list.");
            }

            if (statusString.equals("[X]")) {
                task.markTaskDone();
            }

            listOfTasks.add(task);
        }
        return listOfTasks;
    }
}
