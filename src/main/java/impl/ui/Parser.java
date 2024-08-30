package impl.ui;

import exceptions.InvalidCommandException;
import impl.storage.TaskList;
import impl.interfaces.Deadlines;
import impl.interfaces.Events;
import impl.interfaces.Task;
import impl.interfaces.ToDos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    enum Command{
        List,
        Todo,
        Deadline,
        Event,
        Mark,
        Unmark,
        Delete,
        Error,
        Unknown
    }
    TaskList list;
    /**
     * Initialises Parser.
     *
     * @param list TaskList used to store tasks.
     */
    public Parser(TaskList list){
        this.list = list;
    }

    /**
     * Handles all String input by the user.
     * Creates/mark/unmark/delete Tasks based on input
     *
     * @param in Input String.
     */
    public void handleInput(String in){
        Command cmd = Command.Error;
        try {
            cmd = Parser.checkCommand(in, list.size());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            cmd = Parser.Command.Error;
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
    }
    private void handleList(){
        System.out.println("These are all your unfinished tasks:");
        String text;
        for (int i = 0; i < list.size(); i++) {
            text = (i+1) + "." + list.get(i).toString();
            System.out.println(text);
        }
    }

    private void handleToDo(String in){
        String description = in.substring(5);
        Task add = new ToDos(description);
        list.add(add);
        System.out.println("added: " + add.toString());
    }
    private void handleDeadline(String in){
        String[] deadlineSplit = in.split(" /by ");
        String description = deadlineSplit[0].substring(9);
        String by = handleDate(deadlineSplit[1]);
        Task add = new Deadlines(description,by);
        list.add(add);
        System.out.println("added: " + add.toString());
    }

    private void handleEvent(String in){
        String[] eventSplit = in.split(" /from ");
        String description = eventSplit[0].substring(6);
        String from = handleDate(eventSplit[1].split(" /to ")[0]);
        String by = handleDate(eventSplit[1].split("/to ")[1]);
        Task add = new Events(description,by,from);
        list.add(add);
        System.out.println("added: " + add.toString());
    }

    private void handleMark(String in){
        int i = Integer.parseInt(in.split(" ")[1])-1;
        System.out.println("I have marked this task as done");
        list.get(i).setDone();
        String text = (i+1) + "." + list.get(i).toString();
        System.out.println(text);
    }

    private void handleUnmark(String in){
        int i = Integer.parseInt(in.split(" ")[1])-1;
        list.get(i).setUnDone();
        System.out.println("I have marked this task as undone");
        String text = (i+1) + "." + list.get(i).toString();
        System.out.println(text);
    }

    private void handleDelete(String in){
        int i = Integer.parseInt(in.split(" ")[1])-1;
        System.out.println("I have removed this task from the list");
        String text = (i+1) + "." + list.get(i).toString();
        System.out.println(text);
        list.remove(i);
    }

    public static Command checkCommand(String in, int size) throws InvalidCommandException{
        String test = in.toLowerCase().split(" ")[0];
        switch(test){
            case "list":
                return Command.List;
            case "todo":
                checkTodo(in);
                return Command.Todo;
            case "deadline":
                checkDeadline(in);
                return Command.Deadline;
            case "event":
                checkEvent(in);
                return Command.Event;
            case "mark":
                handleIntInput(in,size);
                return Command.Mark;
            case "unmark":
                handleIntInput(in,size);
                return Command.Unmark;
            case "delete":
                handleIntInput(in,size);
                return Command.Delete;
            default:
                return Command.Unknown;
        }
    }

    private static void handleIntInput(String in, int size) throws InvalidCommandException{
        int i = Integer.parseInt(in.split(" ")[1])-1;
        if(i>=size){
            throw new InvalidCommandException("Out of index");
        }
    }
    private static void checkTodo(String in) throws InvalidCommandException{
        if(in.length()<=4){
            throw new InvalidCommandException("Please Enter Something for Todo!");
        }
    }
    private static void checkDeadline(String in) throws InvalidCommandException{
        if(in.split("/by ").length < 2){
            throw new InvalidCommandException("Wrong Syntax! Use /by for Deadlines!");
        }
    }
    private static void checkEvent(String in) throws InvalidCommandException{
        String[] eventSplit = in.split("/from ");
        if(eventSplit.length < 2 || eventSplit[1].split("/to ").length<2){
            throw new InvalidCommandException("Wrong Syntax! Use /from and /to for Events!");
        }
    }
    private String handleDate(String date){
        try{
            LocalDate d1 = LocalDate.parse(date);
            date = d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        catch(Exception e){
            System.out.println("Not in accepted date format, saving it as is");
        }
        return date;
    }


}
