import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class TaskList {

    private List<Task> list;
    private FileOperation fileOperation;

    public TaskList(FileOperation fileOperation) {
        this.list = new ArrayList<>();
        this.fileOperation = fileOperation;
        try {
            this.list = fileOperation.loadTask();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void add(Task item) {
        try {
            this.list.add(item);
            fileOperation.save(this.list);
        } catch (IOException e) {
            System.out.println("Error saving task");
        }
    }

    public String delete(int index) {
        String taskToBeRemoved = list.get(index - 1).toString();
        try {
            this.list.remove(index - 1);
            fileOperation.save(this.list);
        } catch (IOException e) {
            System.out.println("Error saving task");
        }
        return taskToBeRemoved;
    }

    public int getLength() {
        return this.list.size();
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(i+1 + "." + list.get(i).toString());
        }
    }

    public String markAsDone(int index) {
        try {
            list.get(index).markAsDone();
            fileOperation.save(this.list);
        } catch (IOException e) {
            System.out.println("Error saving task");
        }
        return list.get(index).toString();
    }

    public String markAsNotDone(int index) {
        try {
            list.get(index).markAsNotDone();
            fileOperation.save(this.list);
        } catch (IOException e) {
            System.out.println("Error saving task");
        }
        return list.get(index).toString();
    }
}
