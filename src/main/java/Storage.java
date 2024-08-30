import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    private static LocalDateTime formatDate (String date) throws BuddyException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new BuddyException("you need to state the date in the format 'd/M/yyyy HHmm'");
        }
    }

    private static String localDateTimeString (LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

    public ArrayList<Task> load() throws BuddyException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            throw new BuddyException("  No tasks founds, starting from scratch");
        }

        // reading previously stored tasks in file
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split("\\|");
                String taskType = details[0].trim();
                String description = details[2].trim();
                Task task;

                switch (taskType) {
                    case "T":
                        task = new ToDos(description);
                        break;
                    case "D":
                        String deadline = details[3].trim();
                        LocalDateTime formattedDeadline = formatDate(deadline);
                        task = new Deadlines(description, formattedDeadline);
                        break;
                    case "E":
                        String startDate = details[4].trim();
                        String endDate = details[5].trim();
                        LocalDateTime formattedStartDate = formatDate(startDate);
                        LocalDateTime formattedEndDate = formatDate(endDate);
                        task = new Events(description, formattedStartDate, formattedEndDate);
                        break;
                    default:
                        throw new BuddyException("Unknown task type found in file.");
                }

                if (details[1].trim().equals("1")) {
                    task.markAsDone();
                }

                list.add(task);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("    No previous task list found, starting fresh!");
        } catch (BuddyException e) {
            System.out.println("    OOPS!!! The task list file seems to be corrupted... ");
        }

        return list;
    }

    public void save(ArrayList<Task> list) throws BuddyException {
        try {
            File file = new File(this.FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : list) {
                String line = "";
                if (task.getTaskType().equals("T")) {
                    line = String.format("T | %d | %s", task.isDone ? 1 : 0, task.description);
                } else if (task.getTaskType().equals("D")) {
                    Deadlines deadline = (Deadlines) task;
                    int index = task.description.indexOf("(by:");
                    line = String.format("D | %d | %s | %s", task.isDone ? 1 : 0, deadline.description.substring(0, index).trim(), localDateTimeString(deadline.deadline));
                } else if (task.getTaskType().equals("E")) {
                    Events event = (Events) task;
                    int index = task.description.indexOf("(from:");
                    line = String.format("E | %d | %s | %s | %s", task.isDone ? 1 : 0, event.description.substring(0, index).trim(), localDateTimeString(event.start), localDateTimeString(event.end));
                }
                writer.write(line + System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            throw new BuddyException("An error occurred when saving tasks");
        }
    }

}
