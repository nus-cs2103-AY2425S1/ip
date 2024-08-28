import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListSaver {
    private final String filePath;

    public ListSaver(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> getList() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String parts[] = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        taskList.add(new ToDos(parts[1], parts[2]));
                        break;
                    case "D":
                        taskList.add(new Deadlines(parts[1], parts[2], parts[3]));
                        break;
                    case "E":
                        taskList.add(new Events(parts[1], parts[2], parts[3], parts[4]));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        } catch (IOException e) {
            System.out.println("IO Error found: " + e);
        }

        return taskList;
    }

    public void saveList(ArrayList<Task> taskList) { // to be edited

    }
}