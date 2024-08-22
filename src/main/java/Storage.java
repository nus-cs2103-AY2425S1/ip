import java.util.ArrayList;

public class Storage {
    ArrayList<Task> store = new ArrayList<>();

    public void add(String s){

    }

    public String toString() {
        String thing = "";
        int j = 1;
        for(int i = 0; i<101; i++){
            if(store.get(i).isReal()){
                thing += j + "." + store.get(i).toString() + "\n";
                j++;
            }
        }
        return thing;
    }
}
