import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskStorage {
    private ArrayList<Task> tasks;
    private int count;
    private static final String FILE_PATH = "./data/MGTOW.txt";
    private File saveFile;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public TaskStorage(){
        this.tasks = new ArrayList<>();
        this.count = 0;
        this.saveFile = new File(FILE_PATH);
        createDirectoryIfNotExists();
        loadTasks();
    }

    private void createDirectoryIfNotExists() {
        File directory = saveFile.getParentFile();
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("\tCreated directory: " + directory.getAbsolutePath());
            } else {
                System.out.println("\tFailed to create directory: " + directory.getAbsolutePath());
            }
        }
    }
    private void loadTasks() {
        if (!saveFile.exists()) {
            System.out.println("\tNo existing task file found. Starting with an empty task list.");
            return;
        }

        try (Scanner sc = new Scanner(saveFile)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String desc = parts[2];
                    Task task;
                    try {
                        switch (type) {
                            case "T":
                                task = new ToDo(desc);
                                break;
                            case "D":
                                String deadline = parts.length > 3 ? parts[3] : "";
                                task = new Deadline(desc, deadline);
                                break;
                            case "E":
                                String start = parts.length > 3 ? parts[3] : "";
                                String end = parts.length > 4 ? parts[4] : "";
                                task = new Event(desc, start, end);
                                break;
                            default:
                                continue;
                        }
                        if (isDone) {
                            task.markDone();
                        }
                        tasks.add(task);
                        count++;
                    } catch (InvalidTaskException e) {
                        System.out.println("\tError loading task: " + e.getMessage());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\tError loading tasks: " + e.getMessage());
        }
    }


    public void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(saveFile))) {
            for (Task task : tasks) {
                String line = getTaskString(task);
                writer.println(line);
            }
        } catch (IOException e) {
            System.out.println("\tError saving tasks: " + e.getMessage());
        }
    }

    private String getTaskString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getType()).append("|");
        sb.append(task.getStatus().equals("X") ? "1" : "0").append("|");
        sb.append(task.getDesc());
        if (task instanceof Deadline) {
            sb.append("|").append(((Deadline) task).getDeadline());
        } else if (task instanceof Event) {
            sb.append("|").append(((Event) task).getStart());
            sb.append("|").append(((Event) task).getEnd());
        }
        return sb.toString();
    }

    public ArrayList<Task> getTasksOnDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> {
                    if (task instanceof Deadline) {
                        LocalDateTime deadlineDate = ((Deadline) task).getEnd();
                        return deadlineDate.toLocalDate().equals(date);
                    } else if (task instanceof Event) {
                        // Assuming Event class has been updated to use LocalDateTime
                        LocalDateTime eventStart = ((Event) task).getStartDateTime();
                        LocalDateTime eventEnd = ((Event) task).getEndDateTime();
                        return (eventStart.toLocalDate().equals(date) || eventEnd.toLocalDate().equals(date));
                    }
                    return false;
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void addTask(Task task) throws InvalidTaskException{
        this.tasks.add(task);
        System.out.println("\tOk can. I've added this task:");
        System.out.println("\t  " + task);
        this.count++;
        System.out.println("\tNow you have " + this.count + " tasks in the list.");
        saveTasks();
    }

    public void deleteTask(int i) throws InvalidTaskException {
        if (i < 0 || i >= this.count) {
            throw new InvalidTaskException("Don't bother, task non-existent");
        }
        Task task = this.tasks.get(i);
        this.tasks.remove(i);
        System.out.println("\tSigh. I've removed this task:");
        System.out.println("\t  " + task);
        this.count--;
        System.out.println("\tNow you have " + this.count + " tasks in the list.");
        saveTasks();
    }

    public void markTask(int i) throws InvalidTaskException {
        if (i < 0 || i >= this.count) {
            throw new InvalidTaskException("Don't bother, task non-existent");
        }
        Task task = this.tasks.get(i);
        task.markDone();
        System.out.println("\t0.o I've marked this as done: ");
        System.out.println("\t  " + task);
        saveTasks();
    }

    public void unmarkTask(int i) throws InvalidTaskException {
        if (i < 0 || i >= this.count) {
            throw new InvalidTaskException("Don't bother, task non-existent");
        }
        Task task = this.tasks.get(i);
        task.markNotDone();
        System.out.println("\tAiyo, the task is marked as not done: ");
        System.out.println("\t  " + task);
        saveTasks();
    }

    public void listAllTasks() {
        System.out.println("\tSo many things to do...");
        for (int i = 0; i < count; i++) {
            int index = i + 1;
            Task task = tasks.get(i);
            System.out.println("\t" + index + ". " + task);
        }
    }
}
