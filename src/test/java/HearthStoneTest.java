import org.example.enums.MonsterTypeEnum;
import org.example.game.Game;
import org.example.monsters.Monster;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HearthStoneTest {
    Monster monster1 = new Monster(1,"Vampire1",2,2, MonsterTypeEnum.CLASSIC);
    Monster monster2 = new Monster(1,"Sorcier1",2,2, MonsterTypeEnum.CLASSIC);

    @Test
    public final void testAttack() {
        monster1.attack(monster2);
        assertEquals(monster2.getHp(),0);
    }
}
