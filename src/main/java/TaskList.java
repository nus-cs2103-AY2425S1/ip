import java.nio.file.Files;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Tasks> list;
    public TaskList(ArrayList<Tasks> list) {
        this.list = list;
    }

    public TaskList() {
        list = new ArrayList<>();
        Storage.createFile();
    }

    protected void addList(Tasks task) {
        list.add(task);
    }

    protected Tasks deleteItem(int index) {
        Tasks item = list.get(index);
        list.remove(item);
        return item;
    }


    protected Tasks unmarkDone(int index) {
        Tasks task = list.get(index);
        task.unmarkDone();
        return task;
    }

    protected Tasks markDone(int index) {
        Tasks task = list.get(index);
        task.markDone();
        return task;
    }

    protected String produceList() {
        String result = "";
        for (Tasks task : list) {
            result = result + (list.indexOf(task) + 1) + ". " + " " + task + "\n";
        }
        return result;
    }

    protected int getSize() {
        return list.size();
    }

    protected ArrayList<Tasks> getList() {
        return list;
    }

}

