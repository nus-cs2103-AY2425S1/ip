import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<Task>();

    public Task readItem(int index) {
        return this.taskList.get(index);
    }

    public String addItem(String item) {
        Task task = new Task(item);
        this.taskList.add(task);
        return "added: " + task.printTask();
    }

    public String processCommand(String command) {
        if (command.equals("list")) {
            return this.printList();
        } else if (command.contains("mark") || command.contains("unmark")) {
            String[] commandParts = command.split(" ");
            int index = Integer.parseInt(commandParts[1]);
            if (commandParts[0].equals("mark")) {
                return this.taskList.get(index).markDone();
            } else {
                return this.taskList.get(index).markUndone();
            }
        } else {
            return this.addItem(command);
        }
    }

    public String printList() {
        StringBuilder currentList = new StringBuilder();
        for (int i = 1; i < taskList.size() + 1; i++) {
            currentList.append(Integer.toString(i)).append(".").append(this.readItem(i - 1).printTask());
            if (i < taskList.size()) {
                currentList.append("\n");
            }
        }
        return currentList.toString();
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        System.out.println(taskList.addItem("book"));
        System.out.println(taskList.readItem(0));
    }
}
