import java.util.Scanner;

public class Rei {
    public static void main(String[] args) {
        String logo = "  ____  _____ ___ \n"
                + " |  _ \\| ____|_ _|\n"
                + " | |_) |  _|  | | \n"
                + " |  _ <| |___ | | \n"
                + " |_| \\_\\_____|___|\n";

        System.out.println("Annyeong! I'm\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("-----------YOU------------");

        Task[] list = new Task[100];
        int count = 1;
        Scanner scanner = new Scanner(System.in);


        while (!scanner.hasNext("annyeong") && !scanner.hasNext("Annyeong")) {
            if (scanner.hasNext("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i < count; i++) {
                    System.out.println(i + ". " + list[i].printTask());
                }
                scanner.next();
            } else if (scanner.hasNext("mark")) {
                scanner.next();
                int id = scanner.nextInt();
                if (id < count) {
                    list[id].markAsDone();
                    System.out.println("Okay! I've marked this task as done: ");
                    System.out.println(list[id].printTask());
                } else {
                    System.out.println("No task found. Please retry!");
                }

            } else if (scanner.hasNext("unmark")) {
                scanner.next();
                int id = scanner.nextInt();
                if (id < count) {
                    list[id].markAsUndone();
                    System.out.println("Okay! I've marked this task as not done yet: ");
                    System.out.println(list[id].printTask());
                } else {
                    System.out.println("No task found. Please retry!");
                }
            } else {
                String prompt = scanner.nextLine();
                System.out.println("-----------REI♥-----------");
                System.out.println("added: " + prompt);
                System.out.println("-----------YOU------------");
                list[count++] = new Task(prompt);
            }
        }

        scanner.close();

        System.out.println("-----------REI♥-----------");
        System.out.println("Annyeong. Hope to see you soon.");

    }
}
