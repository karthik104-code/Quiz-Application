import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * OptionCard represents an interactive, selectable multiple-choice option.
 * Displays option letter badge (A, B, C, D), answer text, and a custom
 * styled selection indicator with smooth hover and active animations.
 */
public class OptionCard extends JPanel {
    private final int optionIndex;
    private final char optionLetter;
    private final String optionText;
    private boolean selected = false;
    private boolean hovered = false;
    private Runnable onSelectListener;

    private final JLabel textLabel;
    private final JLabel letterBadge;

    public OptionCard(int optionIndex, char letter, String text) {
        this.optionIndex = optionIndex;
        this.optionLetter = letter;
        this.optionText = text;

        setLayout(new BorderLayout(14, 0));
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBorder(UITheme.createPadding(12, 16, 12, 16));
        setPreferredSize(new Dimension(680, 56));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        // Letter badge (e.g., A, B, C, D)
        letterBadge = new JLabel(String.valueOf(letter), SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                UITheme.enableAntiAliasing(g2);
                int size = Math.min(getWidth(), getHeight());
                if (selected) {
                    g2.setColor(UITheme.ACCENT_PRIMARY);
                } else if (hovered) {
                    g2.setColor(UITheme.ACCENT_SECONDARY);
                } else {
                    g2.setColor(new Color(51, 65, 85));
                }
                g2.fillRoundRect(2, 2, size - 4, size - 4, 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        letterBadge.setPreferredSize(new Dimension(34, 34));
        letterBadge.setFont(UITheme.FONT_BADGE);
        letterBadge.setForeground(UITheme.TEXT_PRIMARY);
        letterBadge.setOpaque(false);

        // Text label
        textLabel = new JLabel(text);
        textLabel.setFont(UITheme.FONT_OPTION);
        textLabel.setForeground(UITheme.TEXT_PRIMARY);

        // Custom Radio Indicator on the right
        JComponent radioIndicator = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                UITheme.enableAntiAliasing(g2);
                int s = 20;
                int x = (getWidth() - s) / 2;
                int y = (getHeight() - s) / 2;

                if (selected) {
                    g2.setColor(UITheme.ACCENT_PRIMARY);
                    g2.fillOval(x, y, s, s);
                    g2.setColor(Color.WHITE);
                    g2.fillOval(x + 5, y + 5, s - 10, s - 10);
                } else {
                    g2.setColor(hovered ? UITheme.TEXT_SECONDARY : UITheme.BORDER_CARD);
                    g2.setStroke(new BasicStroke(2f));
                    g2.drawOval(x, y, s - 1, s - 1);
                }
                g2.dispose();
            }
        };
        radioIndicator.setPreferredSize(new Dimension(28, 28));

        add(letterBadge, BorderLayout.WEST);
        add(textLabel, BorderLayout.CENTER);
        add(radioIndicator, BorderLayout.EAST);

        // Mouse interaction
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (onSelectListener != null) {
                    onSelectListener.run();
                }
            }
        };

        addMouseListener(ma);
        textLabel.addMouseListener(ma);
        letterBadge.addMouseListener(ma);
        radioIndicator.addMouseListener(ma);
    }

    public void setOnSelectListener(Runnable listener) {
        this.onSelectListener = listener;
    }

    public int getOptionIndex() {
        return optionIndex;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean sel) {
        this.selected = sel;
        repaint();
    }

    public void updateText(String text) {
        textLabel.setText(text);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        UITheme.enableAntiAliasing(g2);

        int w = getWidth();
        int h = getHeight();

        // Background
        Color bg;
        Color border;
        float stroke = 1.5f;

        if (selected) {
            bg = UITheme.OPTION_SELECTED;
            border = UITheme.OPTION_BORDER_SEL;
            stroke = 2.0f;
        } else if (hovered) {
            bg = UITheme.OPTION_HOVER;
            border = UITheme.BORDER_ACTIVE;
        } else {
            bg = UITheme.OPTION_DEFAULT;
            border = UITheme.BORDER_CARD;
        }

        g2.setColor(bg);
        g2.fillRoundRect(2, 2, w - 4, h - 4, 14, 14);

        g2.setColor(border);
        g2.setStroke(new BasicStroke(stroke));
        g2.drawRoundRect(2, 2, w - 5, h - 5, 14, 14);

        g2.dispose();
        super.paintComponent(g);
    }
}
