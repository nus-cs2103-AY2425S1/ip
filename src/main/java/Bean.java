import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

public class Bean {
    private ArrayList<Task> taskList;
    private String filePath = "src\\main\\java\\data\\tasks.txt";
    private int pointer = 0;

    public static void main(String[] args) {
        Bean bean = new Bean();
        bean.run();
    }

    public Bean() {
        taskList = new ArrayList<>();
        readStorage();
    }

    public void run(){
        greeting();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String response = scanner.nextLine();
            String[] splited = response.split(" ",2);
            if (splited[0].equals("todo") || splited[0].equals("event") || splited[0].equals("deadline")) {
                Task current = null;
                try {
                    switch (splited[0]) {
                        case "todo":
                            current = new Todo(splited[1]);
                            break;
                        case "event":
                            current = new Event(splited[1]);
                            break;
                        case "deadline":
                            current = new Deadline(splited[1]);
                            break;
                    }
                    taskList.add(current);
                    pointer++;
                    String output = "________________________________\n" + "Got it. I've added this task:";
                    System.out.println(output);
                    System.out.println(current.getString());
                    output = "Now you have " + String.valueOf(pointer) + " tasks in the list.\n" + "________________________________";
                    System.out.println(output);
                } catch (DukeException e) {
                    System.out.println("________________________________");
                    System.out.println(e.getMessage() + "________________________________");
                }
            } else if (response.equals("list")) {
                list();
            } else if (splited[0].equals("mark")) {
                int index = Integer.parseInt(splited[1]) - 1;
                mark(index);
            } else if (splited[0].equals("unmark")) {
                int index = Integer.parseInt(splited[1]) - 1;
                unmark(index);
            } else if (splited[0].equals("delete")) {
                int index = Integer.parseInt(splited[1]) - 1;
                delete(index);
            } else if (splited[0].equals("bye")){
                writeStorage();
                System.out.println("________________________________\nGoodbye!!!!!\n________________________________");
                break;
            } else {
                try {
                    throw new DukeException("I dont understand what you are trying to say :(");
                } catch (DukeException e) {
                    System.out.println("________________________________");
                    System.out.println(e.getMessage() + "\n________________________________");
                }
            }
        }
    }
    public void greeting() {
        String greeting = "________________________________\n"
                + "Hello! I'm Bean\n"
                + "What can i do for you?\n"
                +"________________________________";
        String byeMsg =
                "________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "________________________________";
        System.out.println(greeting);
    }

    public void readStorage() {
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String response = s.nextLine();
                String[] splited = response.split(" ", 3);
                if (splited[1].equals("todo") || splited[1].equals("event") || splited[1].equals("deadline")) {
                    Task current = null;
                    try {
                        switch (splited[1]) {
                            case "todo":
                                current = new Todo(splited[2]);
                                break;
                            case "event":
                                current = new Event(splited[2]);
                                break;
                            case "deadline":
                                current = new Deadline(splited[2]);
                                break;
                        }
                        if ("1".equals(splited[0])) {
                            current.mark();
                        }
                        taskList.add(current);
                        pointer++;
                    } catch (DukeException e) {
                        System.out.println("________________________________");
                        System.out.println(e.getMessage() + "________________________________");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void writeStorage() {
        try {
            FileWriter fw = new FileWriter(filePath,false);
            for (int i = 0; i < pointer; i++) {
                String output = "";
                Task curr = taskList.get(i);
                if (curr.isDone()) {
                    output += "1 ";
                } else {
                    output += "0 ";
                }
                String substring = curr.getString().split("] ",2)[1];
                String type = (curr.getString().split("]")[0]);
                switch (type) {
                    case "[T":
                        output += "todo ";
                        output += substring + '\n';
                        fw.write(output);
                        break;
                    case "[E":
                        output += "event ";
                        substring = substring.replace("(from:", "/from").replace("to:", "/to");
                        substring = substring.substring(0, substring.length() - 1) + "\n";
                        output += substring;
                        fw.write(output);
                        break;
                    case "[D":
                        output += "deadline ";
                        substring = substring.replace("(by:", "/by");
                        substring = substring.substring(0, substring.length() - 1) + "\n";
                        output += substring;
                        fw.write(output);
                        break;
                }
            }
            fw.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void list() {
        System.out.println("________________________________");
        for (int i = 0; i < pointer; i++) {
            String output = String.valueOf(i + 1) + ". " + taskList.get(i).getString();
            System.out.println(output);
        }
        System.out.println("________________________________");
    }

    public void mark(int index) {
        try {
            if (index < 0 || index > pointer) {
                throw new DukeException("Invalid position!");
            }
            Task curr = taskList.get(index);
            if (curr.isDone) {
                throw new DukeException("It is already marked!");
            }
            String msg = curr.mark();
            System.out.println("________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(msg);
            System.out.println("________________________________");
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
        }
    }

    public void unmark(int index) {
        try {
            if (index < 0 || index > pointer) {
                throw new DukeException("Invalid position!");
            }
            Task curr = taskList.get(index);
            if (!curr.isDone) {
                throw new DukeException("It is already unmarked!");
            }
            String msg = curr.mark();
            System.out.println("________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(msg);
            System.out.println("________________________________");
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
        }
    }
    public void delete(int index) {
        try {
            if (index < 0 || index > pointer) {
                throw new DukeException("Invalid position!");
            }
            System.out.println("________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskList.remove(index).getString());
            pointer--;
            System.out.println( "Now you have " + String.valueOf(pointer) + " tasks in the list.\n" + "________________________________");
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
        }
    }
}
