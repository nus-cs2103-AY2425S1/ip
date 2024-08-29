import java.util.ArrayList;

public class Tasklist {
    ArrayList<Task> taskList;

    public Tasklist() {
        this.taskList = new ArrayList<>();
    }

    public String getList() {
        String retString = "";
        for (int i = 0; i < taskList.size(); i++) {
            retString += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return retString;
    }

    public Task getStr(int pos) {
        return taskList.get(pos);
    }

    public Task getLast() {
        return taskList.get((this.getSize() - 1));
    }

    public int getSize() {
        return taskList.size();
    }

    public void add(Task t) {
        taskList.add(t);
    }

    public void delete(int pos) {
        taskList.remove(pos);
    }
}

