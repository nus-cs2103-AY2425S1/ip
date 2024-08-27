import java.util.Scanner;
import java.io.IOException;
public class Bobby {
    public static String greeting = "Hello! I'm Bobby\n"
            + "What can I do for you?\n";
    public static String exit = "Bye. Hope to see you again soon!";
    public static Scanner scan = new Scanner(System.in);
    public static Task[] list = new Task[100];
    public static int counter = 0;

    /*create an add_list function that takes in a
    Task to add it to the list of Task.
     */
    public static void add_list(Task input) {
        list[counter] = input;
        counter++;
    }

//    public static Task check_task(String description) {
//        for (int x = 0; x < counter; x++) {
//            Task t = list[x];
//            if(t.description.equals(description)) {
//                return t;
//            }
//        }
//        Task new_t = new Task(description);
//        return new_t;
//    }

    /* create a retrive_task function for mark and unmark
    action to retrieve that task in the list of Tasks
     */
    public static Task retrive_task(int x) {
        if (x < counter) {
            return list[x - 1];
        }
        return null;
    }

    /* create a print_list function that takes in a
    list of Task to print each Task in a new line.
     */
    public static void print_list() {
        System.out.println("Here are the tasks in your list:");
        for (int x = 0; list[x] != null; x++) {
            System.out.println(x + 1 + "." + list[x].toString());
        }
        System.out.println();
    }

    /* create a check_action function that takes in a
     String input to check what action should the Chatbot do.
     Chatbot will only return an exit signal when user reply bye.
     */
    public static void check_action(String input) throws Exception {
        if (input.equals("bye")) {
            System.out.println(exit);
            return;
        } if (input.equals("list")) {
            print_list();
            check_action(scan.next());
            return;
        } if (input.equals("mark")) {
            Task curr_task = retrive_task(scan.nextInt());
            curr_task.markAsDone();
            check_action(scan.next());
            return;
        } if (input.equals("unmark")) {
            Task curr_task = retrive_task(scan.nextInt());
            curr_task.unMark();
            check_action(scan.next());
            return;
        } if (input.equals("todo")) {
            input = scan.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("OOPS!!! The description of a todo is empty. (e.g. todo xxx)\n");
                check_action(scan.next());
                return;
            }
            ToDos curr_ToDos = new ToDos(input.trim());
            add_list(curr_ToDos);
            System.out.println("Got it. I've added this task:\n" +
                    curr_ToDos.toString() + "\n" +
                    "Now you have " + counter + " tasks in the list.\n");
            check_action(scan.next());
            return;
        } if (input.equals("deadline")) {
            input = scan.nextLine();
            String details[] = input.split("/by");
            if (details.length < 2 || details[0].trim().isEmpty() || details[1].trim().isEmpty()) {
                System.out.println("OOPS!!! Format of deadline should be (deadline xxx /by xxx)\n");
                check_action(scan.next());
                return;
            }
            Deadlines curr_deadlines = new Deadlines(details[0].trim(), details[1].trim());
            add_list(curr_deadlines);
            System.out.println("Got it. I've added this task:\n" +
                    curr_deadlines.toString() + "\n" +
                    "Now you have " + counter + " tasks in the list.\n");
            check_action(scan.next());
            return;
        } if (input.equals("event")) {
            input = scan.nextLine();
            String details[] = input.split("/from");
            if (details.length < 2 || details[0].trim().isEmpty()) {
                System.out.println("OOPS!!! Format of events should be (event xxx /from xxx /to xxx)\n");
                check_action(scan.next());
                return;
            }
            String duration[] = details[1].split("/to");
            if (duration.length < 2 || duration[0].trim().isEmpty()|| duration[1].trim().isEmpty()) {
                System.out.println("OOPS!!! Format of events should be (event xxx /from xxx /to xxx)\n");
                check_action(scan.next());
                return;
            }
            Events curr_events = new Events(details[0].trim(), duration[0].trim(), duration[1].trim());
            add_list(curr_events);
            System.out.println("Got it. I've added this task:\n" +
                    curr_events.toString() + "\n" +
                    "Now you have " + counter + " tasks in the list.\n");
            check_action(scan.next());
            return;
        } else {
            System.out.println("HELP!! I do not recognise this action as of now.");
            System.out.println("You can try: todo xxx, event xxx /from xxx /to xxx, deadline xxx /by xxx\n");
            check_action(scan.next());
            return;
        }

    }
    public static void main(String[] args) throws Exception {
        System.out.println(greeting);
        check_action(scan.next());
    }
}
