import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;


//stores only 1 object
public class TxtStorage {

    private String fileName = "";

    public TxtStorage(String fileName) {
        this.fileName = fileName;
        
    }


    //referenced https://www.w3schools.com/java/java_files_create.asp
    public void appendLine(String line) {
        System.out.println("appending line");
        try {
            FileWriter file = new FileWriter(this.fileName, true);
            try {
                file.write(line);
                file.write("\n");
                file.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File file = new File(this.fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(":");
                System.out.println(data[0]);

                System.out.println(data[1]);


                if (data.length == 0) {
                    break;
                }
                String className = data[0];
                if (className.equals("ToDo")) {
                    tasks.add(ToDo.newObjectFromData(data[1]));
                } else if (className.equals("Event")) {
                    tasks.add(Event.newObjectFromData(data[1]));
                } else if (className.equals("Deadline")) {
                    tasks.add(Deadline.newObjectFromData(data[1]));
                }


            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not created");
            File file = new File(this.fileName);
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
        return tasks;
    }


}
