package fishman.utils;

import fishman.exception.FishmanException;
import fishman.task.Deadline;
import fishman.task.Event;
import fishman.task.Task;
import fishman.task.TaskList;
import fishman.task.ToDo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Storage {
    private final Path filePath;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        createFileIfDoesNotExist();
    }

    private void createFileIfDoesNotExist() {
        try {
            Files.createDirectories(filePath.getParent());
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public void save(TaskList tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(toCsv(task));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error saving tasks to file: " + e.getMessage(), e);
        }
    }

    private String toCsv(Task task) {
        StringBuilder sb = new StringBuilder();
        if(task instanceof ToDo) {
            sb.append("T").append(task.getStatus() ? "|1" : "|0").append("|").append(task.getDescription());
        } else if(task instanceof Deadline) {
            sb.append("D").append(task.getStatus() ? "|1" : "|0").append("|").append(task.getDescription()).append("|")
                            .append(((Deadline) task).getBy().format(DATE_TIME_FORMATTER));
        } else if(task instanceof Event) {
            sb.append("E").append(task.getStatus() ? "|1" : "|0").append("|").append(task.getDescription()).append("|")
                            .append(((Event) task).getFrom().format(DATE_TIME_FORMATTER)).append("|")
                            .append(((Event) task).getTo().format(DATE_TIME_FORMATTER));
        }
        return sb.toString();
    }

    public TaskList load() throws FishmanException {
        TaskList tasks = new TaskList();

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] arguments = line.split("\\|", -1);
                if (arguments.length < 3) {
                    throw new FishmanException.InvalidArgumentsException(line);
                }
                String type = arguments[0];
                boolean isDone = arguments[1].equals("1");
                String description = arguments[2];

                switch (type) {
                case "T":
                    if (arguments.length != 3) {
                        throw new FishmanException.InvalidArgumentsException(line);
                    }
                    tasks.addTask(new ToDo(description, isDone));
                    break;
                case "D":
                    if (arguments.length != 4) {
                        throw new FishmanException.InvalidArgumentsException(line);
                    }
                    String deadline = arguments[3];
                    LocalDateTime deadlineDate = parseDateTime(deadline);
                    tasks.addTask(new Deadline(description, isDone, deadlineDate));
                    break;
                case "E":
                    if (arguments.length != 5) {
                        throw new FishmanException.InvalidArgumentsException(line);
                    }
                    String from = arguments[3];
                    String to = arguments[4];
                    LocalDateTime fromDate = parseDateTime(from);
                    LocalDateTime toDate = parseDateTime(to);
                    tasks.addTask(new Event(description, isDone, fromDate, toDate));
                    break;
                default:
                    throw new FishmanException.InvalidArgumentsException("Empty line or unknown task type in line: "
                                    + "<" + line + ">");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
        return tasks;
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) throws FishmanException {
        try {
            return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new FishmanException.InvalidDateFormatException(dateTimeStr);
        }
    }

}