package nebula.storage;

import nebula.exception.NebulaException;
import nebula.task.Deadline;
import nebula.task.Event;
import nebula.task.Task;
import nebula.task.Todo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    String path;

    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm");
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath The path to the file to be used for storage.
     */
    public Storage(String filePath) {
        this.path = filePath;
    }

    /**
     * Reads a text file and converts its contents into a list of tasks. The method
     * parses each line to create the appropriate task objects and adds them to the list.
     *
     * @return An ArrayList of tasks read from the file.
     * @throws RuntimeException If an I/O error occurs or if the file format is incorrect.
     */
    public ArrayList<Task> textFileToArrayList() {
        ArrayList<Task> listOfTasks = new ArrayList<>();

        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            String textLine;

            while ((textLine = br.readLine()) != null) {
                // Split the line by the '|' character
                String[] parts = textLine.split("\\|");

                // Trim whitespace from each part
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                if (parts.length < 3) {
                    continue; // Skip invalid lines
                }
                // Get done status and task type
                boolean isDone = "1".equals(parts[0]);
                char taskSymbol = parts[1].charAt(1);

                Task task = null;
                switch (taskSymbol) {
                    case 'T':
                        if (parts.length >= 3) {
                            task = new Todo(parts[2]);
                            task.setDone(isDone);
                        }
                        break;

                    case 'D':
                        if (parts.length >= 4) {
                            String deadlineDescription = parts[2];
                            String dueDate = parts[3];

                            String formattedDueDate = convertDate(dueDate);
                            task = new Deadline(deadlineDescription, formattedDueDate);
                            task.setDone(isDone);
                        }
                        break;

                    case 'E':
                        if (parts.length >= 4) {
                            String eventDescription = parts[2];
                            String startEndDate = parts[3];
                            // Split start and end dates
                            String[] dates = startEndDate.split("-");
                            if (dates.length == 2) {
                                String startDate = dates[0].trim();
                                String endDate = dates[1].trim();

                                String formattedStartDate = convertDate(startDate);
                                String formattedEndDate = convertDate(endDate);

                                task = new Event(eventDescription, formattedStartDate, formattedEndDate);
                                task.setDone(isDone);
                            }
                        }
                        break;

                    default:
                        System.out.println("Unknown task type: " + taskSymbol);
                        break;
                }

                if (task != null) {
                    listOfTasks.add(task);
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfTasks;
    }


    /**
     * Converts a date string from one format to another. This method parses
     * the provided date string using a predefined input format and then formats it
     * into a different predefined output format.
     *
     * @param dateStr the date string to be converted
     * @return the date string formatted to the target format
     * @throws IllegalArgumentException if the provided date string is
     * not in the expected format
     */
    public String convertDate(String dateStr) {
        // Parse the date string using the original format
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dateStr, OUTPUT_FORMAT);
        } catch (Exception e) {
            throw new IllegalArgumentException("Date string is not in the expected format: " + dateStr, e);
        }

        // Format the LocalDateTime object to the target format
        return dateTime.format(DATE_TIME_FORMAT);
    }

    /**
     * Loads tasks from the file located at the given path.
     *
     * @return the TaskList containing the tasks loaded from the file.
     * @throws NebulaException if the file does not exist or cannot be accessed.
     */
    public ArrayList<Task> load() throws NebulaException {
        Path nebulaTextFile = Paths.get(path);

        if (Files.exists(nebulaTextFile)) {
            return textFileToArrayList();
        } else {
            throw new NebulaException("File doesn't exist!");
        }
    }

    /**
     * Saves a list of tasks to a text file in the "data" directory. The tasks are saved
     * in a format where each line contains the task's status, type, and description,
     * followed by additional details for specific task types. If the "data" directory
     * does not exist, it will be created.
     *
     * @param listOfTasks The list of tasks to be saved to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveTaskListToTextFile(ArrayList<Task> listOfTasks) throws IOException {

        // Create a folder called "data" if it doesn't exist
        File dataDirectory = new File("./data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();  // Create the "data" directory
        }

        // Define the file path within the "data" directory
        File taskFile = new File(dataDirectory, "nebulaTaskList.txt");

        // Create FileWriter for the file
        FileWriter fw = new FileWriter(taskFile);

        for (Task task : listOfTasks) {

            String isMarked = task.isDone() ? "1" : "0";
            String taskSymbol = task.getTaskSymbol();
            String taskDescription = task.getDescription();

            String taskData = isMarked + " | " + taskSymbol + " | " + taskDescription;

            if(task instanceof Deadline) {
                taskData += " | " + ((Deadline) task).getDeadline();
            } else if (task instanceof Event) {
                taskData += " | " + ((Event) task).getStart() + "-" + ((Event) task).getEnd();
            }

            fw.write(taskData + "\n");
        }

        fw.close();
    }
}
