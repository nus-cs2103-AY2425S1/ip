public class TaskManager {
    private Task[] tasks = new Task[100];
    private int counter = 0;

    public String addTask(String taskDescription) {
        if (counter < tasks.length) {
            tasks[counter] = new Task(taskDescription);
            counter++;
            return "added: " + taskDescription;
        }
        return "Task list is full, unfortunately ~";
    }

    public String listTasks() {
        if (counter == 0) {
            return "You haven't added any task yet! :L";
        }
        StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < counter; i++) {
            list.append(i + 1).append(". ").append(tasks[i].toString()).append("\n");
        }
        return list.toString();
    }
}
