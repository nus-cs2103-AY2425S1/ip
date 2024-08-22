import java.util.Scanner;
import java.util.ArrayList;

public class Park {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arr = new ArrayList<Task>();
        System.out.println("""
                Hello! I'm Park
                What can I do for you?""");
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                System.exit(0);
            } else if (s.equals("list")){
                System.out.println("Here are the tasks in your list:");
                int n = 1;
                for (Task t : arr) {
                    System.out.print(n);
                    System.out.println("." + t.getStatusIcon() + " " +
                            t.getDescription());
                    n++;
                }
            } else if (s.startsWith("mark ")){
                String strn = s.substring(5);
                int n = Integer.parseInt(strn);
                Task t = arr.get(n - 1);
                t = t.mark();
                arr.set(n - 1, t);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t.getStatusIcon() + " " + t.getDescription());
            } else if (s.startsWith("unmark ")){
                String strn = s.substring(7);
                int n = Integer.parseInt(strn);
                Task t = arr.get(n - 1);
                t = t.unmark();
                arr.set(n - 1, t);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(t.getStatusIcon() + " " + t.getDescription());
            } else {
                Task t = new Task(s);
                arr.add(t);
                System.out.println("added: " + s);
            }
        }
    }
}
