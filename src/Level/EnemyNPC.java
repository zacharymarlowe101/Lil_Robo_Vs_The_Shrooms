package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;

import java.util.HashMap;

public class EnemyNPC extends NPC {
    private final int baseHealth;
    private final int baseDamage;
    private int level = 1;
    private boolean isHidden = false;
    private boolean isActive = true;
    private int maxHealth;

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
        this.maxHealth = scaledHealth;
        setHealth(scaledHealth);
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return (int) (baseDamage * Math.pow(1.5, level - 1));
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isDead() {
        return getHealth() <= 0;
    }

    @Override
    protected void performAction(Player player) {
        if (isHidden || !isActive) {
            return;
        }

        facePlayer(player);
        updateEnemyAttack(player);
    }

    
}
