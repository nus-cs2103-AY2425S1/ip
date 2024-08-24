public class TaskList {
    private final Task[] list;
    private int numOfTasks;

    //max of task list
    public TaskList(int i) {
        list = new Task[i];
    }

    public String addTask(Task task) {
        if (checkListSize()) {
            list[numOfTasks] = task;
            numOfTasks++;
            return String.format(
                    "Got it. I've added this task:\n   %s\nNow you have %d %s in the list.",task.getTaskString(), numOfTasks, numOfTasks == 1? "task": "tasks");
        } else return "Too many tasks !!!";
    }

    private boolean checkListSize() {
        return !(numOfTasks > 100);
    }

    public Task getTask(int i) {
        return list[i];
    }

    public String printList() {
        String listString = "Here are the task in your list:\n";
        for (int i = 0; i < numOfTasks; i++) {
            listString = listString.concat(i+1 + "." + this.getTask(i).getTaskString());
        }
        return listString;
    }



}
