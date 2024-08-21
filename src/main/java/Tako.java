import java.util.ArrayList;
import java.util.Scanner;

public class Tako {
    public static void main(String[] args) {
        String name = "Tako";
        ArrayList<Task> listOfTask = new ArrayList<Task>();

        Scanner input = new Scanner(System.in);

        System.out.println("Hello! I'm " + name + "\n" +
                           "What can I do for you?\n");

        String command = input.nextLine();

        while(!command.equals("bye")) {
            if (command.equals("list")) {
                //list command
                listStorage(listOfTask);
            } else if (command.length() > 4 && command.substring(0, 5).equals("mark ")) {
                //mark command
                int taskNumber = Integer.parseInt(command.substring(5));
                markTask(listOfTask, taskNumber);
            } else if (command.length() > 6 && command.substring(0, 7).equals("unmark ")) {
                //unmark command
                int taskNumber = Integer.parseInt(command.substring(7));
                unmarkTask(listOfTask, taskNumber);
            } else if (command.length() > 4 && command.substring(0, 5).equals("todo ")) {
                //to-do command
                String toDoTaskDescription = command.substring(5);
                addStorage(listOfTask, new ToDo(toDoTaskDescription));
            } else if (command.length() > 8 && command.substring(0, 9).equals("deadline ")) {
                //deadline command
                int markerOfBy = command.indexOf("/by");
                String deadlineDescription = command.substring(9, markerOfBy);
                String by = command.substring(markerOfBy + 4);
                addStorage(listOfTask, new Deadline(deadlineDescription, by));
            } else if (command.length() > 5 && command.substring(0, 6).equals("event ")) {
                //event command
                int markerOfFrom = command.indexOf("/from");
                int markerOfTo = command.indexOf("/to");
                String eventDescription = command.substring(6, markerOfFrom);
                String from = command.substring(markerOfFrom + 6, markerOfTo);
                String to = command.substring(markerOfTo + 4);
                addStorage(listOfTask, new Event(eventDescription, from, to));
            } else {
                System.out.println("added: " + command);
                Task newTask = new Task(command);
                addStorage(listOfTask, newTask);
            }
            command = input.nextLine();
        }

        //bye command
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void listStorage(ArrayList<Task> listOfTask) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listOfTask.size(); i++) {
            System.out.println((i + 1) + "." + listOfTask.get(i).toString());
        }
    }

    public static void addStorage(ArrayList<Task> listOfTask, Task newTask) {
        listOfTask.add(newTask);
        System.out.println(newTask.addedConfirmation(listOfTask));
    }

    public static void markTask(ArrayList<Task> listOfTask, int taskNumber) {
        listOfTask.get(taskNumber - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + listOfTask.get(taskNumber - 1).toString());
    }

    public static void unmarkTask(ArrayList<Task> listOfTask, int taskNumber) {
        listOfTask.get(taskNumber - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" + listOfTask.get(taskNumber - 1).toString());
    }
}
