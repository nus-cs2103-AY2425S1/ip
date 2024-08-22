import java.util.*;

public class Rainy {
    public static void main(String[] args) throws InvalidIndexException {
        System.out.println("Hello! I am RAINY - Responsive, Automated, Intelligence Network for You.");
        System.out.println("I am a digital assistant designed to help you keep track of your day.");
        System.out.println("So, what can I do for you today?");
        TaskTracker tm = new TaskTracker();
        Scanner sc = new Scanner(System.in);
        String messages = sc.nextLine();
        String[] input = messages.split(" ");
        String[] splitByTask = messages.split("/");
        String message = input[0];
        int count;
        if (message.equals("mark") || message.equals("unmark")) {
            try {
                count = Integer.parseInt(input[1]);
            } catch (Exception e) {
                count = -1;
            }
        } else {
            count = -1;
        }

        while(!message.equals("bye")) {
            if (message.equals("list")) {
                tm.getList();
            } else if (message.equals("mark") && count != -1) {
                tm.markDone(count - 1);
            } else if (message.equals("unmark") && count != -1) {
                tm.unmarkDone(count - 1);
            } else if (message.equals("todo")) {
                if (input.length == 1) {
                    System.out.println("You neeed to provide a description of your ToDo task, please try again!");
                } else {
                    tm.updateListToDo(splitByTask[0].substring(5));
                }

            } else if (message.equals("deadline")) {
                if (input.length == 1) {
                    System.out.println("You neeed to provide a description of your Deadline, please try again!");
                } else if (splitByTask.length == 1) {
                    System.out.println("Please provide an end date for your Deadline!");
                } else {
                    tm.updateListDeadline(splitByTask[0].substring(9), splitByTask[1]);
                }

            }

            else {
                tm.updateListEvent(splitByTask[0].substring(6), splitByTask[1], splitByTask[2]);
            }
            messages = sc.nextLine();
            input = messages.split(" ");
            splitByTask = messages.split("/");
            message = input[0];
            if (message.equals("mark") || message.equals("unmark")) {
                try {
                    count = Integer.parseInt(input[1]);
                } catch (Exception e) {
                    count = -1;
                }
            } else {
                count = -1;
            }
        }
        System.out.println("Goodbye! Have a nice day ahead!!");
    }
}
