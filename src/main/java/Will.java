import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Will {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
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
            if(Objects.equals(userInput, "list")){
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
                System.out.println("_____________________________________");
                continue;
            }
            list.add(userInput);
            System.out.println("added: " + userInput);
            System.out.println("_____________________________________");
        }
    }
}

