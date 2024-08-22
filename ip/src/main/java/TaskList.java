import java.util.ArrayList;

public class TaskList {

    class Task {
        String name;

        public Task(String name) {
            this.name = name;
        }
    }

    ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }
    public void list() {
        for(int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, this.list.get(i).name);
        }
    }
    public void add(String x){
        this.list.add(new Task(x));
    }

}