import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
        load(filePath);
    }
    public void write(File data, ArrayList<Task> items) {
        try {
            FileWriter writer = new FileWriter(data);
            for (Task item : items) {
                writer.write(String.format("%s | %s | %s | %s \n",
                        item.getTaskType(), item.getStatusString(), item.getDesc(),
                        item instanceof DeadlineTask
                                ? ((DeadlineTask) item).getDate()
                                : item instanceof EventTask
                                ? ((EventTask) item).getFrom() + " - " + ((EventTask) item).getTo()
                                : ""));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }

    private void load(String filePath) {
        // Creates a file to store data if none exists
        try {
            Files.createFile(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("SLAY! Loading up your saved tasks!");
        }
    }

    public ArrayList<Task> read(File data) {
        ArrayList<Task> items = null;
        try {
            items = new ArrayList<>();
            Scanner fileInput = new Scanner(data);
            while (fileInput.hasNextLine()) {
                // Loading from file
                String line = fileInput.nextLine();
                String[] split = line.split(" \\| ");
                String type = split[0];
                boolean status = split[1].equals("X");
                String desc = split[2];
                String date = split[3];
                Task item = createTaskFromFile(type, desc, date);
                item.setStatus(status);
                items.add(item);
            }
            fileInput.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading file.");
            e.printStackTrace();
        } catch (SigmaException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    private Task createTaskFromFile(String type, String desc, String date) throws SigmaException {
        Task item;
        switch (type) {
        case "T":
            item = new ToDoTask(desc);
            break;
        case "D":
            LocalDateTime dateTime;
            try {
                dateTime = LocalDateTime.parse(date.strip(), DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
            } catch (DateTimeParseException e) {
                throw new SigmaException("What the sigma? File contains invalid date format!");
            }
            item = new DeadlineTask(desc, dateTime);
            break;
        case "E":
            String[] dates = date.split("-");
            LocalDateTime from;
            LocalDateTime to;
            try {
                from = LocalDateTime.parse(dates[0].strip(), DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
                to = LocalDateTime.parse(dates[1].strip(), DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"));
            } catch (DateTimeParseException e) {
                throw new SigmaException("What the sigma? File not saved correctly, invalid date format!");
            }
            item = new EventTask(desc, from, to);
            break;
        default:
            throw new SigmaException("What the sigma? Invalid task type!");
        }
        return item;
    }
}
