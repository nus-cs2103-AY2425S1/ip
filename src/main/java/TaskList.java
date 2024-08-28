import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TaskList {
    private final ArrayList<Task> tasks;
    private int taskCount;
    private final String fileName;
    private final String folderPath;

    public TaskList(String fileName) {
        this.tasks = new ArrayList<>();
        this.fileName = fileName;
        this.folderPath = "./data";
        this.taskCount = 0;

        try {
            this.loadTasks();
        } catch (DataIOException | InvalidDataFormatException e) {
            this.tasks.clear();
            this.taskCount = 0;
        }
    }

    public Task markAsDone(int taskNumber) throws DataIOException {
        this.tasks.get(taskNumber - 1).markAsDone();
        this.saveTasks();
        return this.tasks.get(taskNumber - 1);
    }

    public Task markAsNotDone(int taskNumber) throws DataIOException {
        this.tasks.get(taskNumber - 1).markAsNotDone();
        this.saveTasks();
        return this.tasks.get(taskNumber - 1);
    }

    public boolean isValidTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= this.taskCount;
    }

    public boolean isEmpty() {
        return this.taskCount == 0;
    }

    public void addTask(Task task) throws DataIOException {
        this.tasks.add(task);
        this.taskCount++;
        this.saveTasks();
    }

    public Task removeTask(int taskNumber) throws DataIOException {
        Task task = this.tasks.get(taskNumber - 1);
        this.taskCount--;

        for (int i = taskNumber - 1; i < this.taskCount; i++) {
            this.tasks.set(i, this.tasks.get(i + 1));
        }

        this.tasks.remove(this.taskCount);
        this.saveTasks();

        return task;
    }

    @Override
    public String toString() {
        StringBuilder tasksStr = new StringBuilder();

        for (int i = 0; i < this.taskCount; i++) {
            if (i == this.taskCount - 1) {
                tasksStr.append((i + 1)).append(". ").append(this.tasks.get(i));
            } else {
                tasksStr.append((i + 1)).append(". ").append(this.tasks.get(i)).append("\n");
            }
        }

        return tasksStr.toString();
    }

    public String getTaskCount() {
        return "Now you have " + this.taskCount + " tasks in the list.";
    }

    private String getSimpleTaskList() {
        StringBuilder tasksStr = new StringBuilder();

        for (int i = 0; i < this.taskCount; i++) {
            if (i == this.taskCount - 1) {
                tasksStr.append(this.tasks.get(i).simpleFormat());
            } else {
                tasksStr.append(this.tasks.get(i).simpleFormat()).append("\n");
            }
        }

        return tasksStr.toString();
    }

    private void saveTasks() throws DataIOException {
        Path folderPath = Paths.get(this.folderPath);
        Path filePath = Paths.get(this.folderPath + "/" + this.fileName);

        try {
            // Create the folder if it does not exist
            if (Files.notExists(folderPath)) {
                Files.createDirectories(folderPath);
            }
            // Open or create the file if it does not exist
            Files.write(filePath, this.getSimpleTaskList().getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new DataIOException("OOPS!!! There was an error saving the tasks to the file.");
        }
    }

    private void loadTasks() throws DataIOException, InvalidDataFormatException {
        Path filePath = Paths.get(this.folderPath + "/" + this.fileName);

        try {
            if (Files.notExists(filePath)) {
                throw new DataIOException("OOPS!!! The file does not exist.");
            }

            String fileContent = Files.readString(filePath);
            String[] tasksStr = fileContent.split("\n");

            for (String taskStr : tasksStr) {
                String[] taskDetails = taskStr.split("\\|");
                Task.TYPE taskType = switch (taskDetails[0].trim()) {
                    case "T" -> Task.TYPE.TODO;
                    case "D" -> Task.TYPE.DEADLINE;
                    case "E" -> Task.TYPE.EVENT;
                    default ->
                            throw new InvalidDataFormatException("OOPS!!! " +
                                    "There was an error loading the tasks from the file.");
                };
                boolean isDone = taskDetails[1].trim().equals("1");
                String description = taskDetails[2].trim();

                switch (taskType) {
                    case TODO:
                        this.tasks.add(new ToDoTask(description, isDone));
                        break;
                    case DEADLINE:
                        this.tasks.add(new DeadlineTask(description, isDone, taskDetails[3].trim()));
                        break;
                    case EVENT:
                        this.tasks.add(new EventTask(description, isDone, taskDetails[3].trim(),
                                taskDetails[4].trim(), taskDetails[5].trim()));
                        break;
                    default:
                        throw new DataIOException("OOPS!!! There was an error loading the tasks from the file.");
                }
            }

            this.taskCount = tasksStr.length;
        } catch (IOException e) {
            throw new DataIOException("OOPS!!! There was an error loading the tasks from the file.");
        } catch (InvalidDateException e) {
            throw new InvalidDataFormatException("OOPS!!! There was an error loading the tasks from the file." +
                    " Please check the date and time format.");
        }
    }
}
