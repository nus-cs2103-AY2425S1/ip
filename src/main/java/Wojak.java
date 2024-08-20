import java.util.Scanner;

public class Wojak {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Wojak\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        String nextLine = sc.nextLine();
        while (!(nextLine.equals("bye"))) {
            System.out.println(nextLine);
            nextLine = sc.nextLine();
        }


        System.out.println(
                " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
    }
}