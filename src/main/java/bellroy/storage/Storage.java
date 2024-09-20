package bellroy.storage;

import bellroy.task.TaskList;
import bellroy.task.Deadline;
import bellroy.task.Event;
import bellroy.task.Task;
import bellroy.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;

/**
 * The Storage class handles the reading and writing of tasks the storage data file
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        assert filePath != null: "file path cannot be null";
        this.filePath = filePath;
    }

    /**
     * reads in the tasks from the input file line by line and adds it to the tasklist
     * @return the list of tasks in the data file
     * @throws IOException
     */
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

    /**
     * creates a deadline task to add to the tasklist
     * @param remainder the portion of the user input remaining behind the command "deadline"
     * @param isDone boolean value to check if the task is marked as completed
     * @return the deadline task
     */
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

    /**
     * creates an event task to add to the tasklist
     * @param remainder the portion of the user input remaining behind the command "event"
     * @param isDone boolean value to check if the task is marked as completed
     * @return the event task
     */
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

    /**
     * creates a Todo task to add to the tasklist
     * @param remainder the portion of the user input remaining behind the command "todo"
     * @param isDone boolean value to check if the task is marked as completed
     * @return the todo task
     */
    public Todo createTodo(String remainder, boolean isDone) {
        Todo newTodo = new Todo(remainder);
        if (isDone) {
            newTodo.markDone();
        }
        return newTodo;
    }

    /**
     * checks if the task has been mark completed
     * @param input the input from the data file
     * @return true if the task is marked done, false otherwise
     */
    public boolean isDone(String input) {
        return input.charAt(4) == 'X';
    }

    /**
     * checks the type of the task to process it down the line
     * @param input the input from the data file
     * @return the type of the task, T for todo, D for deadline, E for event
     */
    public char taskType(String input) {
        return input.charAt(1);
    }

    /**
     * trims the input to without the task type and completion status
     * @param input the task from the data file
     * @return the remainder string
     */
    public String remainder(String input) {
        return input.substring(7).trim();
    }

    /**
     * saves the tasklist to the data file
     * @param taskList the tasklist to be saved
     * @throws IOException
     */
    public void save(TaskList taskList) throws IOException{
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task t: taskList.getTaskList()) {
                writer.write(t.toString());
                writer.write("\n");
            }
        }
    }
}
