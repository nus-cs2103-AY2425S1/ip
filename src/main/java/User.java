import java.util.ArrayList;

public class User {

    private ArrayList<Task> list;

    public User() {
        this.list = new ArrayList<Task>();
    }

    public void addList(String content) {
        this.list.add(new Task(content));
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

}
