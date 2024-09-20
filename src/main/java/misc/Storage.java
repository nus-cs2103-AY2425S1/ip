package misc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import task.Deadline;
import task.Event;
import task.Tasklist;
import task.Todo;

/**
 * Storage class handles reading and writing to a file.
 */
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Updates the data file whenever tasklist is updated.
     *
     * @param s The filepath.
     */
    public void updateDataFile(String s) {
        try {
            File dataFile = new File(this.filePath);

            // check for file
            if (!dataFile.exists()) {
                if (dataFile.createNewFile()) {
                    System.out.println("File created: " + dataFile.getName());
                } else {
                    System.out.println("Failed to create the file.");
                }
            }

            // Always write to the file, whether it exists or is newly created
            FileWriter writer = new FileWriter(this.filePath);
            writer.write(s);
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    /**
     * Retrieves the data file
     *
     * @param s The filepath.
     */
    public Tasklist retrieveDataFile(String s) {
        File dataFile = new File(this.filePath);
        Tasklist t = new Tasklist();
        String[] splitLine;

        if (!dataFile.exists()) {
            return t;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.split(" ", 2)[1];
                line = line.replaceAll("[\\[\\]()]", "");
                boolean isDone = line.charAt(1) == 'X';

                switch(line.charAt(0)) {
                case 'T':
                    line = line.substring(3);
                    Todo todo = new Todo(line);
                    if (isDone) {
                        todo.setDone();
                    }
                    t.add(todo);
                    break;

                case 'E':
                    line = line.substring(3);
                    splitLine = line.split("from:|to:");
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    LocalDate dateFrom = LocalDate.parse(splitLine[1].strip(), format);
                    LocalDate dateTo = LocalDate.parse(splitLine[2].strip(), format);
                    Event event = new Event(splitLine[0], dateFrom, dateTo);
                    if (isDone) {
                        event.setDone();
                    }
                    t.add(event);
                    break;

                case 'D':
                    System.out.println("here");
                    line = line.substring(3);
                    splitLine = line.split("by:");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    LocalDate dateBy = LocalDate.parse(splitLine[1].strip(), formatter);
                    Deadline deadline = new Deadline(splitLine[0].strip(), dateBy);
                    if (isDone) {
                        deadline.setDone();
                    }
                    t.add(deadline);
                    break;
                default:
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }
        return t;
    }
}
