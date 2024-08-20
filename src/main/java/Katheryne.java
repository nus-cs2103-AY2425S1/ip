import java.util.ArrayList;
import java.util.Scanner;

public class Katheryne {
    private Scanner sc;
    private ArrayList<String> list;

    public Katheryne() {
        this.sc= new Scanner(System.in);
        this.list = new ArrayList<String>();
    }

    private String getList() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            int index = i+1;
            String item = index + ". " + list.get(i) + '\n';
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
                } else {
                    System.out.println("Katheryne: Have added " + userInput + " to your list.");
                    list.add(userInput);
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


