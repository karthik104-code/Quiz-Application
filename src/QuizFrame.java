import java.awt.*;
import javax.swing.*;

/**
 * Screen 2 — Quiz Screen
 * Handles interactive question rendering, 4 option cards with single selection,
 * progress tracking, answer validation, score updates, and smooth navigation.
 */
public class QuizFrame extends JFrame {
    private final String playerName;
    private final Quiz quiz;

    // UI Components
    private final JLabel candidateLabel;
    private final JLabel progressBadge;
    private final ModernProgressBar progressBar;
    private final JLabel questionTag;
    private final JLabel questionTextLabel;
    private final OptionCard[] optionCards;
    private final ModernButton nextButton;

    private int selectedOptionIndex = -1;

    public QuizFrame(String playerName) {
        this.playerName = playerName;
        this.quiz = new Quiz();

        setTitle("Quiz Application — Christ College of Engineering (Group 12)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 600);
        setMinimumSize(new Dimension(760, 520));
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(UITheme.BG_DARK);

        // Top Navigation Bar
        root.add(createTopBar(), BorderLayout.NORTH);

        // Center Area — Question Card + 4 Option Cards
        JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.setOpaque(false);
        centerContainer.setBorder(UITheme.createPadding(10, 32, 10, 32));

        // Question Card
        RoundedPanel qCard = new RoundedPanel(16, UITheme.BG_CARD, UITheme.BORDER_CARD, 1);
        qCard.setLayout(new BoxLayout(qCard, BoxLayout.Y_AXIS));
        qCard.setBorder(UITheme.createPadding(18, 24, 18, 24));
        qCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        qCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        questionTag = new JLabel("QUESTION 01");
        questionTag.setFont(UITheme.FONT_BADGE);
        questionTag.setForeground(UITheme.TEXT_ACCENT);

        questionTextLabel = new JLabel();
        questionTextLabel.setFont(UITheme.FONT_QUESTION);
        questionTextLabel.setForeground(UITheme.TEXT_PRIMARY);

        qCard.add(questionTag);
        qCard.add(Box.createVerticalStrut(6));
        qCard.add(questionTextLabel);

        centerContainer.add(qCard);
        centerContainer.add(Box.createVerticalStrut(14));

        // Options Container
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 0, 10));
        optionsPanel.setOpaque(false);
        optionsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        optionsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));

        optionCards = new OptionCard[4];
        char[] letters = {'A', 'B', 'C', 'D'};
        for (int i = 0; i < 4; i++) {
            final int index = i;
            optionCards[i] = new OptionCard(i, letters[i], "");
            optionCards[i].setOnSelectListener(() -> selectOption(index));
            optionsPanel.add(optionCards[i]);
        }

        centerContainer.add(optionsPanel);
        root.add(centerContainer, BorderLayout.CENTER);

        // Bottom Action Bar
        JPanel bottomBar = new JPanel(new BorderLayout());
        bottomBar.setBackground(UITheme.BG_DARK);
        bottomBar.setBorder(UITheme.createPadding(10, 32, 18, 32));

        JLabel hintLabel = new JLabel("Tip: Select an option and click Next to continue.");
        hintLabel.setFont(UITheme.FONT_SMALL);
        hintLabel.setForeground(UITheme.TEXT_MUTED);

        nextButton = new ModernButton("Next Question   \u2192", ModernButton.Style.PRIMARY, 12);
        nextButton.setPreferredSize(new Dimension(190, 42));
        nextButton.addActionListener(e -> submitAndContinue());

        bottomBar.add(hintLabel, BorderLayout.WEST);
        bottomBar.add(nextButton, BorderLayout.EAST);
        root.add(bottomBar, BorderLayout.SOUTH);

        // Initialize Candidate Info in Top Bar
        this.candidateLabel = new JLabel("Candidate: " + playerName);
        this.candidateLabel.setFont(UITheme.FONT_BADGE);
        this.candidateLabel.setForeground(UITheme.TEXT_PRIMARY);

        this.progressBadge = new JLabel();
        this.progressBadge.setFont(UITheme.FONT_BADGE);
        this.progressBadge.setForeground(UITheme.TEXT_ACCENT);

        this.progressBar = new ModernProgressBar(0, quiz.getTotalQuestions());

        // Attach top bar components
        setupTopBarComponents();

        setContentPane(root);
        showQuestion();
    }

    private JPanel topBarPanel;

    private JPanel createTopBar() {
        topBarPanel = new JPanel(new BorderLayout(14, 0));
        topBarPanel.setBackground(UITheme.BG_DARK);
        topBarPanel.setBorder(UITheme.createPadding(16, 32, 8, 32));
        return topBarPanel;
    }

    private void setupTopBarComponents() {
        JPanel leftBox = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftBox.setOpaque(false);
        leftBox.add(candidateLabel);

        JPanel rightBox = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 0));
        rightBox.setOpaque(false);
        rightBox.add(progressBar);
        rightBox.add(progressBadge);

        topBarPanel.add(leftBox, BorderLayout.WEST);
        topBarPanel.add(rightBox, BorderLayout.EAST);
    }

    private void selectOption(int index) {
        this.selectedOptionIndex = index;
        for (int i = 0; i < optionCards.length; i++) {
            optionCards[i].setSelected(i == index);
        }
    }

    private void showQuestion() {
        selectedOptionIndex = -1;
        Question question = quiz.getCurrentQuestion();
        int currentQNum = quiz.getCurrentIndex() + 1;
        int total = quiz.getTotalQuestions();

        // Update tags and question text
        questionTag.setText(String.format("QUESTION %02d OF %02d", currentQNum, total));
        questionTextLabel.setText("<html><body style='width:680px'>" + currentQNum + ". " + question.getQuestionText() + "</body></html>");

        // Update progress bar & label
        progressBadge.setText(currentQNum + " / " + total);
        progressBar.setValue(currentQNum);

        // Update option cards
        String[] options = question.getOptions();
        for (int i = 0; i < 4; i++) {
            optionCards[i].updateText(options[i]);
            optionCards[i].setSelected(false);
        }

        // Toggle button text on last question
        if (quiz.hasNextQuestion()) {
            nextButton.setText("Next Question   \u2192");
        } else {
            nextButton.setText("Submit Quiz   \u2713");
        }
    }

    private void submitAndContinue() {
        if (selectedOptionIndex == -1) {
            showValidationWarning("Answer Required", "Please select an answer before continuing to the next question.");
            return;
        }

        quiz.submitAnswer(selectedOptionIndex);

        if (quiz.hasNextQuestion()) {
            quiz.moveToNextQuestion();
            showQuestion();
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        Point loc = getLocation();
        Dimension size = getSize();
        dispose();

        SwingUtilities.invokeLater(() -> {
            ResultFrame rf = new ResultFrame(playerName, quiz);
            rf.setLocation(loc);
            rf.setSize(size);
            rf.setVisible(true);
        });
    }

    private void showValidationWarning(String title, String message) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(380, 180);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);

        RoundedPanel panel = new RoundedPanel(14, UITheme.BG_CARD, UITheme.WARNING, 1);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(UITheme.createPadding(20, 20, 20, 20));

        JLabel titleLbl = new JLabel("\u26A0 " + title);
        titleLbl.setFont(UITheme.FONT_SECTION);
        titleLbl.setForeground(UITheme.WARNING);
        titleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel msgLbl = new JLabel("<html><center>" + message + "</center></html>");
        msgLbl.setFont(UITheme.FONT_BODY);
        msgLbl.setForeground(UITheme.TEXT_PRIMARY);
        msgLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        ModernButton okBtn = new ModernButton("OK", ModernButton.Style.PRIMARY, 10);
        okBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        okBtn.addActionListener(e -> dialog.dispose());

        panel.add(titleLbl);
        panel.add(Box.createVerticalStrut(10));
        panel.add(msgLbl);
        panel.add(Box.createVerticalStrut(16));
        panel.add(okBtn);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }
}
