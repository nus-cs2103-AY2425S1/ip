import java.util.Scanner;
public class ChaCha {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String greeting = "     ____________________________________________________________ \n" +
                "     Hello! I'm ChaCha \n" +
                "     What can I do for you? \n" +
                "     ____________________________________________________________ \n";

        String exit = "     ____________________________________________________________ \n" +
                "     Bye. Hope to see you again soon! \n" +
                "     ____________________________________________________________";

        System.out.println(greeting);

        Scanner input = new Scanner(System.in);
        String text = input.nextLine();

        while (!text.equals("bye")) {
            System.out.println(
                    "     ____________________________________________________________ \n" +
                    "     " +
                    text +
                    "\n" +
                    "     ____________________________________________________________ \n");
            text = new Scanner(System.in).nextLine();
        }

        System.out.println(exit);
    }
}
