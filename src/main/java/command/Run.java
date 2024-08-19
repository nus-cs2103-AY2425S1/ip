package command;

import java.lang.Runnable;
import java.util.function.Supplier;

public class Run extends Command {
    private final Runnable runnable;

    public Run(Runnable runnable, Supplier<String> messageSupplier) {
        super(messageSupplier);
        this.runnable = runnable;
    }

    @Override
    public void execute() {
        runnable.run();
        System.out.println(this);
    }
}
