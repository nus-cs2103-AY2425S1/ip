import java.util.ArrayList;
import java.util.List;

public class Note {
    private Task[] myList;
    private int count;

    public Note() {
        this.myList = new Task[100];
        this.count = 0;
    }

    public void addToList(String input) {
        int no = count + 1;
        this.myList[count] = new Task(input);
        count++;
    }


    public void mark(int number) {
        this.myList[number].markAsDone();
    }

    public void unmark(int number) {
        this.myList[number].markAsUnDone();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < count; i++) {
            String result = i + 1 + ". " + this.myList[i].toString();
            System.out.println(result);
        }
        System.out.println("--------------------------------------------");
    }

}
