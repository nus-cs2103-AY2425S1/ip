package twilight;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveData(ArrayList<Task> Tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : Tasks) {
            fw.write(task.toStorage());
            fw.write(System.getProperty( "line.separator" ));
        }
        fw.close();
    }

    public ArrayList<String> readStoredContent() {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            ArrayList<String> Tasks = new ArrayList<String>();
            while (s.hasNext()) {
                Tasks.add(s.nextLine());
            }
            return Tasks;
        } catch (FileNotFoundException e) {
            System.out.println("There was no datafile stored for twilight tasks");
            return new ArrayList<String>();
        }
    }

    public ArrayList<Task> getStoredTasks() {
        ArrayList<String> entries = readStoredContent();
        ArrayList<Task> Tasks = new ArrayList<Task>();
        for (String entry : entries) {
            String[] input = entry.split(",");
            if (input[0].equals("T")) {
                Tasks.add(new Todo(input[1].equals("1"), input[2]));
            } else if ((input[0].equals("E"))) {
                Tasks.add(new Event(input[1].equals("1"), input[2], input[3], input[4]));
            } else {
                Tasks.add(new Deadline(input[1].equals("1"), input[2], input[3]));
            }
        }
        return Tasks;
    }
}
