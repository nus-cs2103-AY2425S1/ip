package beeboo.components;

import beeboo.task.Tasks;

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

    public void addList(Tasks task) {
        list.add(task);
    }

    public Tasks deleteItem(int index) {
        Tasks item = list.get(index);
        list.remove(item);
        return item;
    }


    public Tasks unmarkDone(int index) {
        Tasks task = list.get(index);
        task.unmarkDone();
        return task;
    }

    public Tasks markDone(int index) {
        Tasks task = list.get(index);
        task.markDone();
        return task;
    }

    public String produceList() {
        String result = "";
        for (Tasks task : list) {
            result = result + (list.indexOf(task) + 1) + ". " + " " + task + "\n";
        }
        return result;
    }

    public int getSize() {
        return list.size();
    }

    public ArrayList<Tasks> getList() {
        return list;
    }

}

