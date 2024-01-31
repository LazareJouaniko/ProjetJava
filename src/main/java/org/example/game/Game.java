package org.example.game;

import org.example.abilities.AttaqueMagique;
import org.example.carddecks.CardDeck;
import org.example.carddecks.SorcierDeck;
import org.example.carddecks.VampireDeck;
import org.example.champions.Champion;
import org.example.enums.MonsterTypeEnum;
import org.example.monsters.Monster;
import org.example.game.Game;

import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Game {
    private Champion champion1;
    private Champion champion2;
    private List<Champion> championsList;
    private Champion looser;
    private Scanner scanner;
    private Random random;
    private Logger logger;
    public Game() {
        this.championsList = new ArrayList<Champion>();

        this.scanner = new Scanner(System.in);
        this.random = new Random();

        this.createChampionList();

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

    public Champion getChampion1() {
        return champion1;
    }

    public void setChampion1(Champion champion1) {
        this.champion1 = champion1;
    }

    public Champion getChampion2() {
        return champion2;
    }

    public void setChampion2(Champion champion2) {
        this.champion2 = champion2;
    }

    public Champion getLooser() {
        return looser;
    }

    public void setLooser(Champion looser) {
        this.looser = looser;
    }

    /**
     * Cette fonction permet de créer les différents champions jouable.
     * */
    public void createChampionList() {
        CardDeck desk1 = new SorcierDeck();
        this.championsList.add(new Champion(1,"Le Grand Sorcier",2,2,new AttaqueMagique(),desk1));

        CardDeck desk2 = new VampireDeck();
        this.championsList.add(new Champion(1,"Le Prince Dracula",2,2,new AttaqueMagique(),desk2));
    }
    /**
     * Cette fonction affiche la liste des champions jouables.
     * */
    public void showChampionsList() {
        for (int i = 0; i < this.championsList.size(); i++) {
            Champion champ = this.championsList.get(i);
            System.out.println(i+" - "+champ.getName());
        }
    }
    /**
     * Cette fonction permet au joueur de séléctionner un champion.
     * */
    public void selectedPlayerChampion(int player) {
        System.out.println("Choix du Champion du Joueur : "+player);
        this.showChampionsList();

        int selectedChampIndex = this.scanner.nextInt();

        while (selectedChampIndex < 0 || selectedChampIndex > this.championsList.size()) {
            System.out.println("le nombre doit être compris entre 0 et "+(this.championsList.size()-1));
            selectedChampIndex = this.scanner.nextInt();
        }

        Champion selectedChampion = this.championsList.get(selectedChampIndex);

        if (player == 1) this.champion1 = selectedChampion;
        if (player == 2) this.champion2 = selectedChampion;
        System.out.println("Champion du Joueur "+player+" : "+selectedChampion.getName());
    }
    /**
     * Cette fonction affiche les carte qui sont dans la main du joueur.
     * */
    public void showCardHand(Champion champion) {
        for (int i = 0; i < champion.getCardDesk().getHand().size(); i++) {
            Monster monster = champion.getCardDesk().getHand().get(i);
            System.out.println(i+" - "+monster.getName());
        }
    }
    /**
     * Cette fonction permet au joueur de séléctionner un monstre.
     * */
    public Monster selectedMonster(Champion champion) {
        System.out.println("Laquelle de vos cartes voulez-vous poser ?");
        this.showCardHand(champion);

        int selectedMonsterIndex = this.scanner.nextInt();

        while (selectedMonsterIndex < 0 || selectedMonsterIndex > champion.getCardDesk().getHand().size()) {
            System.out.println("le nombre doit être compris entre 0 et "+(champion.getCardDesk().getHand().size()-1));
            selectedMonsterIndex = this.scanner.nextInt();
        }
        return champion.getCardDesk().getHand().get(selectedMonsterIndex);
    }
    /**
     * Cette fonction permet au joueur de piocher une carte.
     * */
    public void pickCard(Champion champion) {
        int randomInt = this.random.nextInt(champion.getCardDesk().getDesk().size());
        Monster pickedMonster = champion.getCardDesk().getDesk().get(randomInt);
        champion.getCardDesk().getDesk().remove(randomInt);
        champion.getCardDesk().getHand().add(pickedMonster);
        System.out.println("Vous avez pioché un : "+pickedMonster.getName());
    }
    /**
     * Cette fonction permet d'afficher la liste des monstres d'un champion.
     * */
    public void showChampionsMonsters(Champion champion) {
        for (int j = 0; j < champion.getCardDesk().getGame().size(); j++) {
            Monster monsterEnnemi = champion.getCardDesk().getGame().get(j);
            System.out.println(j+" - "+monsterEnnemi.getName());
        }
    }
    /**
     * Cette fonction permet à un montre d'attaquer.
     * */
    public void monsterAttack(Monster monster, Champion champion, Champion ennemyChampion) {
        System.out.println("Voulez vous que "+monster.getName()+" attaque le champion ennemi ?\nO - Oui\nN - Non");
        String answer = this.scanner.next();
        if (Objects.equals(answer, "O") || Objects.equals(answer, "o")) {
            ennemyChampion.takeDamage(monster.getStrength());
        } else if (Objects.equals(answer, "N") || Objects.equals(answer, "n")) {
            System.out.println("Voulez vous que " + monster.getName() + " attaque un autre monstre ?\nO - Oui\nN - Non");
            answer = this.scanner.next();
            if (Objects.equals(answer, "O") || Objects.equals(answer, "o")) {

                System.out.println("Quel monstre ennemi voulez vous attaquer ?");
                this.showChampionsMonsters(ennemyChampion);

                int selectedMonsterIndex = this.scanner.nextInt();

                while (selectedMonsterIndex < 0 || selectedMonsterIndex > champion.getCardDesk().getGame().size()) {
                    System.out.println("Le nombre doit être compris entre 0 et " + (champion.getCardDesk().getGame().size() - 1));
                    System.out.println("Quel monstre ennemi voulez vous attaquer ?");
                    this.showChampionsMonsters(ennemyChampion);
                    selectedMonsterIndex = this.scanner.nextInt();
                }

                Monster monsterEnnemi = champion.getCardDesk().getGame().get(selectedMonsterIndex);
                monster.attack(monsterEnnemi);
                if (!monsterEnnemi.isAlive()) {
                    ennemyChampion.getCardDesk().getGame().remove(selectedMonsterIndex);
                }
            }
        }
    }
    /**
     * Cette fonction permet à un montre de protéger un autre.
     * */
    public void monsterProtection(Monster monster, Champion champion) {
        System.out.println("Voulez vous que "+monster.getName()+" protège votre champion ?\nO - Oui\nN - Non");
        String answer = this.scanner.next();
        if (Objects.equals(answer, "O") || Objects.equals(answer, "o")) {
            champion.addProtector(monster);
        } else if (Objects.equals(answer, "N") || Objects.equals(answer, "n")) {
            System.out.println("Voulez vous que " + monster.getName() + " soigne un de vos monstre ?\nO - Oui\nN - Non");
            answer = this.scanner.next();
            if (Objects.equals(answer, "O") || Objects.equals(answer, "o")) {

                System.out.println("Quel monstre voulez vous protéger ?");
                this.showChampionsMonsters(champion);

                int selectedMonsterIndex = this.scanner.nextInt();

                while (selectedMonsterIndex < 0 || selectedMonsterIndex > champion.getCardDesk().getGame().size()) {
                    System.out.println("Le nombre doit être compris entre 0 et " + (champion.getCardDesk().getGame().size() - 1));
                    System.out.println("Quel monstre voulez vous protéger ?");
                    this.showChampionsMonsters(champion);
                    selectedMonsterIndex = this.scanner.nextInt();
                }

                Monster monsterProtector = champion.getCardDesk().getGame().get(selectedMonsterIndex);
                monsterProtector.addProtector(monster);
            }
        }
    }
    /**
     * Cette fonction permet à un montre de heal un montre.
     * */
    public void monsterHealing(Monster monster, Champion champion) {
        System.out.println("Voulez vous que "+monster.getName()+" soigné votre champion ?\nO - Oui\nN - Non");
        String answer = this.scanner.next();
        if (Objects.equals(answer, "O") || Objects.equals(answer, "o")) {
            champion.heal(monster.getStrength());
        } else if (Objects.equals(answer, "N") || Objects.equals(answer, "n")) {
            System.out.println("Voulez vous que " + monster.getName() + " soigne un de vos monstre ?\nO - Oui\nN - Non");
            answer = this.scanner.next();
            if (Objects.equals(answer, "O") || Objects.equals(answer, "o")) {

                System.out.println("Quel monstre voulez vous soigner ?");
                this.showChampionsMonsters(champion);

                int selectedMonsterIndex = this.scanner.nextInt();

                while (selectedMonsterIndex < 0 || selectedMonsterIndex > champion.getCardDesk().getGame().size()) {
                    System.out.println("Le nombre doit être compris entre 0 et " + (champion.getCardDesk().getGame().size() - 1));
                    System.out.println("Quel monstre voulez vous soigner ?");
                    this.showChampionsMonsters(champion);
                    selectedMonsterIndex = this.scanner.nextInt();
                }

                Monster monsterHeal = champion.getCardDesk().getGame().get(selectedMonsterIndex);
                monsterHeal.heal(monster);
            }
        }
    }
    /**
     * Cette fonction permet à un montre de devenir la mascotte d'un autre.
     * */
    public void monsterMascoting(Monster monster, Champion champion) {
        System.out.println("Voulez vous que "+monster.getName()+" boost votre champion ?\nO - Oui\nN - Non");
        String answer = this.scanner.next();
        if (Objects.equals(answer, "O") || Objects.equals(answer, "o")) {
            champion.addMascot(monster);
        } else if (Objects.equals(answer, "N") || Objects.equals(answer, "n")) {
            System.out.println("Voulez vous que " + monster.getName() + " boost un de vos monstre ?\nO - Oui\nN - Non");
            answer = this.scanner.next();
            if (Objects.equals(answer, "O") || Objects.equals(answer, "o")) {

                System.out.println("Quel monstre voulez vous mascotter ?");
                this.showChampionsMonsters(champion);

                int selectedMonsterIndex = this.scanner.nextInt();

                while (selectedMonsterIndex < 0 || selectedMonsterIndex > champion.getCardDesk().getGame().size()) {
                    System.out.println("Le nombre doit être compris entre 0 et " + (champion.getCardDesk().getGame().size() - 1));
                    System.out.println("Quel monstre voulez vous mascotter ?");
                    this.showChampionsMonsters(champion);
                    selectedMonsterIndex = this.scanner.nextInt();
                }

                Monster monsterMascot = champion.getCardDesk().getGame().get(selectedMonsterIndex);
                monsterMascot.addMascot(monster);
            }
        }
    }
    /**
     * Cette fonction permet à un montre de réaliser une action.
     * */
    public void monstersAction(Champion champion, Champion ennemyChampion) {

        for (int i = 0; i < champion.getCardDesk().getGame().size(); i++) {
            Monster monster = champion.getCardDesk().getGame().get(i);

            if (monster.getType() == MonsterTypeEnum.CLASSIC) this.monsterAttack(monster,champion, ennemyChampion);
            if (monster.getType() == MonsterTypeEnum.PROTECTOR) this.monsterProtection(monster,champion);
            if (monster.getType() == MonsterTypeEnum.HEALER) this.monsterHealing(monster,champion);
            if (monster.getType() == MonsterTypeEnum.MASCOT) this.monsterMascoting(monster,champion);
        }
    }

    /**
     * Cette fonction permet de gérer le fonctionnement des round.
     * */
    public void nextRound(Champion champion,Champion ennemyChampion) {
        System.out.println("C'est au tour du champion : "+champion.getName());

        int num = 2 - champion.getCardDesk().getHand().size();

        for (int i = 0; i < num; i++) {
            this.pickCard(champion);
        }

        System.out.println("Voulez vous poser une carte ?\nO - Oui\nN - Non");
        String answer = this.scanner.next();
        if (Objects.equals(answer, "O") || Objects.equals(answer, "o")) {
            Monster selectedMonster = this.selectedMonster(champion);
            champion.getCardDesk().placeCard(selectedMonster);
        }

        this.monstersAction(champion,ennemyChampion);

        System.out.println("Voulez vous utiliser votre capacité spéciale ?\nO - Oui\nN - Non");
        answer = this.scanner.next();
        if (Objects.equals(answer, "O") || Objects.equals(answer, "o")) champion.useAbility(this);

    }

    /**
     * Cette fonction permet de lancer le jeu.
     * */
    public void start() {
        this.selectedPlayerChampion(1);
        this.selectedPlayerChampion(2);

        while (this.champion1.isAlive() && this.champion2.isAlive()) {
            this.logger.info("Le tour de "+this.champion1+" commence.");
            this.nextRound(this.champion1,this.champion2);
            if (!this.champion1.isAlive() || !this.champion2.isAlive()) break;
            this.logger.info("Le tour de "+this.champion2+" commence.");
            this.nextRound(this.champion2,this.champion1);
        }
        if (!champion1.isAlive()) this.setLooser(this.champion1);
        if (!champion2.isAlive()) this.setLooser(this.champion2);
        this.logger.info("Partie terminée "+this.getLooser()+" a perdu !");
        System.out.println("Partie terminée "+this.getLooser()+" a perdu !");
    }
}
