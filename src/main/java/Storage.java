import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Storage {

    File file;
    public Storage(String pathname) {
        this.file = new File(pathname);
    }

    public List retrieve() {
        try {
            Scanner s = new Scanner(this.file);
            List list = new List();
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] strings = line.split("\\[");
                String type = strings[1].split("]")[0];
                String description = strings[2].split("] ")[1];
                String check = strings[2].split("] ")[0];
                Task task = Task.intepreteTask(description, type);
                if (Objects.equals(check, "X")) {
                    task.markAsDone();
                }
                list.addTaskToList(task);
            }
            return list;
        } catch (FileNotFoundException e) {     // user is new
            return new List();
        }
    }

    public void save(List list) throws IOException {
        FileWriter filewriter = new FileWriter(this.file, false);

        for (int i=0; i < list.getTasks().size(); i++) {
            String line = list.getTasks().get(i).displayTask();
            filewriter.write(line);
        }
        filewriter.close();
    }
}
