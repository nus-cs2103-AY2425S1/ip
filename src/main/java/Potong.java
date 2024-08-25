import java.util.Locale;
import java.util.Scanner;

public class Potong {

    private static final String LINE = "_________________________";
    public static void main(String[] args) throws PotongException {
        System.out.println("Hello! I'm Potong");
        System.out.println("What can I do for you?\n");
        System.out.println(Potong.LINE);

        Scanner input = new Scanner(System.in);
        InputData data = new InputData();
        while (true) {
            String userInput = input.nextLine();
            System.out.println(Potong.LINE);
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                System.out.println(data.toString());
                System.out.println(Potong.LINE);
                continue;
            }
            if (userInput.startsWith("mark")) {
                int index = Integer.valueOf(userInput.substring(5));
                System.out.println(data.mark(index));
                System.out.println(Potong.LINE);
                continue;
            }
            if (userInput.startsWith("unmark")) {
                int index = Integer.valueOf(userInput.substring(7));
                System.out.println(data.unmark(index));
                System.out.println(Potong.LINE);
                continue;
            }
            if (userInput.startsWith("todo")) {
                String todo = userInput.substring(5).trim();
                System.out.println(data.add(new ToDoTask(todo)));
                System.out.println(Potong.LINE);
                continue;
            }
            if (userInput.startsWith("deadline")) {
                String desc = userInput.substring(9).trim();
                String task = desc.split("/", 2)[0].trim();
                String deadline = desc.split("/", 2)[1].trim().substring(3);
                System.out.println(data.add(new DeadlineTask(task, deadline)));
                System.out.println(Potong.LINE);
                continue;
            }
            if (userInput.startsWith("event")) {
                String desc = userInput.substring(6).trim();
                String task = desc.split("/", 3)[0].trim();
                String start = desc.split("/", 3)[1].trim().substring(5);
                String end = desc.split("/", 3)[2].trim().substring(3);
                System.out.println(data.add(new EventTask(task, start, end)));
                System.out.println(Potong.LINE);
                continue;
            } else {
                throw new PotongException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
//            System.out.println(data.add(new Task(userInput)));
//            System.out.println(Potong.LINE);
        }
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(Potong.LINE);
    }

}
