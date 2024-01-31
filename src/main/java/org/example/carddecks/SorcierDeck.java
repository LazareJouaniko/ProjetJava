package org.example.carddecks;

import org.example.enums.MonsterTypeEnum;
import org.example.monsters.Monster;
import org.example.monsters.MonsterElement;

import java.util.ArrayList;
import java.util.List;

public class SorcierDeck extends CardDeck {
    public SorcierDeck() {
        List<Monster> monsters = new ArrayList<Monster>();

        Monster monster1 = new Monster(1,"Sorcier1",2,2, MonsterTypeEnum.CLASSIC, MonsterElement.FIRE);
        Monster monster2 = new Monster(2,"Sorcier2",2,2, MonsterTypeEnum.CLASSIC,MonsterElement.WATER);
        Monster monster3 = new Monster(3,"Sorcier3",2,2, MonsterTypeEnum.CLASSIC,MonsterElement.EARTH);

        monsters.add(monster1);
        monsters.add(monster2);
        monsters.add(monster3);

        this.setDesk(monsters);
    }
    public SorcierDeck(List<Monster> desk, List<Monster> hand, List<Monster> game) {
        super(desk, hand, game);
    }
}
