import java.util.ArrayList;

public class Storage {
    ArrayList<Task> store = new ArrayList<>();

    public void add(String s){
        store.add(new Task(s));
    }

    public String toString() {
        String thing = "";
        int j = 1;
        for(int i = 0; i<store.size(); i++){
                thing += j + ". " + store.get(i).toString() + "\n";
                j++;
        }
        return thing;
    }
}
