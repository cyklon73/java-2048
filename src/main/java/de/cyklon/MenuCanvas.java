package de.cyklon;

import de.cyklon.jui.UICanvas;
import de.cyklon.jui.component.UIButton;
import de.cyklon.jui.component.UICanvasButton;
import de.cyklon.jui.component.UILabel;
import de.cyklon.jui.input.Shortcut;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuCanvas extends UICanvas {

    public MenuCanvas() {
        setBgColor(new Color(41, 222, 141));
        UIButton button = new UICanvasButton(250, 250, 100, 30, GameCanvas::new);
        button.addShortcut(Shortcut.ofKeyboard(KeyEvent.VK_ENTER));
        button.addShortcut(Shortcut.ofKeyboard(KeyEvent.VK_SPACE));
        UILabel label = new UILabel(40, 20, "Play");
        label.setColor(new Color(200, 200, 200));
        button.addComponents(label);
        addComponents(button);
    }

}
