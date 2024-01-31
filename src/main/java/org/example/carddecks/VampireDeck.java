package org.example.carddecks;

import org.example.enums.MonsterTypeEnum;
import org.example.monsters.Monster;
import org.example.monsters.MonsterElement;

import java.util.ArrayList;
import java.util.List;

public class VampireDeck extends CardDeck {
    public VampireDeck() {
        List<Monster> monsters = new ArrayList<Monster>();

        // CHANGER MONSTRE sans abstract car pas besoin faut créer à la chaine

        Monster monster1 = new Monster(1,"Vampire1",2,2, MonsterTypeEnum.CLASSIC,MonsterElement.FIRE);
        Monster monster2 = new Monster(2,"Vampire2",2,2, MonsterTypeEnum.CLASSIC,MonsterElement.WATER);
        Monster monster3 = new Monster(3,"Vampire2",2,2, MonsterTypeEnum.CLASSIC,MonsterElement.EARTH);

        monsters.add(monster1);
        monsters.add(monster2);
        monsters.add(monster3);

        this.setDesk(monsters);
    }
    public VampireDeck(List<Monster> desk, List<Monster> hand, List<Monster> game) {
        super(desk, hand, game);
    }
}
