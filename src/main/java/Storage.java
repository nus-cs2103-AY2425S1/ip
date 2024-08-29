import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Storage {
    private Parser parser;
    private static String filePath;
    private ArrayList<Task> savedTasks = new ArrayList<>();

    public Storage(String filePath) {
        parser = new Parser();
        this.filePath = filePath;
    }
    public ArrayList<Task> load() throws SocchatException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner s = new Scanner(file);
            while(s.hasNextLine()) {
                String line = s.nextLine();
                String[] strToken = line.split("[ | ]");
                String type = strToken[0].trim();
                String done = strToken[1].trim();
                String des = strToken[2].trim();
                Task t;
                Boolean isDone;
                if (done.equals("Done")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                if(type.equals("T")) {
                    t = new Todo(des,isDone);
                } else if (type.equals("E")) {
                    String date =strToken[3].trim();
                    String[] dateToken = date.split(" to ");
                    LocalDateTime from = parser.parseDate(dateToken[0]);
                    LocalDateTime to = parser.parseDate(dateToken[0]);
                    t = new Event(des, from, to, isDone);
                } else {
                    String date =strToken[3].trim();
                    LocalDateTime by = parser.parseDate(date);

                    t = new Deadline(des, by, isDone);
                }
                tasks.add(t);
            }
        } catch (FileNotFoundException e) {
              throw  new SocchatException("Storage file not found");
        }

        return tasks;

    }

    public static void update(ArrayList<Task>tasks, Boolean needAppend) {
        ArrayList<Task> content = new ArrayList<>();
        if (needAppend) {
            content.add(tasks.get(tasks.size() - 1)); // Append only the last added task
        } else {
            content = tasks; // Rewrite the tasks
        }
        try(FileWriter writer = new FileWriter(filePath, needAppend)) {
            for (Task t : content) {
                writer.write(t.toSave());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
