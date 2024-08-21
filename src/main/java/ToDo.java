import java.util.ArrayList;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    public static void addToDo(TaskList list, ArrayList<String> tokens) {
        if (tokens.size() == 1) {
            UI.response("Failed. Specify a task for your todo!!!! D:");
        } else {
            String taskDescription = "";
            int len = tokens.size();
            for (int i = 1; i < len; i++) {
                taskDescription += tokens.get(i) + " ";
            }
            ToDo todo = new ToDo(taskDescription);
            list.addTask(todo);
        }
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
