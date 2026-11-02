import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Screen 1 — Welcome Screen
 * Displays college & project identity, team information, instructions,
 * and player name registration with robust validation.
 */
public class WelcomeFrame extends JFrame {
    private JTextField nameField;

    public WelcomeFrame() {
        setTitle("Quiz Application — Christ College of Engineering (Group 12)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 600);
        setMinimumSize(new Dimension(760, 520));
        setLocationRelativeTo(null);

        // Root container with dark navy theme
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(UITheme.BG_DARK);

        // Top Header Bar — Academic Branding
        root.add(createHeaderPanel(), BorderLayout.NORTH);

        // Center Hero Section — Card with Title, Features, and Name Input
        root.add(createCenterCard(), BorderLayout.CENTER);

        // Bottom Footer — Team Credits & Info
        root.add(createFooterPanel(), BorderLayout.SOUTH);

        setContentPane(root);
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UITheme.BG_DARK);
        header.setBorder(UITheme.createPadding(18, 30, 8, 30));

        JPanel collegeBox = new JPanel(new GridLayout(2, 1, 0, 3));
        collegeBox.setOpaque(false);

        JLabel collegeLabel = new JLabel("CHRIST COLLEGE OF ENGINEERING, THRISSUR");
        collegeLabel.setFont(new Font(UITheme.FONT_SUBTITLE.getFamily(), Font.BOLD, 13));
        collegeLabel.setForeground(UITheme.TEXT_ACCENT);

        JLabel deptLabel = new JLabel("Department of Computer Science & Engineering | OOP Lab Mini Project");
        deptLabel.setFont(UITheme.FONT_SMALL);
        deptLabel.setForeground(UITheme.TEXT_SECONDARY);

        collegeBox.add(collegeLabel);
        collegeBox.add(deptLabel);

        // Badge on right
        RoundedPanel groupBadge = new RoundedPanel(10, UITheme.BG_CARD, UITheme.ACCENT_PRIMARY, 1);
        groupBadge.setLayout(new FlowLayout(FlowLayout.CENTER, 12, 6));
        JLabel groupText = new JLabel("GROUP 12");
        groupText.setFont(UITheme.FONT_BADGE);
        groupText.setForeground(UITheme.TEXT_PRIMARY);
        groupBadge.add(groupText);

        header.add(collegeBox, BorderLayout.WEST);
        header.add(groupBadge, BorderLayout.EAST);
        return header;
    }

    private JPanel createCenterCard() {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(UITheme.createPadding(10, 30, 10, 30));

        RoundedPanel card = new RoundedPanel(20, UITheme.BG_CARD, UITheme.BORDER_CARD, 1);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(UITheme.createPadding(26, 40, 26, 40));
        card.setMaximumSize(new Dimension(640, 420));
        card.setPreferredSize(new Dimension(600, 390));

        // App Title
        JLabel titleLabel = new JLabel("QUIZ APPLICATION");
        titleLabel.setFont(UITheme.FONT_HEADER_TITLE);
        titleLabel.setForeground(UITheme.TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel subLabel = new JLabel("A Java Swing and AWT Based Multiple-Choice Quiz System");
        subLabel.setFont(UITheme.FONT_SUBTITLE);
        subLabel.setForeground(UITheme.TEXT_SECONDARY);
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tagline Pill / Features
        JPanel badgesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        badgesPanel.setOpaque(false);
        badgesPanel.add(createMiniBadge("10 Questions"));
        badgesPanel.add(createMiniBadge("OOP & Core Java"));
        badgesPanel.add(createMiniBadge("Instant Evaluation"));
        badgesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Prompt
        JLabel promptLabel = new JLabel("Enter Player Name to Begin:");
        promptLabel.setFont(UITheme.FONT_SECTION);
        promptLabel.setForeground(UITheme.TEXT_PRIMARY);
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Field in custom rounded wrapper
        JPanel inputWrapper = new JPanel(new BorderLayout());
        inputWrapper.setOpaque(false);
        inputWrapper.setMaximumSize(new Dimension(360, 44));
        inputWrapper.setPreferredSize(new Dimension(360, 44));
        inputWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                UITheme.enableAntiAliasing(g2);
                g2.setColor(UITheme.BG_CARD_LIGHT);
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
                g2.setColor(hasFocus() ? UITheme.ACCENT_PRIMARY : UITheme.BORDER_CARD);
                g2.setStroke(new BasicStroke(hasFocus() ? 2f : 1f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        nameField.setOpaque(false);
        nameField.setBorder(UITheme.createPadding(8, 14, 8, 14));
        nameField.setFont(new Font(UITheme.FONT_BODY.getFamily(), Font.BOLD, 15));
        nameField.setForeground(UITheme.TEXT_PRIMARY);
        nameField.setCaretColor(UITheme.ACCENT_PRIMARY);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        inputWrapper.add(nameField, BorderLayout.CENTER);

        // Action Buttons
        ModernButton startButton = new ModernButton("Start Quiz   \u2192", ModernButton.Style.PRIMARY, 12);
        startButton.setPreferredSize(new Dimension(240, 44));
        startButton.setMaximumSize(new Dimension(240, 44));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> startQuiz());

        // Keyboard Enter triggers quiz start
        nameField.addActionListener(e -> startQuiz());

        // Assembly
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(6));
        card.add(subLabel);
        card.add(Box.createVerticalStrut(18));
        card.add(badgesPanel);
        card.add(Box.createVerticalStrut(24));
        card.add(promptLabel);
        card.add(Box.createVerticalStrut(12));
        card.add(inputWrapper);
        card.add(Box.createVerticalStrut(20));
        card.add(startButton);

        wrapper.add(card);
        return wrapper;
    }

    private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(UITheme.BG_DARK);
        footer.setBorder(UITheme.createPadding(10, 30, 16, 30));

        JLabel copyright = new JLabel("Christ College of Engineering \u2022 Department of CSE \u2022 2026");
        copyright.setFont(UITheme.FONT_SMALL);
        copyright.setForeground(UITheme.TEXT_MUTED);

        ModernButton teamButton = new ModernButton("Project Team (Group 12)", ModernButton.Style.SECONDARY, 10);
        teamButton.setFont(UITheme.FONT_SMALL);
        teamButton.setBorder(UITheme.createPadding(6, 14, 6, 14));
        teamButton.addActionListener(e -> showTeamDialog());

        footer.add(copyright, BorderLayout.WEST);
        footer.add(teamButton, BorderLayout.EAST);
        return footer;
    }

