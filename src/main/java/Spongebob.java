import java.io.File;
import java.util.*;

public class Spongebob {

    static Storage storage;
    static List<Task> cache;

    final static String LINE = "____________________________________________________________\n";
    final static String GREETINGS = "Hey there! I’m SpongeBob SquarePants! \nWhat can I do for ya today?\n";
    final static String GOODBYE = "Aye aye, pal! Bye-bye for now! Hope to catch you in Bikini Bottom again soon! \n";

    public static void main(String[] args) {


        storage = new Storage("data/spongebob.txt");
        try {
            cache = storage.load();
        } catch (SpongebobException e) {
            System.out.println("Uh oh! Storage is Corrupted! \n");
        }


        Scanner scanner = new Scanner(System.in);
        Task curTask;

        System.out.println(LINE + "\n" + GREETINGS + LINE);

        String usrInput = scanner.nextLine();   // get user input

        while (!usrInput.equals("bye")) {
            System.out.println(LINE + "\n");

            // gets first 2 words of command
            String[] command = usrInput.split(" ", 2);

            // array to store arguments
            String[] arguments = new String[4];


            // set array to empty strings
            for (int i = 0; i < arguments.length; i++) {
                arguments[i] = " ";
            }

            // first argument
            arguments[0] = command[0];
            if (command.length > 1) {
                // second argument
                arguments[1] = command[1];
            }

            // check argument
            switch (arguments[0]) {

                // enumerate list
                case "list":
                    ListIterator<Task> iter = cache.listIterator();

                    System.out.println("Alrighty, buddy! Here are the tasks in your list!\n");
                    while (iter.hasNext()) {
                        Task cur = iter.next();
                        System.out.println((iter.previousIndex() + 1) + "." + cur);
                    }

                    break;

                case "mark":

                    // empty argument
                    if (arguments[1].equals(" ")) {
                        System.out.println("Oh, barnacles! You can't mark nothing! \nMake sure to fill it in before you add it.");
                        break;
                    }

                    try {
                        // get index of task
                        int index = Integer.parseInt(arguments[1]);
                        
                        // mark
                        cache.get(index - 1).markAsDone();
                        storage.update(index - 1, cache.get(index - 1));
                        System.out.println("Woohoo! I’ve marked this task as done — great job!");
                        System.out.println(cache.get(index - 1));

                    } catch (NumberFormatException e) {
                        System.out.println("Oopsie-daisy! Looks like there’s a hiccup — index needs to be a whole number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oh no, it’s out of bounds! That index is too far out—try a different number!");
                    }
                    break;

                case "unmark":

                    // empty argument
                    if (arguments[1].equals(" ")) {
                        System.out.println("Oh, barnacles! You can't unmark nothing! \nMake sure to fill it in before you add it.");
                        break;
                    }

                    try {

                        // get index of task
                        int index = Integer.parseInt(arguments[1]);

                        // unmark
                        cache.get(index - 1).unmarkAsDone();
                        storage.update(index - 1, cache.get(index - 1));

                        System.out.println("Alrighty, I’ve put that task back to \"not done yet.\" Keep at it—you’ve got this!");
                        System.out.println(cache.get(index - 1));

                    } catch (NumberFormatException e) {
                        System.out.println("Oopsie-daisy! Looks like there’s a hiccup — index needs to be a whole number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oh no, it’s out of bounds! That index is too far out — try a different number!");
                    } finally {
                        break;
                    }

                case "todo":
                    try {

                        // get args
                        Task newTask = new Todo(arguments[1]);
                        cache.add(newTask);
                        storage.add(newTask);

                        System.out.println("Got it! I've added this task to your list — keep up the great work!");
                        System.out.println(newTask);
                        System.out.println("Now you have " + cache.size() + " in the list!");
                    } catch (SpongebobException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }

                case "deadline":

                    // split input into arguments and put into array
                    command = arguments[1].split("/by", 2);

                    if (command.length > 1) {
                        arguments[2] = command[1];
                        arguments[1] = command[0];
                    }


                    // add deadline
                    try {
                        Task newDeadline = new Deadline(arguments[1], arguments[2]);
                        cache.add(newDeadline);
                        storage.add(newDeadline);

                        System.out.println("Got it! I've added this task to your list — keep up the great work!");
                        System.out.println(newDeadline);
                        System.out.println("Now you have " + cache.size() + " tasks in the list.");
                    } catch (SpongebobException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }

                case "event":

                    // split into args and place into array
                    command = arguments[1].split("/from", 3);

                    if (command.length > 1) {
                        arguments[1] = command[0];
                        arguments[2] = command[1];

                        command = arguments[2].split("/to", 2);
                        if (command.length > 1) {
                            arguments[3] = command[1];
                            arguments[2] = command[0];
                        }
                    }

                    // create event
                    try {
                        Task newEvent = new Event(arguments[1],
                                arguments[2],
                                arguments[3]);
                        cache.add(newEvent);
                        storage.add(newEvent);

                        System.out.println("Got it! I've added this task to your list — keep up the great work!");
                        System.out.println(newEvent);
                        System.out.println("Now you have " + cache.size() + " tasks in the list.");
                    } catch (SpongebobException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        break;
                    }

                case "delete":

                    // empty args
                    if (arguments[1].equals(" ")) {
                        System.out.println("Oh, barnacles! You can't delete nothing! \nMake sure to fill it in before you add it.");
                        break;
                    }

                    // try to search and delete task
                    try {
                        Task cur = cache.get(Integer.parseInt(arguments[1])-1);
                        cache.remove(Integer.parseInt(arguments[1])-1);
                        storage.delete(cur);

                        System.out.println("Noted. I've removed this task:");
                        System.out.println(cur);
                        System.out.println("Now you have " + cache.size() + " tasks in the list.");

                    } catch (NumberFormatException e) {
                        System.out.println("Barnacles! Index must be a number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Oh no, it’s out of bounds! That index is too far out — try a different number!");
                    } finally {
                        break;
                    }

                    // no applicable command
                default:
                    System.out.println("Oh, barnacles! I’m not sure what that means either. \nCan you give me a bit more info? We’ll figure it out together!");
                    break;
            }

            System.out.println(LINE + "\n");
            usrInput = scanner.nextLine();  // get next input
        }
        System.out.println(LINE + GOODBYE + LINE);  // end
    }
}
