import java.util.Scanner;
public class Espresso {
    private String[] tasks;
    private int count;

    public Espresso() {
        tasks = new String[100];
        count = 0;
    }

    void addToList(String str) {
        if (count < tasks.length) {
            tasks[count] = str;
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
            }else if (str.equals("list")) {
                printList();
            } else {
                addToList(str);
            }
        }
        sc.close();
    }
    public static void main(String[] args) {
        Espresso esp = new Espresso();
        esp.process();
    }
}
