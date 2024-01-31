package org.example.champions;

import org.example.abilities.SuperAbility;
import org.example.carddecks.CardDeck;
import org.example.game.Game;
import org.example.monsters.Monster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Champion {

    private int id;
    private String name;
    private int hp;
    private int strength;
    private SuperAbility superAbility;
    private CardDeck cardDeck;
    private List<Monster> protectors;
    private  List<Monster> mascots;
    private Logger logger;
    public Champion(int id, String name, int hp, int strength, SuperAbility superAbility, CardDeck cardDeck) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.superAbility = superAbility;
        this.cardDeck = cardDeck;
        this.protectors = new ArrayList<Monster>();
        this.mascots = new ArrayList<Monster>();

        this.logger = Logger.getLogger("org.example.Main");
        FileHandler fh = null;
        try {
            fh = new FileHandler("./logHearthStone.log");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.logger.addHandler(fh);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public SuperAbility getAbility() {
        return superAbility;
    }

    public CardDeck getCardDesk() {
        return cardDeck;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAbility(SuperAbility superAbility) {
        this.superAbility = superAbility;
    }

    public void setCardDesk(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public List<Monster> getProtectors() {
        return protectors;
    }

    public void setProtectors(List<Monster> protectors) {
        this.protectors = protectors;
    }

    public List<Monster> getMascots() {
        return mascots;
    }

    public void setMascots(List<Monster> mascots) {
        this.mascots = mascots;
    }

    public void takeDamage(int dmg) {
        if (this.protectors.isEmpty()) {
            this.logger.info(this.name+" a pris "+dmg+" de dégât.");
            this.hp -= dmg;
        }
        else {
            Monster protector = protectors.getFirst();
            this.logger.info(protector.getName()+" a encaissé le coup de" +dmg+ " de dégât à la place de "+this.getName());
            System.out.println(protector.getName()+" a encaissé le coup à la place de "+this.getName());
            this.protectors.removeFirst();
        }
    }

    public void heal(int healing) {
        this.hp += healing;
    }

    public boolean isAlive() {
        return (this.hp > 0);
    }

    public void useAbility(Game game) {
        this.superAbility.use(game);
    }

    public void addProtector(Monster monster) {
        this.protectors.add(monster);
    }

    public void addMascot(Monster monster) {
        this.mascots.add(monster);
    }
}
