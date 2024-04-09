package de.cyklon;

import de.cyklon.jui.App;
import de.cyklon.jui.AppBuilder;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        new Main();
    }
    private final App app;

    //private long nextMove = 0;

    //private long nextDir = 0;

    public Main() {
        this.app = new AppBuilder().setSize(600, 600).setTitle("2048").setMaxFPS(AppBuilder.VSYNC).build();
        app.setCanvas(new MenuCanvas());
    }



}