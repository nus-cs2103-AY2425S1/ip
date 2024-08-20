package processor;

import response.Response;

import java.util.List;

public class Exit {
  public static Response process(String prompt) {
    return new Response(List.of("Good Bye! See you soon..."), true);
  }
}
