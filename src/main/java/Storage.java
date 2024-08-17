import java.util.ArrayList;

public class Storage {
    private final ArrayList<Task> storage = new ArrayList<Task>();

    public Task readItem(int index) {
        return this.storage.get(index);
    }

    public String addItem(String item) {
        Task task = new Task(item);
        this.storage.add(task);
        return "added: " + task.printTask();
    }

    public String processCommand(String command) {
        if (command.equals("list")) {
            return this.printList();
        } else if (command.contains("mark") || command.contains("unmark")) {
            String[] commandParts = command.split(" ");
            int index = Integer.parseInt(commandParts[1]);
            if (commandParts[0].equals("mark")) {
                return this.storage.get(index).markDone();
            } else {
                return this.storage.get(index).markUndone();
            }
        } else {
            return this.addItem(command);
        }
    }

    public String printList() {
        StringBuilder currentList = new StringBuilder();
        for (int i = 1; i < storage.size() + 1; i++) {
            currentList.append(Integer.toString(i)).append(".").append(this.readItem(i - 1).printTask());
            if (i < storage.size()) {
                currentList.append("\n");
            }
        }
        return currentList.toString();
    }

    public static void main(String[] args) {
        Storage storage = new Storage();
        System.out.println(storage.addItem("book"));
        System.out.println(storage.readItem(0));
    }
}
