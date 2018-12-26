package character;

import static character.Player.PLAYER_HEIGHT;
import static character.Player.PLAYER_WIDTH;
import game.Audio;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import map.Map;
import static map.Map.TILE_HEIGHT;
import static map.Map.TILE_WIDTH;

public class PlayerAttack {

    public static int ATK_SIZE = 10;
    int atkX, atkY;
    int DIRECTION = 0;
    int ATK_SPEED = 3;
    static int x, y, size = ATK_SIZE;
    boolean boom = false;

    public PlayerAttack(int atkX, int atkY, int DIRECTION) {
        this.atkX = atkX;
        this.atkY = atkY;
        this.DIRECTION = DIRECTION;
    }

    public void flag_boom(boolean boom) {
        this.boom = boom;
    }

    public boolean getBOOM() {
        return this.boom;
    }

    public void draw(Graphics2D pDC, tempAttack myAttack) {
        if (myAttack.getSpecial_Attack()) {
            pDC.setColor(Color.red);
            pDC.fillOval(atkX, atkY, ATK_SIZE, ATK_SIZE);
        } else {
            pDC.setColor(Color.blue);
            pDC.fillOval(atkX, atkY, ATK_SIZE, ATK_SIZE);
        }
    }

    public void drawBOOM(Graphics2D pDC) {
        ImageIcon img = new ImageIcon("c:/Users/AtthawutCPE/Documents/NetBeansProjects/MarioGame/Mario/src/res/NES - Battle City - General Sprites.png");
        Image image = img.getImage();
//        pDC.setColor(Color.red);
//        pDC.fillOval(atkX - 7, atkY - 7, ATK_SIZE * 2, ATK_SIZE * 2);
        pDC.drawImage(image, atkX - 7, atkY - 7, atkX - 7 + ATK_SIZE * 2, atkY - 7 + ATK_SIZE * 2, 258, 130, 269, 142, null);
    }

    public void update(Graphics2D g2d,tempAttack myAttack) {
        if(myAttack.getSpecial_Attack())
            ATK_SPEED=6;
        switch (this.DIRECTION) {
            case 1:
                atkY -= ATK_SPEED;
                break;
            case -1:
                atkY += ATK_SPEED;
                break;
            case 2:
                atkX += ATK_SPEED;
                break;
            case -2:
                atkX -= ATK_SPEED;
                break;
            default:
                break;
        }
        x = atkX;
        y = atkY;
    }

    public static Rectangle getRect() {
        return new Rectangle(x, y, size, size);
    }

    public Rectangle getAttackRect() {
        return new Rectangle(atkX, atkY, ATK_SIZE, ATK_SIZE);
    }

    public boolean attackCollision(Map map, boolean remove, Player p, tempAttack p1Attack, tempAttack myAttack) {
        for (int j = 0; j < 32; j++) {
            for (int i = 0; i < 50; i++) {
                Rectangle r = new Rectangle(i * Map.TILE_WIDTH, j * Map.TILE_HEIGHT, Map.TILE_WIDTH, Map.TILE_HEIGHT);
                if (getAttackRect().intersects(r) && ((map.getmap()[j][i] == 1) || (map.getmap()[j][i] == 2) || (map.getmap()[j][i] == 4))) {
                    if (myAttack.getSpecial_Attack()) {
                        if ((map.getmap()[j][i] == 2 || map.getmap()[j][i] == 4) && remove == true) {
                            map.getmap()[j][i] = 0;
                        }
                    } else {
                        if ((map.getmap()[j][i] == 2) && remove == true) {
                            map.getmap()[j][i] = 0;
                        }
                    }
                    new Audio("Battle City SFX (5).wav", 1000);
                    return true;
                }
            }
        }
        Rectangle r = new Rectangle(12 * Map.ITEM_WIDTH, 7 * Map.ITEM_HEIGHT, Map.ITEM_WIDTH, Map.ITEM_HEIGHT);
        if (getAttackRect().intersects(r) && Map.getItem == false) {
            return true;
        }
        if (getAttackRect().intersects(p.getPlayerRect()) && Player.life == true) {
            new Audio("Battle City SFX (8).wav", 1000);
            Player.life = false;
            return true;
        }
        PlayerAttack temp;
        for (int i = 0; i < p1Attack.ReadList().size(); i++) {
            temp = p1Attack.ReadList().get(i);
            if (getAttackRect().intersects(temp.getAttackRect())) {
                return true;
            }
        }
        return false;
    }

    public boolean attackCollision(Map map, boolean remove, Player2 p, tempAttack p2Attack, tempAttack myAttack) {
        for (int j = 0; j < 30; j++) {
            for (int i = 0; i < 50; i++) {
                Rectangle r = new Rectangle(i * Map.TILE_WIDTH, j * Map.TILE_HEIGHT, Map.TILE_WIDTH, Map.TILE_HEIGHT);
                if (getAttackRect().intersects(r) && ((map.getmap()[j][i] == 1) || (map.getmap()[j][i] == 2) || (map.getmap()[j][i] == 4))) {
                    if (myAttack.getSpecial_Attack()) {
                        if ((map.getmap()[j][i] == 2 || map.getmap()[j][i] == 4) && remove == true) {
                            map.getmap()[j][i] = 0;
                        }
                    } else {
                        if ((map.getmap()[j][i] == 2) && remove == true) {
                            map.getmap()[j][i] = 0;
                        }
                    }
                    new Audio("Battle City SFX (5).wav", 1000);
                    return true;
                }
            }
        }
        Rectangle r = new Rectangle(12 * Map.ITEM_WIDTH, 7 * Map.ITEM_HEIGHT, Map.ITEM_WIDTH, Map.ITEM_HEIGHT);
        if (getAttackRect().intersects(r) && Map.getItem == false) {
            return true;
        }
        if (getAttackRect().intersects(p.getPlayerRect()) && Player2.life == true) {
            new Audio("Battle City SFX (8).wav", 1000);
            Player2.life = false;
            return true;
        }
        PlayerAttack temp;
        for (int i = 0; i < p2Attack.ReadList().size(); i++) {
            temp = p2Attack.ReadList().get(i);
            if (getAttackRect().intersects(temp.getAttackRect())) {
                return true;
            }
        }
        return false;
    }
}
