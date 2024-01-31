package org.example;

import org.example.game.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Membres du groupe : Guillaume CALESSE & Vianney ");

        Game game = new Game();

        game.start();
    }
}