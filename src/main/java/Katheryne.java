import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Katheryne {
    private Scanner sc;
    private ArrayList<Task> list;

    public Katheryne() {
        this.sc= new Scanner(System.in);
        this.list = new ArrayList<Task>();
    }

    private String getList() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            int index = i+1;
            String item = index + ". " + list.get(i).toString() + '\n';
            output = output + item;
        }
        return output;
    }

    public void run() {
        System.out.println("✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧");
        System.out.println("Katheryne: " +
                "Ad astra abyssosque! I'm Katheryne, the receptionist here at the Adventurers' Guild. Welcome!");

        String userInput;
        boolean finish = false;
        while (!finish) {
            System.out.println("✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧");
            userInput = sc.nextLine();
            try {
                if (userInput.equals("list")) {
                    System.out.println(this.getList());
                } else if (userInput.startsWith("mark")) {
                    String[] input = userInput.split(" ");
                    if (input.length < 2) {
                        throw new MissingInformationException("You need to specify the task number to mark.");
                    }
                    int id = Integer.parseInt(input[1]) - 1;
                    Task target = list.get(id);
                    target.mark();
                    System.out.println("Katheryne: " +
                            "Nice! I've marked this task as done:" + '\n' + target.toString());
                } else if (userInput.startsWith("unmark")) {
                    String[] input = userInput.split(" ");
                    if (input.length < 2) {
                        throw new MissingInformationException("You need to specify the task number to unmark.");
                    }
                    int id = Integer.parseInt(input[1]) - 1;
                    Task target = list.get(id);
                    target.unmark();
                    System.out.println("Katheryne: OK, I've marked this task as not done yet:" + '\n' + target.toString());
                } else if (userInput.startsWith("todo")) {
                    String[] input = userInput.split(" ", 2);
                    if (input.length < 2 || input[1].isEmpty()) {
                        throw new MissingInformationException("You need to specify the description of the todo task.");
                    }
                    String des = input[1];
                    ToDo task = new ToDo(des);
                    list.add(task);
                    String str = String.format("Katheryne: " +
                            "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", task.toString(), list.size());
                    System.out.println(str);
                } else if (userInput.startsWith("event")) {
                    int fromIndex = userInput.indexOf("/from");
                    int toIndex = userInput.indexOf("/to");

                    if (fromIndex == -1) {
                        throw  new MissingInformationException("You need to specify FROM time for an event.");
                    }
                    if (toIndex == -1) {
                        throw new MissingInformationException("You need to specify TO time for an event.");
                    }

                    String fromTime = userInput.substring(fromIndex + "/from".length(), toIndex).trim();
                    String toTime = userInput.substring(toIndex + "/to".length()).trim();
                    int firstSpaceIndex = userInput.indexOf(" ");
                    String description = userInput.substring(firstSpaceIndex + 1, fromIndex).trim();
                    if (description.isEmpty()) {
                        throw new MissingInformationException("You need to specify the description of the todo task.");
                    }

                    Event task = new Event(description, fromTime, toTime);
                    list.add(task);
                    String str = String.format("Katheryne: " +
                            "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", task.toString(), list.size());
                    System.out.println(str);
                } else if (userInput.startsWith("deadline")) {
                    String[] input = userInput.split(" /by ", 2);
                    if (input.length < 2) {
                        throw new MissingInformationException("You need to specify both the description and the deadline time.");
                    }
                    String des = input[0];
                    int firstSpaceIndex = des.indexOf(" ");
                    String description = des.substring(firstSpaceIndex + 1).trim();
                    String byTime = input[1].trim();

                    if (description.isEmpty()) {
                        throw new MissingInformationException("You need to specify description of task.");
                    }

                    if (byTime.isEmpty()) {
                        throw new MissingInformationException("You need to specify deadline time of task.");
                    }

                    Deadline task = new Deadline(description, byTime);
                    list.add(task);
                    String str = String.format("Katheryne:" +
                            " Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", task.toString(), list.size());
                    System.out.println(str);
                } else if (userInput.equals("bye")) {
                    finish = true;
                    System.out.println("✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧");
                    System.out.println("Katheryne: " +
                            "Farewell, Adventurer, and thank you for supporting the Adventurers' Guild.");
                    System.out.println("✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧");
                    sc.close();
                } else {
                    throw new InvalidInputException("Katheryne: " + "I'm sorry, Katheryne is unable to comprehend your request.");
                }
            } catch (MissingInformationException e) {
                System.out.println("Katheryne: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Katheryne: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Katheryne: The task number must be a valid integer. Please try again.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Katheryne: That task number doesn't exist. Please try again.");
            } catch (Exception e) {
                System.out.println("Katheryne: An unexpected error occurred: " + e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        Katheryne k = new Katheryne();
        k.run();
    }
}


