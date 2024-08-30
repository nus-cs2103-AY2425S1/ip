
import java.sql.Array;
import java.time.LocalTime;
import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class
import java.lang.ArrayIndexOutOfBoundsException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner; // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class
import java.lang.ArrayIndexOutOfBoundsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tuesday {
    private static ArrayList<Task> tasksArray = new ArrayList<>();
    private static File myFile = new File("src/main/data/tuesday.txt");

    private static void print_line() {
        System.out.println("_______________________________");
    }

    private static void print_taskcount(String action) {
        Tuesday.print_line();
        System.out.println("Got it. I've " + action + " this task:\n  "
                + tasksArray.get(Task.count - 1).toString()
                + "\nNow you have " + Task.count + " task(s) in the list.");
        Tuesday.print_line();
    }
private static void msg_welcome() {
        Tuesday.print_line();
        System.out.println("Hello! I'm Tuesday, a randomly created bot.\n"
                + "What can I do for you?");
        Tuesday.print_line();
    }

    private static void msg_bye() {
        Tuesday.print_line();
        System.out.println("Bye bye. Hope to see you again soon!");
        Tuesday.print_line();
    }

    private static void msg_list() {
        Tuesday.print_line();
        System.out.println("Here are the tasks in your list:");
        for (int n = 0; n < Task.count; n++) {
            System.out.println((n + 1) + "." + tasksArray.get(n).toString());
        }
        Tuesday.print_line();
    }

    private static void comm_mark(int task_num, boolean state) {
        tasksArray.get(task_num - 1).changeDone(state);
        /*String[] dataLine;
        try{
            String line = Files.readAllLines(Paths.get("src/main/data/tuesday.txt")).get(task_num - 1);
            System.out.println(line);
            dataLine = line.split(" \\| ");

        }
        catch(IOException e){
            System.out.println(e);
        }*/
        String message = "";
        if (state)
            message = "Nice! I've marked this task as done: \n  ";
        else
            message = "OK, I've marked this task as not done yet: \n  ";

        message += Tuesday.tasksArray.get(task_num - 1).toString();

        Tuesday.print_line();
        System.out.println(message);
        Tuesday.print_line();
    }

    private static void comm_todo(String title) {
        ToDo taskItem = new ToDo(title, Tuesday.myFile);
        Tuesday.tasksArray.add(taskItem);

        print_taskcount("added");
    }

    private static void comm_deadline(String title, String by_msg) {
        Deadline deadlineItem = new Deadline(title, by_msg, Tuesday.myFile);
        Tuesday.tasksArray.add(deadlineItem);

        print_taskcount("added");
    }

    private static void comm_event(String title, String from_msg, String to_msg) {
        Event eventItem = new Event(title, from_msg, to_msg, Tuesday.myFile);
        Tuesday.tasksArray.add(eventItem);

        print_taskcount("added");
    }

    private static void comm_delete(int index) {
        Task.deleteTask();
        Tuesday.print_line();
        System.out.println("Got it. I've deleted this task:\n  "
                + tasksArray.get(index - 1).toString()
                + "\nNow you have " + Task.count + " task(s) in the list.");
        Tuesday.print_line();
        tasksArray.remove(index - 1);

        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/data/tuesday.txt"));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    if (i != (index-1)) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    } else {
                        line = br.readLine();
                        line = br.readLine();
                    }
                    i++;
                }
                String everything = sb.toString();
                //System.out.println(everything);
                FileWriter wr = new FileWriter(new File("src/main/data/tuesday.txt"), false);
                wr.write(everything);
                //flushing & closing the writer
                wr.flush();
                wr.close();
            } finally {
                br.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No file");
        } catch (IOException e) {
            System.out.println("Error: IOException");
        }
    }

    private static void check_datafile_existence(File myFile) {
        if (!myFile.exists()) {
            /*Tuesday.print_line();
            System.out.println("File created: " + myFile.getName());
            System.out.println("File created: " + myFile.getAbsolutePath());
            Tuesday.print_line();*/
        } else {
            /*Tuesday.print_line();
            System.out.println("File already exists: " + myFile.getAbsolutePath());
            Tuesday.print_line();*/
        }
    }

    public static void read_datafile() {
        try {
            Scanner myReader = new Scanner(Tuesday.myFile);
            try {
                String[] userInputArr;
                String line;

                while (myReader.hasNextLine()) {
                    line = myReader.nextLine();
                    userInputArr = line.split(" \\| ");
                    if (userInputArr[0].equals("T")){
                        ToDo taskItem = new ToDo(userInputArr[2], Tuesday.myFile, userInputArr[1].equals("1"));
                        Tuesday.tasksArray.add(taskItem);
                    } else if (userInputArr[0].equals("D")) {
                        Deadline deadlineItem = new Deadline(userInputArr[2],
                                userInputArr[3], Tuesday.myFile, userInputArr[1].equals("1"));
                        Tuesday.tasksArray.add(deadlineItem);
                    } else if (userInputArr[0].equals("E")) {
                        String[] userInputArrEvent = userInputArr[3].split(" ");
                        Event eventItem = new Event(userInputArr[2],
                                userInputArrEvent[0], userInputArrEvent[1], Tuesday.myFile, userInputArr[1].equals("1"));
                        Tuesday.tasksArray.add(eventItem);
                    }
                }
            } finally {
                myReader.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred. " + e);
        } catch (Exception e) {
            System.out.println("An error occurred. " + e);
        }
    }
    public static void main(String[] args) {
        msg_welcome();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String userInput = "";
        String[] userInputArr;

        check_datafile_existence(myFile);

        read_datafile();

        while (true) {
            userInput = myObj.nextLine(); // Read user input
            userInputArr = userInput.split(" ", 2);
            if (userInputArr[0].equals("bye"))
                break;

            if (userInput.equals("list")) {
                msg_list();
            } else if (userInputArr[0].equals("mark")) {
                try {
                    comm_mark(Integer.parseInt(userInputArr[1]), true);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Hey there! The 'mark' function cannot be empty");
                } catch (Exception e) {
                    System.out.println("Hey there! Can you try typing differently ");
                }
            } else if (userInputArr[0].equals("unmark")) {
                try {
                    comm_mark(Integer.parseInt(userInputArr[1]), false);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Hey there! The 'unmark' function cannot be empty");
                } catch (Exception e) {
                    System.out.println("Hey there! Can you try typing differently ");
                }
            } else if (userInputArr[0].equals("todo")) {
                try {
                    comm_todo(userInputArr[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Hey there! The 'todo' function cannot be empty");
                } catch (Exception e) {
                    System.out.println("Hey there! Can you try typing differently ");
                }
            } else if (userInputArr[0].equals("deadline")) {
                try {
                    String[] msg_split_by = userInputArr[1].split("/by ", 2);
                    comm_deadline(msg_split_by[0], msg_split_by[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Hey there! The 'deadline' function cannot be empty");
                } catch(Exception e) {
                    System.out.println("Hey there! Can you try typing differently " + e);

                }
            } else if (userInputArr[0].equals("event")) {
                try {
                    String[] msg_split_from = userInputArr[1].split("/from ", 2);
                    String[] msg_split_to = msg_split_from[1].split(" /to ", 2);
                    comm_event(msg_split_from[0], msg_split_to[0], msg_split_to[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Hey there! The 'event' function cannot be empty");
                } catch (Exception e) {
                    System.out.println("Hey there! Can you try typing differently ");
                }
            } else if (userInputArr[0].equals("delete")) {
                try {
                    comm_delete(Integer.parseInt(userInputArr[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Hey there! The 'event' function cannot be empty");
                } catch (Exception e) {
                    System.out.println("Hey there! Can you try typing differently " + e);
                }
            } else {
                Tuesday.print_line();
                System.out.println("ERROR: Hey there!! I do not know what you mean. Can you type it out differently?");
                Tuesday.print_line();
            }
        }
        msg_bye();
    }
}
