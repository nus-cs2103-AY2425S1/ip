import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;

//Solution below adapted from https://nus-cs2103-ay2425s1.github.io/website/schedule/week3/topics.html#W3-4
public class Storage {
    
    private String FilePath;
    
    public Storage(String FilePath) {
        this.FilePath = FilePath;
    }
    
    private void createFile() {
        try {
            File myObj = new File(this.FilePath);
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    };
    
    public void writeToFile(String textToAdd) {
        try {
            File dataFile = new File(this.FilePath);
            if(!dataFile.exists()) {
                createFile();
            }
            FileWriter fw = new FileWriter(this.FilePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> taskList = new ArrayList<Task>();
            File file = new File(this.FilePath);
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNext()) {
                String task = scanner.nextLine();
                String[] taskProcessed = task.split("\\|");
                switch (taskProcessed[0].trim()) {
                    case "T":
                        taskList.add(new ToDo(taskProcessed[2], Integer.parseInt(taskProcessed[1].trim()) == 1));
                        break;
                    case "E":
                        taskList.add(new Event(taskProcessed[2], LocalDate.parse(taskProcessed[3].trim()),  LocalDate.parse(taskProcessed[4].trim()),
                            Integer.parseInt(taskProcessed[1].trim()) == 1));
                        break;
                    case "D":
                        taskList.add(new Deadline(taskProcessed[2],  LocalDate.parse(taskProcessed[3].trim()),
                            Integer.parseInt(taskProcessed[1].trim()) == 1));
                        break;
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return new ArrayList<Task>();
    }
    
    public void synchronizeTasks(ArrayList<Task> TaskLists) {
        String updatedTaskList = TaskLists.stream().map(Task::storageFormat).
            reduce("", (firstLine, secondLine) -> firstLine + secondLine);
                this.writeToFile(updatedTaskList);
    }
    
}
