import java.util.Scanner;

public class Ui {
    Ui() {}

    public void showWelcome() {
        String greet = "Hello! I'm Bob\nWhat can I do for you?\n";
        show(greet);
    }
    public void show(String show) {
        String line = "-----------------------------\n";
        System.out.println(line + show +"\n" + line);
    }

    public void showAddTask(Task task, int length) {
        String add  = "Got it. I've added this task:\n" + task.toString().trim()
                + "\nNow you have " + length + " tasks in the list.";
        show(add);
    }

    public void showError(String show) {
        String line = "------------ERROR------------\n";
        System.out.println(line + show +"\n" + line);
    }

    public String readCommand() {

        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    public void showAllTasks(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + ". " + tasks.get(i).toString());
        }
    }
}
