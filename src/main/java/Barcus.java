import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;

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
//        Task[] tasks = new Task[100];
        ArrayList<Task> tasks = downloadSaved();
        int curr = tasks.size();

        // start
        System.out.println(intro);

        boolean exit = false;

        while (!exit) {
            String reply = receive(scanner);
            String[] words = reply.split(" ");

            if (reply.equals("bye")) {
                exit = true;
                talk(goodbye);
                uploadSaved(tasks);
            } else if (reply.equals("list")) {
                // for add list
                talk("Okie, here are your tasks!");
                for (int i = 0; i < curr; i++) {
                    System.out.println(String.valueOf(i+1) + ". " + tasks.get(i).toString());
                }
            } else if (words[0].equals("unmark")) {
                if (words.length != 2) {
                    talk("Uh oh, please have a number after 'unmark'");
                } else {
                    int pos = Integer.parseInt(words[1]);
                    if (pos > 0 && pos <= curr) {
                        tasks.get(pos - 1).unmarkDone();
                        talk("No prob, have marked as undone: " + tasks.get(pos - 1));
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
                        tasks.get(pos - 1).markDone();
                        talk("Good job! Have marked as done: " + tasks.get(pos - 1));
                    } else {
                        talk("Uh oh, please choose a number between 1 and " + curr);
                    }
                }

            } else if (words[0].equals("todo")) {
                if (words.length < 2) {
                    talk("Uh oh, please include a description of the todo");
                } else {
                    tasks.add(new Todo(String.join(" ", Arrays.copyOfRange(words, 1, words.length))));
                    curr++;
                    talk("Added task: " + tasks.get(curr - 1) + "\nThere are " + curr + " task(s) in the list.");
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
                    tasks.add(new Deadline(
                            String.join(" ", Arrays.copyOfRange(words, 1, byI)),
                            String.join(" ", Arrays.copyOfRange(words, byI + 1, words.length))));
                    curr++;
                    talk("Added task: " + tasks.get(curr - 1) + "\nThere are " + curr + " task(s) in the list.");
                }
            } else if (words[0].equals("event")) {
                List<String> wordsList = Arrays.asList(words);
                if (!wordsList.contains("/from") || !wordsList.contains("/to")) {
                    talk("Uh oh, please include '/from' and '/to' as well as dates after each of those words");
                } else {
                    int fromI = wordsList.indexOf("/from");
                    int toI = wordsList.indexOf("/to");
                    tasks.add(new Event(
                            String.join(" ", Arrays.copyOfRange(words, 1, fromI)),
                            String.join(" ", Arrays.copyOfRange(words, fromI + 1, toI)),
                            String.join(" ", Arrays.copyOfRange(words, toI + 1, words.length))
                    ));
                    curr++;
                    talk("Added task: " + tasks.get(curr - 1) + "\nThere are " + curr + " task(s) in the list.");

                }
            } else if (words[0].equals("delete")) {
                if (words.length != 2) {
                    talk("Uh oh, please have a number after 'delete'");
                } else {
                    int pos = Integer.parseInt(words[1]);
                    if (pos > 0 && pos <= curr) {
//                        tasks.get(pos - 1).markDone();
//                        talk("Good job! Have marked as done: " + tasks.get(pos - 1));
                        Task temp = tasks.remove(pos - 1);
                        curr--;
                        talk("Removed task: " + temp + "\nThere are " + curr + " task(s) in the list.");
                    } else {
                        talk("Uh oh, please choose a number between 1 and " + curr);
                    }
                }
            } else {
                // for echo
//                talk(reply);

                // create new task
//                tasks[curr] = new Task(reply);
//                curr++;
//                talk("Added task '" + reply + "'");

                // unknown command
                talk("Uh oh, command not found D:");
            }
        }

        // current problems:

    }

    private static void talk(String s) {
        System.out.println("Barcus: " + s);
    }

    private static String receive(Scanner scanner) {
        System.out.print("You: ");
        return scanner.nextLine();
    }

    private static ArrayList<Task> downloadSaved() {
        File file = new File("./data/savedTasks.txt");
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);

            }
        }

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
//                String line = s.next();
                String[] lineSplit = s.nextLine().split(" \\| ");

                if (lineSplit[0].equals("T")) {
                    tasks.add(new Todo(lineSplit[2], lineSplit[1].equals("1")));
                } else if (lineSplit[0].equals("D")) {
                    tasks.add(new Deadline(
                            lineSplit[2],
                            lineSplit[1].equals("1"),
                            lineSplit[3]
                    ));
                } else if (lineSplit[0].equals("E")) {
                    tasks.add(new Event(
                            lineSplit[2],
                            lineSplit[1].equals("1"),
                            lineSplit[3],
                            lineSplit[4]
                    ));
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return tasks;
    }


    private static void uploadSaved(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter("./data/savedTasks.txt");
             BufferedWriter bfWriter = new BufferedWriter(writer)) {
            for (Task task: tasks) {
                bfWriter.write(task.convertToSavedString() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

