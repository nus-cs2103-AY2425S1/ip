import java.util.Scanner;
public class Espresso {
    private Task[] tasks ;
    private int count;

    public Espresso() {
        tasks = new Task[100];
        count = 0;
    }

    void addToList(String str) {
        if (count < tasks.length) {
            Task task = new Task(str);
            tasks[count] = task;
            System.out.println("____________________________________________________________");
            System.out.println("added: " + str);
            System.out.println("____________________________________________________________");
            count++;
        } else {
            System.out.println("Task list is full");
        }
    }

    void printList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < count; i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    void process() {
        System.out.println("_________________________________________");
        System.out.println("Hello! I'm Espresso");
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________");
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            str = sc.nextLine();

            if (str.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (str.startsWith("mark ")) {
                String extractNum = str.substring(str.indexOf(' ') + 1).trim();
                int pos = Integer.valueOf(extractNum);
                markTask(pos - 1);
            } else if (str.startsWith("unmark ")) {
                String extractNum = str.substring(str.indexOf(' ') + 1).trim();
                int pos = Integer.valueOf(extractNum);
                unmarkTask(pos - 1);
            }else if (str.equals("list")) {
                printList();
            } else {
                addToList(str);
            }
        }
        sc.close();
    }
    void markTask(int position) {
        if (position >= 0 && position < count) {
            tasks[position].mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[position]);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void unmarkTask(int position) {
        if (position >= 0 && position < count) {
            tasks[position].unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[position]);
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public static void main(String[] args) {
        Espresso esp = new Espresso();
        esp.process();
    }
}
