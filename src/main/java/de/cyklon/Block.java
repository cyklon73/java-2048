package de.cyklon;

import de.cyklon.jui.App;
import de.cyklon.jui.component.UIComponent;
import de.cyklon.jui.render.FontRenderer;

import java.awt.*;
import java.util.Map;

public class Block extends UIComponent {

    private static final Font FONT = new Font("Arial", Font.BOLD, 54);

    private static final Map<Integer, Color> colors = Map.ofEntries(
            Map.entry(0, new Color(204, 190, 179)),
            Map.entry(2, new Color(238, 228, 218)),
            Map.entry(4, new Color(237, 224, 200)),
            Map.entry(8, new Color(242, 177, 121)),
            Map.entry(16, new Color(245, 149, 99)),
            Map.entry(32, new Color(246, 124, 95)),
            Map.entry(64, new Color(246, 94, 59)),
            Map.entry(128, new Color(237, 207, 114)),
            Map.entry(256, new Color(237, 204, 97)),
            Map.entry(512, new Color(237, 200, 80)),
            Map.entry(1024, new Color(237, 197, 63))
    );

    private int value;
    private Color color;
    private boolean blocked = false;

    public Block(int value, int x, int y, int width, int height) {
        super(x, y, width, height);
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        this.color = colors.getOrDefault(value, Color.WHITE);
    }

    @Override
    protected void render(App app, Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(new Color(60, 60, 60));

        if (value==0) return;

        FontRenderer fr = new FontRenderer(g);
        double dig = Math.log10(value);
        fr.setFont(FONT.deriveFont((float) (54 - dig*6)));
        fr.setAlignment(FontRenderer.Alignment.CENTERED);
        fr.drawString(String.valueOf(value), x + width/2, (int) (y + 55 - dig*2));
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
