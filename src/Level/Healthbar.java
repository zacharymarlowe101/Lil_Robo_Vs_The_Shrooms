package Level;

import java.awt.*;
import GameObject.Rectangle;
import Engine.ScreenManager;

public class Healthbar {
    private final HasHealth owner;
    private boolean isBoss = false;

    public Healthbar(HasHealth owner) {
        this.owner = owner;
    }

    public void setBossMode(boolean isBoss) {
        this.isBoss = isBoss;
    }

    public void draw(Graphics2D g2d, float camX, float camY) {
        if (owner.isDead()) return;
        if (owner.getHpRatio() >= 1.0f) return;

        if (isBoss) {
            drawBossBar(g2d);
        } else {
            drawStandardBar(g2d, camX, camY);
        }
    }

    private void drawStandardBar(Graphics2D g2d, float camX, float camY) {
        int width = owner.getHealthbarWidth();
        int height = owner.getHealthbarHeight();
        int yOffset = owner.getHealthbarYOffset();

        Rectangle bounds = owner.getBounds();
        float centerX = (float) (bounds.getX() + bounds.getWidth() / 2.0);
        float topY = (float) bounds.getY();

        int sx = (int) (centerX - camX - width / 2f + 9);
        int sy = (int) (topY - camY + yOffset);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(sx - 1, sy - 1, width + 2, height + 2);

        g2d.setColor(Color.RED);
        g2d.fillRect(sx, sy, width, height);

        int fillWidth = (int) (width * owner.getHpRatio());
        g2d.setColor(Color.GREEN);
        g2d.fillRect(sx, sy, fillWidth, height);
    }

    private void drawBossBar(Graphics2D g2d) {
        int screenWidth = ScreenManager.getScreenWidth();
        float hpRatio = owner.getHpRatio();

        int barWidth = 650;
        int barHeight = 24;

        int x = (screenWidth - barWidth) / 2;
        int y = 525;

        int borderThickness = 3;

        // Border
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x - borderThickness, y - borderThickness, barWidth + borderThickness * 2, barHeight + borderThickness * 2);

        // Background
        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, barWidth, barHeight);

        // HP Fill
        int fillWidth = (int) (barWidth * hpRatio);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(x, y, fillWidth, barHeight);

        // Boss Name Above Bar
        String bossName = "Mycorrhizal Amalgamation";
        g2d.setFont(new Font("Monospaced", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        FontMetrics nameFM = g2d.getFontMetrics();
        int nameX = x + (barWidth - nameFM.stringWidth(bossName)) / 2;
        int nameY = y - 10;
        g2d.drawString(bossName, nameX, nameY);
        g2d.setColor(Color.WHITE);
        g2d.drawString(bossName, nameX + 2, nameY + 2);


        // HP % in center
        String hpPercent = String.format("%.0f%%", hpRatio * 100);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 16));
        g2d.setColor(Color.BLACK);
        FontMetrics percentFM = g2d.getFontMetrics();
        int percentX = x + (barWidth - percentFM.stringWidth(hpPercent)) / 2;
        int percentY = y + ((barHeight - percentFM.getHeight()) / 2) + percentFM.getAscent();
        g2d.drawString(hpPercent, percentX, percentY);
    }
}
