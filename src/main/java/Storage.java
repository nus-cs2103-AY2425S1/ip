import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    File file;
    public Storage(String s) {
        this.file = new File(s);
    }
    public List<Task> loadFile() {
        try {
            Scanner s = new Scanner(file);
            List<Task> array = new ArrayList<>();
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                case "T":
                    array.add(new Todo(parts[2], parts[1].equals("1")));
                    break;
                case "D":
                    array.add(new Deadline(parts[2], parts[3], parts[1].equals("1")));
                    break;
                case "E":
                    array.add(new Event(parts[2], parts[3], parts[4], parts[1].equals("1")));
                    break;
                }
            }
            s.close();
            return array;
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public void writeFile(List<Task> array) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : array) {
                fw.write(task.save() + '\n');
            }
            fw.close();
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void createFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void createFolder() {
        File newFolder = new File("./data");
        newFolder.mkdirs();
    }
}
