import java.util.Objects;
import java.util.Scanner;

public class Will {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "WILL";
        System.out.println("Hello! I'm" + logo);
        System.out.println("What can I do for you?");
        System.out.println("_____________________________________");
        while(true) {
            String userInput = scanner.nextLine();
            System.out.println("_____________________________________");
            if(Objects.equals(userInput, "bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(userInput);
            System.out.println("_____________________________________");
        }
    }
}

