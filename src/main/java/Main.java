import impl.Danny;
import impl.TaskLoader;
import impl.interfaces.Task;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        Danny danny = new Danny();
        List<Task> savedTasks = new ArrayList<>();
        TaskLoader loader = null;
        try {
            loader = new TaskLoader("src/main/java/data/tasks.txt",danny);
            System.out.println("Loading previous lists...");
            loader.loadTask();
            System.out.println("Load Completed. Welcome back :)");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Save not found, starting off with new list.");
        }
        danny.start();
        try {
            assert loader != null;
            loader.saveTask();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Saving unsuccessful :(");
        }
    }

}
