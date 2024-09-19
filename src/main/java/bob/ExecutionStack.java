package bob;

import commands.Command;

import java.util.Stack;

public class ExecutionStack {
    private final Stack<Command> commandStack= new Stack<>();

    public Command pop() {
        return this.commandStack.pop();
    }

    public void push(Command command) {
        this.commandStack.push(command);
    }

    public boolean isEmpty() {
        return this.commandStack.isEmpty();
    }
}
