import java.util.Scanner;
public class Bobby {
    public static String greeting = "Hello! I'm Bobby\n"
            + "What can I do for you?\n";
    public static String exit = "Bye. Hope to see you again soon!\n";
    public static Scanner scan = new Scanner(System.in);
    public static Task[] list = new Task[100];
    public static int counter = 1;

    /*create an add_list function that takes in a
    Task to add it to the list of Task.
     */
    public static void add_list(Task input) {
        input.ranking = counter;
        list[counter - 1] = input;
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
        System.out.println("Here are the tasks in your list: \n");
        for (int x = 0; list[x] != null; x++) {
            System.out.println(x + 1 + ". [" + list[x].getStatusIcon() + "] "
                    + list[x].description + "\n");
        }
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
        } else {
            input = input + scan.nextLine();
            Task curr_task = new Task(input);
            add_list(curr_task);
            System.out.println("added: " + input);
            check_action(scan.next());
        }
    }
    public static void main(String[] args) {
        System.out.println(greeting);
        check_action(scan.next());
    }
}
