import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    Storage(String path) {
        this.filePath = path;
        checkSavedFile();
    }

    // create all the necessary directory and file if the file doesn't exists
    private void checkSavedFile() {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error occur when creating file.");
            }
        }
    }

    public static Task getTaskFromSavedCommand(String command) {
        String[] arr = command.split("\\|");
        boolean isDone = arr[1].equals("1");
        return switch (arr[0]) {
            case "T" -> new Todo(arr[2], isDone);
            case "D" -> new Deadline(arr[2], isDone, arr[3]);
            case "E" -> new Event(arr[2], isDone, arr[3], arr[4]);
            default -> null;
        };
    }

    public ArrayList<Task> readSavedFile() {
        ArrayList<Task> ans = new ArrayList<>();
        checkSavedFile();
        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                Task task = getTaskFromSavedCommand(sc.nextLine());
                if (task != null)
                    ans.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find saved data file");
        }
        return ans;
    }

}
