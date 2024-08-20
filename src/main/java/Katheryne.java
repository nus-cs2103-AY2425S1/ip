import java.util.ArrayList;
import java.util.Scanner;

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
        System.out.println("Katheryne: " + "Ad astra abyssosque! I'm Katheryne, the receptionist here at the Adventurers' Guild. Welcome!");

        String userInput;
        boolean finish = false;
        while (!finish) {
            System.out.println("✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧");
            userInput = sc.nextLine();
            if (!userInput.equals("bye")) {
                System.out.println("✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧");
                if (userInput.equals("list")) {
                    System.out.println(this.getList());
                } else if (userInput.startsWith("mark")) {
                    String[] input = userInput.split(" ");
                    int id = Integer.parseInt(input[1]) - 1;
                    Task target = list.get(id);
                    target.mark();
                    System.out.println("Katheryne: Nice! I've marked this task as done:" + '\n' + target.toString());
                } else if (userInput.startsWith("unmark")) {
                    String[] input = userInput.split(" ");
                    int id = Integer.parseInt(input[1]) - 1;
                    Task target = list.get(id);
                    target.unmark();
                    System.out.println("Katheryne: OK, I've marked this task as not done yet:" + '\n' + target.toString());
                } else if (userInput.startsWith("todo")) {
                    String[] input = userInput.split(" ",2);
                    String des = input[1];
                    ToDo task = new ToDo(des);
                    list.add(task);
                    String str = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",task.toString(),list.size());
                    System.out.println(str);
                } else if (userInput.startsWith("event")) {
                    int fromIndex = userInput.indexOf("/from");
                    int toIndex = userInput.indexOf("/to");
                    String fromTime = "";
                    String toTime = "";
                    if (fromIndex != -1) {
                        int startOfFromTime = fromIndex + "/from".length();
                        fromTime = userInput.substring(startOfFromTime, toIndex).trim();
                    }
                    if (toIndex != -1) {
                        int startOfToTime = toIndex + "/to".length();
                        toTime = userInput.substring(startOfToTime).trim();
                    }

                    int firstSpaceIndex = userInput.indexOf(" ");
                    String description = userInput.substring(firstSpaceIndex + 1, fromIndex);
                    Event task = new Event(description, fromTime, toTime);
                    list.add(task);
                    String str = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",task.toString(),list.size());
                    System.out.println(str);
                } else if (userInput.startsWith("deadline")) {
                    String[] input = userInput.split(" /by ",2);
                    String des = input[0];
                    int firstSpaceIndex = des.indexOf(" ");
                    String description = des.substring(firstSpaceIndex + 1);
                    String byTime = input[1];
                    Deadline task = new Deadline(description,byTime);
                    list.add(task);
                    String str = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",task.toString(),list.size());
                    System.out.println(str);
                }

            }
            else{
                finish = true;
                System.out.println("✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧");
                System.out.println("Katheryne: " + "Farewell, Adventurer, and thank you for supporting the Adventurers' Guild.");
                System.out.println("✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧");
                sc.close();
            }
        }
    }

    public static void main(String[] args) {
        Katheryne k = new Katheryne();
        k.run();
    }
}


