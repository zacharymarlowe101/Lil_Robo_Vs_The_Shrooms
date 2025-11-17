package Level;

import java.awt.*;
import GameObject.Rectangle;

public class Healthbar {
    private final HasHealth owner;

    public Healthbar(HasHealth owner) {
        this.owner = owner;
    }

    public void draw(Graphics2D g2d, float camX, float camY) {
        if (owner.isDead()) return;
        if (owner.getHpRatio() >= 1.0f) return;

        int width = owner.getHealthbarWidth();
        int height = owner.getHealthbarHeight();
        int yOffset = owner.getHealthbarYOffset();

        // Correct centering using bounds
        Rectangle bounds = owner.getBounds();
        float centerX = (float) (bounds.getX() + bounds.getWidth() / 2.0);
        float topY = (float) bounds.getY();


        int sx = (int) (centerX - camX - width / 2f + 9);
        int sy = (int) (topY - camY + yOffset);

        // Draw black border
        g2d.setColor(Color.BLACK);
        g2d.fillRect(sx - 1, sy - 1, width + 2, height + 2);

        // Draw background red
        g2d.setColor(Color.RED);
        g2d.fillRect(sx, sy, width, height);

        // Fill in green based on HP %
        int fillWidth = (int) (width * owner.getHpRatio());
        g2d.setColor(Color.GREEN);
        g2d.fillRect(sx, sy, fillWidth, height);
    }
}
