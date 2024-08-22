import java.util.ArrayList;
import java.util.List;

public class Note {
    private String[] myList;
    private int count;

    public Note() {
        this.myList = new String[100];
        this.count = 0;
    }

    public void addToList(String input) {
        int no = count + 1;
        this.myList[count] = no + ". " + input;
        count++;
    }

    public void printList() {
        for (int i = 0; i < count; i++) {
            System.out.println(myList[i]);
        }
    }

}
