import java.util.ArrayList;
import java.util.Scanner;


public class Kitty {
    private static final String name = "Kitty";
    private static final ArrayList<Task> list = new ArrayList<Task>(100);
    private static final String divisionLine = "--------------------------";
    public static void main(String[] args) {
//        String logo = """
//                 ____        -
//                |  _ \\ _   _| | _____\s
//                | | | | | | | |/ / _ \\
//                | |_| | |_| |   <  __/
//                |____/ \\__,_|_|\\_\\___|
//                """;
//        System.out.println("Hello from\n" + logo);
        Greet();
    }

    private static void Greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?\n");
        System.out.println(divisionLine);
        Echo();
    }

    private static void Echo() {
        String command = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            command = sc.nextLine();
            if (command.contains("bye")) {
                Exit();
                return;
            } else if (command.contains("list")) {
                List();
            } else if (command.contains("delete")) {
                delete(extractFirstNumber(command));
            } else if (command.contains("unmark")) {
                unmark(extractFirstNumber(command));
            } else if (command.contains("mark")) {
                mark(extractFirstNumber(command));
            } else if (command.contains("todo") || command.contains("deadline") || command.contains("event")){
                add(command);
            } else {
                System.out.println(divisionLine);
                System.out.println("Burrrrr~ What is this??? I have no idea about it...\n");
                System.out.println(divisionLine);
            }
        }
    }

    private static void add(String item) {
        String[] aux = item.split(" ", 2);
        String type = aux[0];
        Task tmp;
        try {
            if (aux.length == 1) {
                System.out.println(divisionLine);
                System.out.println("Oooops... I don't know what you want to do though...");
                throw new TaskException();
            }
            String name = aux[1];
            tmp = type.equals("todo")
                    ? new Todo(name)
                    : type.equals("deadline")
                    ? createDeadline(name)
                    : type.equals("event")
                    ? createEvent(name)
                    : null;
            list.add(tmp);
            System.out.println(divisionLine);
            System.out.println("Okie, I added it into the list:");
            System.out.println("  " + tmp);
            System.out.printf("Now you have %d tasks in the list.\n\n", list.size());
            System.out.println(divisionLine);
        } catch (KittyException e) {
            System.out.println(e);
            System.out.println("\n" + divisionLine);
        }
    }

    private static Task createDeadline(String str) throws DeadlineException{
        String[] parts = str.split("/by");
        if (parts.length == 1)
            throw new DeadlineException();
        return new Deadline(parts[0], parts[1]);
    }

    private static Task createEvent(String str) throws EventException{
        String[] parts = str.split("/from|/to");
        if (parts.length < 3)
            throw new EventException();
        return new Event(parts[0], parts[1], parts[2]);

    }

    private static void List() {
        int count = 1;
        Task[] tmp = new Task[0];
        System.out.println(divisionLine);
        System.out.println("Meow~ Here you are!");
        for (Task item: list.toArray(tmp)) {
            System.out.println(count++ + "." + item);
        }
        System.out.println("\n" + divisionLine);
    }

    private static void delete(int index) {
        try {
            Task tmp = list.get(index - 1);
            String note = tmp.toString();
            list.remove(index - 1);
            System.out.println(divisionLine);
            System.out.println("I have removed it from the list :)");
            System.out.println("  " + note);
            System.out.printf("Now you have %d tasks in the list\n\n", list.size());
            System.out.println(divisionLine);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(divisionLine + "\nIndex out of bound, you can only input integer from 1 to "
                    + list.size() + ".\n\n" + divisionLine);
        }
    }

    private static void mark(int index) {
        try {
            Task tmp = list.get(index - 1);
            tmp.mark();
            System.out.println(divisionLine);
            System.out.println("Well done! You have completed this task!");
            System.out.println("  " + tmp);
            System.out.println("\n" + divisionLine);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(divisionLine + "\nIndex out of bound, you can only input integer from 1 to "
                    + list.size() + ".\n\n" + divisionLine);
        }
    }

    private static void unmark(int index) {
        try {
            Task tmp = list.get(index - 1);
            tmp.unmark();
            System.out.println(divisionLine);
            System.out.println("Meow~ Okay we can continue this task!");
            System.out.println("  " + tmp);
            System.out.println("\n" + divisionLine);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(divisionLine + "\nIndex out of bound, you can only input integer from 1 to "
                    + list.size() + ".\n\n" + divisionLine);
        }
    }

    private static int extractFirstNumber(String input) {
        // Replace all non-digit characters with spaces
        String cleanedInput = input.replaceAll("\\D+", " ");

        // Split the cleaned string by spaces
        String[] parts = cleanedInput.trim().split("\\s+");

        // Check if there are any parts and parse the first one
        if (parts.length > 0) {
            try {
                return Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                // Handle the case where parsing fails
                return -1;
            }
        }

        // Return null if no number is found
        return -1;
    }

    private static void Exit() {
        System.out.println(divisionLine);
        System.out.println("Bye. Hope I can see you again soon!\nNext time bring me some cat food please!!!\n");
        System.out.println(divisionLine);
    }
}
