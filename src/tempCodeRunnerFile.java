import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface RPGCharacter {
    String getName();
    int getLevel();
    int getMaxHP();
    int getCurrentHP();
    int getMana();
    int getRunSpeed();
    int getAttack(); 
    void setAttack(int attack);
    int getIntelligence();
    void setIntelligence(int intelligence);
    void attack(RPGCharacter target);
    void takeDamage(int damage);
    void useAbility(String ability);
    void equipAccessory(Accessory accessory);
    void unequipAccessory(Accessory accessory);
    void addHP(int hp);
    void addMana(int mana);
    void addAttackSpeed(int attackSpeed);
    void addArmor(int armor);
    void setMana(int mana);
    void setCurrentHP(int currentHP);
    void displayEquippedAccessories();
    int getAttackSpeed();
    void setAttackSpeed(int attackSpeed);
}

interface Warrior extends RPGCharacter {
    void useSlashAttack();
    void useDefend();
}

interface Mage extends RPGCharacter {
    void castSpell(String spell);
    void meditate();
}

interface Accessory {
    String getName();
    void applyEffect(RPGCharacter character);
    void addHP(RPGCharacter character, int hpBonus);
    void addMana(RPGCharacter character, int manaBonus);
    void addAttackSpeed(RPGCharacter character, int attackSpeedBonus);
}

class Gauntlet implements Accessory {
    private String name;
    private int strengthBonus;
    private int hpBonus;

    public Gauntlet(String name, int strengthBonus, int hpBonus) {
        this.name = name;
        this.strengthBonus = strengthBonus;
        this.hpBonus = hpBonus;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void applyEffect(RPGCharacter character) {
        int currentAttack = character.getAttack();
        int newAttack = currentAttack + strengthBonus;
        character.setAttack(newAttack);

        addHP(character, hpBonus);
    }

    @Override
    public void addHP(RPGCharacter character, int hpBonus) {
        int currentHP = character.getCurrentHP();
        int newHP = currentHP + hpBonus;
        character.setCurrentHP(newHP);
    }

    @Override
    public void addMana(RPGCharacter character, int manaBonus) {
        int currentMana = character.getMana();
        int newMana = currentMana + manaBonus;
        character.setMana(newMana);
    }

    @Override
    public void addAttackSpeed(RPGCharacter character, int attackSpeedBonus) {
        int currentAttackSpeed = character.getAttackSpeed();
        int newAttackSpeed = currentAttackSpeed + attackSpeedBonus;
        character.setAttackSpeed(newAttackSpeed);
    }
}

class Ring implements Accessory {
    private String name;
    private int intelligenceBonus;
    private int manaBonus;

    public Ring(String name, int intelligenceBonus, int manaBonus) {
        this.name = name;
        this.intelligenceBonus = intelligenceBonus;
        this.manaBonus = manaBonus;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void applyEffect(RPGCharacter character) {
        int currentIntelligence = character.getIntelligence();
        int newIntelligence = currentIntelligence + intelligenceBonus;
        character.setIntelligence(newIntelligence);

        addMana(character, manaBonus);
    }

    @Override
    public void addHP(RPGCharacter character, int hpBonus) {
        int currentHP = character.getCurrentHP();
        int newHP = currentHP + hpBonus;
        character.setCurrentHP(newHP);
    }

    @Override
    public void addMana(RPGCharacter character, int manaBonus) {
        int currentMana = character.getMana();
        int newMana = currentMana + manaBonus;
        character.setMana(newMana);
    }

    @Override
    public void addAttackSpeed(RPGCharacter character, int attackSpeedBonus) {
        int currentAttackSpeed = character.getAttackSpeed();
        int newAttackSpeed = currentAttackSpeed + attackSpeedBonus;
        character.setAttackSpeed(newAttackSpeed);
    }
}

class RPGCharacterImpl implements RPGCharacter {
    private String name;
    private int level;
    private int maxHp;
    private int currentHp;
    private int maxMana;
    private int mana;
    private int baseRunSpeed = 10;
    private int runSpeed;
    private int runSpeedDecrease;
    private int attack;
    private int intelligence;
    private List<Accessory> equippedAccessories;
    private int attackSpeed;

    public RPGCharacterImpl(String name, int level, int maxHp, int maxMana) {
        this.name = name;
        this.level = level;
        this.maxHp = maxHp * level;
        this.maxMana = maxMana * level;
        this.currentHp = this.maxHp;
        this.mana = this.maxMana;
        this.runSpeed = baseRunSpeed;
        this.runSpeedDecrease = -2;
        this.attack = 10;
        this.intelligence = 1;
        this.attackSpeed = 5;
        this.equippedAccessories = new ArrayList<>();
    }

