import java.util.ArrayList;

public class User {

    private ArrayList<Task> list;
    private int count = 0;

    public User() {
        this.list = new ArrayList<Task>();
    }

    public void addList(Task item) {
        this.list.add(item);
        this.count++;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public int getCount() {
        return this.count;
    }

}
