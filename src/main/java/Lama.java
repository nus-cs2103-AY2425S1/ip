import java.util.Scanner;

public class Lama {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String bar = "____________________________________________________________";
        Task[] list = new Task[100];
        int count = 0;

        System.out.println(bar);
        System.out.println("Hello! I'm Lama");
        System.out.println("What can I do for you?");
        System.out.println(bar + "\n");

        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] words = input.split(" ");

            if (words[0].equals("bye")) {
                System.out.println(bar);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(bar);
                break;
            } else if (words[0].equals("list")) {
                System.out.println(bar);
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < count; i++) {
                    System.out.println((i + 1) + "." + list[i]);
//                    System.out.println(String.format("%d. [%s] %s", i + 1, list[i].getStatusIcon(), list[i]));
                }
                System.out.println(bar);
            } else if (words[0].equals("mark")){
                int n = Integer.parseInt(words[1]) - 1;
                list[n].markAsDone();
                System.out.println(bar);
                System.out.println("Nice! I've marked this task as done:");
//                System.out.println("  [" + list[n].getStatusIcon() + "] " + list[n]);
                System.out.println("  " + list[n]);
                System.out.println(bar);
            } else if (words[0].equals("unmark")){
                int n = Integer.parseInt(words[1]) - 1;
                list[n].markAsUnDone();
                System.out.println(bar);
                System.out.println("OK, I've marked this task as not done yet:");
//                System.out.println("  [" + list[n].getStatusIcon() + "] " + list[n]);
                System.out.println("  " + list[n]);
                System.out.println(bar);
            }

            else {
                Task task = new Task(input);
                list[count] = task;
                count++;
                System.out.println(bar);
                System.out.println("added: " + input);
                System.out.println(bar + "\n");
            }


        }

        scanner.close();

    }
}
