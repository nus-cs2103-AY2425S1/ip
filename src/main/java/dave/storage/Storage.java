package dave.storage;

import dave.task.Task;
import dave.task.TaskList;
import dave.task.Event;
import dave.task.Deadline;
import dave.task.Todo;
import dave.exceptions.InvalidDescriptionException;
import dave.exceptions.InvalidDateTimeFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Storage {
    private static String filePath;

    String horizontal = "__________________________________________________________";

    public Storage(String file, TaskList taskList) {
        try {
            this.filePath = file;
            taskList.setTaskList(loadFile());
        } catch (IOException e) {
            System.out.println(horizontal);
            System.out.println("An error occurred while saving the task to the file.");
        }
    }

    public static void saveFile(TaskList dataList) throws IOException
    {
        File fileObj = new File(filePath);
        fileObj.getParentFile().mkdirs();
        if (!fileObj.exists()) {
            fileObj.createNewFile();
        }
        FileWriter fw = new FileWriter(fileObj);
        for (int i = 0; i < dataList.getSize(); i++)
        {
            fw.write(dataList.getTask(i).write());
        }
        fw.close();
    }

    public static void clearFile() throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        fw.write("");
        fw.close();
    }

    public static void amendFile(Task task) throws IOException
    {
        File fileObj = new File(filePath);
        fileObj.getParentFile().mkdirs();
        if (!fileObj.exists()) {
            fileObj.createNewFile();
        }
        FileWriter fw = new FileWriter(fileObj, true);
        fw.write(task.write());
        fw.close();
    }

    public ArrayList<Task> loadFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;  // Return empty list if file doesn't exist
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            String taskType = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();

            try {
                switch (taskType) {
                    case "T": // Todo
                        Todo todo = new Todo(description);
                        if (isDone) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;

                    case "D": // Deadline
                        String deadlineStr = parts[3].trim();
                        LocalDateTime dueDate = LocalDateTime.parse(deadlineStr, dateFormatter);
                        Deadline deadline = new Deadline(description + " /by " + dueDate.format(formatter));
                        if (isDone) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;

                    case "E": // Event
                        String eventStr = parts[3].trim();
                        String[] eventParts = eventStr.split(" - ");
                        LocalDateTime fromDate = LocalDateTime.parse(eventParts[0], dateFormatter);
                        Event event = new Event(description + " /from " + fromDate.format(formatter) + " /to " + eventParts[1]);
                        if (isDone) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;

                    default:
                        throw new IOException("Unrecognized task type: " + taskType);
                }
            } catch (InvalidDateTimeFormatException | InvalidDescriptionException e) {
                System.out.println("An error occurred while loading the task from the file.");
            } catch (Exception e) {
                System.out.println("Error processing task: " + line);
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
        return tasks;
    }
}





