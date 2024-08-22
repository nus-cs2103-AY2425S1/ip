import java.util.Scanner;
public class R2D2 {
    public static void main(String[] args) {
        //Opening dialogue for the bot
        String hline = "____________________________________________________________";
        System.out.println(hline);
        System.out.println("Hello! I'm R2D2! *Beep* *Boop*");
        System.out.println("What can I do for you?");
        System.out.println(hline);

        // Reading input from user
        Scanner reader = new Scanner(System.in);
        String userInput = reader.nextLine();

        // init a new database and counter for memory recall
        String[] database = new String[100];
        int counter = 1;

        // Main interaction
        while (!userInput.equals("bye")) {
            if (!userInput.equals("list")) {
                //added into database
                System.out.println(hline);
                System.out.println("Buzzinga! Added to your list: \n" + userInput);
                System.out.println(hline);

                //processing in the background (adding to database)
                database[counter] = userInput;
                counter++;
            } else { // list has been typed
                System.out.println(hline);
                for (int i = 1; i < counter; i++) {
                    System.out.println(i + ". " + database[i]);
                }
                System.out.println(hline);
            }
            userInput = reader.nextLine();
        }

        // standard exit when bye is typed
        System.out.println(hline);
        System.out.println("Bye. Hope to see you again soon! *bzzzt*");
        System.out.println(hline);
    }
}
