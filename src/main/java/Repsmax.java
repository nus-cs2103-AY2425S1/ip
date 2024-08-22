import java.util.Scanner;

public class Repsmax {
    public static void main(String[] args) {
        //initialise scanner
        Scanner scanner = new Scanner(System.in);
        //initialise string array
        String[] tasks = new String[100];
        int taskcount = 0;
        //greet the user
        String greeting = "  ____________________________________________________________\n" +
                "   Hello! I'm Repsmax\n" +
                "   What can I do for you?\n" +
                "  ____________________________________________________________\n";
        String goodbye = "  ____________________________________________________________\n" +
                "   Bye. Hope to see you again soon!\n" +
                "  ____________________________________________________________\n";
        System.out.println(greeting);
        while(true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(goodbye);
                break;
            } else if (userInput.equals("list")) {
                System.out.println("  ____________________________________________________________\n");
                for (int i = 0; i < taskcount; i++) {
                    System.out.println("   " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("  ____________________________________________________________\n");
            } else {
                tasks[taskcount] = userInput;
                taskcount++;
                System.out.println("  ____________________________________________________________\n");
                System.out.println("added: " + userInput);
                System.out.println("  ____________________________________________________________\n");
            }
        }
    }
}