import java.util.Scanner;

public class Majima {
    //leave no magic numbers
    private static int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    //counter, used in main
    private static int task_count = 0;
    static String linegap = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(linegap);
        System.out.println("KIIIIIRYU-CHAN! It's ya old pal, Majima!");
        System.out.println("What can I do fer ya?");
        System.out.println(linegap);
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                //Exit main, close everything
                System.out.println(linegap);
                System.out.println("Bye bye! Don't keep me waiting fer too long now, ya hear?");
                System.out.println(linegap);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                //Print out all tasks so far
                System.out.println(linegap);
                System.out.println("Here's whatcha gotta do, Kiryu-chan!");
                for (int i = 0; i < task_count; i++) {
                    System.out.println((i + 1) + ". " + "[" + tasks[i].getStatusIcon() + "] " + (tasks[i].description));
                }
                System.out.println(linegap);
            } else if (input.startsWith("mark")) {
                //We minus 1 here, to account for zero counting.
                //For example, mark 1 (Mark the first task) corresponds to the task in the
                //0th position of the tasks array.
                int numberArgument = Integer.parseInt(input.substring(5)) - 1;
                if (numberArgument >= 0 && numberArgument < task_count) {
                    tasks[numberArgument].markAsDone();
                    System.out.println(linegap);
                    System.out.println("Okay, I've gone ahead and marked that one fer ya.");
                    System.out.println("  " + "[" + tasks[numberArgument].getStatusIcon() + "] "
                            + (tasks[numberArgument].description));
                    System.out.println(linegap);
                } else {
                    System.out.println(linegap);
                    System.out.println("Eh? Kiryu-chan, yain't making any sense!");
                    System.out.println(linegap);
                }
            } else if (input.startsWith("unmark")) {
                //note that this numberArgument variable is different, as the length of the
                //commands differ.
                int numberArgument = Integer.parseInt(input.substring(7)) - 1;
                if (numberArgument >= 0 && numberArgument < task_count) {
                    tasks[numberArgument].markAsUndone();
                    System.out.println(linegap);
                    System.out.println("Okay, I've gone ahead and unmarked that one fer ya.");
                    System.out.println("  " + "[" + tasks[numberArgument].getStatusIcon() + "] "
                            + (tasks[numberArgument].description));
                    System.out.println(linegap);
                } else {
                    System.out.println(linegap);
                    System.out.println("Eh? Kiryu-chan, yain't making any sense!");
                    System.out.println(linegap);
                }
            }
            //i also note that mark and unmark have many similar lines of code. maybe they can
            //be put into one method.
            //is it not better to just make a class which checks whether it's a valid command?
            //OOP and all that: better than making main super clunky right?
            //With regards to step 4, this was all quite hard/new to me, as I'm not familiar with string manip.
            //My good friend ChatGPT helped out significantly here.
            else if (input.startsWith("deadline")) {
                String[] parts = input.split("/by", 2);
                if (parts.length < 2) {
                    System.out.println(linegap);
                    System.out.println("Eh? Kiryu-chan, yain't got no '/by' argument!");
                    System.out.println(linegap);
                    continue;
                }
                String description = parts[0].substring(9).trim();
                String by = parts[1].trim();
                if (canAddTask()) {
                    tasks[task_count] = new Deadline(description, by);
                    task_count++;
                    System.out.println(linegap);
                    System.out.println("Understood, Kiryu-chan! Adding that task to the list.");
                    System.out.println(" " + tasks[task_count - 1].toString());
                    System.out.println("Now you've got " + task_count + " tasks need doin'.");
                    System.out.println(linegap);
                }
            } else if (input.startsWith("todo")) {
                String description = input.substring(5).trim();
                if (canAddTask()) {
                    tasks[task_count] = new Todo(description);
                    task_count++;
                    System.out.println(linegap);
                    System.out.println("Understood, Kiryu-chan! Adding that task to the list.");
                    System.out.println(" " + tasks[task_count - 1].toString());
                    System.out.println("Now you've got " + task_count + " tasks need doin'.");
                    System.out.println(linegap);
                }
            } else if (input.startsWith("event")) {
                String parts[] = input.split("/from", 2);
                if (parts.length < 2) {
                    System.out.println(linegap);
                    System.out.println("Eh? Kiryu-chan, y'aint got no '/from' argument!");
                    System.out.println(linegap);
                    continue;
                }
                String description = parts[0].substring(6).trim();
                String[] dateParts = parts[1].split("/to", 2);
                if (dateParts.length < 2) {
                    System.out.println(linegap);
                    System.out.println("Eh? Kiryu-chan, y'aint got no '/to' argument!");
                    System.out.println(linegap);
                    continue;
                }
                String from = dateParts[0].trim();
                String to = dateParts[1].trim();
                tasks[task_count] = new Event(description, from, to);
                task_count++;
                System.out.println(linegap);
                System.out.println("Understood, Kiryu-chan! Adding that task to the list.");
                System.out.println(" " + tasks[task_count - 1].toString());
                System.out.println("Now you've got " + task_count + " tasks need doin'.");
                System.out.println(linegap);
            } else {
                System.out.println(linegap);
                System.out.println("Uhh, Kiryu-chan? There ain't no sense in whatever ya just said!");
                System.out.println(linegap);
            }
        }
        scanner.close();
    }


    private static boolean canAddTask() {
        if (task_count >= MAX_TASKS) {
            System.out.println(linegap);
            System.out.println("O-oi, Kiryu-chan! Ya can't expect me to 'member all this crap!");
            System.out.println(linegap);
            return false;
        }
        return true;
    }

    //Code for refactoring goes here, so main doesn't look so crowded.
}
