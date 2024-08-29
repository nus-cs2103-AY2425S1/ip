package ahmad.processor;

import ahmad.response.Response;

import java.util.List;

public class Echo {
  public static Response process(String prompt) {
    return new Response(List.of(prompt));
  }
}
