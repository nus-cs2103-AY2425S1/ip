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
                default:
                    Mahesh.addToList(originalInput);
            }
            System.out.println(divider);
        }

        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(divider);
        scan.close();
    }

    private static void addToList(String task) {
        Mahesh.list[taskCount++] = new Task(task);
        System.out.println("added: " + task);
    }

    private static void printList() {
        int count = 1;
        for (Task task : Mahesh.list) {
            if (task == null) break;
            System.out.println(count++ + "." + task);
        }
    }
}
