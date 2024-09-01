import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Storage {
    private ArrayList<Task> store = new ArrayList<>();
    Path filePath = Paths.get(".", "data", "not-gpt.txt");
    public Storage() {
        try {
            Files.createDirectories(filePath.getParent());

            if (Files.exists(filePath)) {
                System.out.println("Data found and loaded from: " + filePath.toAbsolutePath());
                System.out.println("*the first word will always be read as the command*");
                Notgpt.lnDiv();
            } else {
                Files.createFile(filePath);
                System.out.println("New Data file successfully created at: " + filePath.toAbsolutePath());
                System.out.println("*the first word will always be read as the command*");
                Notgpt.lnDiv();
            }

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void todo(String s){
        store.add(new Todo(s));
    }
    public void event(String s){
        store.add(new Event(s));
    }
    public void deadline(String s){
        store.add(new Deadline(s));
    }
    public String toString() {
        String thing = "";
        int j = 1;
        for(int i = 0; i<store.size()-1; i++){
                thing += j + ". " + store.get(i).toString() + "\n";
                j++;
        }
        thing += j + ". " + store.get(store.size()-1).toString();
        return thing;
    }
    public void mark(int i){
        store.get(i-1).complete();
    }
    public void unmark(int i){
        store.get(i-1).uncomplete();
    }
    public void delete(int i){
        store.remove(i-1);
    }
    public int size() {
        return store.size();
    }
}
