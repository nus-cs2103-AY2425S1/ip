package com.chatbaby;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    public DeleteCommand(String commandBody) {
        super(commandBody);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException {
        try {
            int index = Integer.parseInt(commandBody.substring(7).trim()) - 1;
            if (index >= 0 && index < tasks.size()) {
                Task removedTask = tasks.deleteTask(index);
                System.out.println("Noted. I've removed this task:\n"
                        + removedTask.toString() + "\n"
                        + "Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new ChatBabyException("Oh no!!! The task index is invalid.");
            }
        } catch (NumberFormatException e) {
            throw new ChatBabyException("Oh no!!! The task index is invalid.");
        }
    }
}