public class Ui {
    public void showLine() {
        System.out.println("----------------------------------");
    }

    public void showCommandEndMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showCommandEndMessage(String command, String task) {
        switch (command) {
        case "mark":
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);
            break;

        case "unmark":
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);

        case "delete":
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);

        case "todo":
            System.out.println("Got it. I've added this task:");
            System.out.println(task);

        case "deadline":
            System.out.println("Got it. I've added this task:");
            System.out.println(task);

        case "event":
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
        }
    }

    public void showTaskNumber(int size) {
        System.out.printf("Now you have %d %s in the list\n", size, size == 1 ? "task" : "tasks");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
