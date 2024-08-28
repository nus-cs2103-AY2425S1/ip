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
                System.out.println("-----------REI♥-----------");
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i < count; i++) {
                    System.out.println(i + ". " + list[i]);
                }
                scanner.next();
            } else if (scanner.hasNext("mark")) {
                System.out.println("-----------REI♥-----------");
                scanner.next();
                int id = scanner.nextInt();
                if (id < count) {
                    list[id].markAsDone();
                    System.out.println("Okay! I've marked this task as done: ");
                    System.out.println(list[id]);
                } else {
                    System.out.println("No task found. Please retry!");
                }

            } else if (scanner.hasNext("unmark")) {
                System.out.println("-----------REI♥-----------");
                scanner.next();
                int id = scanner.nextInt();
                if (id < count) {
                    list[id].markAsUndone();
                    System.out.println("Okay! I've marked this task as not done yet: ");
                    System.out.println(list[id]);
                } else {
                    System.out.println("No task found. Please retry!");
                }
            } else if (scanner.hasNext("todo")
                        || scanner.hasNext("deadline")
                        || scanner.hasNext("event")) {

                String prompt = scanner.nextLine();

                System.out.println("-----------REI♥-----------");
                System.out.println("Got it. I've added this task:");

                if (prompt.startsWith("todo")) {
                    list[count++] = Task.createToDo(prompt.substring(5));
                } else if (prompt.startsWith("deadline")) {
                    list[count++] = Task.createDeadline(prompt.substring(9, prompt.indexOf("/by")),
                                                        prompt.substring(prompt.indexOf("/by") + 4));
                } else { // event
                    list[count++] = Task.createEvent(prompt.substring(6, prompt.indexOf("/from")),
                                                     prompt.substring(prompt.indexOf("/from") + 6, prompt.indexOf("/to")),
                                                     prompt.substring(prompt.indexOf("/to") + 4));
                }

                System.out.println("    " + list[count - 1]);
                System.out.println(String.format("Now you have %d tasks in the list.", count - 1));

            } else {

            }
            System.out.println("-----------YOU------------");
        }

        scanner.close();

        System.out.println("-----------REI♥-----------");
        System.out.println("Annyeong. Hope to see you soon.");

    }
}
