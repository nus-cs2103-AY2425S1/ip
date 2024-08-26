import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;

//Solution below adapted from https://nus-cs2103-ay2425s1.github.io/website/schedule/week3/topics.html#W3-4
public class FileUtility {
    private static String FILEPATH = "./tasks_data.txt";
    
    private static void createFile() {
        try {
            File myObj = new File(FILEPATH);
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    };
    
    public static void writeToFile(String textToAdd) {
        try {
            File dataFile = new File(FILEPATH);
            if(!dataFile.exists()) {
                createFile();
            }
            FileWriter fw = new FileWriter(FILEPATH);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
    public static ArrayList<Task> fileContents() {
        try {
            ArrayList<Task> taskList = new ArrayList<Task>();
            File file = new File(FILEPATH);
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
    
}
