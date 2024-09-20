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

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Updates the data file whenever tasklist is updated
     * @param s The string to write to the data file
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

    public Tasklist retrieveDataFile(String s) {
        File dataFile = new File(this.filePath);
        Tasklist t = new Tasklist();
        String[] splitLine;

        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.split(" ", 2)[1];
                line = line.replaceAll("[\\[\\]()]", "");
                boolean isDone = line.charAt(1) == 'X';

                System.out.println("have line" + line);
                switch(line.charAt(0)) {
                case 'T':
                    System.out.println("here");
                    line = line.substring(3);
                    Todo todo = new Todo(line);
                    if (isDone) {
                        todo.setDone();
                    }
                    t.add(todo);
                    break;
                    
                case 'E':
                    System.out.println("here");
                    line = line.substring(3);
                    splitLine = line.split("from:|to:");
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    LocalDate dateFrom = LocalDate.parse(splitLine[1], format);
                    LocalDate dateTo = LocalDate.parse(splitLine[2], format);
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
                    LocalDate dateBy = LocalDate.parse(splitLine[1], formatter);
                    Deadline deadline = new Deadline(splitLine[0], dateBy);
                    if (isDone) {
                        deadline.setDone();
                    }
                    t.add(deadline);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in your application
        }
        return t;
    }
}
