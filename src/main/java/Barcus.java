import java.util.Scanner;

public class Barcus {
    public static void main(String[] args) {
        // fixed dialogue
        String intro =
                "╭━━╮\n" +
                "┃╭╮┃\n" +
                "┃╰╯╰┳━━┳━┳━━┳╮╭┳━━╮\n" +
                "┃╭━╮┃╭╮┃╭┫╭━┫┃┃┃━━┫\n" +
                "┃╰━╯┃╭╮┃┃┃╰━┫╰╯┣━━┃\n" +
                "╰━━━┻╯╰┻╯╰━━┻━━┻━━╯\n" +
                "Beep bop! Hello I am Barcus, ready to be of assistance!\n" +
                "Write 'bye' to leave!\n";
        String goodbye = "Alright, good talk!\n";

        // scanner object
        Scanner scanner = new Scanner(System.in);

        // list to save info
//        String[] tasks = new String[100];
        Task[] tasks = new Task[100];
        int curr = 0;

        // start
        System.out.println(intro);

        boolean exit = false;

        while (!exit) {
            String reply = receive(scanner);
            String[] words = reply.split(" ");

            if (reply.equals("bye")) {
                exit = true;
                talk(goodbye);
            } else if (reply.equals("list")) {
                // for add list
                talk("Okie, here are your tasks!");
                for (int i = 0; i < curr; i++) {
                    System.out.println(String.valueOf(i+1) + ". " + tasks[i].toString());
                }
            } else if (words[0].equals("unmark")) {
                int pos = Integer.parseInt(words[1]);
                tasks[pos - 1].unmarkDone();
                talk("No prob, have marked as undone: " + tasks[pos - 1]);
            } else if (words[0].equals("mark")) {
                int pos = Integer.parseInt(words[1]);
                tasks[pos - 1].markDone();
                talk("Good job! Have marked as done: " + tasks[pos - 1]);
            } else {
                // for echo
//                talk(reply);

                // create new task
                tasks[curr] = new Task(reply);
                curr++;
                talk("Added task '" + reply + "'");
            }
        }

        // current problems:
        // - typing mark papers as a task will cause error
        // - trying to mark or unmark something out of index has error

    }

    private static void talk(String s) {
        System.out.println("Barcus: " + s);
    }

    private static String receive(Scanner scanner) {
        System.out.print("You: ");
        return scanner.nextLine();
    }
}

