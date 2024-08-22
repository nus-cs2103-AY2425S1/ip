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

    public String markTask(int taskNumber, boolean isDone) {
        if (taskNumber > 0 && taskNumber <= counter) {
            if (isDone) {
                tasks[taskNumber - 1].markAsDone();
                return "Yippee~ *uweah* I've marked this task as done:\n  " + tasks[taskNumber - 1].toString();
            } else {
                tasks[taskNumber - 1].unmarkAsDone();
                return "LOL I've marked this task as not done yet:\n  " + tasks[taskNumber - 1].toString();
            }
        }
        return "Invalid task number!!!!";
    }
}
