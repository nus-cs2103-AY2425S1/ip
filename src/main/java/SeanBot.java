import java.util.Scanner;
public class SeanBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm SeanBot\n" + //
                            "What can I do for you?");
        String[] tasks = new String[100];
        int counter = 0;
        
        Scanner scanner = new Scanner(System.in);
        while(true){
            String userInput = scanner.nextLine();
            System.out.println(userInput);
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")){
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[counter] = userInput;
                counter++;
                System.out.println("added: " + userInput);
            }
        }
        scanner.close();
    }
}
