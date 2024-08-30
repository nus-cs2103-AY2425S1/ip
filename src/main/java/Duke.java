import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> userInputs = new ArrayList<>();

    private static void addItem(String inp) {
        userInputs.add(new Task(inp));
    }

    private static void display() {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<userInputs.size(); i++) {
            System.out.println((i+1) + "." + userInputs.get(i));
        }
    }

    private static void taskAddDisplay(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + userInputs.size() + "tasks in the list.");
    }

    public static void processInput(String inp) throws EmptyTaskException, InvalidInstructionException {
        String instruction = inp.split(" ",2)[0];
        if (instruction.equals("list")) {
            display();
            return;
        }
        if (inp.split(" ",2).length==1) {
            throw new EmptyTaskException();
        }
        String remainingInput = inp.split(" ",2)[1];
        if (instruction.equals("mark")) {
            int idx = Integer.parseInt(remainingInput)-1;
            userInputs.get(idx).setDone(true);
            System.out.println("Nice! I've marked this task as done:\n" +
                    userInputs.get(idx));
        } else if (instruction.equals("unmark")) {
            int idx = Integer.parseInt(remainingInput)-1;
            userInputs.get(idx).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:\n" +
                    userInputs.get(idx));
        } else if (instruction.equals("todo")){
            Todo task = new Todo(remainingInput);
            userInputs.add(task);
            taskAddDisplay(task);
        } else if (instruction.equals("deadline")) {
            String name = remainingInput.split(" /by ", 2)[0];
            String endDate = remainingInput.split(" /by ", 2)[1];
            Deadline task = new Deadline(name, endDate);
            userInputs.add(task);
            taskAddDisplay(task);
        } else if (instruction.equals("event")) {
            String name = remainingInput.split(" /from ", 2)[0];
            remainingInput = remainingInput.split(" /from ", 2)[1];
            String start = remainingInput.split(" /to ", 2)[0];
            String end = remainingInput.split(" /to ", 2)[1];
            Event task = new Event(name, start, end);
            userInputs.add(task);
            taskAddDisplay(task);
        } else {
            throw new InvalidInstructionException();
        }
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________\n" +
                "Hello! I'm Tesla\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String inp = scanner.nextLine();
        while (!inp.equals("bye")) {
            try {
                processInput(inp);
            } catch (EmptyTaskException e) {
                System.out.println("Remaining description of task cannot be empty.");
            } catch (InvalidInstructionException e) {
                System.out.println("Invalid instruction given.");
            }
            inp = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
