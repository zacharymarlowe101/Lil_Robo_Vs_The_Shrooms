package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;

import java.util.HashMap;

public class EnemyNPC extends NPC implements HasHealth {

    private float baseHealth;
    private final int baseDamage;
    private int level = 1;

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
        float scaledHealth = (float) (baseHealth * Math.pow(1.5, this.level - 1));
        this.setHealth(scaledHealth);
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return (int) (baseDamage * Math.pow(1.5, level - 1));
    }

    @Override
    public float getHp() {
        return getHealth();
    }

    @Override
    public float getMaxHp() {
        return (float) (baseHealth * Math.pow(1.5, level - 1));
    }

    @Override
    public float getHpRatio() {
        return hp / maxHp;
    }

    @Override
    public boolean isDead() {
        //Need to increase value of player thing when this is called... guess I should find where it is called...
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

    @Override
    protected void performAction(Player player) {
        if (isHidden || isDead()) return;

        facePlayer(player);
        updateEnemyAttack(player);
    }
}
