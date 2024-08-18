import java.util.Scanner;
import java.util.ArrayList;
public class Cookie {
    enum Commands {
        list,
        bye
    }
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String task) {
        tasks.add(new Task(task));
    }

    public String printTasks() {
        int count = 1;
        StringBuilder list = new StringBuilder();
        for(Task task: this.tasks) {
            list.append(count++).append(": ").append(task.toString());
        }
        return list.toString();
    }
    public String printLogo() {
        return "    o      o    \n"
                + " ____\\____/____\n"
                + "|   _      _   |\n"
                + "|  / \\    / \\  |   /\n"
                + "|  \\_/    \\_/  |  /\n"
                + "|              | /\n"
                + "|______________|/\n"
                + "\n";
    }
    public String printGreet() {
        return "Hello! I'm Cookie \n"
                + "How can I help you?\n"
                + "\n";
    }
    public String printQuit() {
        return "Bye. See you soon!";
    }
    public static void main(String[] args) {
        Cookie cookie = new Cookie();
        System.out.println(cookie.printLogo() + cookie.printGreet());

        Scanner scanner = new Scanner(System.in);
        String echoText = scanner.nextLine();

        while(!echoText.equals("bye")) {

            if (echoText.equals("list")) {
                System.out.println(cookie.printTasks());
            } else {
                System.out.println("added: " + echoText);
                cookie.addTask(echoText);
            }

            echoText = scanner.nextLine();
        }

        System.out.println(cookie.printQuit());

    }
}
