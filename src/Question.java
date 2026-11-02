/**
 * Represents a single multiple-choice quiz question with exactly four options
 * and one correct answer. Demonstrates encapsulation and validation.
 */
public class Question {
    private final String questionText;
    private final String[] options;
    private final int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        if (questionText == null || questionText.trim().isEmpty()) {
            throw new IllegalArgumentException("Question text cannot be empty.");
        }
        if (options == null || options.length != 4) {
            throw new IllegalArgumentException("A question must have exactly four options.");
        }
        if (correctOption < 0 || correctOption >= options.length) {
            throw new IllegalArgumentException("Valid correct option index must be between 0 and 3.");
        }
        this.questionText = questionText;
        this.options = options.clone();
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options.clone();
    }

    public String getOption(int index) {
        if (index >= 0 && index < options.length) {
            return options[index];
        }
        return "";
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public String getCorrectOptionText() {
        return options[correctOption];
    }

    public boolean isCorrect(int selectedOption) {
        return selectedOption == correctOption;
    }
}
