import java.util.ArrayList;
/**
 * This class represents a todolist.
 * It provides methods to add and print tasks in the todolist.
 */
public class TodoList {
    private ArrayList<Task> listOfTasks;

    public TodoList() {
        listOfTasks = new ArrayList<>();
    }

    /**
     * Adds new task to todolist
     *
     * @param name the name of task to be added
     */
    public void addTask(String name) {
        Task task = new Task(name);
        listOfTasks.add(task);
        Reply.printMessage(" added: " + task.printTask());
    }

    /**
     * print all tasks added to todolist
     *
     * @return String of all tasks added
     */
    public String printTodo(){
        StringBuilder tasks = new StringBuilder();

        for (int i = 0; i < listOfTasks.size(); i++) {
            tasks.append(" ").append(i + 1).append(". ").append(listOfTasks.get(i).printTask());
            if (i < listOfTasks.size() - 1) {
                tasks.append("\n");
            }
        }
        return tasks.toString();
    }
}
