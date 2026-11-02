import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Application Entry Point for the Group 12 Quiz Application.
 * Configures system anti-aliasing and initializes the Welcome Screen.
 */
public class Main {
    public static void main(String[] args) {
        // Enable high-quality anti-aliasing for text and 2D graphics
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Fallback to default Swing Look & Feel if system theme is not found
            }
            new WelcomeFrame().setVisible(true);
        });
    }
}
