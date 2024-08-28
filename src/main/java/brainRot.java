import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
public class brainRot {

    static String home = System.getProperty("user.home");


    public static void main(String[] args) throws IOException {

        //creating an arraylist
        File myData = new File(home + "/ip/data/brainRot.txt");
        BufferedReader dataR = new BufferedReader(new FileReader(myData));


        String line;
        ArrayList<Task> arr = new ArrayList<>();

        while ((line = dataR.readLine()) != null) {
            String[] dataFromDisk = line.split("/" ,4);
            char eventType = dataFromDisk[0].charAt(1);

            switch(eventType) {
                case('T') -> {
                    ToDo T = new ToDo(dataFromDisk[1]);
                    if(dataFromDisk[1].equals("[X]")) {
                        T.isDone = true;
                    }
                    arr.add(T);
                    System.out.println("retrieved from data!");

                }
                case('D') -> {
                    Deadline D = new Deadline(dataFromDisk[1], dataFromDisk[2]);
                    if(dataFromDisk[1].equals("[X]")) {
                        D.isDone = true;
                    }
                    arr.add(D);

                }
                case('E') -> {
                    Event E = new Event(dataFromDisk[1], dataFromDisk[2], dataFromDisk[3]);
                    if(dataFromDisk[1].equals("[X]")) {
                        E.isDone = true;
                    }
                    arr.add(E);

                }
            }

        }

            Scanner reader = new Scanner(System.in);

        String greeting =
                "____________________________________________________________\n"
                + "Hello! I'm fanumTaxRizzlerOhioSigmaLooksmaxxer\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";

        String goodBye = "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";



        System.out.println(greeting);

        //logic to check if "bye" or "list" has been said
        while(true) {
            String answer = reader.nextLine();

            try {
                if (answer.equals("list")) {
                    System.out.println("____________________________________________________________\n"
                            + "Here are the tasks in your list:");

                    for (int i = 0; i < arr.size(); i++) {
                        System.out.println((i+1) + "." + arr.get(i).toString());
                    }
                    System.out.println("____________________________________________________________\n");


                } else if (answer.equals("bye")) {

                    System.out.println(goodBye);
                    break;

                }else if (answer.startsWith("unmark")) {

                    int index = answer.charAt(7) - 48;
                    arr.get(index).unmark();
                    saveTasks(myData, arr);

                } else if (answer.startsWith("mark")) {

                    int index = answer.charAt(5) - 48;
                    arr.get(index).mark();
                    saveTasks(myData, arr);

                } else if (answer.startsWith("delete")) {
                        int index = answer.charAt(7) - 48;
                        arr.remove(index-1);
                        saveTasks(myData, arr);



                } else {
                    addTask(answer, arr);
                    saveTasks(myData, arr);
                }

                System.out.println("Now you have " + (arr.size()) + " tasks in the list.\n"
                        + "____________________________________________________________");

            } catch (UnknownCommandException e) {
                // Catch the custom exception and print the desired message
                System.out.println("""
                        ____________________________________________________________
                        OOPS!!! I'm sorry, but I don't know what that means :-(
                        ____________________________________________________________""");

            } catch (UnknownActivityException e) {
                System.out.println("""
                        ____________________________________________________________
                         OOPS!!! The description of an activity cannot be empty.
                        ____________________________________________________________""");


            }
        }
        reader.close();

    }

    private static void addTask(String answer, ArrayList<Task> arr) throws IOException, UnknownCommandException, UnknownActivityException {
        String[] commands = answer.split("/", 2);
        String activity = commands[0];

        File myData = new File(home + "/ip/data/brainRot.txt");

        try (FileWriter dataW = new FileWriter(myData, true)) {

            if (activity.isEmpty()) {
                throw new UnknownActivityException("Unknown command: " + answer);
            }


            if (answer.startsWith("todo")) {

                activity = activity.substring(5).trim();

                dataW.write("[T][ ]/" + activity + "\n");
                arr.add(new ToDo(activity));


                System.out.println("____________________________________________________________\n"
                        + "Got it. I've added this task:\n"
                        + arr.get(arr.size() - 1).toString() + "\n");


            } else if (answer.startsWith("deadline")) {

                String end = commands[1].substring(3).trim();
                activity = activity.substring(9).trim();

                dataW.write("[D][ ]/" + activity + "/" + end + "\n");

                arr.add(new Deadline(activity, end));
                System.out.println("____________________________________________________________\n"
                        + "Got it. I've added this task:\n"
                        + arr.get(arr.size() - 1).toString() + "\n");


            } else if (answer.startsWith("event")) {

                String[] eventTimes = commands[1].split(" /to ");
                String start = eventTimes[0].substring(5).trim();
                String end = eventTimes[1].trim();
                activity = activity.substring(6).trim();

                dataW.write("[E][ ]/" + activity + "/" + start + "/" + end + "\n");

                arr.add(new Event(activity, start, end));
                System.out.println("____________________________________________________________\n"
                        + "Got it. I've added this task:\n"
                        + arr.get(arr.size() - 1).toString() + "\n");


            }
        }
    }
    // Save tasks to the file
    private static void saveTasks(File myData, ArrayList<Task> arr) throws IOException {
        FileWriter dataW = new FileWriter(myData);

        for (Task task : arr) {
            dataW.write(task.toFileString() + "\n");
        }

        dataW.close();
    }

}
