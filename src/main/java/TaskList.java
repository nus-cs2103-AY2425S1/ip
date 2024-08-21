import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList(){
        this.taskList = new ArrayList<>();
    }
    public Task getTask(int index) {
        return this.taskList.get(index);
    }
    public void listAllTask() {
        System.out.println("-------------------------------------");
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i < this.taskList.size(); i ++) {
            System.out.println("\t" + (i + 1) + "." + this.getTask(i).toString());
        }
        System.out.println("-------------------------------------");
    }

    public void addTask(Task userTask) {
        System.out.println("-------------------------------------");
        this.taskList.add(userTask);
        System.out.println("\tadded: " + userTask.getDescription());
        System.out.println("-------------------------------------");
    }


}
