package de.cyklon;

import de.cyklon.jui.App;
import de.cyklon.jui.component.UIComponent;

import java.awt.*;
import java.util.Map;

public class Block extends UIComponent {

    private static final Font FONT = new Font("Arial", Font.BOLD, 54);

    private static final Map<Integer, Color> colors = Map.of(
            2, new Color(238, 228, 218),
            4, new Color(237, 224, 200),
            8, new Color(242, 177, 121),
            16, new Color(245, 149, 99),
            32, new Color(246, 124, 95),
            64, new Color(246, 94, 59),
            128, new Color(237, 207, 114),
            256, new Color(237, 204, 97),
            512, new Color(237, 200, 80),
            1024, new Color(237, 197, 63)
    );

    private int value;
    private Color color;

    public Block(int value, int x, int y, int width, int height) {
        super(x, y, width, height);
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if (value!=0) this.color = colors.getOrDefault(value, Color.WHITE);
    }

    @Override
    protected void render(App app, Graphics g) {
        if (value==0) return;
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(new Color(60, 60, 60));
        g.setFont(FONT);
        g.drawString(String.valueOf(value), x + 25, y + 55);
    }
}
