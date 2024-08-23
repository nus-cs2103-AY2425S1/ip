import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;
    private int count = 1;

    public TaskList() {
        this.list = new ArrayList<>();
    }
    public String addItem(String item) {
        list.add(new Task(item, this.count++));
        return "Added to list: " + item;
    }

    public String listItems() {
        if (list.isEmpty()) {
            return "You haven't added anything to the list, dummy!";
        }
        StringBuilder fullList = new StringBuilder("Here are your tasks:\n");
        for (Task t : list) {
            String currItem = t + "\n";
            fullList.append(currItem);
        }
        fullList.append("That's all, you can do this!");
        return fullList.toString();
    }

    public String markItem(int place) {
        try {
            Task curr = list.get(place - 1);
            curr.mark();
            return "Well done! I have checked this item off the list:\n" + curr;
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }

    public String unmarkItem(int place) {
        try {
            Task curr = list.get(place - 1);
            curr.unmark();
            return "Oh no! It's ok, I will uncheck this item for now:\n" + curr;
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }
}
