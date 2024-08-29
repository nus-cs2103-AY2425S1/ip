package wenjigglybot;

public class Ui {

    public void intro() {
        String name = "WenJigglyBot";
        System.out.println("Sup im " + name);
        System.out.println("What can I do for you?");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showDeleteTask(TaskList tasks, int idx) {
        System.out.println("\tRemoving this task!");
        System.out.println("\t\t" + tasks.get(idx));
    }

    public void showTaskCount(TaskList tasks) {
        System.out.println("You now have" + " " + tasks.size() + " tasks!");
    }

    public void showCompletedTask(Task task) {
        System.out.println("\tYay! wenjigglybot.Task Completed!");
        System.out.println("\t" + task);
    }

    public void showUncompletedTask(Task task) {
        System.out.println("\tGet to work boy, why not done!!!");
        System.out.println("\t" + task);
    }

    public void showAddedTask(Task task) {
        System.out.printf("\tAdding %s\n", task.taskType());
        System.out.printf("\tDone! Added: %s\n", task.getDescription());
    }

    public void displayTasks(TaskList tasks) {
        showLine();
        System.out.println("Here are your tasks :)");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s%n", i + 1, tasks.get(i).toString());
        }
        showLine();
    }
}