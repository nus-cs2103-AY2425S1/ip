package task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public void addTask(Task task) {
        super.add(task);
    }

    public Task deleteTask(int i) {
        return super.remove(i);
    }

    public Task markTask(int i) {
        super.get(i).markAsDone();
        return super.get(i);
    }

    public Task unmarkTask(int i){
        super.get(i).markAsNotDone();
        return super.get(i);
    }

    @Override
    public String toString() {
        if (super.isEmpty()) {
            return "You have no tasks currently";
        } else if (super.size() == 1) {
            return "You have 1 task currently";
        } else {
            return "You have " + super.size() + " tasks currently";
        }
    }
}
