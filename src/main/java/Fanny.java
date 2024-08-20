import java.util.Scanner;

public class Fanny {

    public static void main(String[] args) {
        System.out.println("_____________________________________________");
        System.out.println("Hello! I'm Fanny");
        System.out.println("What can I do for you?");
        System.out.println("_____________________________________________");

        String[] list = new String[100];
        int listIndex = 0;
        Scanner scanner = new Scanner(System.in);

        //while ensure continuous reading of user inputs
        while(true) {
            System.out.println("User: ");
            String cmd = scanner.nextLine();

            //condition to exit when cmd is bye
            if(cmd.equals("bye")) {
                System.out.println("_____________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("_____________________________________________");
                break;
            } else if(cmd.equals("list")) {
                System.out.println("_____________________________________________");
                for(int i = 0; i < listIndex; i++) {
                    System.out.println(i+1 + ":" + list[i]);
                }
                System.out.println("_____________________________________________");
            } else {
                System.out.println("_____________________________________________");
                System.out.println("Fanny: ");
                System.out.println("Added: " + cmd);
                System.out.println("_____________________________________________");
                list[listIndex] = cmd;
                listIndex++;
            }

        }

        scanner.close();
    }
}
