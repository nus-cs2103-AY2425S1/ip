public class GuideCommand extends Command{
    public GuideCommand(){
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MarkException{
        System.out.println(GuideText.GUIDE);
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
