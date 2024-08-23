public class List {

    private Task[] tasks;

    public List(){
        this.tasks = new Task[100];
    }

    public void addTaskToList(Task task) {
        tasks[task.getIndex()] = task;
        System.out.println(task.addedString());
    }

    public String displayList() {
        String line = "____________________________________________________________\n";
        StringBuilder list = new StringBuilder();
        list.append("Here are the tasks in your list:\n");
        for (int i=0; tasks[i]!=null; i++) {
            list.append(i+1).append(".").append(tasks[i].displayTask());
        }
        return line + list + line;
    }

    public Task getTask(int index) {
        return tasks[index];
    }
}
