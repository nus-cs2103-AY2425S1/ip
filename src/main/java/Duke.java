import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int current = 0;

        Scanner reader = new Scanner(System.in);

        String greet = "Hello! I'm Bob \n What can I do for you? \n";
        String bye = "Bye. Hope to see you again soon!";
        String completed = "Nice! I've marked this task as done:";
        String notCompleted = "OK, I've marked this task as not done yet:";
        String input = "";

        System.out.println(greet);

        while (true) {
            input = reader.nextLine();
            if (input.contains("bye")) {
                System.out.println(bye);
                return;
            } else if (input.contains("list")) {
                for (int i = 0; i < current; i++) {
                    System.out.println(i+1 + ". " + tasks[i].toString());
                }
            } else if (input.contains("mark")) {
                String [] pieces = input.split(" ");
                tasks[Integer.parseInt(pieces[1]) - 1].mark();
                System.out.println(completed);
                System.out.println(tasks[Integer.parseInt(pieces[1]) - 1]);
            }else if (input.contains("unmark")) {
                String [] pieces = input.split(" ");
                tasks[Integer.parseInt(pieces[1]) - 1].mark();
                System.out.println(notCompleted);
                System.out.println(tasks[Integer.parseInt(pieces[1]) - 1]);
            }
            else {
                tasks[current] = new Task(input);
                System.out.println("added: " + tasks[current].getDescription());
                current++;
            }
        }
    }
}
