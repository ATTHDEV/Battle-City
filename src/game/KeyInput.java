/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import character.Player;
import character.Player2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author AtthawutCPE
 */
public class KeyInput extends KeyAdapter {

    Player player;
    Player2 player2;

    public KeyInput(Player player, Player2 player2) {
        this.player = player;
        this.player2 = player2;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyDown(e);
        player2.keyDown(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyUp(e);
        player2.keyUp(e);
    }

}
