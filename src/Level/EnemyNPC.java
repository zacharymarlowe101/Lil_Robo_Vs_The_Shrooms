package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;

import java.util.HashMap;

public class EnemyNPC extends NPC implements HasHealth {

    protected float hp;
    protected float maxHp;

    private float baseHealth;
    private final int baseDamage;
    private int level = 1;

    protected boolean isHidden = false;
    private boolean isActive = true;

    private Healthbar healthbar;

    public EnemyNPC(int id, float x, float y, SpriteSheet spriteSheet, String startingAnimation, int baseHealth, int baseDamage, int level) {
        super(id, x, y, spriteSheet, startingAnimation);
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;

        setLevel(level);

        this.healthbar = new Healthbar(this);
    }

    public EnemyNPC(int id, float x, float y, HashMap<String, Frame[]> animations, String startingAnimation, int baseHealth, int baseDamage, int level) {
        super(id, x, y, animations, startingAnimation);
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;

        setLevel(level);

        this.healthbar = new Healthbar(this);
    }

    public void setLevel(int level) {
        this.level = Math.max(1, level);

        float scaledHealth = (float)(baseHealth * Math.pow(1.5, this.level - 1));
        this.maxHp = scaledHealth;
        this.hp = scaledHealth;

        setHealth((int)scaledHealth);
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return (int)(baseDamage * Math.pow(1.5, level - 1));
    }

    @Override
    public float getHp() {
        return hp;
    }

    @Override
    public float getMaxHp() {
        return maxHp;
    }

    @Override
    public float getHpRatio() {
        return hp / maxHp;
    }

    @Override
    public boolean isDead() {
        
        return hp <= 0.0001f;
    }

    @Override
    public void takeDamage(float amount) {
        if (isDead()) return;
        hp = Math.max(0f, hp - amount);
    }

    @Override
    public void takeDamage(int amount) {
        takeDamage((float) amount);
    }

    @Override
    public Healthbar getHealthbar() {
        return healthbar;
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

    @Override
    protected void performAction(Player player) {
        if (isHidden || !isActive) return;

        facePlayer(player);
        updateEnemyAttack(player);
    }
}
