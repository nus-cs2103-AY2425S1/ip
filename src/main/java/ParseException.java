public class ParseException extends  MoniqueException{
    public ParseException() {
        super("Error parsing, missing arguments");
    }
    @Override
    public void advice(){
        System.out.println("Parsing Exception. Please re-enter commands using the correct template.");
        System.out.println("To find out command templates, please enter '/commands' ");
    }
}
