import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.enums.MonsterTypeEnum;
import org.example.monsters.Monster;
import org.example.monsters.MonsterElement;
import org.junit.jupiter.api.Test;

public class HearthStoneTest {
    Monster monster1 = new Monster(1,"Vampire1",2,2, MonsterTypeEnum.CLASSIC, MonsterElement.FIRE);
    Monster monster2 = new Monster(1,"Sorcier1",2,2, MonsterTypeEnum.CLASSIC,MonsterElement.FIRE);

    @Test
    public final void testAttack() {
        monster1.attack(monster2);
        assertEquals(monster2.getHp(),0);
    }
    @Test
    public final void testElement(){
        Monster monster1 = new Monster(0,"Sorcier1",2,2, MonsterTypeEnum.CLASSIC, MonsterElement.FIRE);
        Monster monster2 = new Monster(1,"Sorcier1",2,2, MonsterTypeEnum.CLASSIC, MonsterElement.WATER);
        monster1.attack(monster2);
    }
    //CHANGELOG :
    // Monster -> Ajout d'une fonction getWeakness qui retourne un coefficient en fonction de l'adversaire ciblé
    // Monster -> Attack, ajout du coeff getWeakness dans le calcul des dégats
    // Ajout d'une enum MonsterElement 
    // Monster -> Ajout MonsterElement element


        
        

    }

