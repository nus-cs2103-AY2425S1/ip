import java.util.Scanner;

public class Ontos {
    public static String hello = " Hello! I'm Ontos \n"
                   + " What can I do for you?\n";
    public static String line = "____________________________________________________________\n";
    public static String bye = " Bye. Hope to see you again soon!\n";
    public static int maxInput = 100;
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        
        Scanner sc = new Scanner(System.in);
        String[] inputs = new String[maxInput];
        int elements = 0;

        System.out.println(line + hello + line);

        while (true){
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line + bye + line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < elements; i++) {
                    int j = i + 1;
                    System.out.println(" " + j + ". " + inputs[i]);
                }
                System.out.println(line);
            } else {
                inputs[elements] = input;
                elements++;
                System.out.println(line + " added: " + input + "\n" + line);
            }
        }
        sc.close();
    }
}
