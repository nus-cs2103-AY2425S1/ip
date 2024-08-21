import java.util.Scanner;
import java.util.ArrayList;
public class MichaelScott {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        boolean flag = true;
        ArrayList<Task> todo = new ArrayList<Task>();

        String logo = " __  __ _      _                _   ____            _   _  \n"
                + "|  \\/  (_) ___| |__   __ _  ___| | / ___|  ___ ___ | |_| |_ \n"
                + "| |\\/| | |/ __| '_ \\ / _` |/ _ \\ | \\___ \\ / __/ _ \\| __| __|\n"
                + "| |  | | | (__| | | | (_| |  __/ |  ___) | (_| (_) | |_| |_ \n"
                + "|_|  |_|_|\\___|_| |_|\\__,_|\\___|_| |____/ \\___\\___/ \\__|\\__|\n";

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Michael Scott");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while(flag) {
            String command = myScanner.nextLine();
            String[] parts = command.split(" ");
            if (parts[0].equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < todo.size(); i++) {
                    System.out.println(i + 1 + ". " + todo.get(i).toString());
                }
                System.out.println("____________________________________________________________");

            } else if (parts[0].equals("blah")) {
                System.out.println("____________________________________________________________");
                System.out.println("blah");
                System.out.println("____________________________________________________________");
            } else if (parts[0].equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Catch you on the flippity flip! ");
                System.out.println("____________________________________________________________");
                flag = false;
            } else if (parts[0].equals("mark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                Task task = todo.get(index);
                task.completeTask();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task.toString());
                System.out.println("____________________________________________________________");
            } else if (parts[0].equals("unmark")) {
                int index = Integer.parseInt(parts[1]) - 1;
                Task task = todo.get(index);
                task.undoTask();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(task.toString());
                System.out.println("____________________________________________________________");
            } else {
                todo.add(new Task(command));
                System.out.println("____________________________________________________________");
                System.out.println("added: " + command);
                System.out.println("____________________________________________________________");
            }

        }
    }
}
