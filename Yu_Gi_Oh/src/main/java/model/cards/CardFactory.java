package model.cards;

import model.cards.monstercards.*;
import model.cards.spellcards.*;
import model.cards.trapcards.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class CardFactory {
    private static ArrayList<Card> allCards;


    public static void readCVSOfCards() {
        allCards = new ArrayList<>();
        String[] cardDate;
        File file = new File("Yu_Gi_Oh/src/main/resources/database/Monster.csv");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            cardDate = scanner.nextLine().split("(?=\\S),(?=\\S)");
            switch (cardDate[0]) {
                case "Battle OX":
                    allCards.add(new BattleOX(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Axe Raider":
                    allCards.add(new AxeRaide(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Yomi Ship":
                    allCards.add(new YomiShip(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Horn Imp":
                    allCards.add(new HornImp(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Silver Fang":
                    allCards.add(new SilverFang(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Suijin":
                    allCards.add(new Suijin(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Fireyarou":
                    allCards.add(new FireYarou(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Curtain of the dark ones":
                    allCards.add(new CurtainOfTheDarkOnes(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Feral Imp":
                    allCards.add(new FeralImp(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Dark magician":
                    allCards.add(new DarkMagician(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Wattkid":
                    allCards.add(new Wattkid(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Baby dragon":
                    allCards.add(new BabyDragon(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Hero of the east":
                    allCards.add(new HeroOfTheEast(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Battle warrior":
                    allCards.add(new BattleWarrior(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Crawling dragon":
                    allCards.add(new CrawlingDragon(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Flame manipulator":
                    allCards.add(new FlameManipulator(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Blue-Eyes white dragon":
                    allCards.add(new BlueEyes(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Crab Turtle":
                    allCards.add(new CrabTurtle(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Skull Guardian":
                    allCards.add(new SkullGuardian(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Slot Machine":
                    allCards.add(new SlotMachine(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Haniwa":
                    allCards.add(new Haniwa(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Man-Eater Bug":
                    allCards.add(new ManEaterBug(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Gate Guardiane":
                    allCards.add(new GateGuardian(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Scanner":
                    allCards.add(new model.cards.monstercards.Scanner(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Bitron":
                    allCards.add(new Bitron(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Marshmallon":
                    allCards.add(new Marshmallon(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Beast King Barbaros":
                    allCards.add(new BeastKingBarbaros(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Texchanger":
                    allCards.add(new Texchanger(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Leotron":
                    allCards.add(new Leotron(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "The Calculator":
                    allCards.add(new TheCalculator(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Alexandrite Dragon":
                    allCards.add(new AlexandriteDragon(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Mirage Dragon":
                    allCards.add(new MirageDragon(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Herald of Creation":
                    allCards.add(new HeroOfTheEast(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Exploder Dragon":
                    allCards.add(new ExploderDragon(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Warrior Dai Grepher":
                    allCards.add(new WarriorDaiGrepher(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Dark Blade":
                    allCards.add(new DarkBlade(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Wattaildragon":
                    allCards.add(new WattailDragon(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Terratiger, the Empowered Warrior":
                    allCards.add(new Terratiger(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "The Tricky":
                    allCards.add(new TheTricky(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Command Knight":
                    allCards.add(new CommandKnight(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
                    break;
                case "Spiral Serpent":
                    allCards.add(new SpiralSerpent(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2]),
                            Integer.parseInt(cardDate[3]), Integer.parseInt(cardDate[4]), Integer.parseInt(cardDate[5])));
            }
        }
        File file2 = new File("Yu_Gi_Oh/src/main/resources/database/SpellAndTrap.csv");
        Scanner scanner2 = null;
        try {
            scanner2 = new Scanner(file2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner2.hasNextLine()) {
            cardDate = scanner2.nextLine().split("(?=\\S),(?=\\S)");
            switch (cardDate[0]) {
                case "Monster Reborn":
                    allCards.add(new MonsterReborn(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Terraforming":
                    allCards.add(new Terraforming(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Pot of Greed":
                    allCards.add(new PotOfGreed(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Raigeki":
                    allCards.add(new Raigeki(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Change of Heart":
                    allCards.add(new ChangeOfHeart(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Harpie's Feather Duster":
                    allCards.add(new HarpiesFeatherDuster(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Dark Hole":
                    allCards.add(new DarkHole(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Supply Squad":
                    allCards.add(new SupplySquad(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Spell Absorption":
                    allCards.add(new SpellAbsorption(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Messenger of peace":
                    allCards.add(new MessengerOfPeace(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Twin Twisters":
                    allCards.add(new TwinTwisters(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Mystical space typhoon":
                    allCards.add(new MysticalSpaceTyphoon(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Ring of defense":
                    allCards.add(new RingOfDefence(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Yami":
                    allCards.add(new Yami(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Forest":
                    allCards.add(new Forest(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Umiiruka":
                    allCards.add(new Umiiruka(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Sword of dark destruction":
                    allCards.add(new SwordOfDarkDestruction(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Black Pendant":
                    allCards.add(new BlackPendent(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "United We Stand":
                    allCards.add(new UnitedWeStand(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Magnum Shield":
                    allCards.add(new MagnumShield(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Advanced Ritual Art":
                    allCards.add(new AdvancedRitualArt(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Trap Hole":
                    allCards.add(new TrapHole(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Mirror Force":
                    allCards.add(new MirrorForce(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Magic Cylinder":
                    allCards.add(new MagicCylinder(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Mind Crush":
                    allCards.add(new MindCrush(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Torrential Tribute":
                    allCards.add(new TorrentialTribute(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Time Seal":
                    allCards.add(new TimeSeal(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Negate Attack":
                    allCards.add(new NegateAttack(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Solemn Warning":
                    allCards.add(new SolemnWarning(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Magic Jamamer":
                    allCards.add(new MagicJammer(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Call of The Haunted":
                    allCards.add(new CallOfTheHunted(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Vanity's Emptiness":
                    allCards.add(new VanitysEmptiness(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
                case "Wall of Revealing Light":
                    allCards.add(new WallOfRevealingLight(cardDate[0], cardDate[1], Integer.parseInt(cardDate[2])));
                    break;
            }
        }

    }

    public static Card getCardByCardName(String cardName) {
        for (Card card : allCards) {
            if (card.name.equalsIgnoreCase(cardName))
                return (Card) card.clone();
        }
        return null;
    }

    public static ArrayList<Card> getAllCards() {
        return allCards;
    }
}