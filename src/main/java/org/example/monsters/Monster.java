package org.example.monsters;

import org.example.enums.MonsterTypeEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Monster {
    private int id;
    private String name;
    private int hp;
    private int strength;
    private MonsterTypeEnum type;
    private List<Monster> protectors;
    private List<Monster> mascots;
    private Logger logger;
    private MonsterElement element;

    public Monster(int id, String name, MonsterTypeEnum type ) {
        this.id = id;
        this.name = name;
        this.hp = 2;
        this.strength = 0;  
        this.type = type;
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
    public Monster(int id, String name, int hp, int strength, MonsterTypeEnum type,MonsterElement element) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.strength = strength;
        this.type = type;
        this.element = element;
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

    public void setId(int id) {
        this.id = id;
    }
    public void setElement(MonsterElement element) {
        this.element = element;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStrength() {
        return strength;
    }
    public MonsterElement getElement() {
        return this.element;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public MonsterTypeEnum getType() {
        return type;
    }

    public void setType(MonsterTypeEnum type) {
        this.type = type;
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

    public void attack(Monster enemy) {
        if (this.canAttack()) {
            if (this.getMascots().isEmpty()) {
                this.logger.info(enemy.getName()+" se prend un coup de "+this.strength*this.getWeakness(enemy)+" de dégât par "+this.getName());
                enemy.takeDamage(this.getStrength() * this.getWeakness(enemy));
            }
            else {
                int boost = 0;
                for (Monster mascot : this.getMascots()) {
                    boost += mascot.getStrength();
                }
                this.logger.info(enemy.getName()+" se prend un coup de "+(this.strength + boost)*this.getWeakness(enemy)+" de dégât par "+this.getName());
                enemy.takeDamage((this.strength+ boost)* this.getWeakness(enemy));
            }
        }
    }


    public boolean canAttack() {
        return (this.type == MonsterTypeEnum.CLASSIC);
    }
    //récuperer le coeff de dégat supp en fonction de l'élément

public int getWeakness(Monster m){
switch (this.element){
    //si feu faible devant eau
    case FIRE:
        if (m.element == MonsterElement.WATER){
            //feu fort 
            return 1;
        }
        if (m.element == MonsterElement.EARTH){
            return 2;
        }
        return 1;
    case WATER:
    if (m.element == MonsterElement.FIRE){
        //feu fort 
        return 2 ;
    }
    if (m.element == MonsterElement.EARTH){
        return 1;
    }
    case EARTH:
    if (m.element == MonsterElement.WATER){
        //feu fort 
        return 1;
    }
    if (m.element == MonsterElement.EARTH){
        return 2;
    }
}
return 1;
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

    public void heal(Monster ally) {
        this.hp += ally.getStrength();
        this.logger.info(ally.getName()+" a soigné de "+ally.getStrength()+" pv");
    }

    public boolean isAlive() {
        return (this.hp > 0);
    }

    public void addProtector(Monster monster) {
        this.protectors.add(monster);
    }

    public void addMascot(Monster monster) {
        this.mascots.add(monster);
    }
}
