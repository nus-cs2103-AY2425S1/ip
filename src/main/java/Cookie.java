import java.util.Scanner;
import java.util.ArrayList;
public class Cookie {
    enum Commands {
        list,
        bye
    }
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String description) {
        tasks.add(new Task(description));
    }
    public String markDone(int index) {
        return tasks.get(index - 1).markDone();
    }
    public String unmarkDone(int index) {
        return tasks.get(index - 1).unmarkDone();
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

            } else if (echoText.contains("unmark")) {
                System.out.println("Cookie has marked this task as not done! \n" +
                        cookie.unmarkDone(
                                Integer.parseInt(echoText.substring(7))));

            } else if (echoText.contains("mark")) {
                System.out.println("Cookie has marked this as done! Good job! \n" +
                        cookie.markDone(
                                Integer.parseInt(echoText.substring(5))));

            } else {
                System.out.println("added: " + echoText);
                cookie.addTask(echoText);
            }

            echoText = scanner.nextLine();
        }
        scanner.close();
        System.out.println(cookie.printQuit());

    }
}
