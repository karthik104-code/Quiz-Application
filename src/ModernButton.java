import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * ModernButton is a custom styled JButton with rounded corners,
 * smooth hover/pressed feedback, and crisp anti-aliased rendering.
 */
public class ModernButton extends JButton {
    public enum Style {
        PRIMARY,
        SECONDARY,
        SUCCESS,
        PURPLE,
        DANGER
    }

    private final Style style;
    private final int cornerRadius;
    private boolean isHovered = false;
    private boolean isPressed = false;

    public ModernButton(String text) {
        this(text, Style.PRIMARY, 12);
    }

    public ModernButton(String text, Style style) {
        this(text, style, 12);
    }

    public ModernButton(String text, Style style, int cornerRadius) {
        super(text);
        this.style = style;
        this.cornerRadius = cornerRadius;

        setFont(UITheme.FONT_BUTTON);
        setForeground(UITheme.TEXT_PRIMARY);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBorder(UITheme.createPadding(12, 24, 12, 24));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isEnabled()) {
                    isHovered = true;
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (isEnabled()) {
                    isPressed = true;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        UITheme.enableAntiAliasing(g2);

        int w = getWidth();
        int h = getHeight();

        Color baseStart;
        Color baseEnd;
        Color borderColor = null;

        if (!isEnabled()) {
            baseStart = new Color(51, 65, 85);
            baseEnd = new Color(51, 65, 85);
            setForeground(UITheme.TEXT_MUTED);
        } else {
            setForeground(UITheme.TEXT_PRIMARY);
            switch (style) {
                case SECONDARY:
                    baseStart = isPressed ? new Color(24, 33, 47) : (isHovered ? new Color(37, 49, 72) : UITheme.BG_CARD);
                    baseEnd = baseStart;
                    borderColor = isHovered ? UITheme.ACCENT_PRIMARY : UITheme.BORDER_CARD;
                    break;
                case SUCCESS:
                    baseStart = isPressed ? new Color(21, 128, 61) : (isHovered ? new Color(34, 197, 94) : new Color(22, 163, 74));
                    baseEnd = isPressed ? new Color(16, 185, 129) : (isHovered ? new Color(52, 211, 153) : new Color(16, 185, 129));
                    break;
                case PURPLE:
                    baseStart = isPressed ? new Color(109, 40, 217) : (isHovered ? new Color(139, 92, 246) : new Color(124, 58, 237));
                    baseEnd = isPressed ? new Color(147, 51, 234) : (isHovered ? new Color(192, 132, 252) : new Color(168, 85, 247));
                    break;
                case DANGER:
                    baseStart = isPressed ? new Color(185, 28, 28) : (isHovered ? new Color(239, 68, 68) : new Color(220, 38, 38));
                    baseEnd = baseStart;
                    break;
                case PRIMARY:
                default:
                    baseStart = isPressed ? new Color(29, 78, 216) : (isHovered ? new Color(59, 130, 246) : new Color(37, 99, 235));
                    baseEnd = isPressed ? new Color(99, 102, 241) : (isHovered ? new Color(129, 140, 248) : new Color(79, 70, 229));
                    break;
            }
        }

        // Draw background
        GradientPaint gp = new GradientPaint(0, 0, baseStart, w, h, baseEnd);
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, w - 1, h - 1, cornerRadius, cornerRadius);

        // Draw border if configured
        if (borderColor != null) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawRoundRect(1, 1, w - 3, h - 3, cornerRadius, cornerRadius);
        }

        // Draw subtle inner highlight for premium look
        if (isEnabled() && style != Style.SECONDARY && !isPressed) {
            g2.setColor(new Color(255, 255, 255, 35));
            g2.drawRoundRect(1, 1, w - 3, h / 2, cornerRadius, cornerRadius);
        }

        g2.dispose();
        super.paintComponent(g);
    }
}
