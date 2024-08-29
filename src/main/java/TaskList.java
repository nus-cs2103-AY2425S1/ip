import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private Task[] tasks;
    public int cmdNum = 0;
    TaskList(Task[] t, int  n){
        tasks = t;
        cmdNum = n;
    }

    public void add(Task t) {
        tasks[cmdNum] = t;
        Ui.printText("Got it. I've added this task:\n  " + get(cmdNum));
        cmdNum++;
        Ui.printText("Now you have " + cmdNum + " tasks in the list.");
    }
    public Task get(int n) {
        return tasks[n];
    }
    public void delete(int n) throws DuckException {
        if (n >= cmdNum + 1) {
            throw new DuckException("There is no task with the given task number.");
        }
        Ui.printText("Noted. I've removed this task:\n "+ get(n-1));
        ArrayList<Task> newCmds = new ArrayList<Task>(Arrays.asList(tasks));
        newCmds.remove(n-1);
        tasks = newCmds.toArray(new Task[100]);
        n--;
        cmdNum--;
        Ui.printText("Now you have " + cmdNum + " tasks in the list.");
    }

    public Task[] allTasks(){
        return tasks;
    }

    public void printTasks() {
        Ui.printText("Here are the tasks in your list:");
        for (int n = 1; n<=cmdNum; n++) {
            System.out.println(n + ". " + get(n - 1));
        }
    }
    public void mark(int num) throws DuckException {
        if (num < cmdNum + 1) {
            get(num-1).mark();
        } else {
            throw new DuckException("There is no task with the given task number.");
        }
        Ui.printText("Nice! I've marked this task as done:\n "+ get(num-1));
    }

    public void unmark(int num) throws DuckException {
        if (num < cmdNum + 1) {
            get(num-1).unmark();
        } else {
            throw new DuckException("There is no task with the given task number.");
        }
        Ui.printText("OK, I've marked this task as not done yet:\n "+ get(num-1));
    }
    public void save() throws DuckException {
        Storage.save(allTasks(), cmdNum);
    }
}
