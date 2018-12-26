/*

 */
package character;

import static character.Player.PLAYER_HEIGHT;
import static character.Player.PLAYER_WIDTH;
import static character.Player.life;
import static character.PlayerAttack.ATK_SIZE;
import game.GameLoop;
import map.Map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author AtthawutCPE
 */
public class Player2 {

    public static final int PLAYER_WIDTH = 40;
    public static final int PLAYER_HEIGHT = 40;
    int playerPostX, playerPostY;
    int walk_x, walk_y;
    final int WALK_SPEED = 1;
    boolean Attack = false;
    int atkx, atky;
    tempAttack temp_atk = new tempAttack();
    boolean f = false;
    int DIRECTION = -1;
    boolean left = false, right = false, down = false, up = false;
    int pX = 64;
    public static boolean life = true;
    boolean die = false;
    public static boolean getItem = false;

    public Player2(int playerPostX, int playerPostY) {
        this.playerPostX = playerPostX;
        this.playerPostY = playerPostY;
    }

    public void keyDown(KeyEvent e) {
        if (!life) {
            return;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                if (down || left || right) {
                    break;
                }
                pX = 0;
                up = true;
                DIRECTION = 1;
                walk_y = -WALK_SPEED;
                break;
            case KeyEvent.VK_S:
                if (up || left || right) {
                    break;
                }
                pX = 64;
                down = true;
                walk_y = +WALK_SPEED;
                DIRECTION = -1;
                break;
            case KeyEvent.VK_A:
                if (down || up || right) {
                    break;
                }
                pX = 33;
                left = true;
                walk_x = -WALK_SPEED;
                DIRECTION = -2;
                break;
            case KeyEvent.VK_D:
                if (down || left || up) {
                    break;
                }
                pX = 95;
                right = true;
                walk_x = WALK_SPEED;
                DIRECTION = 2;
                break;
            case KeyEvent.VK_SPACE:
//                atkx = playerPostX;
//                atky = playerPostY;
//                switch (DIRECTION) {
//                    case 1:
//                        temp_atk.addList(new PlayerAttack(atkx + 15, atky - 5, DIRECTION));
//                        break;
//                    case -1:
//                        temp_atk.addList(new PlayerAttack(atkx + 15, atky + PLAYER_HEIGHT, DIRECTION));
//                        break;
//                    case 2:
//                        temp_atk.addList(new PlayerAttack(atkx + PLAYER_WIDTH, atky + 17, DIRECTION));
//                        break;
//                    case -2:
//                        temp_atk.addList(new PlayerAttack(atkx - 5, atky + 17, DIRECTION));
//                        break;
//                    default:
//                        break;
//                }
                break;
        }
    }

    public void keyUp(KeyEvent e) {
        if (!life) {
            return;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                up = false;
                walk_y = 0;
                break;
            case KeyEvent.VK_S:
                down = false;
                walk_y = 0;
                break;
            case KeyEvent.VK_A:
                left = false;
                walk_x = 0;
                break;
            case KeyEvent.VK_D:
                right = false;
                walk_x = 0;
                break;
            case KeyEvent.VK_SPACE:
                atkx = playerPostX;
                atky = playerPostY;
                switch (DIRECTION) {
                    case 1:
                        temp_atk.addList(new PlayerAttack(atkx + 15, atky - 5, DIRECTION));
                        break;
                    case -1:
                        temp_atk.addList(new PlayerAttack(atkx + 15, atky + PLAYER_HEIGHT, DIRECTION));
                        break;
                    case 2:
                        temp_atk.addList(new PlayerAttack(atkx + PLAYER_WIDTH, atky + 17, DIRECTION));
                        break;
                    case -2:
                        temp_atk.addList(new PlayerAttack(atkx - 5, atky + 17, DIRECTION));
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    public void draw(Graphics2D pDC) {
        ImageIcon img = new ImageIcon(getClass().getResource("/res/NES - Battle City - General Sprites.png"));
        Image image = img.getImage();
        Thread t = new Thread(() -> {
            try {
                pDC.drawImage(image, playerPostX, playerPostY, playerPostX + PLAYER_WIDTH, playerPostY + PLAYER_HEIGHT, 306, 130, 333, 158, null);
                Thread.sleep(200);
                GameLoop.game = false;
                die = true;
                this.draw(pDC);
            } catch (InterruptedException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        if (!life) {
            if (die) {
                return;
            }
            t.start();
        }
        pDC.drawImage(image, playerPostX, playerPostY, playerPostX + PLAYER_WIDTH, playerPostY + PLAYER_HEIGHT, pX, 130, pX + 15, 144, null);
    }

    public Rectangle getPlayerRect() {
        return new Rectangle(playerPostX + walk_x, playerPostY/*+PLAYER_HEIGHT/2*/ + walk_y, PLAYER_WIDTH, PLAYER_HEIGHT/*/2*/);
    }

    public void Update(Map map, Graphics2D pDC, Player p) {
        if (!life) {
            return;
        }
        temp_atk.listDraw(pDC);
        temp_atk.listUpdate(pDC, map, p, p.getMyAttackRect());
        for (int j = 0; j < 30; j++) {
            for (int i = 0; i < 50; i++) {
                Rectangle r = new Rectangle(i * Map.TILE_WIDTH, j * Map.TILE_HEIGHT, Map.TILE_WIDTH, Map.TILE_HEIGHT);
                if (getPlayerRect().intersects(r) && ((map.getmap()[j][i] == 1) || (map.getmap()[j][i] == 2) || (map.getmap()[j][i] == 4) || (map.getmap()[j][i] == 5))) {
                    return;
                }
            }
        }
        Rectangle r = new Rectangle(12 * Map.ITEM_WIDTH, 7 * Map.ITEM_HEIGHT, Map.ITEM_WIDTH, Map.ITEM_HEIGHT);
        if (getPlayerRect().intersects(r) && Map.getItem == false) {
            temp_atk.setSpecial_Attack(true);
            Map.getItem = true;
            return;
        }
        if (getPlayerRect().intersects(p.getPlayerRect()) && Player.life == true) {
            return;
        }
        playerPostX += walk_x;
        playerPostY += walk_y;
    }

    public tempAttack getMyAttackRect() {
        return temp_atk;
    }
}
