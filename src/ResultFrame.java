import java.awt.*;
import javax.swing.*;

/**
 * Screen 3 — Result Screen
 * Displays quiz performance metrics, percentage calculation,
 * custom evaluation message, and navigation to review answers or play again.
 */
public class ResultFrame extends JFrame {
    private final String playerName;
    private final Quiz quiz;

    public ResultFrame(String playerName, Quiz quiz) {
        this.playerName = playerName;
        this.quiz = quiz;

        setTitle("Quiz Application — Final Results (" + playerName + ")");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 600);
        setMinimumSize(new Dimension(760, 520));
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(UITheme.BG_DARK);

        // Header branding
        root.add(createTopHeader(), BorderLayout.NORTH);

        // Center card with scores and performance
        root.add(createCenterCard(), BorderLayout.CENTER);

        // Footer with action buttons
        root.add(createActionFooter(), BorderLayout.SOUTH);

        setContentPane(root);
    }

    private JPanel createTopHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UITheme.BG_DARK);
        header.setBorder(UITheme.createPadding(18, 30, 6, 30));

        JLabel title = new JLabel("QUIZ COMPLETION SUMMARY");
        title.setFont(new Font(UITheme.FONT_SUBTITLE.getFamily(), Font.BOLD, 13));
        title.setForeground(UITheme.TEXT_ACCENT);

        JLabel candidate = new JLabel("Candidate: " + playerName);
        candidate.setFont(UITheme.FONT_BADGE);
        candidate.setForeground(UITheme.TEXT_PRIMARY);

        header.add(title, BorderLayout.WEST);
        header.add(candidate, BorderLayout.EAST);
        return header;
    }

    private JPanel createCenterCard() {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(UITheme.createPadding(10, 30, 10, 30));

        RoundedPanel card = new RoundedPanel(20, UITheme.BG_CARD, UITheme.BORDER_CARD, 1);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(UITheme.createPadding(24, 36, 24, 36));
        card.setPreferredSize(new Dimension(660, 420));
        card.setMaximumSize(new Dimension(700, 430));

        // Celebration Badge
        RoundedPanel badgeBox = new RoundedPanel(12, UITheme.BG_CARD_LIGHT, UITheme.GOLD, 1);
        badgeBox.setLayout(new FlowLayout(FlowLayout.CENTER, 14, 4));
        badgeBox.setMaximumSize(new Dimension(200, 32));
        badgeBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel starLbl = new JLabel("QUIZ COMPLETED");
        starLbl.setFont(UITheme.FONT_BADGE);
        starLbl.setForeground(UITheme.GOLD);
        badgeBox.add(starLbl);

        // Greeting
        JLabel greeting = new JLabel("Well done, " + playerName + "!");
        greeting.setFont(UITheme.FONT_TITLE);
        greeting.setForeground(UITheme.TEXT_PRIMARY);
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Performance feedback message
        JLabel perfMessage = new JLabel("<html><center>" + quiz.getPerformanceMessage() + "</center></html>");
        perfMessage.setFont(UITheme.FONT_SUBTITLE);
        perfMessage.setForeground(UITheme.TEXT_ACCENT);
        perfMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Score Card with big numbers
        JPanel scoreRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        scoreRow.setOpaque(false);

        RoundedPanel scoreBox = new RoundedPanel(14, UITheme.BG_CARD_LIGHT, UITheme.BORDER_ACTIVE, 1);
        scoreBox.setLayout(new BoxLayout(scoreBox, BoxLayout.Y_AXIS));
        scoreBox.setBorder(UITheme.createPadding(12, 28, 12, 28));

        JLabel scoreTitle = new JLabel("FINAL SCORE", SwingConstants.CENTER);
        scoreTitle.setFont(UITheme.FONT_BADGE);
        scoreTitle.setForeground(UITheme.TEXT_SECONDARY);
        scoreTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreVal = new JLabel(quiz.getScore() + " / " + quiz.getTotalQuestions(), SwingConstants.CENTER);
        scoreVal.setFont(UITheme.FONT_SCORE_BIG);
        scoreVal.setForeground(UITheme.TEXT_PRIMARY);
        scoreVal.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreBox.add(scoreTitle);
        scoreBox.add(Box.createVerticalStrut(4));
        scoreBox.add(scoreVal);

        RoundedPanel pctBox = new RoundedPanel(14, UITheme.BG_CARD_LIGHT, UITheme.ACCENT_PURPLE, 1);
        pctBox.setLayout(new BoxLayout(pctBox, BoxLayout.Y_AXIS));
        pctBox.setBorder(UITheme.createPadding(12, 28, 12, 28));

        JLabel pctTitle = new JLabel("PERCENTAGE", SwingConstants.CENTER);
        pctTitle.setFont(UITheme.FONT_BADGE);
        pctTitle.setForeground(UITheme.TEXT_SECONDARY);
        pctTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel pctVal = new JLabel(quiz.getPercentage() + "%", SwingConstants.CENTER);
        pctVal.setFont(UITheme.FONT_SCORE_BIG);
        pctVal.setForeground(quiz.getPercentage() >= 60 ? UITheme.SUCCESS : UITheme.GOLD);
        pctVal.setAlignmentX(Component.CENTER_ALIGNMENT);

        pctBox.add(pctTitle);
        pctBox.add(Box.createVerticalStrut(4));
        pctBox.add(pctVal);

        scoreRow.add(scoreBox);
        scoreRow.add(pctBox);
        scoreRow.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Metric statistics pills
        JPanel statsRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 0));
        statsRow.setOpaque(false);
        statsRow.add(createStatPill("Total: " + quiz.getTotalQuestions(), UITheme.BORDER_CARD, UITheme.TEXT_SECONDARY));
        statsRow.add(createStatPill("Correct: " + quiz.getScore(), UITheme.SUCCESS, UITheme.SUCCESS));
        statsRow.add(createStatPill("Incorrect: " + quiz.getIncorrectCount(), UITheme.DANGER, UITheme.DANGER));
        statsRow.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(badgeBox);
        card.add(Box.createVerticalStrut(10));
        card.add(greeting);
        card.add(Box.createVerticalStrut(6));
        card.add(perfMessage);
        card.add(Box.createVerticalStrut(18));
        card.add(scoreRow);
        card.add(Box.createVerticalStrut(16));
        card.add(statsRow);

        wrapper.add(card);
        return wrapper;
    }

    private JPanel createActionFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 16));
        footer.setBackground(UITheme.BG_DARK);

        ModernButton reviewBtn = new ModernButton("Review Answers", ModernButton.Style.SECONDARY, 12);
        reviewBtn.setPreferredSize(new Dimension(170, 44));
        reviewBtn.addActionListener(e -> {
            ReviewDialog rd = new ReviewDialog(this, quiz);
            rd.setVisible(true);
        });

        ModernButton playAgainBtn = new ModernButton("Play Again", ModernButton.Style.SUCCESS, 12);
        playAgainBtn.setPreferredSize(new Dimension(160, 44));
        playAgainBtn.addActionListener(e -> playAgain());

        ModernButton exitBtn = new ModernButton("Exit", ModernButton.Style.DANGER, 12);
        exitBtn.setPreferredSize(new Dimension(110, 44));
        exitBtn.addActionListener(e -> System.exit(0));

        footer.add(reviewBtn);
        footer.add(playAgainBtn);
        footer.add(exitBtn);
        return footer;
    }

    private RoundedPanel createStatPill(String text, Color border, Color textCol) {
        RoundedPanel pill = new RoundedPanel(8, UITheme.BG_CARD_LIGHT, border, 1);
        pill.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 4));
        JLabel label = new JLabel(text);
        label.setFont(UITheme.FONT_BADGE);
        label.setForeground(textCol);
        pill.add(label);
        return pill;
    }

    private void playAgain() {
        Point loc = getLocation();
        Dimension size = getSize();
        dispose();

        SwingUtilities.invokeLater(() -> {
            WelcomeFrame wf = new WelcomeFrame();
            wf.setLocation(loc);
            wf.setSize(size);
            wf.setVisible(true);
        });
    }
}
