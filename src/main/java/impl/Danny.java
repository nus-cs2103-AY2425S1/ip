package impl;

import exceptions.InvalidCommandException;
import impl.interfaces.Deadlines;
import impl.interfaces.Events;
import impl.interfaces.Task;
import impl.interfaces.ToDos;
import impl.MessageParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Danny {
    static String cat = """
                  |\\      _,,,---,,_
            ZZZzz /,`.-'`'    -.  ;-;;,_
                 |,4-  ) )-,_. ,\\ (  `'-'
                '---''(_/--'  `-'\\_)
            """;
    static String seperator = "____________________________________________________________";
    List<Task> arr = new ArrayList<Task>(); // Array to keep all the Tasks
    public Danny() {
        System.out.println(seperator);
        System.out.println("Hello! I'm Danny!");
        System.out.println(cat);
        System.out.println("What can I do for you?");
        System.out.println(seperator);
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();
        String text;

        while(!in.equalsIgnoreCase("bye")){
            System.out.println(seperator);
            MessageParser.Command cmd = MessageParser.Command.Error;
            try {
                cmd = MessageParser.checkCommand(in, arr.size());
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                cmd = MessageParser.Command.Error;
            }

            switch (cmd){
                case List:
                    handleList();
                    break;
                case Todo:
                    handleToDo(in);
                    break;
                case Deadline:
                    handleDeadline(in);
                    break;
                case Event:
                    handleEvent(in);
                    break;
                case Mark:
                    handleMark(in);
                    break;
                case Unmark:
                    handleUnmark(in);
                    break;
                case Delete:
                    handleDelete(in);
                    break;
                case Error:
                    System.out.println("Error! Please try again :(");
                    break;
                case Unknown:
                    // Fall through as default
                default:
                    System.out.println("Unknown Command Detected!");
                    break;
            }
            System.out.println(seperator);
            in = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(seperator);
    }

    private void handleList(){
        System.out.println("These are all your unfinished tasks:");
        String text;
        for (int i = 0; i < arr.size(); i++) {
            text = (i+1) + "." + arr.get(i).toString();
            System.out.println(text);
        }
    }

    private void handleToDo(String in){
        String description = in.substring(4);
        Task add = new ToDos(description);
        arr.add(add);
        System.out.println("added: " + add.toString());
    }
    private void handleDeadline(String in){
        String[] deadlineSplit = in.split("/by ");
        String description = deadlineSplit[0].substring(8);
        String by = deadlineSplit[1];
        Task add = new Deadlines(description,by);
        arr.add(add);
        System.out.println("added: " + add.toString());
    }

    private void handleEvent(String in){
        String[] eventSplit = in.split("/from ");
        String description = eventSplit[0].substring(5);
        String from = eventSplit[1].split(" /to ")[0];
        String by = eventSplit[1].split("/to ")[1];
        Task add = new Events(description,by,from);
        arr.add(add);
        System.out.println("added: " + add.toString());
    }

    private void handleMark(String in){
        int i = Integer.parseInt(in.split(" ")[1])-1;
        System.out.println("I have marked this task as done");
        arr.get(i).setDone();
        String text = (i+1) + "." + arr.get(i).toString();
        System.out.println(text);
    }

    private void handleUnmark(String in){
        int i = Integer.parseInt(in.split(" ")[1])-1;
        arr.get(i).setUnDone();
        System.out.println("I have marked this task as undone");
        String text = (i+1) + "." + arr.get(i).toString();
        System.out.println(text);
    }

    private void handleDelete(String in){
        int i = Integer.parseInt(in.split(" ")[1])-1;
        System.out.println("I have removed this task from the list");
        String text = (i+1) + "." + arr.get(i).toString();
        System.out.println(text);
        arr.remove(i);
    }

}
