package babblebot.storage;

// Standard Java packages
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// Project-specific packages
import babblebot.task.Deadline;
import babblebot.task.Event;
import babblebot.task.Task;
import babblebot.task.TaskList;
import babblebot.task.TentativeEvent;
import babblebot.task.Todo;

/**
 * The Storage class handles the loading and saving of tasks to and from a file.
 * It provides methods to load tasks from a specified file path and save the current list of tasks to a file.
 */
public class Storage {

    private String path;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param path The file path to store the tasks.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads the tasks from the file at the specified path.
     * If the file does not exist, an empty TaskList is returned.
     *
     * @return A TaskList containing all the tasks loaded from the file.
     * @throws IOException If there is an error reading from the file.
     */
    public TaskList load() throws IOException {
        TaskList storedTasks = new TaskList();
        File file = new File(this.path);
        if (!file.exists()) {
            return storedTasks; // No file exists, start with an empty task list
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] taskData = line.split(" \\| ");
            Task task;
            switch (taskData[0]) {
            case "T":
                task = new Todo(taskData[2]);
                break;
            case "D":
                task = new Deadline(taskData[2], taskData[3]);
                break;
            case "E":
                String description = taskData[2];
                String[] fromTo = taskData[3].split(" to ");
                String from = fromTo[0];
                String to = fromTo[1];
                task = new Event(description, from, to);
                break;
            case "P": // TentativeEvent (P for Pending)
                String tentativeDescription = taskData[2];
                ArrayList<LocalDate[]> timeSlots = new ArrayList<>();
                String[] slots = taskData[3].split(", ");
                for (String slot : slots) {
                    String[] dates = slot.split(" to ");
                    LocalDate startDate = LocalDate.parse(dates[0], formatter);
                    LocalDate endDate = LocalDate.parse(dates[1], formatter);
                    timeSlots.add(new LocalDate[]{startDate, endDate});
                }
                TentativeEvent tentativeEvent = new TentativeEvent(tentativeDescription, timeSlots);
                if (taskData.length == 5 && taskData[4].startsWith("Confirmed: ")) {
                    // Confirm the event if it was previously confirmed
                    tentativeEvent.confirmSlotByIndex(Integer.parseInt(taskData[4].split(": ")[1]) - 1);
                }
                task = tentativeEvent;
                break;
            default:
                continue; // Skip unknown task types
            }
            if (taskData[1].equals("1")) {
                task.markAsDone();
            }
            storedTasks.addTask(task);
        }
        scanner.close();
        return storedTasks;
    }

    /**
     * Saves the current list of tasks to the file at the specified path.
     * If the directories do not exist, they will be created.
     *
     * @param storedTasks The TaskList to be saved to the file.
     * @throws IOException If there is an error writing to the file.
     */
    public void save(TaskList storedTasks) throws IOException {
        File f = new File(this.path);
        f.getParentFile().mkdirs(); // create directories if they don't exist
        FileWriter fw = new FileWriter(this.path);
        for (Task task : storedTasks.getAllTasks()) {
            fw.write(task.toFileFormat() + "\n");
        }
        fw.close();
    }
}
