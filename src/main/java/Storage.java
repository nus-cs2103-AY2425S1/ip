import java.util.ArrayList;

public class Storage {
    private ArrayList<String> list = new ArrayList<>();

    public void add(String str) {
        list.add(str);
    }

    public void printAll() {
        int i = 0;
        for (String s : list) {
            System.out.println(++i + ". " + s);
        }
    }

}
