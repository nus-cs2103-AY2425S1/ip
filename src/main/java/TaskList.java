public class TaskList {
    public Task[] Task;
    public int n = 0;

    public TaskList() {
        Task = new Task[100];
    }

    public String add(String adding) {
        Task[n] = new Task(adding);
        n++;
        return adding;
    }

    public void mark(int i) {
        Task[i - 1].markDone();
    }

    public String getSpecific(int i) {
        String temp = Task[i - 1].getStatusIcon() + " ";
        temp = temp + Task[i - 1].getDescription();
        return temp;
    }

    public String get() {
        String temp = "";
        for (int i = 0; i < n; i++) {
            temp = String.format("%s %d.", temp, i + 1);
            temp = temp + Task[i].getStatusIcon() + " ";
            temp = temp + Task[i].getDescription();
            if (i < n - 1) {
                temp = temp + "\n";
            }
        }
        return temp;
    }
}
