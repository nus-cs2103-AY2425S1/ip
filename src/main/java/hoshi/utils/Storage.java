package hoshi.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

import hoshi.task.Deadline;
import hoshi.task.Event;
import hoshi.task.Task;
import hoshi.task.TaskList;
import hoshi.task.Todo;

/**
 * Storage class that handles I/O related functions such as Loading and Saving for Hoshi
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a new instance of Storage.
     *
     * @param filePath String path of where Hoshi txt file is relatively located from root
     */
    public Storage(String filePath) {
        // assert filePath is not null and not empty
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from hoshi txt file if user is not new else greets the user.
     *
     * @param taskList ArrayList of 3 types of tasks to be retrieved from hoshi txt file.
     */
    public void load(TaskList taskList) throws FileNotFoundException {

        File file = new File(this.filePath);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {

                String line = scanner.nextLine();
                String[] parts = line.split(", ");

                String taskType = parts[0];
                Boolean isDone = Boolean.FALSE;

                if (Objects.equals(parts[1], "D")) {
                    isDone = Boolean.TRUE;
                }
                String description = parts[2];

                switch (taskType) {
                case "Todo":

                    Todo todo = new Todo(description, isDone);
                    taskList.add(todo);
                    break;

                case "Deadline":

                    String deadlineEndTime = parts[3];
                    LocalDate deadlineDateTimeEnd = LocalDate.parse(deadlineEndTime);

                    Deadline deadline = new Deadline(description, isDone, deadlineDateTimeEnd);
                    taskList.add(deadline);
                    break;

                case "Event":

                    String endTime = parts[3];
                    String startTime = parts[4];

                    LocalDate dateTimeEnd = LocalDate.parse(endTime);
                    LocalDate dateTimeStart = LocalDate.parse(startTime);

                    Event event = new Event(description, isDone, dateTimeEnd, dateTimeStart);
                    taskList.add(event);
                    break;

                default:

                    System.out.println("Hoshi is not aware of this task type: " + taskType + "!");
                    break;
                }

            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Hoshi has detected a new user! Welcome!");
        }

    }


    /**
     * Saves tasks added and retrieved during the program to hoshi txt file.
     *
     * @param taskList TaskList of 3 types of tasks to be written to hoshi txt file.
     */
    public void save(TaskList taskList) throws IOException {
        // assert taskList is not null
        assert taskList != null : "TaskList cannot be null";
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);

            for (int i = 0; i < taskList.size(); i++) {

                Task task = taskList.get(i);

                // Deadline(TaskType), T(D = Done/ ND = Not Done), Description, endTime, startTime
                String taskType = task.getClass().getSimpleName();
                String isDone = task.getStatusIcon();
                if (Objects.equals(isDone, " ")) {
                    isDone = "ND";
                } else {
                    isDone = "D";
                }
                String description = task.getDesc();
                assert description != null && !description.isEmpty() : "Task description cannot be null or empty";

                String additionalFields = "";

                if (taskType.equals("Deadline")) {

                    Deadline deadline = ((Deadline) task);
                    additionalFields = ", " + deadline.getEndTime();

                } else if (taskType.equals("Event")) {

                    Event event = ((Event) task);
                    additionalFields = ", " + event.getEndTime() + ", " + event.getStartTime();

                }

                String textToAdd = taskType + ", " + isDone + ", " + description + additionalFields + "\n";
                fileWriter.write(textToAdd);
            }
            fileWriter.close();


        } catch (IOException e) {
            System.out.println("Hoshi has an error! " + e.getMessage());
        }

    }


}
