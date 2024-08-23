import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;
    private int count = 0;

    public TaskList() {
        this.list = new ArrayList<>();
    }
    public String addTodo(String item) {
        Todo curr = new Todo(item, ++count);
        list.add(curr);
        return "Ok! I've added a new todo task:\n" + curr +
                "\n" + countTasks();
    }

    public String addDeadline(String item, String dueDate) {
        Deadline curr = new Deadline(item, ++count, dueDate);
        list.add(curr);
        return "Ok! I've added a new task with a deadline:\n" + curr +
                "\n" + countTasks();
    }

    public String addEvent(String item, String fromDate, String toDate) {
        Event curr = new Event(item, ++count, fromDate, toDate);
        list.add(curr);
        return "Ok! I've added a new event:\n" + curr +
                "\n" + countTasks();
    }

    public String countTasks() {
        return "You have " + count + " tasks in total.";
    }

    public String listItems() {
        if (list.isEmpty()) {
            return "You haven't added anything to the list, dummy!";
        }
        StringBuilder fullList = new StringBuilder("Here are your tasks:\n");
        for (Task t : list) {
            String currItem = t.getCount() + "." + t + "\n";
            fullList.append(currItem);
        }
        fullList.append("That's all, you can do this!");
        return fullList.toString();
    }

    public String markItem(int place) {
        try {
            Task curr = list.get(place - 1);
            return curr.mark()
                ? "Well done! I have checked this item off the list:\n" + curr
                : "This item was already marked as done previously!";
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }

    public String unmarkItem(int place) {
        try {
            Task curr = list.get(place - 1);
            return curr.unmark()
                ? "Oh no! It's ok, I will uncheck this item for now:\n" + curr
                : "This item was already unchecked previously!";
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }
}
