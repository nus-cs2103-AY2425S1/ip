package elster;

import elster.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    protected static void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }

    public void addToList(Task task) {
        list.add(task);
    }

    public Task getTask(int index) throws Elseption {
        Task task;

        try {
            task = list.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new Elseption();
        }

        return task;
    }

    public Integer getSize() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean markTaskAsDone(int index) throws Elseption {
        Task task = getTask(index);

        return task.markAsDone();
    }

    public boolean unmarkTaskAsUndone(int index) throws Elseption {
        Task task = getTask(index);

        return task.unmarkAsUndone();
    }

    public Task  deleteTask(int index) throws Elseption {
        Task task;

        try {
            task = list.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new Elseption();
        }

        return task;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public String printString() {
        StringBuilder returnStr = new StringBuilder();

        if (list.isEmpty()) {
            returnStr.append("    No tasks to do? that's pretty goooood.");

        } else {
            returnStr.append("    So here's the tasks in your list, you should proooobably do them\n");
            for (int i = 0; i < list.size(); i++) {
                returnStr.append("    ").append(i + 1).append(". ").append(list.get(i)).append("\n");
            }
        }

        return returnStr.toString().stripTrailing();
    }

    public String fileString() {
        StringBuilder returnStr = new StringBuilder();

        for (Task task : list) {
            returnStr.append(task.toFileString()).append(System.lineSeparator());
        }

        return returnStr.toString();
    }

    public List<Task> findByDescription(String input) {
        ArrayList<Task> result = new ArrayList<>();

        for (Task i : list) {
            if (i.descContains(input)) {
                result.add(i);
            }
        }
        
        return result;
    }
}
