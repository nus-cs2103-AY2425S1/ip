import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> botMemory;

    public TaskList(ArrayList<Task> botMemory) {
        this.botMemory = botMemory;
    }

    public void listToString() {
        if (this.botMemory.isEmpty()) {
            System.out.println("No items in your list");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < this.botMemory.size(); i++) {
                System.out.println((i + 1) + ". " + this.botMemory.get(i).toString());
            }
        }
    }

    public ArrayList<Task> getBotMemory() {
        return this.botMemory;
    }


    public void toggleTaskDone(int taskNumber) {
        botMemory.get(taskNumber).toggleTaskDone();
        Ui.printLine();
    }

    public void removeTask(int taskToRemove) {
        Ui.printLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(" " + botMemory.get(taskToRemove).toString());
        botMemory.remove(taskToRemove);
        System.out.println("Now you have " + botMemory.size() + " tasks in the list.");
        Ui.printLine();
    }

    public void addTask(Task newTask) {
        botMemory.add(newTask);
        Ui.printLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println(" " + newTask.toString());
        System.out.println("Now you have " + botMemory.size() + " task(s) in the list.");
        System.out.println();
        this.listToString();
        Ui.printLine();

    }


}
