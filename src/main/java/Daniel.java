import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Daniel {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Daniel\nWhat can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        boolean val = true;
        List<String> array = new ArrayList<>();
        while(val){
            String input = scanner.nextLine();
            if (input.equals("bye")){
                val = false;
                System.out.println("Bye hope to see you again soon");
            } else if (input.equals("list")) {
                int i = 1;
                for ( String element : array) {
                    System.out.println(i + "." + element);
                    i += 1;
                }
            } else {
                System.out.println("added: " + input);
                array.add(input);
            }
        }
    }
}
