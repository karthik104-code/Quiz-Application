import java.awt.*;
import javax.swing.*;

/**
 * ModernProgressBar is a custom styled progress bar featuring rounded edges,
 * a slate navy track, and a vibrant blue-to-purple gradient fill.
 */
public class ModernProgressBar extends JProgressBar {
    private final int cornerRadius;

    public ModernProgressBar(int min, int max) {
        super(min, max);
        this.cornerRadius = 10;
        setOpaque(false);
        setBorderPainted(false);
        setStringPainted(false);
        setPreferredSize(new Dimension(200, 12));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        UITheme.enableAntiAliasing(g2);

        int w = getWidth();
        int h = getHeight();

        // Draw track
        g2.setColor(UITheme.BG_CARD_LIGHT);
        g2.fillRoundRect(0, 0, w, h, cornerRadius, cornerRadius);

        // Draw border
        g2.setColor(UITheme.BORDER_CARD);
        g2.setStroke(new BasicStroke(1.0f));
        g2.drawRoundRect(0, 0, w - 1, h - 1, cornerRadius, cornerRadius);

        // Draw progress fill
        double percent = getPercentComplete();
        int fillW = (int) (w * percent);
        if (fillW > 0) {
            GradientPaint gp = new GradientPaint(0, 0, UITheme.ACCENT_PRIMARY, fillW, 0, UITheme.ACCENT_GRAD_END);
            g2.setPaint(gp);
            g2.fillRoundRect(1, 1, Math.max(fillW - 2, cornerRadius), h - 2, cornerRadius, cornerRadius);
        }

        g2.dispose();
    }
}
