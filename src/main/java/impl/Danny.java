package impl;

import java.util.Scanner;

public class Danny {
    private TaskList list; // Array to keep all the Tasks
    Ui ui;
    Parser parser;
    public Danny() {
        list = new TaskList();
        parser = new Parser(list);
        ui = new Ui(parser);
    }
    public TaskList run(){
        Scanner input = new Scanner(System.in);
        String in = input.nextLine();

        while(!in.equalsIgnoreCase("bye")){
            ui.running(in);
            in = input.nextLine();
        }
        ui.end();
        return list;
    }

    public void parseString(String in){
        parser.handleInput(in);
    }

    public void setLastDone(){
        list.setLastDone();
    }

    public String saveTasks(){
       return list.saveTasks();
    }
}
