# 🧽 Spongebob : A Chatbot for your daily needs. 

![alt text](https://i.imgur.com/IhjSup1.jpeg "Spongebot")
---

Spongebob fulfills **all** your needs for chatbot:

* 🅰️ Text-based
* 📖 Easy to learn
* ⌛ _Fast_ response time
* 🧳 Coded in Java

---

## ✨ Key Features

-[ ] **Manages** and **tracks** your tasks for you.
-[ ] Set different types of **tasks**, from deadlines to events.
-[ ] Set and manages **deadlines**.
-[ ] **Mark** tasks as done.
-[ ] **Filter** tasks based on description. 

--- 
## 🛠️ Setup

1. Download a version of Spongebob [here.](https://github.com/Jaynon/ip/releases)

2. Then, run the included jar file in any terminal with the line below.
```cmd
java -jar spongebob.jar
```

---
## 🧠 Contributions
Feel free to clone this repository and make your own features!
```cmd
gh repo clone Jaynon/ip
```
> **Requirements**
> <p> Have Java 17.0 and above</p>

Here is an snippet of the codebase:

```Java
public static void main(String[] args) {
        new Spongebob("data/spongebob.txt").run();
    }
```