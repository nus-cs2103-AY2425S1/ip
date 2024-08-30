import impl.chatbot.Danny;
import impl.storage.Storage;
import impl.interfaces.Task;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /**
     * Overview of the whole program.
     * Initialises Danny and Storage loader.
     */
    public static void main(String[] args){
        Danny danny = new Danny();
        List<Task> savedTasks = new ArrayList<>();
        Storage loader = null;
        try {
            loader = new Storage("src/main/java/data/tasks.txt",danny);
            System.out.println("Loading previous lists...");
            loader.loadTask();
            System.out.println("Load Completed. Welcome back :)");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Save not found, starting off with new list.");
        }
        danny.run();
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
