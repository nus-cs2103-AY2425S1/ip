public class TalkieUnknownCommandException extends TalkieException {

    protected String command;

    public TalkieUnknownCommandException(String command) {
        this.command = command;
    }

   @Override
   public String toString() {
       return "-------------------------------------------------------------------\n"
               + super.toString() + " I do not recognise the command, " + this.command +  ".\n"
               + "Please try again! :D\n"
               + "-------------------------------------------------------------------\n";
   }
}
