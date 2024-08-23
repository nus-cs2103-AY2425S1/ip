import java.util.Scanner;

public class BabbleBot {
    public static void main(String[] args) {
        Boolean notBye = true;
        Scanner in = new Scanner(System.in);
        String separator = "---------------------------------------------";
        System.out.println(separator);
        System.out.println("Hello! I'm BabbleBot!\nWhat can I do for you?");
        System.out.println(separator);

        while(notBye) {
            String userInp = in.nextLine();
            if (userInp.equals("bye")) {
                notBye = false;
            } else {
                System.out.println(separator);
                System.out.println(userInp);
                System.out.println(separator);
            }
        }
        System.out.println(separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
