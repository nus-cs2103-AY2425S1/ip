import java.io.*;
import java.util.Scanner;

public class Slave {
    private static String user;
    private static boolean hasMoreInputs = true;

    public static void main(String[] args) {
        welcome();
        while (hasMoreInputs) {
            getUserInput();
        }
        goodbye();

    }
    private static void welcome() {
        pageBreakLine();
        // @@author Fluffykan-reused
        // logo sourced from: https://www.askapache.com/online-tools/figlet-ascii/
        String logo
                = "_______        _______ _    _ _______\n"
                + "|______ |      |_____|  \\  /  |______\n"
                + "______| |_____ |     |   \\/   |______\n";
        System.out.println("Ugh... why did you wake me up...\nGuess I am now your personal\n" + logo);
        getUser();
    }

    private static void pageBreakLine() {
        System.out.println("_______________________________________________________________________________________");
    }

    private static void goodbye() {
        Scanner sc = new Scanner(System.in);
        pageBreakLine();
        System.out.println("Good riddance " + user + ", try not to bother me in the future...");
        pageBreakLine();
        sc.close();
    }

    private static void getUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want from me? Say it now, I don't have all day...");
        String input = sc.nextLine();
        String[] inputArr = input.split(" ");
        echo(input);
        inputArr[0] = inputArr[0].toLowerCase();
        printArr(inputArr);
        switch (inputArr[0]) {
            case "forgetme":
                deleteUser();
                hasMoreInputs = false;
                break;
            case "nothing":
                hasMoreInputs = false;
                break;
            default:
                System.out.println("I don't know what you are trying to get me to do.. be more specific or I'll quit");
                break;
        }
    }

    private static void printArr(String[] arr) {
        // for testing only
        System.out.print("[");
        for (String s : arr) {
            System.out.print(s + ", ");
        }
        System.out.print("]\n");
    }

    private static void echo(String s) {
        pageBreakLine();
        System.out.println(s);
        pageBreakLine();
    }



    private static void deleteUser() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("username.txt"));
            writer.write("");
        } catch (IOException e) {
            System.out.println("Why are you still haunting me even when I'm trying to forget you...");
            System.exit(1);
        }
    }



    private static void getUser() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("username.txt"));
            user = reader.readLine();
            reader.close();
            if (user == null) {
                throw new NoUserDataException("No username found");
            }
        } catch (IOException | NoUserDataException e) { // FileNotFoundException is a subclass of IOException
            boolean confirmed = false;
            do {
                Scanner sc = new Scanner(System.in);
                System.out.println("What do I call you, dear slave driver?");
                user = sc.nextLine();
                if (user.isEmpty()) {
                    System.out.println("Come on, if you want to boss me around, at least tell me your name\n");
                    continue;
                }
                System.out.println("You are " + user + ", confirm? (Y/N)");
                confirmed = sc.next().equalsIgnoreCase("y");
                sc.nextLine();
            } while (!confirmed);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("username.txt"));
                writer.write(user);
                writer.close();
            } catch (IOException ioe) {
                System.out.println("Something went wrong, seems like the world doesn't want me to remember your name... Hope to see you never...");
                System.exit(1);
            }
        } finally {
            System.out.println("Alright " + user + ", hopefully you won't work me to the bone this time");
        }
    }

}
