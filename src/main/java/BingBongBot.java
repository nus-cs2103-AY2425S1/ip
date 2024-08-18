import java.util.ArrayList;
import java.util.List;

public class BingBongBot {
    private final BingBongUI ui;
    private final List<Task> taskList;

    public BingBongBot(BingBongUI ui) {
        this.ui = ui;
        this.taskList = new ArrayList<>();
    }

    public void run() {
        ui.showGreeting();
        boolean isRunning = true;

        while (isRunning) {
            String command = ui.readCommand();
            if (command.equalsIgnoreCase("bye")) {
                isRunning = false;
                ui.showGoodbye();
            } else if (command.equalsIgnoreCase("list")) {
                listTasks();
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                markTask(index);
            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                unmarkTask(index);
            } else {
                addTask(command);
            }
        }
    }

    private void listTasks() {
        if (taskList.isEmpty()) {
            ui.showResponse("No commands have been saved.");
        } else {
            StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                list.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
            ui.showResponse(list.toString());
        }
    }

    private void addTask(String command) {
        if (command.startsWith("todo")) {
            String description = command.substring(5).trim();
            Task todo = new Todo(description);
            taskList.add(todo);
            showAddTaskMessage(todo);
        } else if (command.startsWith("deadline")) {
            String[] parts = command.substring(9).trim().split(" /by ");
            String description = parts[0];
            String by = parts[1];
            Task deadline = new Deadline(description, by);
            taskList.add(deadline);
            showAddTaskMessage(deadline);
        } else if (command.startsWith("event")) {
            String[] parts = command.substring(6).trim().split(" /from | /to ");
            String description = parts[0];
            String from = parts[1];
            String to = parts[2];
            Task event = new Event(description, from, to);
            taskList.add(event);
            showAddTaskMessage(event);
        } else {
            ui.showResponse("command not recognised");
        }
    }

    public void showAddTaskMessage(Task t) {
        ui.showResponse("Got it. I've added this task:\n" + t
                + "\n" + "Now you have " + taskList.size()
                + " tasks in the list");
    }
    private void markTask(int i) {
        Task task = taskList.get(i);
        task.markAsDone();
        ui.showResponse("Nice! I've marked this task as done:\n" + task);
    }

    private void unmarkTask(int i) {
        Task task = taskList.get(i);
        task.markAsNotDone();
        ui.showResponse("OK, I've marked this task as not done yet:\n" + task);
    }
}
