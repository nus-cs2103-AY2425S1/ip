public class Ui {

    public static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TaskList.getSize(); i++) {
            int serial = i + 1;
            Task task = TaskList.getTask(i);
            System.out.println(serial + "." + task.toString());
        }
    }

}
