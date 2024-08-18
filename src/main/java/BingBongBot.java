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
            try {
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
                } else if (command.startsWith("delete")){
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    deleteTask(index);
                } else {
                    addTask(command);
                }
            } catch (BingBongException e) {
                ui.showResponse(e.getMessage());
            } catch (NumberFormatException e) {
                ui.showResponse("Invalid task number. Please enter a valid number.");
            } catch (IndexOutOfBoundsException e) {
                ui.showResponse("Task number is out of range. Please enter a valid task number.");
            } catch (Exception e) {
                ui.showResponse("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void listTasks() throws BingBongException {
        if (taskList.isEmpty()) {
            throw new BingBongException("No tasks have been saved.");
        } else {
            StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < taskList.size(); i++) {
                list.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
            ui.showResponse(list.toString());
        }
    }

    private void addTask(String command) throws BingBongException {
        if (command.startsWith("todo")) {
            String description = command.substring(5).trim();
            if (description.isEmpty()) {
                throw new BingBongException("The description of a todo cannot be empty.");
            }
            Task todo = new Todo(description);
            taskList.add(todo);
            showAddTaskMessage(todo);
        } else if (command.startsWith("deadline")) {
            String[] parts = command.substring(9).trim().split(" /by ");
            if (parts.length < 2) {
                throw new BingBongException("The deadline format is incorrect. Use: deadline <task> /by <time>");
            }
            String description = parts[0];
            String by = parts[1];
            Task deadline = new Deadline(description, by);
            taskList.add(deadline);
            showAddTaskMessage(deadline);
        } else if (command.startsWith("event")) {
            String[] parts = command.substring(6).trim().split(" /from | /to ");
            if (parts.length < 3) {
                throw new BingBongException("The event format is incorrect. Use: event <task> /from <start time> /to <end time>");
            }
            String description = parts[0];
            String from = parts[1];
            String to = parts[2];
            Task event = new Event(description, from, to);
            taskList.add(event);
            showAddTaskMessage(event);
        } else {
            throw new BingBongException("Command not recognised. Please try again...");
        }
    }

    private void showAddTaskMessage(Task t) {
        ui.showResponse("Got it. I've added this task:\n" + t
                + "\n" + "Now you have " + taskList.size()
                + " tasks in the list");
    }

    private void markTask(int i) throws BingBongException {
        if (i < 0 || i >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = taskList.get(i);
        task.markAsDone();
        ui.showResponse("Nice! I've marked this task as done:\n" + task);
    }

    private void unmarkTask(int i) throws BingBongException {
        if (i < 0 || i >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = taskList.get(i);
        task.markAsNotDone();
        ui.showResponse("OK, I've marked this task as not done yet:\n" + task);
    }

    private void deleteTask(int i) throws BingBongException {
        if (i < 0 || i >= taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = taskList.get(i);
        taskList.remove(task);
        showRemoveTaskMessage(task);
    }

    private void showRemoveTaskMessage(Task t) {
        ui.showResponse("Noted. I've removed this task:\n" + t
                + "\n" + "Now you have " + taskList.size()
                + " tasks in the list");
    }
}
