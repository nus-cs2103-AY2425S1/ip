import java.util.Scanner;
public class ChatterBox {
    public static void main(String[] args) {

        String[] taskList = new String[100];
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        //Greeting the user
        System.out.println("\t\t" + "_".repeat(50));
        System.out.println("\t\t" + "Hey there! I'm ChatterBox");
        System.out.println("\t\t" + "What's on your plate today?");
        System.out.println("\t\t" + "_".repeat(50));

        while (true) {
            taskList[counter] = sc.nextLine();
            if (taskList[counter].equalsIgnoreCase("bye")) {
                System.out.println("\t\t" + "_".repeat(50));
                System.out.println("\t\t" + "Take care! Looking forward to helping you again soon!");
                System.out.println("\t\t" + "_".repeat(50));
                break;
            } else if (taskList[counter].equalsIgnoreCase("list")) {
                System.out.println("\t\t" + "_".repeat(50));
                System.out.println("\t\t Here's what you've got on your to-do list so far:");
                for(int i = 0; i < counter; i++) {
                    System.out.println("\t\t\t- " + taskList[i]);
                }
                System.out.println("\t\t" + "_".repeat(50));
            } else {
                System.out.println("\t\t" + "_".repeat(50));
                System.out.println("\t\t" + taskList[counter] + " is added to your list");
                System.out.println("\t\t" + "_".repeat(50));
                counter++;
            }

        }
    }
}
