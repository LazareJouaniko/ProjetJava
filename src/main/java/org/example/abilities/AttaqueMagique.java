package org.example.abilities;

import org.example.game.*;

import java.util.Scanner;

public class AttaqueMagique implements SuperAbility {

    @Override
    public void use(Game game) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Attaque Magique !");
    }
}
