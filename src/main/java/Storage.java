import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> store = new ArrayList<>();

    public void add(String s){
        store.add(new Task(s));
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
    public int size() {return store.size();}
}
