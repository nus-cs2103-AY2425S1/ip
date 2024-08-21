import java.util.Scanner;

public class BMO {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        System.out.println("Hello! I'm BMO!\nWhat can I do for you?\n" + line);

        //Initialise storage and scanner
        Storage storage = new Storage();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        //Loop to read user inputted commands
        while (!command.equals("bye")) {
            //Stores the input line as an array of words
            String[] words = command.split(" ");

            //Switch statement to handle different commands
            switch (words[0]) {
                case "list":
                    System.out.println(storage.getTasks() + line);
                    break;
                case "mark":
                    int index = Integer.parseInt(words[1]) - 1;
                    Task taskToMark = storage.getTask(index);
                    taskToMark.mark();
                    System.out.println("Nice! I've marked this task as done:\n" + taskToMark.toString() + "\n" + line);
                    break;
                case "unmark":
                    int index2 = Integer.parseInt(words[1]) - 1;
                    Task taskToUnmark = storage.getTask(index2);
                    taskToUnmark.unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n" + taskToUnmark.toString() + "\n" + line);
                    break;
                default:
                    storage.addTask(command);
                    System.out.println("added: " + command + "\n" + line);
                    break;
            }
            command = sc.nextLine();
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }
}
