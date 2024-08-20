import java.util.Scanner;

public class Mahesh {

    private static String[] list = new String[100];
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
            String input = scan.nextLine();
            System.out.println(divider);
            switch (input) {
                case "list":
                    Mahesh.printList();
                    break;
                case "bye":
                    exit = true;
                    break;
                default:
                    Mahesh.addToList(input);
            }
            System.out.println(divider);
        }

        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(divider);

    }

    private static void addToList(String task) {
        Mahesh.list[taskCount++] = task;
        System.out.println("added: " + task);
    }

    private static void printList() {
        int count = 1;
        for (String task : Mahesh.list) {
            if (task == null) break;
            System.out.println(count++ + ". " + task);
        }
    }
}