    @Override
    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    @Override
    public int getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getMaxHP() {
        return maxHp;
    }

    @Override
    public int getCurrentHP() {
        return currentHp;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public int getRunSpeed() {
        return runSpeed - runSpeedDecrease;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getIntelligence() {
        return intelligence;
    }

    @Override
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    @Override
    public void attack(RPGCharacter target) {
        // Implement attack logic
        if (this.getAttack() > 0) {
            int damageDealt = this.calculateDamage();
            target.takeDamage(damageDealt);
            System.out.println(this.getName() + " dealt " + damageDealt + " damage to " + target.getName());
        } else {
            System.out.println(this.getName() + " cannot attack as it has no weapon.");
        }
    }

    private int calculateDamage() {
        // Implement your own logic to calculate damage
        return this.getAttack();
    }

    @Override
    public void takeDamage(int damage) {
        // Implement damage logic
        this.currentHp -= damage;
        if (this.currentHp < 0) {
            this.currentHp = 0;
        }
    }

    @Override
    public void useAbility(String ability) {
        // Implement ability logic
    }

    @Override
    public void equipAccessory(Accessory accessory) {
        equippedAccessories.add(accessory);
        accessory.applyEffect(this);
    }

    @Override
    public void unequipAccessory(Accessory accessory) {
        equippedAccessories.remove(accessory);
        // Implement logic to remove effects of the accessory
    }

    @Override
public void addHP(int hp) {
    this.currentHp += hp;
    if (this.currentHp > this.maxHp) {
        this.currentHp = this.maxHp;
    }
    if (this.currentHp < 0) {
        this.currentHp = 0;
    }
}

    @Override
    public void addMana(int mana) {
        this.mana += mana;
        if (this.mana > maxMana) {
            this.mana = maxMana;
        }
    }

    @Override
    public void addAttackSpeed(int attackSpeed) {
        // Implement logic to add attack speed
        this.attackSpeed += attackSpeed;
    }

    @Override
    public void addArmor(int armor) {
        // Implement logic to add armor
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void setCurrentHP(int currentHP) {
        this.currentHp = currentHP;
    }

    @Override
    public void displayEquippedAccessories() {
        System.out.print("Equipped Accessories: ");
        for (Accessory accessory : equippedAccessories) {
            System.out.print(accessory.getName() + " ");
        }
        System.out.println();
    }
}

class WarriorImpl extends RPGCharacterImpl implements Warrior {
    public WarriorImpl(String name, int level, int maxHp, int maxMana) {
        super(name, level, maxHp, maxMana);
    }

    @Override
    public void useSlashAttack() {
        // Implement warrior-specific slash attack logic
    }

    @Override
    public void useDefend() {
        // Implement warrior-specific defend logic
    }
}

class MageImpl extends RPGCharacterImpl implements Mage {
    public MageImpl(String name, int level, int maxHp, int maxMana) {
        super(name, level, maxHp, maxMana);
    }

    @Override
    public void castSpell(String spell) {
        // Implement mage-specific spell casting logic
    }

    @Override
    public void meditate() {
        // Implement mage-specific meditate logic
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Warrior player1 = new WarriorImpl("Player 1", 5, 90, 50);
        Mage player2 = new MageImpl("Player 2", 5, 80, 70);

        player1.equipAccessory(new Gauntlet("Gauntlet of Strength", 5, 20));
        player2.equipAccessory(new Ring("Ring of Wisdom", 3, 30));

        // Display initial stats
        displayCharacterStats(player1);
        displayCharacterStats(player2);

        // Player 1 attacks Player 2
        player1.attack(player2);
        // Player 2 attacks Player 1
        player2.attack(player1);

        // Display updated stats after interaction
        displayCharacterStats(player1);
        displayCharacterStats(player2);

        // Additional interactions or game mechanics can be added here
    }

    private static void displayCharacterStats(RPGCharacter character) {
        System.out.println("Name: " + character.getName());
        System.out.println("Level: " + character.getLevel());
        System.out.println("HP: " + character.getCurrentHP() + "/" + character.getMaxHP());
        System.out.println("Mana: " + character.getMana());
        System.out.println("Run Speed: " + character.getRunSpeed());
        System.out.println("Attack: " + character.getAttack());
        System.out.println("Intelligence: " + character.getIntelligence());
        character.displayEquippedAccessories();
        System.out.println();
    }
}
