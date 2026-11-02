import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * UITheme encapsulates the visual styling system for the Quiz Application.
 * Follows a modern dark navy, royal blue, and purple accent palette
 * designed for an educational technology desktop application.
 */
public final class UITheme {

    // Prevent instantiation
    private UITheme() {}

    // --- Color Palette ---
    public static final Color BG_DARK          = new Color(15, 23, 42);      // #0F172A - Deep slate navy
    public static final Color BG_CARD          = new Color(30, 41, 59);      // #1E293B - Card slate
    public static final Color BG_CARD_LIGHT    = new Color(40, 53, 76);      // #28354C - Lighter card
    public static final Color BORDER_CARD      = new Color(51, 65, 85);      // #334155 - Subtle border
    public static final Color BORDER_ACTIVE    = new Color(99, 102, 241);    // #6366F1 - Accent border

    // Accents & Buttons
    public static final Color ACCENT_PRIMARY   = new Color(59, 130, 246);    // #3B82F6 - Electric Blue
    public static final Color ACCENT_SECONDARY = new Color(99, 102, 241);    // #6366F1 - Indigo
    public static final Color ACCENT_PURPLE    = new Color(139, 92, 246);    // #8B5CF6 - Royal Purple
    public static final Color ACCENT_GRAD_END  = new Color(168, 85, 247);    // #A855F7 - Purple gradient end

    // Interactive States
    public static final Color BUTTON_HOVER     = new Color(37, 99, 235);     // Darker blue
    public static final Color BUTTON_PRESSED   = new Color(29, 78, 216);
    public static final Color OPTION_DEFAULT   = new Color(24, 33, 47);
    public static final Color OPTION_HOVER     = new Color(37, 49, 72);
    public static final Color OPTION_SELECTED  = new Color(30, 58, 138);     // Deep blue
    public static final Color OPTION_BORDER_SEL= new Color(129, 140, 248);   // Glowing border

    // Status Colors
    public static final Color SUCCESS          = new Color(34, 197, 94);     // #22C55E - Green
    public static final Color SUCCESS_BG       = new Color(20, 83, 45, 100);
    public static final Color DANGER           = new Color(239, 68, 68);     // #EF4444 - Red
    public static final Color DANGER_BG        = new Color(127, 29, 29, 100);
    public static final Color WARNING          = new Color(245, 158, 11);    // Amber
    public static final Color GOLD             = new Color(251, 191, 36);

    // Text Colors
    public static final Color TEXT_PRIMARY     = new Color(248, 250, 252);   // Pure white/ice
    public static final Color TEXT_SECONDARY   = new Color(148, 163, 184);   // Slate silver
    public static final Color TEXT_MUTED       = new Color(100, 116, 139);   // Dim gray-blue
    public static final Color TEXT_ACCENT      = new Color(165, 180, 252);   // Soft indigo text

    // --- Typography ---
    private static final String FONT_FAMILY = getPreferredFontFamily();

    public static final Font FONT_HEADER_TITLE = new Font(FONT_FAMILY, Font.BOLD, 26);
    public static final Font FONT_TITLE        = new Font(FONT_FAMILY, Font.BOLD, 22);
    public static final Font FONT_SUBTITLE     = new Font(FONT_FAMILY, Font.PLAIN, 14);
    public static final Font FONT_SECTION      = new Font(FONT_FAMILY, Font.BOLD, 17);
    public static final Font FONT_QUESTION     = new Font(FONT_FAMILY, Font.BOLD, 18);
    public static final Font FONT_OPTION       = new Font(FONT_FAMILY, Font.PLAIN, 15);
    public static final Font FONT_BUTTON       = new Font(FONT_FAMILY, Font.BOLD, 15);
    public static final Font FONT_BODY         = new Font(FONT_FAMILY, Font.PLAIN, 14);
    public static final Font FONT_SMALL        = new Font(FONT_FAMILY, Font.PLAIN, 12);
    public static final Font FONT_BADGE        = new Font(FONT_FAMILY, Font.BOLD, 12);
    public static final Font FONT_SCORE_BIG    = new Font(FONT_FAMILY, Font.BOLD, 46);

    // Preferred font selector with cross-platform fallbacks
    private static String getPreferredFontFamily() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] available = ge.getAvailableFontFamilyNames();
        for (String candidate : new String[]{"Segoe UI", "Inter", "Roboto", "Arial", "SansSerif"}) {
            for (String fontName : available) {
                if (fontName.equalsIgnoreCase(candidate)) {
                    return candidate;
                }
            }
        }
        return "SansSerif";
    }

    // --- Rendering Helpers ---
    public static void enableAntiAliasing(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }

    public static Border createPadding(int top, int left, int bottom, int right) {
        return new EmptyBorder(top, left, bottom, right);
    }
}
