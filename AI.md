## AI Usage:
- Used Chatgpt to generate and improve some JavaDocs header comments.
  - Prompt:
  ```
  Help me generate a JavaDoc header comment for this method.
  ```
  - Output:
  ```
  JavaDoc header comments for the method.
  ```
  - Observations:
  ```
  Chatgpt may give Javadocs comments that does not adhere to the style specified in CS2103T.
  
  Specifically, this rule is often violated:
  In method header comments, the first sentence should start in the form Returns ..., Sends ..., Adds ... etc. (not Return or Returning etc.)
  ```
- Used Copilot to autocomplete some code.
   - Observation:
   ```
   Copilot can help programmers save a lot of time especially if the code is repeated.
   
   Example: 
   The shouldExit() method in commands can be auto-completed after writing out 1 of its method implementation.
   
   However, sometimes they may copy blindly, like for the shouldExit() in ByeCommand should return true.
   But Copilot generates return true.
   ```