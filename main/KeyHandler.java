package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean up, down, left, right, enter;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //play state
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                up = true;
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                down = true;
            }
            if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
                left = true;
            }
            if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
                right = true;
            }

            if (code == KeyEvent.VK_ENTER) {
                enter = true;
            }

            if(code == KeyEvent.VK_J){
               gp.player.attacking = true;
               gp.attack.start();
            }
        }

        if (gp.gameState == gp.dialogueState) { //dialogueState
            if (code == KeyEvent.VK_ENTER) {
                gp.cursor.start();
                gp.gameState = gp.playState;
            }
        }

        // title state
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                gp.ui.menuOption--;
                gp.cursor.start();
                if (gp.ui.menuOption < 0) {
                    gp.ui.menuOption = 1;
                }
            }
            if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                gp.ui.menuOption++;
                gp.cursor.start();
                if (gp.ui.menuOption > 1) {
                    gp.ui.menuOption = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.cursor.start();
                if (gp.ui.menuOption == 0) {
                    gp.gameState = gp.playState;
                }
                if (gp.ui.menuOption == 1) {
                    System.exit(0);
                }
            }
        }

        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            up = true;
        }

        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            down = true;
        }

        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            left = true;
        }

        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            right = true;
        }


        //Pause game
        if (code == KeyEvent.VK_P) {
            gp.cursor.start();
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }

//
//        //DEBUG FAIL SCREEN
//        if (code == KeyEvent.VK_B) {
//            gp.player.health = 0;
//        }

        //open & close option
        if (code == KeyEvent.VK_ESCAPE) {
            gp.cursor.start();
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.optionsState;
            } else if (gp.gameState == gp.optionsState) {
                gp.gameState = gp.playState;
            }
        }

        //option cursor moving
        if (gp.gameState == gp.optionsState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.cursor.start();
                enter = true;
            }

            int maxCommandNum = 0;
            switch (gp.ui.subState) {
                case 0:
                    maxCommandNum = 4;
                    break;
                case 3:
                    maxCommandNum = 1;
                    break;
            }

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.menuOption--;
                gp.cursor.start();
                if (gp.ui.menuOption < 0) {
                    gp.ui.menuOption = maxCommandNum;
                }
            }

            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.menuOption++;
                gp.cursor.start();
                if (gp.ui.menuOption > maxCommandNum) {
                    gp.ui.menuOption = 0;
                }
            }

            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                gp.cursor.start();
                if (gp.ui.subState == 0) {
                    if (gp.ui.menuOption == 0 && gp.defaultBgm.volumeScale > 0) {
                        gp.defaultBgm.volumeScale--;
                        gp.defaultBgm.checkVolume();
                        gp.bossBgm.volumeScale--;
                        gp.bossBgm.checkVolume();
                    }

                    if (gp.ui.menuOption == 1 && gp.defaultBgm.volumeScale > 0) {
                        gp.gotBone.volumeScale--;
                        gp.gotBone.checkVolume();
                        gp.attack.volumeScale--;
                        gp.attack.checkVolume();
                        gp.cursor.volumeScale--;
                        gp.cursor.checkVolume();
                    }
                }
            }

            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                gp.cursor.start();
                if (gp.ui.subState == 0) {
                    if (gp.ui.menuOption == 0 && gp.defaultBgm.volumeScale < 5) {
                        gp.defaultBgm.volumeScale++;
                        gp.defaultBgm.checkVolume();
                        gp.bossBgm.volumeScale++;
                        gp.bossBgm.checkVolume();
                    }

                    if (gp.ui.menuOption == 1 && gp.defaultBgm.volumeScale < 5) {
                        gp.gotBone.volumeScale++;
                        gp.gotBone.checkVolume();
                        gp.attack.volumeScale++;
                        gp.attack.checkVolume();
                        gp.cursor.volumeScale++;
                        gp.cursor.checkVolume();
                    }
                }
            }
        }

        //game over
        if (gp.gameState == gp.gameOverState) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.menuOption--;
                gp.cursor.start();
                if (gp.ui.menuOption < 0) {
                    gp.ui.menuOption = 1;
                }
            }

            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.menuOption++;
                gp.cursor.start();
                if (gp.ui.menuOption > 1) {
                    gp.ui.menuOption = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER) {
                gp.cursor.start();
                if (gp.ui.menuOption == 0) {
                    gp.gameState = gp.playState;
                    gp.resetGame();
                } else if (gp.ui.menuOption == 1) {
                    gp.gameState = gp.titleState;
                    gp.resetGame();
                }
            }

        }

        //game win
        if (gp.gameState == gp.winState) {

            if (code == KeyEvent.VK_ENTER) {
                gp.cursor.start();
                if (gp.ui.menuOption == 0) {
                    gp.gameState = gp.playState;
                    System.exit(0);
                }
            }

        }


    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
            up = false;
        }
        if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
            down = false;
        }
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            left = false;
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            right = false;
        }

        if(code == KeyEvent.VK_J){
            gp.player.attacking = false;
        }
    }
}