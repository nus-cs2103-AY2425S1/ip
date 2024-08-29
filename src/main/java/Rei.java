import java.util.Scanner;

public class Rei {
    public static void main(String[] args) {
        String logo = "  ____  _____ ___ \n"
                + " |  _ \\| ____|_ _|\n"
                + " | |_) |  _|  | | \n"
                + " |  _ <| |___ | | \n"
                + " |_| \\_\\_____|___|\n";

        System.out.println("Annyeong! I'm\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("-----------YOU------------");

        /**
         * stores
         */
        Task[] listOfTasks = new Task[100];
        int count = 1;
        Scanner scanner = new Scanner(System.in);


        while (!scanner.hasNext("annyeong") && !scanner.hasNext("Annyeong")) {
            if (scanner.hasNext("list")) {
                System.out.println("-----------REI♥-----------");
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i < count; i++) {
                    System.out.println(i + ". " + listOfTasks[i]);
                }
                scanner.nextLine();
            } else if (scanner.hasNext("mark")) {
                System.out.println("-----------REI♥-----------");
                scanner.next();

                // Read the rest of the line after "mark"
                String prompt = scanner.nextLine().trim();

                // Check if the rest of the line is an integer
                if (prompt.isEmpty() || !prompt.matches("\\d+")) {
                    System.out.println("State the task number.");
                    System.out.println("-----------YOU------------");
                    continue;
                }

                int id = Integer.parseInt(prompt);
                if (id < count && id > 0) {
                    listOfTasks[id].markAsDone();
                    System.out.println("Okay! I've marked this task as done: ");
                    System.out.println(listOfTasks[id]);
                } else {
                    System.out.println("No task found. Please retry!");
                }

            } else if (scanner.hasNext("unmark")) {
                System.out.println("-----------REI♥-----------");
                scanner.next();

                // Read the rest of the line after "mark"
                String prompt = scanner.nextLine().trim();

                // Check if the rest of the line is an integer
                if (prompt.isEmpty() || !prompt.matches("\\d+")) {
                    System.out.println("State the task number.");
                    System.out.println("-----------YOU------------");
                    continue;
                }

                int id = Integer.parseInt(prompt);
                if (id < count && id > 0) {
                    listOfTasks[id].markAsUndone();
                    System.out.println("Okay! I've marked this task as not done yet: ");
                    System.out.println(listOfTasks[id]);
                } else {
                    System.out.println("No task found. Please retry!");
                }
            } else if (scanner.hasNext("todo")
                        || scanner.hasNext("deadline")
                        || scanner.hasNext("event")) {

                String prompt = scanner.nextLine();

                System.out.println("-----------REI♥-----------");

                if (prompt.startsWith("todo")) {
                    if (isAllWhitespace(prompt.substring(4))) {
                        System.out.println("Task is empty. Please state the task name.");
                        System.out.println("-----------YOU------------");
                        continue;
                    }
                    listOfTasks[count++] = Task.createToDo(prompt.substring(5));
                } else if (prompt.startsWith("deadline")) {
                    if (isAllWhitespace(prompt.substring(8))) {
                        System.out.println("Task is empty. Please state the task and deadline.");
                        System.out.println("-----------YOU------------");
                        continue;
                    }

                    if (prompt.indexOf("/by") == -1) {
                        System.out.println("When is the deadline? Please state the task with the deadline.");
                        System.out.println("-----------YOU------------");
                        continue;
                    }

                    listOfTasks[count++] = Task.createDeadline(prompt.substring(9, prompt.indexOf("/by")),
                                                        prompt.substring(prompt.indexOf("/by") + 4));

                } else { // event
                    if (isAllWhitespace(prompt.substring(5))) {
                        System.out.println("Event is empty. Please state the event and time range.");
                        System.out.println("-----------YOU------------");
                        continue;
                    }

                    if (prompt.indexOf("/from") == -1 || prompt.indexOf("/to") == -1) {
                        System.out.println("State the START and FINISH time of the event");
                        System.out.println("-----------YOU------------");
                        continue;
                    }

                    listOfTasks[count++] = Task.createEvent(prompt.substring(6, prompt.indexOf("/from")),
                                                     prompt.substring(prompt.indexOf("/from") + 6, prompt.indexOf("/to")),
                                                     prompt.substring(prompt.indexOf("/to") + 4));


                }
                System.out.println("Got it. I've added this task:");
                System.out.println("    " + listOfTasks[count - 1]);
                System.out.println(String.format("Now you have %d tasks in the list.", count - 1));

            } else {
                System.out.println("-----------REI♥-----------");
                System.out.println("I don't understand what you want me to do.");
                System.out.println("-----------YOU------------");
                scanner.nextLine();
                continue;
            }

            System.out.println("-----------YOU------------");
        }

        scanner.close();

        System.out.println("-----------REI♥-----------");
        System.out.println("Annyeong. Hope to see you soon.");

    }

    public static boolean isAllWhitespace(String input) {
        return input.replaceAll("\\s+","").isEmpty();
    }

}
