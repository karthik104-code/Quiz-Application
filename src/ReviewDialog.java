import java.awt.*;
import javax.swing.*;

/**
 * ReviewDialog provides a comprehensive breakdown of all quiz questions,
 * displaying the candidate's selected choice versus the correct answer.
 */
public class ReviewDialog extends JDialog {

    public ReviewDialog(JFrame parent, Quiz quiz) {
        super(parent, "Quiz Review — Detailed Question Analysis", true);
        setSize(720, 560);
        setLocationRelativeTo(parent);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(UITheme.BG_DARK);
        root.setBorder(UITheme.createPadding(20, 24, 20, 24));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel title = new JLabel("Detailed Question-by-Question Review");
        title.setFont(UITheme.FONT_TITLE);
        title.setForeground(UITheme.TEXT_PRIMARY);

        JLabel subtitle = new JLabel("Review your selected answers alongside the correct answers.");
        subtitle.setFont(UITheme.FONT_SUBTITLE);
        subtitle.setForeground(UITheme.TEXT_SECONDARY);

        header.add(title, BorderLayout.NORTH);
        header.add(subtitle, BorderLayout.SOUTH);
        header.setBorder(UITheme.createPadding(0, 0, 16, 0));
        root.add(header, BorderLayout.NORTH);

        // Scrollable list of questions
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setOpaque(false);

        for (int i = 0; i < quiz.getTotalQuestions(); i++) {
            Question q = quiz.getQuestionAt(i);
            int userChoice = quiz.getUserAnswer(i);
            boolean isCorrect = quiz.isQuestionCorrect(i);

            listPanel.add(createQuestionReviewCard(i + 1, q, userChoice, isCorrect));
            listPanel.add(Box.createVerticalStrut(12));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        root.add(scrollPane, BorderLayout.CENTER);

        // Bottom Close button
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setOpaque(false);
        bottom.setBorder(UITheme.createPadding(12, 0, 0, 0));

        ModernButton closeBtn = new ModernButton("Close Review", ModernButton.Style.PRIMARY, 10);
        closeBtn.addActionListener(e -> dispose());
        bottom.add(closeBtn);
        root.add(bottom, BorderLayout.SOUTH);

        setContentPane(root);
    }

    private JPanel createQuestionReviewCard(int questionNum, Question q, int userChoice, boolean isCorrect) {
        Color borderCol = isCorrect ? UITheme.SUCCESS : UITheme.DANGER;
        RoundedPanel card = new RoundedPanel(12, UITheme.BG_CARD, borderCol, 1);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(UITheme.createPadding(14, 18, 14, 18));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Header: Question number & status badge
        JPanel cardHeader = new JPanel(new BorderLayout());
        cardHeader.setOpaque(false);

        JLabel qNum = new JLabel("Question " + questionNum);
        qNum.setFont(UITheme.FONT_BADGE);
        qNum.setForeground(UITheme.TEXT_ACCENT);

        JLabel statusBadge = new JLabel(isCorrect ? "Correct (+1)" : "Incorrect (0)");
        statusBadge.setFont(UITheme.FONT_BADGE);
        statusBadge.setForeground(isCorrect ? UITheme.SUCCESS : UITheme.DANGER);

        cardHeader.add(qNum, BorderLayout.WEST);
        cardHeader.add(statusBadge, BorderLayout.EAST);

        // Question text
        JLabel qText = new JLabel("<html><body style='width:580px'>" + q.getQuestionText() + "</body></html>");
        qText.setFont(UITheme.FONT_BODY);
        qText.setForeground(UITheme.TEXT_PRIMARY);

        // Answers row
        String userAnsText = userChoice >= 0 ? (char)('A' + userChoice) + ". " + q.getOption(userChoice) : "None selected";
        JLabel userAnsLbl = new JLabel("Your Answer: " + userAnsText);
        userAnsLbl.setFont(UITheme.FONT_SMALL);
        userAnsLbl.setForeground(isCorrect ? UITheme.SUCCESS : UITheme.DANGER);

        card.add(cardHeader);
        card.add(Box.createVerticalStrut(6));
        card.add(qText);
        card.add(Box.createVerticalStrut(8));
        card.add(userAnsLbl);

        if (!isCorrect) {
            String correctAnsText = (char)('A' + q.getCorrectOption()) + ". " + q.getCorrectOptionText();
            JLabel correctAnsLbl = new JLabel("Correct Answer: " + correctAnsText);
            correctAnsLbl.setFont(UITheme.FONT_SMALL);
            correctAnsLbl.setForeground(UITheme.SUCCESS);
            card.add(Box.createVerticalStrut(4));
            card.add(correctAnsLbl);
        }

        return card;
    }
}
