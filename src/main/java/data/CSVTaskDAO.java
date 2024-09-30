package data;

import exceptions.InvalidCsvFormatException;
import features.task.DeadlineTask;
import features.task.EventTask;
import features.task.TodoTask;
import features.task.Task;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import config.Config;

/**
 * A class that implements the TaskDAO interface using CSV files for persistence.
 */
public class CSVTaskDAO implements TaskDAO {
    private List<Task> tasks;
    private static final String CSV_FILE = Config.CSV_FILE_PATH;

    /**
     * Constructs a CSVTaskDAO object by reading tasks from the CSV file.
     *
     * @throws Exception if there's an error reading the CSV file.
     */
    public CSVTaskDAO() throws Exception {
        tasks = readTasksFromCSV();
    }

    /**
     * Reads tasks from a CSV file.
     *
     * @return a list of tasks read from the CSV file.
     * @throws Exception if there's an error reading the CSV file.
     */
    public static List<Task> readTasksFromCSV() throws Exception {
        List<Task> tasks = new ArrayList<>();
        Path jarDir = Paths.get(System.getProperty("user.dir"));

        // Resolve the path to the CSV file in the same directory as the JAR
        Path csvFilePath = jarDir.resolve(CSV_FILE);

        // Use a FileWriter to write to the CSV file
        File csvFile = csvFilePath.toFile();

        if (!csvFile.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
                String header = "id,type,isMarked,description,extraData";
                bw.write(header);
                bw.newLine();
                System.out.println("File created with headers: " + header);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        br.readLine();
        String line;

        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length < 4) continue;
            int id = Integer.parseInt(fields[0]);
            String type = fields[1].trim();
            boolean isMarked = "1".equals(fields[2].trim());
            String description = fields[3].trim();
            String extraData = fields.length > 4 ? fields[4].trim() : "";
            Task task = null;

            switch (type) {
                case "T" -> task = new TodoTask(description);
                case "D" -> task = new DeadlineTask(description, extraData);
                case "E" -> {
                    String[] eventTimes = extraData.split("~");
                    if (eventTimes.length == 2) {
                        String startAt = eventTimes[0].trim();
                        String endAt = eventTimes[1].trim();
                        task = new EventTask(description, startAt, endAt);
                    } else {
                        throw new InvalidCsvFormatException();
                    }
                }
                default -> System.out.println("Unknown task type in CSV: " + type);
            }

            if (task != null) {
                task.setIsMarked(isMarked);
                task.setId(id);
                tasks.add(task);
            }
        }

        return tasks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Task> findTaskById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTask(Task task) {
        task.setId(tasks.size() + 1);
        tasks.add(task);
        writeTasksToCSV();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTask(Task task) {
        writeTasksToCSV();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Task deleteTask(int id) {
        Task t = this.findTaskById(id).get();
        tasks = this.tasks.stream()
                .filter(task -> !task.getId().equals(id))
                .toList();
        IntStream.range(0, tasks.size())
                .forEach(i -> tasks.get(i).setId(i + 1));

        writeTasksToCSV();
        return t;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteTasks(List<Integer> ids) {
        tasks = this.tasks.stream()
                .filter(task -> !ids.contains(task.getId()))
                .toList();

        IntStream.range(0, tasks.size())
                .forEach(i -> tasks.get(i).setId(i + 1));

        writeTasksToCSV();
        return tasks.size();
    }

    /**
     * Writes tasks to the CSV file.
     */
    private void writeTasksToCSV() {
        Path jarDir = Paths.get(System.getProperty("user.dir"));
        // Resolve the path to the CSV file in the same directory as the JAR
        Path csvFilePath = jarDir.resolve(CSV_FILE);
        File csvFile = csvFilePath.toFile();
        try (FileWriter writer = new FileWriter(csvFile, false)) {
            writer.write("id,type,isMarked,description,extraData\n");
            for (Task task : tasks) {
                writer.write(convertTaskToCSVFormat(task));
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a task to a CSV format string.
     *
     * @param task the task to convert.
     * @return the task in CSV format.
     */
    private String convertTaskToCSVFormat(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getId()).append(",");
        if (task instanceof TodoTask) {
            sb.append("T,");
            sb.append(task.getIsMarked() ? "1," : "0,");
            sb.append(task.getName());
        } else if (task instanceof DeadlineTask) {
            sb.append("D,");
            sb.append(task.getIsMarked() ? "1," : "0,");
            sb.append(task.getName()).append(",");
            sb.append(((DeadlineTask) task).getDeadline());
        } else if (task instanceof EventTask) {
            sb.append("E,");
            sb.append(task.getIsMarked() ? "1," : "0,");
            sb.append(task.getName()).append(",");
            sb.append(((EventTask) task).getStartAt()).append("~").append(((EventTask) task).getEndAt());
        }
        return sb.toString();
    }
}
