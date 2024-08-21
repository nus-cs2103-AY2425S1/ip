package processor;

import exceptions.bye.ByeInvalidArgsException;
import response.Response;

import java.util.List;

public class Exit {
  public static Response process(String prompt) throws ByeInvalidArgsException {
    if (prompt.length() != 3) throw new ByeInvalidArgsException();
    return new Response(List.of("Good Bye! See you soon..."), true);
  }
}