    private RoundedPanel createMiniBadge(String text) {
        RoundedPanel badge = new RoundedPanel(8, UITheme.BG_CARD_LIGHT, UITheme.BORDER_CARD, 1);
        badge.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 4));
        JLabel label = new JLabel(text);
        label.setFont(UITheme.FONT_SMALL);
        label.setForeground(UITheme.TEXT_SECONDARY);
        badge.add(label);
        return badge;
    }

    private void showTeamDialog() {
        JDialog dialog = new JDialog(this, "Group 12 — Project Team", true);
        dialog.setSize(480, 420);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);

        RoundedPanel content = new RoundedPanel(16, UITheme.BG_CARD, UITheme.BORDER_ACTIVE, 1);
        content.setLayout(new BorderLayout(15, 15));
        content.setBorder(UITheme.createPadding(22, 25, 22, 25));

        // Title
        JLabel title = new JLabel("OOP Lab Mini Project — Group 12", SwingConstants.CENTER);
        title.setFont(UITheme.FONT_SECTION);
        title.setForeground(UITheme.TEXT_ACCENT);

        // Members list
        JPanel membersPanel = new JPanel(new GridLayout(6, 1, 0, 8));
        membersPanel.setOpaque(false);
        membersPanel.setBorder(UITheme.createPadding(10, 10, 10, 10));

        String[][] members = {
            {"C S Karthik", "CCE25CS104"},
            {"Baby Ashna P V", "CCE25CS087"},
            {"Antony Raj", "CCE25CS068"},
            {"Aswathy MS", "CCE25CS082"},
            {"Devika MN", "CCE25CS105"},
            {"Aswin Salish", "CCE25CS083"}
        };

        for (String[] member : members) {
            JPanel row = new JPanel(new BorderLayout());
            row.setOpaque(false);
            JLabel nameLbl = new JLabel(member[0]);
            nameLbl.setFont(UITheme.FONT_BODY);
            nameLbl.setForeground(UITheme.TEXT_PRIMARY);

            JLabel regLbl = new JLabel(member[1]);
            regLbl.setFont(UITheme.FONT_BODY);
            regLbl.setForeground(UITheme.TEXT_SECONDARY);

            row.add(nameLbl, BorderLayout.WEST);
            row.add(regLbl, BorderLayout.EAST);
            membersPanel.add(row);
        }

        ModernButton closeBtn = new ModernButton("Close", ModernButton.Style.SECONDARY, 10);
        closeBtn.addActionListener(e -> dialog.dispose());

        content.add(title, BorderLayout.NORTH);
        content.add(membersPanel, BorderLayout.CENTER);
        content.add(closeBtn, BorderLayout.SOUTH);

        dialog.setContentPane(content);
        dialog.setVisible(true);
    }

    private void startQuiz() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            showValidationWarning("Player Name Required", "Please enter your name before starting the quiz.");
            nameField.requestFocusInWindow();
            return;
        }

        Point currentLoc = getLocation();
        Dimension currentSize = getSize();
        dispose();

        SwingUtilities.invokeLater(() -> {
            QuizFrame quizFrame = new QuizFrame(name);
            quizFrame.setLocation(currentLoc);
            quizFrame.setSize(currentSize);
            quizFrame.setVisible(true);
        });
    }

    private void showValidationWarning(String title, String message) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(380, 190);
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
