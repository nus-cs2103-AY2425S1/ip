import java.util.ArrayList;

public class List {
    private static final ArrayList<String> list = new ArrayList<>();
    public static String addItem(String item) {
        list.add(item);
        return "Added to list: " + item;
    }

    public static String listItems() {
        if (list.isEmpty()) {
            return "You haven't added anything to the list, dummy!";
        }
        StringBuilder fullList = new StringBuilder("Here are your tasks:\n");
        int counter = 1;
        for (String s : list) {
            String currItem = counter + ". " + s + "\n";
            fullList.append(currItem);
            counter++;
        }
        return fullList.toString();
    }
}
