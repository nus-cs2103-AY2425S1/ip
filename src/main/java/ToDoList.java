import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    List<String> list = new ArrayList<String>(){{
        add("first item in list");
    }};

    private String addToList(String str) {
        list.add(str);
        return "Successfully added to list.";
    }

    private String deleteFromList(String str) {
        if (list.contains(str)) {
            list.remove(str);
            return "Successfully deleted from list.";
        } else {
            return "failed to delete " + str;
        }
    }

    public String listToString() {
        return "List:\n" + list.toString();
    }
}
