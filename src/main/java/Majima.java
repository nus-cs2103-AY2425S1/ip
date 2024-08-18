import java.util.Scanner;

public class Majima {
    //leave no magic numbers
    static int MAX_TASKS = 100;
    static String[] tasks = new String[MAX_TASKS];
    //counter, used in main
    static int task_count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String linegap = "____________________________________________________________";
        System.out.println(linegap);
        System.out.println("KIIIIIRYU-CHAN! It's ya old pal, Majima!");
        System.out.println("What can I do fer ya?");
        System.out.println(linegap);
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                //Exit main, close everything
                System.out.println(linegap);
                System.out.println("Bye bye! Don't keep me waiting fer too long now, ya hear?");
                System.out.println(linegap);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                //Print out all tasks so far
                System.out.println(linegap);
                System.out.println("Here's whatcha gotta do, Kiryu-chan!");
                for (int i = 0; i < task_count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(linegap);
            } else {
                //Becomes new task
                if (task_count < MAX_TASKS) {
                    tasks[task_count] = input;
                    task_count++;
                    System.out.println(linegap);
                    System.out.println("Understood, Kiryu-chan! Adding that to the list.");
                    System.out.println(linegap);
                } else {
                    System.out.println(linegap);
                    System.out.println("O-oi, Kiryu-chan! Ya can't expect me to 'member all this crap!");
                    System.out.println(linegap);
                }
            }
        }
        scanner.close();
    }
}
