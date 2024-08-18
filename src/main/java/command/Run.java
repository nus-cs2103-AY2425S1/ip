package command;

import java.lang.Runnable;

public class Run extends Command {
    private final Runnable runnable;

    public Run(String description, Runnable runnable) {
        super(description);
        this.runnable = runnable;
    }

    @Override
    public void execute() {
        runnable.run();
        System.out.println(this);
    }
}
