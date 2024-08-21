import java.util.ArrayList;

public class User {

    private ArrayList<String> list;

    public User() {
        this.list = new ArrayList<String>();
    }

    public void addList(String content) {
        this.list.add(content);
    }

    public ArrayList<String> getList() {
        return this.list;
    }

}
