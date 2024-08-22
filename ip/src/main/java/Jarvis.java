import java.util.Scanner;

public class Jarvis {

    public static void main(String[] args) {
        String logo =
                "       _                  _     \n"+
                        "      (_)___ _______   __(_)____\n"+
                        "     / / __ `/ ___/ | / / / ___/\n"+
                        "    / / /_/ / /   | |/ / (__  )\n"+
                        " __/ /\\__,_/_/    |___/_/____/\n"+
                        "/___/\n";
        Scanner scanner = new Scanner(System.in);


        System.out.println("Hello from\n" + logo);
        System.out.println(" What can I do for you?\n\n");
        System.out.println();

        String input = "";
        TaskList tasklist = new TaskList();

        // Keep reading input until "bye" is entered
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            if(input.equals("list")) {
                tasklist.list();

            } else if (!input.equals("bye")) { // Only print if it's not "bye"
                System.out.println("__________________________________\n" + input+ "\n" +
                        "__________________________________");
                tasklist.add(input);
            }

        }
        System.out.println( "Bye. Hope to see you again soon!");
    }

}
