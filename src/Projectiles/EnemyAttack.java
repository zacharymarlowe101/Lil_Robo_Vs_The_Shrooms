package Projectiles;

import Level.NPC;
import Level.Player;

public interface EnemyAttack {
    // Triggers the attack action
    void perform(NPC npc, Player player);

    // Returns true if the attack can be used based on range
    boolean isInRange(NPC npc, Player player);

    // Time in frames before the next attack
    int getCooldown();
}
