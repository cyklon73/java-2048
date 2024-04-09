package de.cyklon;

import de.cyklon.jui.UICanvas;
import de.cyklon.jui.component.UIButton;
import de.cyklon.jui.component.UILabel;

import java.awt.*;

public class GameOverCanvas extends UICanvas {

    public GameOverCanvas() {
        setBgColor(new Color(41, 222, 141));
        addComponents(new UILabel(250, 150, "Game Over"));
        UIButton button = new UIButton(250, 250, 100, 30, (app, btn) -> app.setCanvas(new GameCanvas(app)));
        UILabel label = new UILabel(20, 20, "Play Again");
        label.setColor(new Color(200, 200, 200));
        button.addComponents(label);
        addComponents(button);
    }

}
