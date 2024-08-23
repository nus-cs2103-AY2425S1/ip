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
        for (int i=0; tasks[i]!=null; i++) {
            list.append(tasks[i].displayTask()).append("\n");
        }
        return line + list + line;
    }
}
