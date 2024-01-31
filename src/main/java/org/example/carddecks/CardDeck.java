package org.example.carddecks;

import org.example.monsters.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class CardDeck {
    private List<Monster> desk;
    private List<Monster> hand;
    private List<Monster> game;

    public CardDeck() {
        this.desk = new ArrayList<Monster>();
        this.hand = new ArrayList<Monster>();
        this.game = new ArrayList<Monster>();
    }

    public CardDeck(List<Monster> desk, List<Monster> hand, List<Monster> game) {
        this.desk = desk;
        this.hand = hand;
        this.game = game;
    }

    public List<Monster> getDesk() {
        return desk;
    }

    public void setDesk(List<Monster> desk) {
        this.desk = desk;
    }

    public List<Monster> getHand() {
        return hand;
    }

    public List<Monster> getGame() {
        return game;
    }

    public void setHand(List<Monster> hand) {
        this.hand = hand;
    }

    public void addMonster(Monster monster) {
        this.desk.add(monster);
    }

    public void setGame(List<Monster> game) {
        this.game = game;
    }
    public void pick() {
        Random rand = new Random();
        int max = this.desk.size();
        int min = 0;
        int nombreAleatoire = rand.nextInt(max - min + 1) + min;
        Monster monster = this.desk.get(nombreAleatoire);
        this.hand.add(monster);
        this.desk.remove(monster);
        System.out.println("Vous avez pioché un : "+monster.getName());
    }

    public void placeCard(Monster monster) {
        this.game.add(monster);
        this.hand.remove(monster);
    }
}
