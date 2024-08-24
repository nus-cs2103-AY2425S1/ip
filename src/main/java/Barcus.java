import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Barcus {
    public static void main(String[] args) {
        // fixed dialogue
        String intro =
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
                if (words.length != 2) {
                    talk("Uh oh, please have a number after 'unmark'");
                } else {
                    int pos = Integer.parseInt(words[1]);
                    if (pos > 0 && pos <= curr) {
                        tasks[pos - 1].unmarkDone();
                        talk("No prob, have marked as undone: " + tasks[pos - 1]);
                    } else {
                        talk("Uh oh, please choose a number between 1 and " + curr);
                    }
                }

            } else if (words[0].equals("mark")) {
                if (words.length != 2) {
                    talk("Uh oh, please have a number after 'mark'");
                } else {
                    int pos = Integer.parseInt(words[1]);
                    if (pos > 0 && pos <= curr) {
                        tasks[pos - 1].markDone();
                        talk("Good job! Have marked as done: " + tasks[pos - 1]);
                    } else {
                        talk("Uh oh, please choose a number between 1 and " + curr);
                    }
                }

            } else if (words[0].equals("todo")) {
                if (words.length < 2) {
                    talk("Uh oh, please include a description of the todo");
                } else {
                    tasks[curr] = new Todo(String.join(" ", Arrays.copyOfRange(words, 1, words.length)));
                    curr++;
                    talk("Added task: " + tasks[curr - 1] + "\nThere are " + curr + " task(s) in the list.");
                }

            } else if (words[0].equals("deadline")) {
                List<String> wordsList = Arrays.asList(words);
                if (!wordsList.contains("/by")) {
                    talk("Uh oh, please include '/by' and deadline after it");
                } else {
                    int byI = wordsList.indexOf("/by");
//
//                    String[] temp = reply.split(" /by ");
//                    String by = temp[1];
                    tasks[curr] = new Deadline(
                            String.join(" ", Arrays.copyOfRange(words, 1, byI)),
                            String.join(" ", Arrays.copyOfRange(words, byI + 1, words.length)));
                    curr++;
                    talk("Added task: " + tasks[curr - 1] + "\nThere are " + curr + " task(s) in the list.");
                }
            } else if (words[0].equals("event")) {
                List<String> wordsList = Arrays.asList(words);
                if (!wordsList.contains("/from") || !wordsList.contains("/to")) {
                    talk("Uh oh, please include '/from' and '/to' as well as dates after each of those words");
                } else {
                    int fromI = wordsList.indexOf("/from");
                    int toI = wordsList.indexOf("/to");
                    tasks[curr] = new Event(
                            String.join(" ", Arrays.copyOfRange(words, 1, fromI)),
                            String.join(" ", Arrays.copyOfRange(words, fromI + 1, toI)),
                            String.join(" ", Arrays.copyOfRange(words, toI + 1, words.length))
                    );
                    curr++;
                    talk("Added task: " + tasks[curr - 1] + "\nThere are " + curr + " task(s) in the list.");

                }
            } else {
                // for echo
//                talk(reply);

                // create new task
//                tasks[curr] = new Task(reply);
//                curr++;
//                talk("Added task '" + reply + "'");

                // unknown command
                talk("Error, command not found D:");
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

