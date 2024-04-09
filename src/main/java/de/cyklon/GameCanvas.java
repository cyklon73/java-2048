package de.cyklon;

import de.cyklon.jui.App;
import de.cyklon.jui.UICanvas;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameCanvas extends UICanvas {

    private final Random random = new Random();
    private final Block[][] blocks = new Block[4][4];

    public GameCanvas(App app) {
        setBgColor(new Color(41, 222, 141));
        int i1 = 0;
        for (int x = 50; x < (80+50)*4; x+=80+50) {
            int i2 = 0;
            for (int y = 50; y < (80+50)*4; y+=80+50) {
                addComponents(blocks[i1][i2] = new Block(0, x, y, 80, 80));
                i2++;
            }
            i1++;
        }
        spawn(app);
        spawn(app);
        app.runTask(a -> {
            if (a.getKeyboard().isClicked(KeyEvent.VK_RIGHT)) moveRight(app, true);
            if (a.getKeyboard().isClicked(KeyEvent.VK_LEFT)) moveLeft(app, true);
            if (a.getKeyboard().isClicked(KeyEvent.VK_UP)) moveUp(app, true);
            if (a.getKeyboard().isClicked(KeyEvent.VK_DOWN)) moveDown(app, true);
        });
    }

    private boolean isBlockValid(int x, int y) {
        int value = blocks[x][y].getValue();
        boolean valid = x > 0 && blocks[x - 1][y].getValue() == value;
        if (x<blocks.length-1 && blocks[x+1][y].getValue()==value) valid = true;
        if (y>0 && blocks[x][y-1].getValue()==value) valid = true;
        if (y<blocks[x].length-1 && blocks[x][y+1].getValue()==value) valid = true;
        return valid;
    }

    private boolean isGameOver() {
        boolean gameOver = true;
        for (int x = 0; x < blocks.length; x++) {
            for (int y = 0; y < blocks[x].length; y++) {
                if (isBlockValid(x, y)) {
                    gameOver = false;
                    break;
                }
            }
        }
        return gameOver && !isSpace();
    }

    private boolean isSpace() {
        boolean free = false;
        for (Block[] block : blocks) {
            for (Block block1 : block) {
                if (block1.getValue() == 0) {
                    free = true;
                    break;
                }
            }
        }
        return free;
    }

    private void spawn(App app) {
        if (!isSpace()) return;
        int x;
        int y;

        do {
            x = random.nextInt(4);
            y = random.nextInt(4);
        } while (blocks[x][y].getValue()!=0);

        blocks[x][y].setValue(2);
        if (isGameOver()) app.setCanvas(new GameOverCanvas());
    }

    private void moveRight(App app, boolean initial) {
        boolean moved = false;
        for (int x = blocks.length - 1; x >= 0; x--) {
            if (x!=blocks.length-1) {
                for (int y = 0; y < blocks[x].length; y++) {
                    if (blocks[x][y].getValue()!=0) {
                        if (blocks[x+1][y].getValue()==0) {
                            blocks[x+1][y].setValue(blocks[x][y].getValue());
                            blocks[x][y].setValue(0);
                            moved = true;
                        } else if (blocks[x+1][y].getValue()==blocks[x][y].getValue()) {
                            blocks[x+1][y].setValue(blocks[x][y].getValue()*2);
                            blocks[x][y].setValue(0);
                        }
                    }
                }
            }
        }
        if (moved) moveRight(app, false);
        else if (!initial) spawn(app);
    }

    private void moveLeft(App app, boolean initial) {
        boolean moved = false;
        for (int x = 0; x < blocks.length; x++) {
            if (x!=0) {
                for (int y = 0; y < blocks[x].length; y++) {
                    if(blocks[x][y].getValue()!=0) {
                        if (blocks[x-1][y].getValue()==0) {
                            blocks[x-1][y].setValue(blocks[x][y].getValue());
                            blocks[x][y].setValue(0);
                            moved = true;
                        } else if (blocks[x-1][y].getValue()==blocks[x][y].getValue()) {
                            blocks[x-1][y].setValue(blocks[x][y].getValue()*2);
                            blocks[x][y].setValue(0);
                        }
                    }
                }
            }
        }
        if (moved) moveLeft(app, false);
        else if (!initial) spawn(app);
    }

    private void moveDown(App app, boolean initial) {
        boolean moved = false;
        for (Block[] block : blocks) {
            for (int y = block.length - 1; y >= 0; y--) {
                if (y != block.length - 1) {
                    if (block[y].getValue() != 0) {
                        if (block[y + 1].getValue() == 0) {
                            block[y + 1].setValue(block[y].getValue());
                            block[y].setValue(0);
                            moved = true;
                        } else if (block[y + 1].getValue() == block[y].getValue()) {
                            block[y + 1].setValue(block[y].getValue() * 2);
                            block[y].setValue(0);
                        }
                    }
                }
            }
        }
        if (moved) moveDown(app, false);
        else if (!initial) spawn(app);
    }

    private void moveUp(App app, boolean initial) {
        boolean moved = false;
        for (Block[] block : blocks) {
            for (int y = 0; y < block.length; y++) {
                if (y!=0) {
                    if (block[y].getValue()!=0) {
                        if (block[y-1].getValue()==0) {
                            block[y-1].setValue(block[y].getValue());
                            block[y].setValue(0);
                            moved = true;
                        } else if (block[y-1].getValue()==block[y].getValue()) {
                            block[y-1].setValue(block[y].getValue()*2);
                            block[y].setValue(0);
                        }
                    }
                }
            }
        }
        if (moved) moveUp(app, false);
        else if (!initial) spawn(app);
    }
}
