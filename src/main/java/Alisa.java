import java.util.ArrayList;
import java.util.Scanner;

public class Alisa {
    public static void main(String[] args) throws AlisaException {
        Scanner sc = new Scanner(System.in);
        String divider = "------------------------------------------------------------------------------------";
        String exitCommand = "bye";
        String input = "";
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("Hey, Alisa here! What do you need help with?");
        System.out.println("BTW Say the word bye to get out of this conversation");
        System.out.println(divider);

        while (true) {
            try {
                input = sc.nextLine();
                System.out.println(divider);

                if (input.equals(exitCommand)) {
                    System.out.println("Since you technically said bye, see ya next time!");
                    System.out.println(divider);
                    sc.close();
                    break;
                } else if (input.startsWith("mark")) {
                    String[] inputArray = input.split(" ");
                    if (inputArray.length < 2) {
                        throw new AlisaException("You didn't tell me which task to mark as done!");
                    } else if (Integer.parseInt(inputArray[1]) > taskList.size()) {
                        throw new AlisaException("Task #" + inputArray[1] + " doesn't exist. Give me a task that does exist to mark as done.");
                    } else {
                        int index = Integer.parseInt(inputArray[1]) - 1;
                        taskList.get(index).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(taskList.get(index));
                        System.out.println(divider);
                    }
                } else if (input.startsWith("unmark")) {
                    String[] inputArray = input.split(" ");
                    if (inputArray.length < 2) {
                        throw new AlisaException("You didn't tell me which task to mark as not done!");
                    } else if (Integer.parseInt(inputArray[1]) > taskList.size()) {
                        throw new AlisaException("Task #" + inputArray[1] + " doesn't exist. Give me a task that does exist to mark as not done.");
                    } else {
                        int index = Integer.parseInt(inputArray[1]) - 1;
                        taskList.get(index).markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(taskList.get(index));
                        System.out.println(divider);
                    }
                } else if (input.startsWith("todo")) {
                    if (input.substring(5).length() == 0) {
                        throw new AlisaException("Give me a description for your task!");
                    }
                    Todo newTodo = new Todo(input.substring(5));
                    taskList.add(newTodo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTodo);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(divider);
                }
                else if (input.startsWith("deadline")) {
                    if (input.substring(9).length() == 0) {
                        throw new AlisaException("Give me a description for your task!");
                    }
                    String[] inputArray = input.substring(9).split(" /by ");
                    if (inputArray.length < 2) {
                        throw new AlisaException("You got to give a deadline for your task!");
                    }
                    Deadline newDeadline = new Deadline(inputArray[0], inputArray[1]);
                    taskList.add(newDeadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(divider);
                } else if (input.startsWith("event")) {
                    if (input.substring(6).length() == 0) {
                        throw new AlisaException("Give me a description for your task!");
                    }
                    String[] inputArray = input.substring(6).split(" /from ");
                    String[] fromToArray = inputArray[1].split(" /to ");
                    if (fromToArray.length < 2) {
                        throw new AlisaException("You got to give me a start and end timing for your event!");
                    }
                    Event newEvent = new Event(inputArray[0], fromToArray[0], fromToArray[1]);
                    taskList.add(newEvent);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(divider);
                }
                else if (input.equals("list")) {
                    if (taskList.isEmpty()) {
                        System.out.println("The list is empty, sorry :(");
                        System.out.println(divider);
                    } else {
                        for (int i = 1; i <= taskList.size(); i++) {
                            System.out.println(i + "." + taskList.get(i-1));
                        }
                        System.out.println(divider);
                    }
                }
                else {
                    throw new AlisaException("Sorry, I didn't quite catch that. Put in a command that I understand");
                }
            } catch (AlisaException e) {
                System.out.print(e.getMessage());
            }
        }

    }
}







