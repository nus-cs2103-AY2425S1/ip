import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Daniel {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Daniel\nWhat can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        boolean val = true;
        List<Task> array = new ArrayList<>();
        while(val){
            String input = scanner.nextLine();
            if (input.equals("bye")){
                val = false;
                System.out.println("Bye hope to see you again soon");
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list");
                int i = 1;
                for ( Task element : array) {
                    System.out.println(i + "." + element.toString());
                    i += 1;
                }
            } else if (input.startsWith("todo")) {
                Todo x = new Todo(input);
                array.add(x);
                System.out.println("Got it. I've added this task:\n" + x.toString());
                System.out.println("Now you have " + array.size() + " task in the list");
            }else if (input.startsWith("deadline")) {
                String[] split = input.substring(9).split(" /by ");
                Deadline x = new Deadline(split[0], split[1]);
                array.add(x);
                System.out.println("Got it. I've added this task;\n" + x.toString());
                System.out.println("Now you have " + array.size() + " task in the list");
            }else if (input.startsWith("event")) {
                String[] split = input.substring(6).split(" /from | /to ");
                Event x = new Event(split[0], split[1], split[2]);
                array.add(x);
                System.out.println("Got it. I've added this task;\n" + x.toString());
                System.out.println("Now you have " + array.size() + " task in the list");
            }else if (input.startsWith("mark")) {
                int index = input.charAt(input.length() - 1) - '0';
                array.get(index - 1).markAsDone();
                System.out.println("Nice I have marked this task as done:");
                System.out.println(array.get(index - 1).toString());
            } else if (input.startsWith("unmark")) {
                int index = input.charAt(input.length() - 1) - '0';
                array.get(index - 1).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(array.get(index - 1).toString());
            } /*else {
                System.out.println("added: " + input);
                array.add(new Task(input));
            }*/
        }
    }
}
