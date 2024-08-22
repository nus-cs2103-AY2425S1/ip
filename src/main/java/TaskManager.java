public class TaskManager {
    private Task[] tasks = new Task[100];
    private int counter = 0;

    public String addTask(String type, String description, String... timeInfo) {
        if (counter >= tasks.length) {
            return "Task list is full, unfortunately ~";
        }
        switch (type) {
            case "todo":
                tasks[counter] = new Todo(description);
                break;
            case "deadline":
                tasks[counter] = new Deadline(description, timeInfo[0]);
                break;
            case "event":
                tasks[counter] = new Event(description, timeInfo[0], timeInfo[1]);
                break;
        }
        counter++;
        return "Roger that! I've added in this task:\n  " + tasks[counter - 1].toString() + "\nNow you have " + counter + " tasks in the list ~ !";
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
