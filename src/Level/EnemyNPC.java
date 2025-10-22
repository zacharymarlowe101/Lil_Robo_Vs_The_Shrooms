package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;

import java.util.HashMap;

public class EnemyNPC extends NPC {
    private final int baseHealth;
    private final int baseDamage;
    private int level = 1;

    public EnemyNPC(int id, float x, float y, SpriteSheet spriteSheet, String startingAnimation, int baseHealth, int baseDamage, int level) {
        super(id, x, y, spriteSheet, startingAnimation);
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        setLevel(level);
    }

    public EnemyNPC(int id, float x, float y, HashMap<String, Frame[]> animations, String startingAnimation, int baseHealth, int baseDamage, int level) {
        super(id, x, y, animations, startingAnimation);
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        setLevel(level);
    }

    public void setLevel(int level) {
        this.level = Math.max(1, level);
        int scaledHealth = (int) (baseHealth * Math.pow(1.5, this.level - 1));
        setHealth(scaledHealth);
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return (int) (baseDamage * Math.pow(1.5, level - 1));
    }

    @Override
    protected void performAction(Player player) {
        // Example: face and attack player
        facePlayer(player);
        updateEnemyAttack(player);
    }
}
