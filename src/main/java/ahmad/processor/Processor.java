package ahmad.processor;

import ahmad.exceptions.AhmadException;
import ahmad.response.Response;

/**
 * Functional interface for processors.
 */
public interface Processor {
  /**
   * Returns a response based on the prompt.
   * 
   * @param prompt Prompt/argument for the processor.
   * @throws AhmadException If prompt is invalid, or if an exception occurs within the processor.
   */
  Response process(String prompt) throws AhmadException;
}
