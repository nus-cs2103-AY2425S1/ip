// deals with loading tasks from the file and saving tasks in the file
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void printFileContents() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int i = 1;
        while (s.hasNext()) {
            String str = s.nextLine();
            System.out.println(i + ". " + str);
            i++;
        }
    }

    public void writeToFile(String textToAdd, boolean appendorNot) throws IOException {
        FileWriter fw = new FileWriter(filePath, appendorNot);
        fw.write(textToAdd);
        fw.close();
    }

    public int numofTasks() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int i = 1;
        while (s.hasNext()) {
            String str = s.nextLine();
            i++;
        }
        return i;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            List<String> lst = Files.readAllLines(Path.of(filePath));
            for (String str : lst) {
                String[] arr = str.split("] ");
                String s = arr[arr.length - 1];
                if (arr[0].contains("X") || arr[1].contains("X")) {
                    tasks.add(new Task(s, true));
                } else {
                    tasks.add(new Task(s));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

}
