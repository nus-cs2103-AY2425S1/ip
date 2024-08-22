import java.util.Scanner;
public class brainRot {
    public static void main(String[] args) {

        //creating an array with a number to memorise how many memory slots have been taken
        Task[] arr = new Task[100];
        int mem = 1;
            Scanner reader = new Scanner(System.in);

        String greeting = "____________________________________________________________ \n"
        + "Hello! I'm fanumTaxRizzlerOhioSigmaLooksmaxxer\n"
        + "What can I do for you?\n"
        + "____________________________________________________________ \n";

        String goodBye = "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(greeting);
//        reader.next(" ");
        String answer = reader.nextLine();

        //logic to check if "bye" or "list" has been said
        while(!answer.equals("bye")) {
            try {
                if (answer.equals("list")) {
                    System.out.println("____________________________________________________________ \n"
                            + "Here are the tasks in your list:\n");

                    for (int i = 1; i < mem; i++) {
                        System.out.println(i + "." + arr[i].toString());
                    }
                    System.out.println("____________________________________________________________ \n");

                    answer = reader.nextLine();


                } else if (answer.startsWith("unmark")) {
                    int index = answer.charAt(7) - 48;
                    arr[index].unmark();
                    reader = new Scanner(System.in);
                    answer = reader.nextLine();

                } else if (answer.startsWith("mark")) {
                    int index = answer.charAt(5) - 48;
                    arr[index].mark();
                    reader = new Scanner(System.in);
                    answer = reader.nextLine();

                } else {
                    String[] commands = answer.split("/", 2);
                    String activity = commands[0];
                    if(activity.isEmpty()) {
                        throw new UnknownActivityException("Unknown command: " + answer);
                    }


                    if (answer.startsWith("todo")) {

                        activity = activity.substring(5).trim();
                        arr[mem] = new ToDo(activity);
                        System.out.println("____________________________________________________________\n"
                                + "Got it. I've added this task:\n"
                                + arr[mem].toString() + "\n");
                        mem++;

                    } else if (answer.startsWith("deadline")) {

                        String end = commands[1].substring(3).trim();
                        activity = activity.substring(9).trim();

                        arr[mem] = new Deadline(activity, end);
                        System.out.println("____________________________________________________________\n"
                                + "Got it. I've added this task:\n"
                                + arr[mem].toString() + "\n");
                        mem++;

                    } else if (answer.startsWith("event")) {

                        String[] eventTimes = commands[1].split(" /to ");
                        String start = eventTimes[0].substring(5).trim();
                        String end = eventTimes[1].trim();
                        activity = activity.substring(6).trim();
                        
                        arr[mem] = new Event(activity, start, end);
                        System.out.println("____________________________________________________________\n"
                                + "Got it. I've added this task:\n"
                                + arr[mem].toString() + "\n");
                        mem++;

                    } else {
                        // Throw custom exception for invalid commands
                        throw new UnknownCommandException("Unknown command: " + answer);
                    }

                    System.out.println("Now you have " + (mem - 1) + " tasks in the list. \n"
                            + "____________________________________________________________");

                    answer = reader.nextLine();

                }
            } catch (UnknownCommandException e) {
                // Catch the custom exception and print the desired message
                System.out.println("____________________________________________________________\n"
                        + "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        + "____________________________________________________________");
            } catch (UnknownActivityException e) {
                System.out.println("____________________________________________________________\n"
                        + " OOPS!!! The description of an activity cannot be empty.\n"
                        + "____________________________________________________________");
            }

        }
        System.out.println(goodBye);
    }
}
