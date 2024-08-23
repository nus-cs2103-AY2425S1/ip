import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Daniel {
    public static void handleList(List<Task> array){
        System.out.println("Here are the tasks in your list");
        int i = 1;
        for ( Task element : array) {
            System.out.println(i + "." + element.toString());
            i += 1;
        }
    }
    public static void handleTask(String input, List<Task> array) throws WrongKeyword, MissingArg{
        if (input.startsWith("todo")) {
            try {
                Todo x = new Todo(input.substring(5));
                array.add(x);
                System.out.println("Got it. I've added this task:\n" + x.toString());
                System.out.println("Now you have " + array.size() + " task in the list");
            } catch(Exception e) {
                throw new MissingArg("Wrong number of Arguments");
            }
        } else if (input.startsWith("deadline")) {
            try {
                String[] split = input.substring(9).split(" /by ");
                Deadline x = new Deadline(split[0], split[1]);
                array.add(x);
                System.out.println("Got it. I've added this task;\n" + x.toString());
                System.out.println("Now you have " + array.size() + " task in the list");
            } catch (Exception e) {
                throw new MissingArg("Wrong number of Arguments");
            }
        } else if (input.startsWith("event")) {
            try {
                String[] split = input.substring(6).split(" /from | /to ");
                Event x = new Event(split[0], split[1], split[2]);
                array.add(x);
                System.out.println("Got it. I've added this task;\n" + x.toString());
                System.out.println("Now you have " + array.size() + " task in the list");
            } catch (Exception e) {
                throw new MissingArg("Wrong number of Arguments");
            }
        } else {
            throw new WrongKeyword("Wrong keyword");
        }
    }
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
                handleList(array);
            } else if (input.startsWith("mark")) {
                int index = input.charAt(input.length() - 1) - '0';
                array.get(index - 1).markAsDone();
                System.out.println("Nice I have marked this task as done:");
                System.out.println(array.get(index - 1).toString());
            } else if (input.startsWith("unmark")) {
                int index = input.charAt(input.length() - 1) - '0';
                array.get(index - 1).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(array.get(index - 1).toString());
            } else if (input.startsWith("delete")) {
                int index = input.charAt(input.length() - 1) - '0';
                Task t = array.get(index - 1);
                array.remove(index - 1);
                System.out.println("Noted. I've removed this task:\n" + t.toString());
                System.out.println("Now you have " + array.size() +" tasks in the list.");
            }else {
                try {
                    handleTask(input, array);
                } catch (WrongKeyword | MissingArg e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
