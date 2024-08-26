import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Storage {
    private Storage(){}

    public static void saveData() {
        try {
            FileWriter saveFile = new FileWriter("src/main/Data/save.txt");
            int numTask = Task.getNumTask();
            for (int i = 0; i < numTask; i++) {
                saveFile.write(Task.taskFileFormatGet(i));
                if (i != numTask-1) {
                    saveFile.write("\n");
                }
            }
            saveFile.close();
        } catch (IOException e) {
            System.out.println("There was an error saving your data. Details can be found below.");
            System.out.println(e.getMessage());
        }
    }

    public static void loadData() {
        try {
            File savedData = new File("src/main/Data/save.txt");
            Scanner reader = new Scanner(savedData);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String taskType = data.substring(0, 1);
                String[] taskDetails = data.split(" \\| ");
                switch (taskType) {
                    case "E" -> Event.load(taskDetails);
                    case "D" -> Deadline.load(taskDetails);
                    case "T" -> ToDo.load(taskDetails);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading the data.");
            System.out.println(e.getMessage());
        }
    }
}