public class ListOfTask {
    private Task[] tasks;
    private int index;

    public ListOfTask() {
        this.tasks = new Task[100];
        this.index = 0;
    }

    public String addTask(String t) {
        this.tasks[index] = new Task(t);
        this.index++;
        return "     ____________________________________________________________ \n" +
                "     " + "added: " + t + "\n" +
                "     ____________________________________________________________ \n";
    }

    public String markDone(int i) {
        return "     ____________________________________________________________ \n" +
                this.tasks[i - 1].markDone() + "\n" +
                "     ____________________________________________________________ \n";
    }

    public String markUndone(int i) {
        return "     ____________________________________________________________ \n" +
                this.tasks[i - 1].markUndone() + "\n" +
                "     ____________________________________________________________ \n";
    }

    public String printList() {
        String output = "     ____________________________________________________________ \n" +
                "     Here are the tasks in your list: \n";
        for (int i = 0; i < this.index; i++) {
            int label = i + 1;
            output += "     " + label + "." + this.tasks[i].printTask() + "\n";
        }
        return output + "     ____________________________________________________________ \n";
    }
}
