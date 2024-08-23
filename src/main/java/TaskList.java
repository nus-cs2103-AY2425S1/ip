import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public String addTask(String taskType, String input) {
        Task newTask;
        String response;
        try {
            switch (taskType) {
                case "todo":
                    newTask = Todo.handleInput(input);
                    response = "Ok! I've added a new todo task:\n" + newTask +
                            "\n";
                    break;
                case "deadline":
                    newTask = Deadline.handleInput(input);
                    response = "Ok! I've added a new task with a deadline:\n" + newTask +
                            "\n";
                    break;
                case "event":
                    newTask = Event.handleInput(input);
                    response = "Ok! I've added a new event:\n" + newTask +
                            "\n";
                    break;
                default:
                    response = "";
                    newTask = null;
                    break;
            }
            list.add(newTask);
            return response + countTasks();
        } catch (WrongInputException e) {
            return e.toString();
        }
    }

    public String countTasks() {
        return "You have " + list.size() + " tasks in total.";
    }

    public String listItems() {
        if (list.isEmpty()) {
            return "You haven't added anything to the list, dummy!";
        }
        StringBuilder fullList = new StringBuilder("Here are your tasks:\n");
        for (Task t : list) {
            String currItem = list.indexOf(t) + 1 + "." + t + "\n";
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
