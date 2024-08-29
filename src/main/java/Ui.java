import java.util.List;

public class Ui {

    public void showWelcome() {
        System.out.println("-----------------------");
        System.out.println("DGPT> Hello! I'm DGPT");
        System.out.println("-----------------------");
    }

    public void showLine() {
        System.out.println("-----------------------");
    }

    public void showBye() {
        System.out.println("-----------------------");
        System.out.println("DGPT> Bye. Hope to see you again soon!");
        System.out.println("-----------------------");
    }

    public void showLoadingError() {
        System.out.println("-----------------------");
        System.out.println("Error loading file");
        System.out.println("-----------------------");
    }

    public void showReadingError() {
        System.out.println("-----------------------");
        System.out.println("Error reading file");
        System.out.println("-----------------------");
    }

    public void showError(Exception e) {
        System.out.println("-----------------------");
        System.out.println(e.getMessage());
        System.out.println("-----------------------");
    }

    public void showList(TaskList taskList) {
        System.out.println("-----------------------");
        System.out.println("Here are the following items in your list:");
        for (Task t : taskList.getTaskList()) {
            System.out.println(t.toString());
        }
        System.out.println("-----------------------");
    }

    public void showMark(Task t) {
        System.out.println("-----------------------");
        System.out.println("DGPT> Nice! I've marked this task as done: ");
        System.out.println(t.toString());
        System.out.println("-----------------------");
    }

    public void showUnmark(Task t) {
        System.out.println("-----------------------");
        System.out.println("DGPT> OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
        System.out.println("-----------------------");
    }

    public void showDelete(Task t, int size) {
        System.out.println("-----------------------");
        System.out.println("DGPT> OK, I've removed this task from the list:");
        System.out.println(t.toString());
        System.out.printf("Now you have %d tasks in the list.%n", size);
        System.out.println("-----------------------");
    }

    public void showTask(Task t, int size) {
        System.out.println("-----------------------");
        System.out.println("DGPT> Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.printf("Now you have %d tasks in the list.%n", size);
        System.out.println("-----------------------");
    }

    public void showUser() {
        System.out.print("User> ");
    }
}
