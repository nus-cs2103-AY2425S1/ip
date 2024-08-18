import java.util.ArrayList;
import java.util.List;

public class BingBongBot {
    private final BingBongUI ui;
    private final List<Task> tasksList;

    public BingBongBot(BingBongUI ui) {
        this.ui = ui;
        this.tasksList = new ArrayList<>();
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
                Task t = new Task(command);
                this.tasksList.add(t);
                ui.showResponse("added: " + command);
            }
        }
    }

    private void listTasks() {
        if (tasksList.isEmpty()) {
            ui.showResponse("No commands have been saved.");
        } else {
            StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasksList.size(); i++) {
                list.append(i + 1).append(". ").append(tasksList.get(i)).append("\n");
            }
            ui.showResponse(list.toString());
        }
    }

    private void markTask(int i) {
        Task task = tasksList.get(i);
        task.markAsDone();
        ui.showResponse("Nice! I've marked this task as done:\n" + task);
    }

    private void unmarkTask(int i) {
        Task task = tasksList.get(i);
        task.markAsNotDone();
        ui.showResponse("OK, I've marked this task as not done yet:\n" + task);
    }
}
