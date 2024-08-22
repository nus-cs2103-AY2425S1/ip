import java.util.Scanner;

public class Daniel {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Daniel\nWhat can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        boolean val = true;
        while(val){
            String input = scanner.nextLine();
            if (input.equals("bye")){
                val = false;
                System.out.println("Bye hope to see you again soon");
            } else {
                System.out.println(input);
            }
        }
    }
}
