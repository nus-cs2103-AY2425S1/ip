public class TaskList {
    public Task[] tasks;
    public int n = 0;

    public TaskList() {
        tasks = new Task[100];
    }

    public String add(Task adding) {
        tasks[n] = (adding);
        n++;
        return getSpecific(n);
    }

    public int getN() {
        return n;
    }

    public void mark(int i) {
        tasks[i - 1].markDone();
    }

    public String getSpecific(int i) {
        String temp = "";
        Task task = tasks[i-1];
        temp = temp + task.getTaskType();
        temp = temp + task.getStatusIcon() + " ";
        temp = temp + task.getDescription();
        return temp;
    }

    public String get() {
        String temp = "";
        for (int i = 0; i < n; i++) {
            Task task = tasks[i];
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
}
