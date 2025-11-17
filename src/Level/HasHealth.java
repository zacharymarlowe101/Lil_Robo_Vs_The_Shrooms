package Level;

import GameObject.Rectangle;

public interface HasHealth {
    float getX();
    float getY();
    float getHp();
    float getMaxHp();
    float getHpRatio();
    boolean isDead();
    void takeDamage(float dmg);
    Healthbar getHealthbar();
    Rectangle getBounds();

    default int getHealthbarWidth() {
        return 50;
    }

    default int getHealthbarHeight() {
        return 6;
    }

    default int getHealthbarYOffset() {
        return -25;
    }
}
