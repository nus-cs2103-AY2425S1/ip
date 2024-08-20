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
                } else {
                    System.out.println("Katheryne: Have added " + userInput + " to your list.");
                    Task t = new Task(userInput);
                    list.add(t);
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


