public class TaskManager {
    private String[] tasks = new String[100];
    private int counter = 0;

    public String addTask(String task) {
        if (counter < tasks.length) {
            tasks[counter++] = task;
            return "added: " + task;
        }
        return "Task list is full, unfortunately ~";
    }

    public String listTasks() {
        if (counter == 0) {
            return "You haven't add any task yet! :L";
        }
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < counter; i++) {
            list.append(i + 1).append(". ").append(tasks[i]).append("\n");
        }
        return list.toString();
    }
}
