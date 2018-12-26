package character;

import static character.PlayerAttack.ATK_SIZE;
import game.Audio;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import map.Map;

public class tempAttack {

    LinkedList<PlayerAttack> List = new LinkedList<>();
    boolean flag = true;
    boolean flag_boom = false;
    boolean flagRemove = false;
    boolean special_attack =false;

    public void addList(PlayerAttack p_atk) {
        if (flag) {
            Thread t = new Thread(() -> {
                //new Audio("mario_09.wav",3000);
                new Audio("Battle City SFX (6).wav",1000);
            });
            t.start();
            List.add(p_atk);
            flag = false;
        }
    }

    public void removeList(PlayerAttack p_atk, Map map, Player p,tempAttack myAttack) {
        Thread t = new Thread(() -> {
            try {
                flagRemove = true;
                Thread.sleep(50);
                List.remove(p_atk);
                p_atk.attackCollision(map, true, p,myAttack,this);
                flagRemove = false;
                flag = true;
            } catch (InterruptedException ex) {
                Logger.getLogger(tempAttack.class.getName()).log(Level.SEVERE, null, ex);
            }catch(NullPointerException ex){}
        });
         t.start();
    }

    public void removeList(PlayerAttack p_atk, Map map, Player2 p,tempAttack myAttack) {
        
        Thread t = new Thread(() -> {
            try {
                flagRemove = true;
                Thread.sleep(50);
                List.remove(p_atk);
                p_atk.attackCollision(map, true, p, myAttack,this);
                flagRemove = false;
                flag = true;
            } catch (InterruptedException ex) {
                Logger.getLogger(tempAttack.class.getName()).log(Level.SEVERE, null, ex);
            }catch(NullPointerException ex){}
        });
        t.start();
    }

    public void listDraw(Graphics2D pDC) {
        PlayerAttack temp;
        try {
            for (int i = 0; i < List.size(); i++) {
                temp = List.get(i);
                if (temp.getBOOM() == false) {
                    temp.draw(pDC,this);
                } else {
                    temp.drawBOOM(pDC);
                }
            }
        } catch (NullPointerException ex) {

        }
    }

    public void listUpdate(Graphics2D pDC, Map map, Player p,tempAttack p1Attack) {
        PlayerAttack temp;
        for (int i = 0; i < List.size(); i++) {
            temp = List.get(i);
            if (temp.attackCollision(map, false, p,p1Attack,this)) {
                temp.flag_boom(true);
                removeList(temp, map, p,p1Attack);
                continue;
            }
            if (flagRemove == false) {
                temp.update(pDC,this);
            }
        }
    }

    public void listUpdate(Graphics2D pDC, Map map, Player2 p,tempAttack p2Attack) {
        PlayerAttack temp;
        for (int i = 0; i < List.size(); i++) {
            temp = List.get(i);
            if (temp.attackCollision(map, false, p,p2Attack,this)) {
                temp.flag_boom(true);
                removeList(temp, map, p,p2Attack);
                continue;
            }
            if (flagRemove == false) {
                temp.update(pDC,this);
            }
        }
    }
    
    public LinkedList<PlayerAttack> ReadList(){
        return this.List;
    }
    public void setSpecial_Attack(boolean special_attack){
        this.special_attack=special_attack;
    }
    public boolean getSpecial_Attack(){
        return this.special_attack;
    }
}
