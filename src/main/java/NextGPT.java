import java.util.Scanner;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class NextGPT {
    static String filePath = "./data/tasks.txt";
    static List<Task> todo_list = new ArrayList<>();
    private static void createTaskList() throws IOException{
        File dir = new File("./data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        } else {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] split = task.split(",");
                String taskType = split[0];
                Boolean isDone = split[1].equals("X");
                String desc = split[2];
                if (taskType.equals("T")) {
                    Todo todo = new Todo(desc);
                    todo_list.add(todo);
                    if (isDone) {
                        todo.mark();
                    }
                } else if (taskType.equals("D")) {
                    String by = split[3];

                    Deadline deadline = new Deadline(desc, by);
                    todo_list.add(deadline);
                    if (isDone) {
                        deadline.mark();
                    }
                } else {
                    String start = split[3];
                    String end = split[4];
                    Event event = new Event(desc, start, end);
                    todo_list.add(event);
                    if (isDone) {
                        event.mark();
                    }
                }

            }
            sc.close();
        }
    }


    private static void saveToList() throws IOException{
        FileWriter fw = new FileWriter(filePath);

        String text = "";

        for (Task task: todo_list) {
            if (task instanceof Event) {
                text += "E,";
                text += task.isDone()? "X,":" ,";
                text += task.getDescription() + ",";
                text += ((Event) task).getStart() + ",";
                text += ((Event) task).getEnd() + "\n";
            } else if (task instanceof Deadline) {
                text += "D,";
                text += task.isDone()? "X,":" ,";
                text += task.getDescription() + ",";
                text += ((Deadline) task).getBy() + "\n";
            } else {
                text += "T,";
                text += task.isDone()? "X,":" ,";
                text += task.getDescription();
                text += "\n";

            }
        }
        fw.write(text);
        fw.close();
    }

    public static void addToDo(String name) {
        Todo task = new Todo(name);
        todo_list.add(task);
        try {
            saveToList();

        } catch (IOException e) {
        }
        System.out.println("_______________________________________________________\n added: " +
                task + "\n Now you have " + todo_list.size() + " tasks in the list\n" +
                "_______________________________________________________\n");
    }
    private static void addDeadline(String name, String by) {
        Deadline deadline = new Deadline(name, by);
        todo_list.add(deadline);
        try {
            saveToList();
        } catch (IOException e) {

        }
        System.out.println("_______________________________________________________\n added: " +
                deadline + "\n Now you have " + todo_list.size() + " tasks in the list\n" +
                "_______________________________________________________\n");
    }

    private static void addEvent(String name, String start, String end) {
        Event event = new Event(name, start, end);
        todo_list.add(event);
        try {
            saveToList();
        } catch (IOException e) {

        }
        System.out.println("_______________________________________________________\n added: " +
                event + "\n Now you have " + todo_list.size() + " tasks in the list\n" +
                "_______________________________________________________\n");
    }

    private static void displayList(){
        System.out.println("_______________________________________________________\n");
        for (int i = 0; i <todo_list.size() ; i++) {
            System.out.println(i+1 + "." + " " + todo_list.get(i));
        }
        System.out.println("_______________________________________________________\n");
    }



    public static void main(String[] args) {

        String greeting = "_______________________________________________________\n" +
                "Hello! I'm NextGPT and I'll be your assistant chatbot.\n" +
                "What can I do for you today?\n" +
                "_______________________________________________________\n";

        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);

        try {
            createTaskList();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            try {
                    String input = sc.next();
                    if (input.equals("bye")) break;
                    else if (input.equals("list")) {
                        displayList();
                    } else if (input.equals("mark")) {
                        int index = sc.nextInt();
                        if (index > todo_list.size() || index <= 0)
                            throw new Exception("Task is not within the saved list! Please double check");
                        Task task = todo_list.get(index - 1);
                        task.mark();
                        try {
                            saveToList();
                        } catch (IOException e) {

                        }
                        System.out.println("_______________________________________________________\n " +
                                "Nice! I've marked this task as done:\n " + task +
                                "\n_______________________________________________________\n");
                    } else if (input.equals("unmark")) {
                        int index = sc.nextInt();
                        if (index > todo_list.size() || index <= 0)
                            throw new Exception("Task is not within the saved list! Please double check");
                        Task task = todo_list.get(index - 1);
                        task.unmark();
                        try {
                            saveToList();
                        } catch (IOException e) {

                        }
                        System.out.println("_______________________________________________________\n " +
                                "Ok, I've marked this task as not done yet:\n " + task +
                                "\n_______________________________________________________\n");
                    } else if (input.equals("deadline")) {

                        String line = sc.nextLine();
                        if (line.split("/by").length != 2) {
                            throw new Exception("The bot does not support the given format. Please give it in this format:\n" +
                                    "Deadline <description> /by <description>");
                        }
                        String name = line.split("/by")[0].trim();
                        String by = line.split("/by")[1].trim();

                        if (name.length() == 0 || by.length() == 0) {
                            throw new Exception(("Event names/to/from must not be empty please!"));
                        }
                        addDeadline(name, by);
                    } else if (input.equals("event")) {
                        String line = sc.nextLine();
                        String[] split = line.split("/from");
                        if (split.length != 2) {
                            throw new Exception("Please provide your event task in the following format:\n" +
                                    "Event <description> /from <description> /to <description>");
                        }
                        String name = split[0].trim();
                        String start = split[1].split("/to")[0].trim();
                        String end = split[1].split("/to")[1].trim();
                        if (name.length() == 0 || start.length() == 0 || end.length() == 0) {
                            throw new Exception(("Event names/start/end must not be empty please!"));
                        }

                        addEvent(name, start, end);
                    } else if (input.equals("todo")) {
                        String line = sc.nextLine().trim();
                        if (line.length() == 0) throw new Exception("Todo task cannot be empty!");
                        addToDo(line);
                    } else if (input.toLowerCase().equals("delete")) {
                        int index = sc.nextInt();
                        if (index > todo_list.size() || index <= 0)
                            throw new Exception("Task is not within the saved list! Please double check");
                        Task task = todo_list.remove(index - 1);
                        try {
                            saveToList();
                        } catch (IOException e) {

                        }
                        System.out.println("_______________________________________________________\n " +
                                "Noted. I've removed this task:\n " + task + "\n" +
                                "Now you have " + todo_list.size() + " tasks in the list." + "\n" +
                                "_______________________________________________________\n");
                    } else {
                        throw new Exception(":( I am unable to understand what that means! Please do try again in the available formats!");
                    }
                } catch (Exception e) {
                    System.out.println("_______________________________________________________\n "
                            + e.getMessage()
                            + "\n_______________________________________________________\n ");

                }
            }

            String exit = "_______________________________________________________\n" +
                    "Bye. Hope to see you soon!\n" +
                    "_______________________________________________________\n";
            System.out.println(exit);

    }


}
