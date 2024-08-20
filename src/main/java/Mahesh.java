import java.util.Scanner;
import java.util.StringTokenizer;

public class Mahesh {

    private static String[] list = new String[100];
    private static int[] done = new int[100];
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
            switch (command) {
                case "list":
                    Mahesh.printList();
                    break;
                case "bye":
                    exit = true;
                    break;
                case "mark":
                    markAsDone(Integer.parseInt(tokenizedInput.nextToken()));
                    break;
                case "unmark":
                    unmarkAsDone(Integer.parseInt(tokenizedInput.nextToken()));
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
        Mahesh.list[taskCount++] = task;
        System.out.println("added: " + task);
    }

    private static void printList() {
        int count = 1;
        for (String task : Mahesh.list) {
            if (task == null) break;
            String indicator = Mahesh.done[count - 1] == 0 ? "[ ]" : "[X]";
            System.out.println(count++ + "." + indicator + " " + task);
        }
    }

    private static void markAsDone(int index) {
        Mahesh.done[index - 1] = 1;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + Mahesh.list[index - 1]);
    }

    private static void unmarkAsDone(int index) {
        Mahesh.done[index - 1] = 0;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + Mahesh.list[index - 1]);
    }
}
