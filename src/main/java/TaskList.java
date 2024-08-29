import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> currentList;

    public TaskList() {
        currentList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listLoaded) {
        currentList = listLoaded;
    }

    public ArrayList<Task> getCurrentList() {
        return currentList;
    }

    public static void printlist() {
        for (int i = 1; i <= currentList.size(); i++) {
            Task task = currentList.get(i-1);
            System.out.println(i + "." + task.toString());
        }
    }

}
