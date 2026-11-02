# Quiz Application
### A Java Swing and AWT Based Multiple-Choice Quiz System

**Institution:** Christ College of Engineering, Thrissur  
**Department:** Department of Computer Science and Engineering  
**Course:** Object-Oriented Programming Lab  
**Group Number:** 12  

---

## 👥 Project Team (Group 12)

| Student Name | Register Number | Role / Contribution |
| :--- | :--- | :--- |
| **C S Karthik** | `CCE25CS104` | Lead Developer & UI Architecture |
| **Baby Ashna P V** | `CCE25CS087` | Question Bank & Scoring Logic |
| **Antony Raj** | `CCE25CS068` | Swing Component Customization & Theming |
| **Aswathy MS** | `CCE25CS082` | Result Metrics & Evaluation System |
| **Devika MN** | `CCE25CS105` | Input Validation & Navigation Workflow |
| **Aswin Salish** | `CCE25CS083` | Testing, Packaging & Documentation |

---

## 📖 Project Description

The **Quiz Application** is an educational technology desktop software designed for the Object-Oriented Programming Lab at Christ College of Engineering. Built entirely in core Java using the Swing and AWT GUI frameworks, the application offers an engaging, interactive multiple-choice evaluation platform covering essential Java and Object-Oriented Programming principles.

The user interface follows a modern dark navy, royal blue, and purple accent design system with anti-aliased rounded cards, interactive hover states, dynamic progress tracking, robust validation, and comprehensive post-quiz performance analytics.

---

## 🚀 Key Features

1. **Screen 1 — Welcome Screen:**
   - Academic branding featuring Christ College of Engineering and Group 12 credentials.
   - Interactive "Project Team (Group 12)" modal displaying full team credits.
   - Player name registration with input validation (prevents empty/whitespace input).
   - Quick feature badges (10 questions, core OOP topics, instant scoring).
   - Enter key shortcut to immediately start the quiz.

2. **Screen 2 — Quiz Screen:**
   - Candidate banner displaying the active player's name.
   - Styled progress bar (`ModernProgressBar`) with real-time percentage and question counter.
   - High-contrast Question Card with clear typography.
   - Exactly four multiple-choice option cards (`OptionCard`) with letter badges (A, B, C, D).
   - Smooth hover lighting and interactive radio indicator.
   - Single-selection enforcement.
   - Validation preventing users from proceeding without selecting an option.
   - Automatic dynamic button label change on the 10th question ("Submit Quiz ✓").

3. **Screen 3 — Result Screen:**
   - Completion trophy banner and personalized candidate greeting.
   - Large score card showing final score out of 10.
   - Real-time calculated percentage card.
   - Dynamic, tiered educational performance feedback messages:
     - **100%:** Outstanding! Perfect Score! You are a true Java & OOP Master!
     - **80–90%:** Excellent Performance! Strong grasp of Object-Oriented concepts!
     - **60–70%:** Good Effort! Solid foundation, review a few concepts to master them!
     - **40–50%:** Fair Attempt! Keep practicing and reviewing core OOP concepts.
     - **< 40%:** Needs Revision. Don't worry, review the topics and try again!
   - Statistical metric summary cards (Total questions, Correct answers, Incorrect answers).
   - **Review Answers Dialog (`ReviewDialog`):** Full question-by-question modal audit displaying candidate choices alongside correct answers with color-coded status badges.
   - "Play Again" button that resets quiz state and allows another round.
   - "Exit" button to close the application cleanly.

---

## 🧩 Object-Oriented Programming (OOP) Concepts Demonstrated

This project is structured specifically to showcase key OOP principles required by the curriculum:

1. **Classes and Objects:**
   - `Question`: Concrete model encapsulating question text, four choices, and correct index.
   - `Quiz`: Concrete domain controller managing question flow and scores.
   - `UITheme`, `RoundedPanel`, `ModernButton`, `OptionCard`, `ModernProgressBar`: Modular GUI classes.
   - `WelcomeFrame`, `QuizFrame`, `ResultFrame`, `ReviewDialog`: Window frames and dialogs.

2. **Encapsulation:**
   - All critical fields (`questionText`, `options`, `correctOption`, `score`, `userAnswers`, `currentIndex`) are declared `private`.
   - Access is mediated through public getter methods (`getQuestionText()`, `getScore()`, `getPercentage()`).
   - Immutable array defensive cloning (`options.clone()`) prevents external tampering.

3. **Abstraction:**
   - Frame classes do not manipulate raw question arrays or calculate score formulas directly.
   - All domain operations are exposed through high-level abstractions like `quiz.submitAnswer(option)`, `quiz.hasNextQuestion()`, `quiz.getPercentage()`, and `quiz.getPerformanceMessage()`.

