import java.util.Scanner;
import java.util.ArrayList;
public class brainRot {
    public static void main(String[] args) {

        //creating an array with a number to memorise how many memory slots have been taken
        ArrayList<Task> arr = new ArrayList<>();
        int mem = 0;
            Scanner reader = new Scanner(System.in);

        String greeting = "____________________________________________________________\n"
                + "Hello! I'm fanumTaxRizzlerOhioSigmaLooksmaxxer\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";

        String goodBye = "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(greeting);
//        reader.next(" ");

        //logic to check if "bye" or "list" has been said
        while(reader.hasNextLine()) {
            String answer = reader.nextLine();

            try {
                if (answer.equals("list")) {
                    System.out.println("____________________________________________________________\n"
                            + "Here are the tasks in your list:");

                    for (int i = 0; i < mem; i++) {
                        System.out.println((i+1) + "." + arr.get(i).toString());
                    }
                    System.out.println("____________________________________________________________\n");

                    answer = reader.nextLine();


                } else if (answer.equals("bye")) {
                    System.out.println(goodBye);
                    break;

                }else if (answer.startsWith("unmark")) {
                    int index = answer.charAt(7) - 48;
                    arr.get(index).unmark();

                    answer = reader.nextLine();

                } else if (answer.startsWith("mark")) {
                        int index = answer.charAt(5) - 48;
                        arr.get(index).mark();
                        answer = reader.nextLine();

                } else if (answer.startsWith("delete")) {
                        int index = answer.charAt(7) - 48;
                    System.out.println(index);
                        arr.remove(index-1);
                        mem--;
                        System.out.println("Now you have " + (mem) + " tasks in the list.\n"
                            + "____________________________________________________________");
                        answer = reader.nextLine();

                } else {
                        String[] commands = answer.split("/", 2);
                        String activity = commands[0];
                        if(activity.isEmpty()) {
                            throw new UnknownActivityException("Unknown command: " + answer);
                        }


                        if (answer.startsWith("todo")) {

                            activity = activity.substring(5).trim();
                            arr.add(new ToDo(activity));
                            System.out.println("____________________________________________________________\n"
                                    + "Got it. I've added this task:\n"
                                    + arr.get(mem).toString() + "\n");
                            mem++;

                        } else if (answer.startsWith("deadline")) {

                            String end = commands[1].substring(3).trim();
                            activity = activity.substring(9).trim();

                            arr.add(new Deadline(activity, end));
                            System.out.println("____________________________________________________________\n"
                                    + "Got it. I've added this task:\n"
                                    + arr.get(mem).toString() + "\n");
                            mem++;

                        } else if (answer.startsWith("event")) {

                            String[] eventTimes = commands[1].split(" /to ");
                            String start = eventTimes[0].substring(5).trim();
                            String end = eventTimes[1].trim();
                            activity = activity.substring(6).trim();

                            arr.add(new Event(activity, start, end));
                            System.out.println("____________________________________________________________\n"
                                    + "Got it. I've added this task:\n"
                                    + arr.get(mem).toString() + "\n");
                            mem++;

                        } else {
                            // Throw custom exception for invalid commands
                            throw new UnknownCommandException("Unknown command: " + answer);
                        }
                        System.out.println("Now you have " + (mem) + " tasks in the list.\n"
                                + "____________________________________________________________");

                        answer = reader.nextLine();

                    }


            } catch (UnknownCommandException e) {
                // Catch the custom exception and print the desired message
                System.out.println("""
                        ____________________________________________________________
                        OOPS!!! I'm sorry, but I don't know what that means :-(
                        ____________________________________________________________""");
                answer = reader.nextLine();
            } catch (UnknownActivityException e) {
                System.out.println("""
                        ____________________________________________________________
                         OOPS!!! The description of an activity cannot be empty.
                        ____________________________________________________________""");
                answer = reader.nextLine();

            }
            reader.close();
        }

    }
}
