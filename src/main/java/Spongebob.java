import java.util.*;

public class Spongebob {

     static List<Task> cache = new ArrayList<>();

    final static String LINE = "____________________________________________________________\n";
    final static String lOGO = "▕╮╭┻┻╮╭┻┻╮╭▕╮╲\n" +
                        "▕╯┃╭╮┃┃╭╮┃╰▕╯╭▏\n" +
                        "▕╭┻┻┻┛┗┻┻┛ ╰▏\n" +
                        "▕╰━━━┓┈┈┈╭╮▕╭╮▏\n" +
                        "▕╭╮╰┳┳┳┳╯╰╯▕╰╯▏\n" +
                        "▕╰╯┈┗┛┗┛┈╭╮▕╮┈▏";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String greetings = "Hello! I'm Spongebob! \nWhat can I do for you?\n";
        String goodbye =  "Bye. Hope to see you again soon!\n";

        System.out.println(LINE + lOGO + "\n" + greetings + LINE);

        String usrInput = scanner.nextLine();   // get user input

        while (!usrInput.equals("bye")) {
            System.out.println(LINE + "\n");

            // gets first 2 words of command
            String[] command = usrInput.split(" ", 2);

            switch (command[0]) {

                // enumerate list
                case "list":
                    ListIterator<Task> iter = cache.listIterator();

                    System.out.println("Here are the tasks in your list:\n");
                    while (iter.hasNext()) {
                        Task cur = iter.next();
                        System.out.println((iter.previousIndex() + 1) + "." + cur.getStatusIcon() + cur.getDescription());
                    }

                    break;

                case "mark":
                    try {

                        // get index of task
                        int index = Integer.parseInt(command[1]);

                        // mark 
                        cache.get(index - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(cache.get(index - 1).getStatusIcon() + cache.get(index - 1).getDescription());

                    } catch (NumberFormatException e) {
                        System.out.println("Error, index must be an integer!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Index out of bounds!");
                    }
                    break;

                case "unmark":
                    try {

                        // get index of task
                        int index = Integer.parseInt(command[1]);

                        // unmark
                        cache.get(index - 1).unmarkAsDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(cache.get(index - 1).getStatusIcon() + cache.get(index - 1).getDescription());

                    } catch (NumberFormatException e) {
                        System.out.println("Error, index must be an integer!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Index out of bounds!");
                    }
                    break;

                default:
                    System.out.println("added: " + usrInput + "\n");
                    cache.add(new Task(usrInput));
                    break;
            }

            System.out.println(LINE + "\n");
            usrInput = scanner.nextLine();  // get next input
        }
        System.out.println(LINE + goodbye + LINE);  // end
    }
}
