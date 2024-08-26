import java.util.Scanner;
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
    public static void check_action(String input) {
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
            ToDos curr_ToDos = new ToDos(input.trim());
            add_list(curr_ToDos);
            System.out.println("Got it. I've added this task:\n" +
                    curr_ToDos.toString() + "\n" +
                    "Now you have " + counter + " tasks in the list.\n");
            check_action(scan.next());
        } if (input.equals("deadline")) {
            input = scan.nextLine();
            String details[] = input.split("/by");
            Deadlines curr_deadlines = new Deadlines(details[0].trim(), details[1].trim());
            add_list(curr_deadlines);
            System.out.println("Got it. I've added this task:\n" +
                    curr_deadlines.toString() + "\n" +
                    "Now you have " + counter + " tasks in the list.\n");
            check_action(scan.next());
        } if (input.equals("event")) {
            input = scan.nextLine();
            String details[] = input.split("/from");
            String duration[] = details[1].split("/to");
            Events curr_events = new Events(details[0].trim(), duration[0].trim(), duration[1].trim());
            add_list(curr_events);
            System.out.println("Got it. I've added this task:\n" +
                    curr_events.toString() + "\n" +
                    "Now you have " + counter + " tasks in the list.\n");
            check_action(scan.next());
        }
    }
    public static void main(String[] args) {
        System.out.println(greeting);
        check_action(scan.next());
    }
}
