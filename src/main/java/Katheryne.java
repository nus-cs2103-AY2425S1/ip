import java.util.Scanner;

public class Katheryne {
    private Scanner sc;

    public Katheryne() {
        this.sc= new Scanner(System.in);
    }

    public void run() {
        System.out.println("Katheryne: " + "Ad astra abyssosque! I'm Katheryne, the receptionist here at the Adventurers' Guild. Welcome!");

        String userInput;
        boolean finish = false;
        while (!finish) {
            userInput = sc.nextLine();
            if (!userInput.equalsIgnoreCase("bye")) {
                System.out.println("Katheryne: " + userInput);
            } else {
                finish = true;
                System.out.println("Katheryne: " + "Farewell, Adventurer, and thank you for supporting the Adventurers' Guild.");
                sc.close();
            }
        }
    }

    public static void main(String[] args) {
        Katheryne k = new Katheryne();
        k.run();
    }
}


