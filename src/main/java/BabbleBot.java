import java.util.Scanner;

public class BabbleBot {
    public static void main(String[] args) {
        Boolean notBye = true;
        String[] storedString = new String[100];
        int counter = 0;
        Scanner in = new Scanner(System.in);
        String separator = "---------------------------------------------";
        System.out.println(separator);
        System.out.println("Hello! I'm BabbleBot!\nWhat can I do for you?");
        System.out.println(separator);

        while(notBye) {
            String userInp = in.nextLine();
            if (userInp.equals("bye")) {
                notBye = false;
            } else if (userInp.equals("list")) {
                System.out.println(separator);
                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + ". " + storedString[i]);
                }
                System.out.println(separator);
            } else {
                System.out.println(separator);
                System.out.println("added: " + userInp);
                storedString[counter] = userInp;
                counter++;
                System.out.println(separator);
            }
        }
        System.out.println(separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
