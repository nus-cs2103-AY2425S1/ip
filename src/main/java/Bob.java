
import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bob \nWhat can I do for you?");
        System.out.println();
        String s;
        while (true) {
            Scanner input = new Scanner(System.in);
            s = input.nextLine();
            System.out.println("--------------------------------------------------");
            int i = s.indexOf(" ");
            if (i != -1) {
                String s1 = s.substring(0, i).toLowerCase();
                String s2 = s.substring(i+1);
                if (s2.trim().isEmpty()) {
                    System.out.println("Description empty");
                    continue;
                }
                switch (s1) {
                    case "mark", "unmark" -> {
                        int a = Integer.parseInt(s2) - 1;
                        if (s1.equals("mark")) {
                            Task.mark(a);
                        } else {
                            Task.unmark(a);
                        }
                    }
                    case "todo" -> new ToDo(s2);
                    case "deadline" -> {
                        int a = s2.indexOf(" /by ");
                        String s3 = s2.substring(0, a);
                        String s4 = s2.substring(a+5);
                        new Deadline(s3, s4);
                    }
                    case "event" -> {
                        int a = s2.indexOf(" /from ");
                        int b = s2.indexOf(" /to ");
                        String s3 = s2.substring(0, a);
                        String s4 = s2.substring(a+8, b);
                        String s5 = s2.substring(b+5);
                        new Event(s3, s4, s5);
                    }
                    case "delete" -> {
                        int a = Integer.parseInt(s2);
                        Task.deleteTask(a);
                    }
                }
                s = "list";
            }
            if (s.equals("list")) {
                Task.printList();
                System.out.println("--------------------------------------------------");
            } else if (s.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------------------------------");
                break;
            } else{
                System.out.println("I don't know how to do that.");
                System.out.println("--------------------------------------------------");
            }

        }

    }
}
