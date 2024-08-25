import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;


public class TaskManager {
    private ArrayList<Task> taskList = new ArrayList<>();  
    private int listIndex = 0;
    private static final String FILE_PATH = "./data/tasks.txt";

    public TaskManager() {
        this.taskList = new ArrayList<>();
        this.listIndex = 0;
        loadTasks();
    }

    private void saveTasks() {
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();
        if (!directory.exists() && directory != null) {
            directory.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : taskList) {
                writer.write(task.getTaskTypeIcon() + " | " 
                            + (task.isDone ? "1" : "0") + " | " 
                            + task.getSaveFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                Task task = null;

                switch (taskType) {
                    case "[T]":
                        task = new Todo(parts[2]);
                        break;
                    case "[D]":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "[E]":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                }

                if (task != null) {
                    if (isDone) {
                        task.mark();
                    }
                    taskList.add(task);
                    listIndex++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found. Starting fresh...");
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
    }

    public String[] add(TaskType taskType, String input) throws GladosException {
        switch (taskType) {
        case TODO:
            String todoDescription = input.trim();
            checkDescription(todoDescription);
            taskList.add(new Todo(todoDescription));
            break;
        case EVENT:
            checkDescription(input.trim());
            String[] eventInputs = input.split(" /from ");
            String eventDescription = eventInputs[0].trim();
            checkDescription(eventDescription);
            if (eventInputs.length != 2) {
                throw new DateRangeNotFoundException();
            }
            String[] dateRange = eventInputs[1].split(" /to ");
            if (dateRange.length != 2 || dateRange[0].trim().equals("") || dateRange[1].trim().equals("")) {
                throw new DateRangeNotFoundException();
            }
            taskList.add(new Event(eventInputs[0].trim(), dateRange[0].trim(), dateRange[1].trim()));
            break;
        case DEADLINE:
            checkDescription(input.trim());
            String[] deadlineInputs = input.split(" /by ");
            String deadlineDescription = deadlineInputs[0].trim();
            checkDescription(deadlineDescription);
            if (deadlineInputs.length != 2 || deadlineInputs[1].trim().equals("")) {
                throw new DateNotFoundException();
            }
            taskList.add(new Deadline(deadlineInputs[0].trim(), deadlineInputs[1].trim()));
            break;
        default:
            break;
        }
        listIndex++;
        saveTasks();
        return new String[]{taskList.get(listIndex - 1).toString(), String.valueOf(listIndex)};
    }

    public String[] delete(int index) throws TaskNotFoundException{
        if (index - 1 < 0 || index - 1 >= listIndex) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.remove(index - 1);
        listIndex--;
        saveTasks();
        return new String[]{task.toString(), String.valueOf(listIndex)};
    }

    public ArrayList<Task> list() {
        return this.taskList;
    }

    public String mark(int index) {
        Task task = taskList.get(index - 1);
        task.mark();
        saveTasks();
        return task.toString();
    }

    public String unmark(int index) {
        Task task = taskList.get(index - 1);
        task.unmark();
        saveTasks();
        return task.toString();
    }

    private static void checkDescription(String description) throws DescriptionNotFoundException {
        if (description.equals("")) {
           throw new DescriptionNotFoundException();
        }
   }
}
