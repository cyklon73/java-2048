package de.cyklon;

import de.cyklon.jui.UICanvas;
import de.cyklon.jui.component.UIButton;
import de.cyklon.jui.component.UILabel;

import java.awt.*;

public class MenuCanvas extends UICanvas {

    public MenuCanvas() {
        setBgColor(new Color(41, 222, 141));
        UIButton button = new UIButton(250, 250, 100, 30, (app, btn) -> app.setCanvas(new GameCanvas(app)));
        UILabel label = new UILabel(40, 20, "Play");
        label.setColor(new Color(200, 200, 200));
        button.addComponents(label);
        addComponents(button);
    }

}
