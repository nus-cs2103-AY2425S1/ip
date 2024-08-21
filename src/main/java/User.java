import java.util.ArrayList;

public class User {

    private ArrayList<Task> list;
    private static int count = 0;

    public User() {
        this.list = new ArrayList<Task>();
    }

    public void addList(Task item) {
        this.list.add(item);
        User.count++;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public static int getCount() {
        return User.count;
    }

}