4. **Constructors:**
   - Parameterized constructors ensure valid initialization with robust preconditions (validating that questions have non-empty text, exactly 4 options, and correct index bounds 0–3).

5. **Composition:**
   - `Quiz` **has-a** `List<Question>`: it composes multiple `Question` objects.
   - `QuizFrame` **has-a** `Quiz` and an array of `OptionCard` components.
   - `ResultFrame` **has-a** `Quiz` to retrieve performance metrics.

6. **Separation of Concerns:**
   - Presentation logic (Swing/AWT components) is cleanly separated from domain logic (`Quiz`, `Question`) and the visual design system (`UITheme`).

---

## 🛠️ Technologies Used

- **Language:** Java (JDK 8 / 11 / 17 / 21 compatible)
- **GUI Toolkit:** Java Swing (`javax.swing.*`)
- **Graphics & 2D:** Java AWT (`java.awt.*`, `java.awt.geom.*`)
- **Rendering Hints:** Custom anti-aliasing pipeline (`KEY_ANTIALIASING`, `KEY_TEXT_ANTIALIASING`)
- **Data Structures:** Java Collections Framework (`java.util.ArrayList`, `java.util.List`)
- **Build / Packaging:** Java Archive (`jar.exe`) executable desktop bundle

---

## 📁 Project Structure

```
Java Mini Project/
├── bin/                       # Compiled .class bytecode files
│   ├── Main.class
│   ├── UITheme.class
│   ├── Question.class
│   ├── Quiz.class
│   ├── RoundedPanel.class
│   ├── ModernButton.class
│   ├── ModernProgressBar.class
│   ├── OptionCard.class
│   ├── WelcomeFrame.class
│   ├── QuizFrame.class
│   ├── ResultFrame.class
│   └── ReviewDialog.class
├── src/                       # Java source code
│   ├── Main.java              # Application entry point with anti-aliasing setup
│   ├── UITheme.java           # Design system palette, typography, render helpers
│   ├── Question.java          # Question entity (encapsulation & validation)
│   ├── Quiz.java              # Quiz domain controller (scoring & answer recording)
│   ├── RoundedPanel.java      # Custom anti-aliased rounded card component
│   ├── ModernButton.java      # Custom interactive button with hover states
│   ├── ModernProgressBar.java # Custom rounded gradient progress indicator
│   ├── OptionCard.java        # Interactive multiple-choice selection card
│   ├── WelcomeFrame.java      # Screen 1: Welcome & candidate registration
│   ├── QuizFrame.java         # Screen 2: Interactive quiz runner
│   ├── ResultFrame.java       # Screen 3: Score summary & evaluation
│   └── ReviewDialog.java      # Answer review modal dialog
├── QuizApplication.jar        # Standalone executable JAR desktop application
├── README.md                  # Complete project documentation
└── README.txt                 # Original summary notes
```

---

## 💻 How to Compile and Run

### Prerequisites
- Java Development Kit (JDK 8 or higher, e.g., JDK 17 or JDK 21).
- Verify installation in Command Prompt / PowerShell:
  ```bash
  java -version
  javac -version
  ```

### Method 1: Run the Executable JAR (Recommended)
Simply double-click `QuizApplication.jar`, or run:
```bash
java -jar QuizApplication.jar
```

### Method 2: Compile and Run from Source
1. Open Command Prompt or PowerShell in the project directory:
   ```bash
   cd "c:\Users\Lakshmigauri\Desktop\CSK\Java Mini Project"
   ```

2. Compile all source files into the `bin` directory:
   ```bash
   javac -d bin src/*.java
   ```

3. Launch the application:
   ```bash
   java -cp bin Main
   ```

---

## 📝 Quiz Questions & Topics Covered

The 10 multiple-choice questions test fundamental Java and OOP knowledge:

1. **Object Creation:** Keyword used to instantiate an object in Java (`new`).
2. **Inheritance:** Mechanism where a class acquires properties of another class.
3. **Entry Point:** Signature and name of the main execution method (`main()`).
4. **Data Types:** Distinguishing primitive vs reference types (`String` vs `int`, `boolean`, `char`).
5. **Access Modifiers:** Universal visibility modifier (`public`).
6. **Encapsulation:** Practice of hiding internal data using `private` fields.
7. **Class Extension:** Keyword used to extend a class (`extends`).
8. **Polymorphism:** Principle of "one interface, multiple forms".
9. **Exception Handling:** Structured error-handling block (`try-catch`).
10. **Collections Framework:** Collection that enforces uniqueness (`HashSet`).

---

## 🏛️ Academic Submission Details

- **Academic Year:** 2025–2026
- **Institution:** Christ College of Engineering, Irinjalakuda, Thrissur, Kerala
- **Affiliation:** APJ Abdul Kalam Technological University (KTU)
- **Course:** Object-Oriented Programming Lab (CS)
- **Lab Group:** Group 12
