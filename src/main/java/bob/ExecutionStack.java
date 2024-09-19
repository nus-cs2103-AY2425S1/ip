package bob;

import java.util.Stack;

import commands.Command;

/**
 * Manages a stack of commands to track the execution history.
 * This class allows for pushing new commands to the stack and popping the last executed command.
 */
public class ExecutionStack {
    private final Stack<Command> commandStack = new Stack<>();

    /**
     * Removes and returns the most recently added command from the stack.
     *
     * @return the most recently added {@code Command} from the stack.
     */
    public Command pop() {
        return this.commandStack.pop();
    }

    /**
     * Adds a new command to the top of the stack.
     *
     * @param command the {@code Command} to be added to the stack.
     */
    public void push(Command command) {
        this.commandStack.push(command);
    }

    /**
     * Checks if the stack is empty, i.e., if there are no commands stored.
     *
     * @return {@code true} if the stack is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return this.commandStack.isEmpty();
    }
}
