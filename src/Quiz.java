import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Encapsulates the complete quiz domain logic, question collection,
 * progress tracking, scoring, user answer records, and evaluation metrics.
 * Demonstrates Abstraction, Encapsulation, and Composition.
 */
public class Quiz {
    private final List<Question> questions;
    private final int[] userAnswers;
    private int currentIndex;
    private int score;

    public Quiz() {
        this.questions = new ArrayList<>();
        loadQuestions();
        this.userAnswers = new int[questions.size()];
        Arrays.fill(this.userAnswers, -1);
        this.currentIndex = 0;
        this.score = 0;
    }

    /**
     * Initializes the original 10 questions covering core Java & OOP topics.
     */
    private void loadQuestions() {
        questions.add(new Question("Which keyword is used to create an object in Java?",
                new String[]{"class", "new", "this", "void"}, 1));
        questions.add(new Question("Which concept allows a class to acquire properties of another class?",
                new String[]{"Encapsulation", "Polymorphism", "Inheritance", "Abstraction"}, 2));
        questions.add(new Question("Which method is the entry point of a Java application?",
                new String[]{"start()", "run()", "main()", "init()"}, 2));
        questions.add(new Question("Which of these is not a primitive data type in Java?",
                new String[]{"int", "boolean", "String", "char"}, 2));
        questions.add(new Question("Which access modifier makes a member accessible from anywhere?",
                new String[]{"private", "protected", "default", "public"}, 3));
        questions.add(new Question("What is the process of hiding data using private fields and methods called?",
                new String[]{"Inheritance", "Encapsulation", "Compilation", "Overloading"}, 1));
        questions.add(new Question("Which keyword is used to inherit a class?",
                new String[]{"implements", "extends", "inherits", "super"}, 1));
        questions.add(new Question("Which feature means one name can have many forms?",
                new String[]{"Polymorphism", "Abstraction", "Encapsulation", "Package"}, 0));
        questions.add(new Question("Which block is commonly used to handle an exception?",
                new String[]{"check-catch", "try-catch", "do-catch", "if-else"}, 1));
        questions.add(new Question("Which collection does not allow duplicate elements?",
                new String[]{"ArrayList", "LinkedList", "HashSet", "Vector"}, 2));
    }

    public Question getCurrentQuestion() {
        return questions.get(currentIndex);
    }

    public Question getQuestionAt(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getTotalQuestions() {
        return questions.size();
    }

    public int getScore() {
        return score;
    }

    public int getIncorrectCount() {
        return questions.size() - score;
    }

    public boolean hasNextQuestion() {
        return currentIndex < questions.size() - 1;
    }

    /**
     * Submits an answer for the current question.
     * Records the user choice and safely updates score only once.
     */
    public void submitAnswer(int selectedOption) {
        if (selectedOption < 0 || selectedOption >= 4) {
            throw new IllegalArgumentException("Selected option index must be between 0 and 3.");
        }
        // Only update if not already answered
        if (userAnswers[currentIndex] == -1) {
            userAnswers[currentIndex] = selectedOption;
            if (getCurrentQuestion().isCorrect(selectedOption)) {
                score++;
            }
        }
    }

    public void moveToNextQuestion() {
        if (hasNextQuestion()) {
            currentIndex++;
        }
    }

    public int getUserAnswer(int questionIndex) {
        if (questionIndex >= 0 && questionIndex < userAnswers.length) {
            return userAnswers[questionIndex];
        }
        return -1;
    }

    public boolean isQuestionCorrect(int questionIndex) {
        if (questionIndex >= 0 && questionIndex < questions.size()) {
            return questions.get(questionIndex).isCorrect(userAnswers[questionIndex]);
        }
        return false;
    }

    public int getPercentage() {
        if (questions.isEmpty()) return 0;
        return (int) Math.round((score * 100.0) / questions.size());
    }

    /**
     * Generates an appropriate educational feedback message based on score percentage.
     */
    public String getPerformanceMessage() {
        int pct = getPercentage();
        if (pct == 100) {
            return "Outstanding! Perfect Score! You are a true Java & OOP Master!";
        } else if (pct >= 80) {
            return "Excellent Performance! Strong grasp of Object-Oriented concepts!";
        } else if (pct >= 60) {
            return "Good Effort! Solid foundation, review a few concepts to master them!";
        } else if (pct >= 40) {
            return "Fair Attempt! Keep practicing and reviewing core OOP concepts.";
        } else {
            return "Needs Revision. Don't worry, review the topics and try again!";
        }
    }

    /**
     * Resets the quiz state to start fresh.
     */
    public void reset() {
        this.currentIndex = 0;
        this.score = 0;
        Arrays.fill(this.userAnswers, -1);
    }
}
