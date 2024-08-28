import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String directoryPath;
    private String filePath;
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");

    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFromFile() throws BrunoException {
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            ArrayList<Task> taskList = new ArrayList<>();
            while(s.hasNext()) {
                String line = s.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] lineParts = line.split("\\|");

                String type = lineParts[0].trim();
                boolean done = lineParts[1].trim().charAt(1) == 'X';
                String description = lineParts[2].trim();

                if (type.equals("T")) {
                    taskList.add(new ToDo(description, done));
                } else if (type.equals("D")) {
                    String byString = lineParts[3].substring(lineParts[3].indexOf("by:") + 3).trim();
                    LocalDateTime by = LocalDateTime.parse(byString, formatter2);
                    taskList.add(new Deadline(description, by, done));
                } else if (type.equals("E")) {
                    String fromString = lineParts[3].substring(6, lineParts[3].indexOf("to")).trim();
                    String toString = lineParts[3].substring(lineParts[3].indexOf("to") + 3).trim();
                    LocalDateTime from = LocalDateTime.parse(fromString, formatter2);
                    LocalDateTime to = LocalDateTime.parse(toString, formatter2);
                    taskList.add(new Event(description, from, to, done));
                } else {
                    System.out.println("There was a problem when loading some task");
                }
            }
            return taskList;
        } catch (IOException e) {
            throw new FileLoadingException();
        }
    }
    public void updateFile(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String listAsString = "";
            for (Task task : taskList) {
                listAsString += task.toString() + "\n";
            }
            fw.write(listAsString);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void ensureDirectoryExists() {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created to store data file");
            }
        }
    }

    public void ensureFileExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Data file created");
                }
            } catch (IOException e) {
                System.out.println("Something went wrong when creating the data file");
            }
        }
    }
}
