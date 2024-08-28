import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class Save {
    // Reads a file and returns the contents as a List of tasks
    File file;
    
    /*
     * Constructor for Save class
     * @Params String filePath
     * @Return None
     */
    public Save(List<Task> tasks) {
        try {
            file = new File("meow.txt");
            file.createNewFile();
            System.out.println("File created: " + file.getName());
            
            saveTasks(tasks);
        } catch (java.io.IOException e) {
            System.out.println("Meowception 007: meow meow error creating file meow");
            e.printStackTrace();
        }
    }

    public Save() throws java.io.IOException {
        file = new File("meow.txt");
    }

    /*
     * Saves a list of tasks to a file
     * @Params List<Task> tasks
     * @Return None
     */  
    public void saveTasks(List<Task> tasks) {
        try {
            java.io.FileWriter writer = new java.io.FileWriter(file);
            for (Task task : tasks) {
                String completed = task.isDone() ? "1" : "0";
                writer.write(completed + " " + task.getType() + " " + task.getTaskName() + " " + task.getExtra() +"\n");
            }
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    

    /*
     * Reads a file and returns the contents as a List of tasks
     * @Params None
     * @Return List<String>
     */
    public List<String> read() throws java.io.IOException {
        
        try {
            ArrayList<String> tasks_String = new ArrayList<String>();
            java.util.Scanner reader = new java.util.Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                tasks_String.add(data);
            }
            reader.close();
            return tasks_String;
        } catch (java.io.FileNotFoundException e) {
            System.out.println("WOMP  WOMP");
            e.printStackTrace();
            return null;
        }
        
        
    }

    
}
