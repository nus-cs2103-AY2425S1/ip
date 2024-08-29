package data;

import features.task.DeadlineTask;
import features.task.EventTask;
import features.task.TodoTask;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import features.task.Task;
import config.Config;
public class CSVTaskDAO implements TaskDAO {
    private List<Task> tasks;
    private static final String CSV_FILE = Config.CSV_FILE_PATH;

    public CSVTaskDAO() throws Exception {
        tasks = readTasksFromCSV();
    }
    public static List<Task> readTasksFromCSV() throws Exception {
        List<Task> tasks = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(CSV_FILE));
        String line = br.readLine();

        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");

            if (fields.length < 4) continue;
            int id = Integer.valueOf(fields[0]);
            String type = fields[1].trim();
            boolean isMarked = "1".equals(fields[2].trim());
            String description = fields[3].trim();
            String extraData = fields.length > 4 ? fields[4].trim() : "";

            Task task = null;

            switch (type) {
                case "T":
                    task = new TodoTask(description);
                    break;
                case "D":
                    task = new DeadlineTask(description, extraData);
                    break;
                case "E":
                    String[] eventTimes = extraData.split("-");
                    if (eventTimes.length == 2) {
                        String startAt = eventTimes[0].trim();
                        String endAt = eventTimes[1].trim();
                        task = new EventTask(description, startAt, endAt);
                    } else {
                        throw new Exception("Invalid format for EventTask in CSV.");
                    }
                    break;
                default:
                    System.out.println("Unknown task type in CSV: " + type);
            }

            if (task != null) {
                task.setIsMarked(isMarked);
                task.setId(id);
                tasks.add(task);
            }
        }

        return tasks;
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks;
    }

    @Override
    public Optional<Task> findTaskById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    @Override
    public void addTask(Task task) {
        task.setId(tasks.size() + 1);
        tasks.add(task);
        writeTasksToCSV();
    }

    @Override
    public void updateTask(Task task) {
        writeTasksToCSV();
    }

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

    private void writeTasksToCSV() {
        try (FileWriter writer = new FileWriter(CSV_FILE, false)) {
            writer.write("id,type,isMarked,description,extraData\n");
            for (Task task : tasks) {
                writer.write(convertTaskToCSVFormat(task));
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertTaskToCSVFormat(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getId() + ",");
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
            sb.append(((EventTask) task).getStartAt()).append("-").append(((EventTask) task).getEndAt());
        }
        return sb.toString();
    }

}
