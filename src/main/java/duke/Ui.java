package duke;

public class Ui {

    public Ui() {
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            int serial = i + 1;
            Task task = taskList.getTask(i);
            System.out.println(serial + "." + task.toString());
        }
    }

}
