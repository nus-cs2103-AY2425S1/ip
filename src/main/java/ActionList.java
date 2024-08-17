import java.util.ArrayList;
import java.util.List;

public class ActionList {

    private List<String> items;
    private int size;

    ActionList(){
        items = new ArrayList<>();
        size = 0;
    }

    public void addAction(String command) {
        items.add(command);
        size += 1;
    }

    public String getActionByIndex(int index){
        return items.get(index);
    }

    public int getSize(){
        return size;
    }

    public List<String> getAction(){
        return items;
    }

    public void printActions(){
        for (int i = 1; i <= size; i++){
            System.out.printf("%,d. %s%n", i ,items.get(i-1));
        }
    }




}
