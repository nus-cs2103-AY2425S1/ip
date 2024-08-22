import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    List<String> list = new ArrayList<String>();

    public String addToList(String str) {
        list.add(str);
        return "Successfully added " + str + " to list.";
    }

    public String deleteFromList(String str) {
        if (list.contains(str)) {
            list.remove(str);
            return "Successfully deleted " + str + " from list.";
        } else {
            return "Failed to delete " + str + " from list.";
        }
    }

    public void listToString() {
        System.out.println("`````\n" + "List:");
        int i = 1;
        for (String item : list) {
            System.out.print(i + ". " + item + "\n");
            i++;
        }
        System.out.println("`````\n");
    }
}
