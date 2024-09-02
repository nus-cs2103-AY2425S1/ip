import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
        private static final String filePath = "./data/bottle.txt";

        private void handleMissingFile() {
            Path path = Paths.get(filePath);
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e){
                System.out.println("Error occurred creating file");
            }
        }
        public ArrayList<Task> loadTasks() {
            File file = new File(filePath);
            ArrayList<Task> taskList = new ArrayList<>();
            if (!file.exists()) {
                handleMissingFile();
            } else {
                try {
                    List<String> lines = Files.readAllLines(Paths.get(filePath));

                    for (String line : lines) {
                       String[] parts = line.split("\\|");
                       Task task = null;
                       try {
                           switch (parts[0]) {
                               case "T ":
                                   task = new Todo(parts[2]);
                                   break;
                               case "D ":
                                   task = new Deadline(parts[2], parts[3]);
                                   break;
                               case "E ":
                                   task = new Event(parts[2], parts[3], parts[4]);
                                   break;
                               default:
                                   throw new IllegalArgumentException("Wrong input format");

                           }
                           if (task != null) {
                               if (parts[1].equals(" 1 ")) {
                                   task.mark();
                               } else if (parts[1].equals(" 0 ")) {
                                   task.unMark();
                               } else {
                                   throw new IllegalArgumentException("Wrong isMarked input format");
                               }
                               taskList.add(task);
                           }
                       } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                           System.out.println("Wrong input format" + e.getMessage());
                       }
                    }
                } catch (IOException e) {
                    System.out.println("Error reading file");
                }
            }
            return taskList;
        }

        public void saveTasks(ArrayList<Task> taskList) {
            File file = new File(filePath);
            if (!file.exists()) {
                handleMissingFile();
            }
            try {
                FileWriter fileWriter =  new FileWriter(file);
                for (Task task : taskList) {
                    fileWriter.write(task.toSaveFormat() + System.lineSeparator());
                }
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error saving file");
            }
        }

        public static void main (String[] args){
            TaskManager t = new TaskManager();
            System.out.println(t.loadTasks());
        }
}
