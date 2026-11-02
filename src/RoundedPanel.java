import java.awt.*;
import javax.swing.*;

/**
 * A reusable JPanel with smooth rounded corners, customizable background,
 * optional border, and gradient fill support.
 */
public class RoundedPanel extends JPanel {
    private int cornerRadius;
    private Color backgroundColor;
    private Color borderColor;
    private int borderThickness;
    private boolean gradientEnabled;
    private Color gradientEndColor;

    public RoundedPanel(int radius, Color bg) {
        this(radius, bg, null, 0);
    }

    public RoundedPanel(int radius, Color bg, Color border, int borderThickness) {
        super();
        this.cornerRadius = radius;
        this.backgroundColor = bg;
        this.borderColor = border;
        this.borderThickness = borderThickness;
        this.gradientEnabled = false;
        setOpaque(false);
    }

    public void setGradient(Color startColor, Color endColor) {
        this.backgroundColor = startColor;
        this.gradientEndColor = endColor;
        this.gradientEnabled = true;
        repaint();
    }

    public void setCustomBackground(Color bg) {
        this.backgroundColor = bg;
        this.gradientEnabled = false;
        repaint();
    }

    public void setCustomBorder(Color border, int thickness) {
        this.borderColor = border;
        this.borderThickness = thickness;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        UITheme.enableAntiAliasing(g2);

        int width = getWidth();
        int height = getHeight();

        // Fill background
        if (gradientEnabled && gradientEndColor != null) {
            GradientPaint gp = new GradientPaint(0, 0, backgroundColor, width, height, gradientEndColor);
            g2.setPaint(gp);
        } else if (backgroundColor != null) {
            g2.setColor(backgroundColor);
        }

        int offset = borderThickness > 0 ? borderThickness / 2 : 0;
        int drawW = width - (borderThickness > 0 ? borderThickness : 1);
        int drawH = height - (borderThickness > 0 ? borderThickness : 1);

        g2.fillRoundRect(offset, offset, drawW, drawH, cornerRadius, cornerRadius);

        // Draw border if present
        if (borderColor != null && borderThickness > 0) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.drawRoundRect(offset, offset, drawW, drawH, cornerRadius, cornerRadius);
        }

        g2.dispose();
    }
}
