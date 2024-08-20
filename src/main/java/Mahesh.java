import java.util.Scanner;
import java.util.StringTokenizer;

public class Mahesh {

    private static Task[] list = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        String divider = "-------------------------------------------------------";
        String logo = 
                  "#     #                                       ######                               ###### \n"
                + "##   ##   ##   #    # ######  ####  #    #    #     #   ##   #      #              #      \n"
                + "# # # #  #  #  #    # #      #      #    #    #     #  #  #  #      #              #      \n"
                + "#  #  # #    # ###### #####   ####  ######    #     # #    # #      #      #####   #####  \n"
                + "#     # ###### #    # #           # #    #    #     # ###### #      #              #      \n"
                + "#     # #    # #    # #      #    # #    #    #     # #    # #      #              #      \n"
                + "#     # #    # #    # ######  ####  #    #    ######  #    # ###### ######         ###### \n";
        
        System.out.println("Hello from\n" + logo);

        System.out.println(divider);
        System.out.println("Hello! I'm Mahesh Dall-E [but you can call me Mahesh ;)]");
        System.out.println("What can I do for you?\n");
        System.out.println(divider);
        
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            String originalInput = scan.nextLine();
            StringTokenizer tokenizedInput = new StringTokenizer(originalInput);
            System.out.println(divider);
            String command = tokenizedInput.nextToken();
            Task task;
            switch (command) {
                case "list":
                    Mahesh.printList();
                    break;
                case "bye":
                    exit = true;
                    break;
                case "mark":
                    task = list[Integer.parseInt(tokenizedInput.nextToken()) - 1];
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task);
                    break;
                case "unmark":
                    task = list[Integer.parseInt(tokenizedInput.nextToken()) - 1];
                    task.unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + task);
                    break;
                case "todo":
                    Mahesh.addToList(Mahesh.parseTodo(tokenizedInput));
                    break;
                case "deadline":
                    Mahesh.addToList(Mahesh.parseDeadline(tokenizedInput));
                    break;
                case "event": 
                    Mahesh.addToList((Mahesh.parseEvent(tokenizedInput)));
                    break;
                default:
                    exit = true;
                    break;
            }
            System.out.println(divider);
        }

        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(divider);
        scan.close();
    }

    private static void addToList(Task task) {
        Mahesh.list[taskCount++] = task;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + Mahesh.taskCount + " tasks in the list.");
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : Mahesh.list) {
            if (task == null) break;
            System.out.println(count++ + "." + task);
        }
    }
    
    private static Todo parseTodo(StringTokenizer tokenizedInput) {
        StringBuilder description = new StringBuilder();
        String token = "";
        while (tokenizedInput.hasMoreTokens()) {
            token = tokenizedInput.nextToken();
            description.append(token).append(" ");
        }
        return new Todo(description.toString());
    }

    private static Deadline parseDeadline(StringTokenizer tokenizedInput) {
        StringBuilder description = new StringBuilder();
        String token = tokenizedInput.nextToken();
        while (!token.equals("/by")) {
            description.append(token).append(" ");
            token = tokenizedInput.nextToken();
        }
        StringBuilder by = new StringBuilder();
        while (tokenizedInput.hasMoreTokens()) {
            token = tokenizedInput.nextToken();
            by.append(token).append(" ");
        }
        return new Deadline(description.toString(), by.toString());
    }

    private static Event parseEvent(StringTokenizer tokenizedInput) {
        StringBuilder description = new StringBuilder();
        String token = tokenizedInput.nextToken();
        while (!token.equals("/from")) {
            description.append(token).append(" ");
            token = tokenizedInput.nextToken();
        }
        StringBuilder from = new StringBuilder();
        token = tokenizedInput.nextToken();
        while (!token.equals("/to")) {
            from.append(token).append(" ");
            token = tokenizedInput.nextToken();
        }
        StringBuilder to = new StringBuilder();
        while (tokenizedInput.hasMoreTokens()) {
            token = tokenizedInput.nextToken();
            to.append(token).append(" ");
        }
        return new Event(description.toString(), from.toString(), to.toString());
    }

}
