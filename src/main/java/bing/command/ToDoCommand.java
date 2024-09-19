package bing.command;

import bing.task.ToDo;

public class ToDoCommand extends AddCommand {
    public ToDoCommand(String info) {
        super(new ToDo(info));
    }
}
