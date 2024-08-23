public class ListOfTask {
    private Task[] tasks;
    private int index;

    public ListOfTask() {
        this.tasks = new Task[100];
        this.index = 0;
    }

    public int getTotal() {
        return this.index;
    }

//    public String addTask(String t) {
//        this.tasks[index] = new Task(t);
//        this.index++;
//        return "     ____________________________________________________________ \n" +
//                "     " + "added: " + t + "\n" +
//                "     ____________________________________________________________ \n";
//    }

    public Task addToDo(String t) {
        Task task = new ToDoTask(t);
        this.tasks[index] = task;
        this.index++;
        return task;
//                "     ____________________________________________________________ \n" +
//                "     Got it. I've added this task: \n" +
//                task.printTask() +
//                "\n" +
//                "Now you have" + index + "tasks in the list.\n" +
//                "     ____________________________________________________________ \n";
    }

    public Task addDeadline(String t, String date) {
        Task task = new DeadlineTask(t, date);
        this.tasks[index] = task;
        this.index++;
        return task;
    }

    public Task addEvent(String t, String start, String end) {
        Task task = new EventTask(t, start, end);
        this.tasks[index] = task;
        this.index++;
        return task;
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

        return output +
                "     ____________________________________________________________ \n";
    }
}
