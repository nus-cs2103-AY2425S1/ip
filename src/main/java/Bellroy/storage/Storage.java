package Bellroy.storage;

import Bellroy.task.TaskList;
import Bellroy.task.Deadline;
import Bellroy.task.Event;
import Bellroy.task.Task;
import Bellroy.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        assert filePath != null: "file path cannot be null";
        this.filePath = filePath;
    }

    public List<Task> loadTasks() throws IOException {
        List<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                char taskType = taskType(line);
                boolean isDone = isDone(line);
                String remainder = remainder(line);

                switch (taskType) {
                    case ('T'):
                        Task tempTodo = createTodo(remainder, isDone);
                        taskList.add(tempTodo);
                        break;
                    case ('D'):
                        Task tempDeadline = createDeadline(remainder, isDone);
                        taskList.add(tempDeadline);
                        break;
                    case ('E'):
                        Task tempEvent = createEvent(remainder, isDone);
                        taskList.add(tempEvent);
                        break;
                    default:
                        break;
                }
                line = reader.readLine();
            }
            reader.close();
        }
        return taskList;
    }

    public Deadline createDeadline(String remainder, boolean isDone) {
        String[] deadlineParts = remainder.split(" \\(by: ");
        String deadlineDescription = deadlineParts[0].trim();
        String by = deadlineParts[1].replace(")", "").trim();
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(by, inputFormat);
        String dueDate = dateTime.format(outputFormat);
        Deadline newTask = new Deadline(deadlineDescription, dueDate);
        if (isDone) {
            newTask.markDone();
        }
        return newTask;
    }

    public Event createEvent(String remainder, boolean isDone) {
        String[] eventParts = remainder.split(" \\(from: | to: ");
        String eventDescription = eventParts[0].trim();
        String from = eventParts[1].trim();
        String to = eventParts[2].replace(")", "").trim();
        Event newEvent = new Event(eventDescription, from, to);
        if (isDone) {
            newEvent.markDone();
        }
        return newEvent;
    }

    public Todo createTodo(String remainder, boolean isDone) {
        Todo newTodo = new Todo(remainder);
        if (isDone) {
            newTodo.markDone();
        }
        return newTodo;
    }

    public boolean isDone(String input) {
        return input.charAt(4) == 'X';
    }

    public char taskType(String input) {
        return input.charAt(1);
    }

    public String remainder(String input) {
        return input.substring(7).trim();
    }

    public void save(TaskList taskList) throws IOException{
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task t: taskList.getTaskList()) {
                writer.write(t.toString());
                writer.write("\n");
            }
        }
    }
}
