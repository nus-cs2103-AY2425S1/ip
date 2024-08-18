import java.util.Scanner;
import java.util.ArrayList;

public class Velma {

    public static void printLine() {
        for (int i = 0; i < 50; i ++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {

        int count = 1;
        ArrayList<String> list = new ArrayList<>(100);
        boolean end = false;
        printLine();
        System.out.println("Hello! I am Velma!" );
        System.out.println("What can I do for you?");
        Scanner req = new Scanner(System.in);
        printLine();

        while(!end) {
            String request = req.nextLine();
            if (request.equals("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (request.equals("list")) {
                printLine();
                for (String s : list) {
                    System.out.println(count + ". " + s);
                    count++;
                }
                printLine();
                count = 1;
            } else {
                list.add(request);
                printLine();
                System.out.println("added: " + request);
                printLine();
            }
        }
    }


}
