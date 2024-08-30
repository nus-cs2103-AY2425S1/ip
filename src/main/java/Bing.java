import java.util.Scanner;
import java.util.ArrayList;
public class Bing {
    public static void main(String[] args) {
        String message = "______________________________\n"
                + "Hi! My name is Bing\n"
                + "How can I help you?\n"
                + "______________________________\n";
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (true) {
            input = scanner.nextLine();
            if (input.equals("Bye") || input.equals("bye")) {
                System.out.println("______________________________\n"
                        + "Bye. Have a good day.\n"
                        + "______________________________\n");
                break;
            } else if (input.equals("list")) {
                System.out.println("______________________________\n"
                                    + "All tasks in your list : \n");
                for (int i=0 ; i<tasks.size() ; i++) {
                    System.out.println((i + 1)+". "+tasks.get(i).toString());
                }
                System.out.println("______________________________\n");
            } else if (input.startsWith("mark")) {
                int x = Integer.parseInt(input.substring(5));
                if (x>0 && x<=tasks.size()) {
                    tasks.get(x-1).setToDone();
                    System.out.println("______________________________\n");
                    System.out.println("This task is marked as done :");
                    System.out.println(tasks.get(x-1).toString());
                    System.out.println("______________________________\n");
                } else {
                    System.out.println("Invalid Input.\n");
                }

            } else if (input.startsWith("unmark")) {
                int x = Integer.parseInt(input.substring(7));
                if (x>0 && x<=tasks.size()) {
                    tasks.get(x-1).setToUndone();
                    System.out.println("______________________________\n");
                    System.out.println("This task is marked as undone :");
                    System.out.println(tasks.get(x-1).toString());
                    System.out.println("______________________________\n");
                } else {
                    System.out.println("Invalid Input.\n");
                }

            } else {
                Task temp = new Task(input);
                tasks.add(temp);
                System.out.println("______________________________\n"
                        + "Added: "+ input + "\n"
                        + "______________________________\n");
            }
        }
    }
}

