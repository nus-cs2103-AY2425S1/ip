import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;
    public int n = 0;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public String add(Task adding) throws InputErrorException{
        tasks.add(adding);
        n++;
        return getSpecific(n);
    }

    public int getN() {
        return n;
    }

    public void mark(int i) throws InputErrorException{
        if(tasks.isEmpty()) {
            throw new InputErrorException("Sorry your task list is currently empty");
        } try {
            tasks.get(i - 1).markDone();
        } catch (IndexOutOfBoundsException e) {
            throw new InputErrorException("Sorry you do not have that many Tasks in your list");
        } catch (NullPointerException e) {
            throw new InputErrorException("Sorry your task list is currently empty");
        }
    }

    public String getSpecific(int i) throws InputErrorException {
        if(tasks.isEmpty()) {
            throw new InputErrorException("Sorry your task list is currently empty");
        } try {
            String temp = "";
            Task task = tasks.get(i - 1);
            temp = temp + task.getTaskType();
            temp = temp + task.getStatusIcon() + " ";
            temp = temp + task.getDescription();
            return temp;
        } catch(IndexOutOfBoundsException e) {
            throw new InputErrorException("Sorry you do not have that many Tasks in your list");
        } catch (NullPointerException e) {
            throw new InputErrorException("Sorry your task list is currently empty.");
        }
    }

    public String get() {
        String temp = "";
        for (int i = 0; i < n; i++) {
            Task task = tasks.get(i);
            temp = String.format("%s %d.", temp, i + 1);
            temp = temp + task.getTaskType();
            temp = temp + task.getStatusIcon() + " ";
            temp = temp + task.getDescription();
            if (i < n - 1) {
                temp = temp + "\n";
            }
        }
        return temp;
    }

    public String delete(int i) throws InputErrorException {
        String str = getSpecific(i-1);
        tasks.remove(i-1);
        n--;
        return str;
    }
}
