import java.util.Scanner;

public class Mahesh {
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
        
        while (true) {
            String input = scan.nextLine();
            System.out.println(divider);
            if (input.equals("bye")) break;
            System.out.println(input);
            System.out.println(divider);
        }

        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println(divider);

    }
}
