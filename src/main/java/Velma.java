import java.util.Scanner;

public class Velma {

    public static void printLine() {
        for (int i = 0; i < 50; i ++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {

        boolean end = false;
        printLine();
        System.out.println("Hello! I am Velma!" );
        System.out.println("What can I do for you?");
        Scanner req = new Scanner(System.in);
        printLine();

        while(!end) {
            String request = req.nextLine();
            printLine();
            System.out.println(request);
            printLine();
        }
    }


}
