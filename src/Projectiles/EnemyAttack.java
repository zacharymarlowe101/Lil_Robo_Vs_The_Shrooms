package Projectiles;

import Level.NPC;
import Level.Player;

public interface EnemyAttack {
    void perform(NPC npc, Player player);

    boolean isInRange(NPC npc, Player player);

    int getCooldown();
}
