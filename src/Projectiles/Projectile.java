package Projectiles;

import java.awt.Point;
import java.util.ArrayList;

import GameObject.Frame;
import GameObject.GameObject;
import Level.MapCollisionHandler;
import Level.MapEntity;
import Level.NPC;
import Level.Player;
import Utils.Direction;
import java.util.HashMap;
import Builders.FrameBuilder;
import GameObject.SpriteSheet;
import GameObject.AnimatedSprite;
import java.awt.image.BufferedImage;
import NPCs.*;
import Engine.ImageLoader;

public class Projectile extends MapEntity{
    //private Direction direction;
    private double dx, dy;
    private double length;
    private double dirX, dirY;
    private int speed = 5;
    private float velocityX, velocityY;
    private double lifetime = 1_000; // milliseconds
    private double deltaTime = 16.67; // milliseconds, approx 60 FPS
    private boolean expired = false;
    protected GameObject owner;
    private int cooldown = 500; // milliseconds

    protected BufferedImage bulletImage;
    protected AnimatedSprite bulletSprite;


    protected ArrayList<Projectile> projectilesHit = new ArrayList<>();

    //Damage
    public int enemyDamage = 1;
    public static int playerDamage = 1;


    public Projectile(SpriteSheet spriteSheet, float x, float y, Utils.Point p1, Point p2) { //P1 is start point, P2 is target point
        super(x, y, spriteSheet, "Bullet");
        this.animations = loadAnimations(spriteSheet);

        //this.direction = direction;
        dx = p2.x - p1.x;
        dy = p2.y - p1.y;
        length = Math.sqrt(dx*dx + dy*dy);
        dirX = dx / length;
        dirY = dy / length;
        velocityX = (float)(dirX * speed);
        velocityY = (float)(dirY * speed);
    }


    public Projectile(SpriteSheet spriteSheet,float x, float y, float dirX, float dirY, NPC owner) { //P1 is start point, P2 is target point
    super(x, y, spriteSheet, "Bullet");
        super.animations = loadAnimations(spriteSheet);

        //this.direction = direction;
        length = Math.sqrt(dx*dx + dy*dy);
        this.dirX = dirX;
        this.dirY = dirY;
        velocityX = (float)(dirX * speed);
        velocityY = (float)(dirY * speed);
    }


    public Projectile(SpriteSheet spriteSheet, float x, float y) {
        super(x, y, spriteSheet, "Bullet");
        this.animations = loadAnimations(spriteSheet);

    }


    public void update(Player player) {
        this.performAction(player);
        super.update();//line 74, the super update
        moveY(velocityY);
        moveX(velocityX);
        lifetime -= deltaTime; //decides how long projectile lasts in the scene if they dont hit anything
        cooldown -= deltaTime; //this is for time between bullets spawned
        if(isExpired()){ //if its been a second it despawns
            this.isHidden = true;
            expired = true;
        }

        if (map != null && map.getProjectiles() != null) {
            for (NPC npcs : map.getActiveNPCs()) { //checks if projectile hits npc
                //System.out.println("Checking if Exists");
                if (this == null || this.isHidden()) continue;
                if (!projectilesHit.contains(this) && this.getBounds().intersects(npcs.getBounds()/*<<<Should be list of all NPCs*/) && this.getOwner() != npcs) {
                    if (npcs.getHealth() > 0) {
                        npcs.takeDamage(playerDamage);
                        projectilesHit.add(this);
                        this.isHidden = true; // Hide the projectile upon hitting the NPC
                        map.getProjectiles().remove(this);
                    }
                }
                //System.out.println("Checking projectile collisions for Wall " + MapCollisionHandler.isCollidingWithMapEntity(p, map, null));
            }
            //System.out.println("Player Reflecting = " + player.isReflecting);
            if(!projectilesHit.contains(this) && this.getBounds().intersects(player.getBounds()) && this.getOwner() != player){ //checks if projectile hits player
                if (player.getHealth() > 0) {
                        player.takeDamage(enemyDamage);
                        projectilesHit.add(this);
                        this.isHidden = true; // Hide the projectile upon hitting the NPC
                        map.getProjectiles().remove(this);
                    }
            }
            if(!projectilesHit.contains(this) && MapCollisionHandler.isCollidingWithMapEntity(this, map, null)){ //checks if projectile hits wall
               // System.out.println("Projectile hit wall");
                projectilesHit.add(this);
                this.isHidden = true;
                map.getProjectiles().remove(this);
            }
            
        }
    }

    protected void performAction(Player player) {
    }

  
    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {

    this.currentAnimationName = "Bullet";

        

    HashMap<String, Frame[]> animation;

        
        animation = new HashMap<String, Frame[]>() {{
            put("Bullet", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                        .withScale(2)
                        .withBounds(0, 0, spriteSheet.getSpriteWidth(), spriteSheet.getSpriteHeight())
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1))
                        .withScale(2)
                        .withBounds(0, 0, spriteSheet.getSpriteWidth(), spriteSheet.getSpriteHeight())
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2))
                        .withScale(2)
                        .withBounds(0, 0, spriteSheet.getSpriteWidth(), spriteSheet.getSpriteHeight())
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 3))
                        .withScale(2)
                        .withBounds(0, 0, spriteSheet.getSpriteWidth(), spriteSheet.getSpriteHeight())
                        .build(),
            });
    
        }};
 

        return animation;
    }


    public void walk(Direction direction, float speed) {
        if (direction == Direction.UP) {
            moveY(-speed);
        }
        else if (direction == Direction.DOWN) {
            moveY(speed);
        }
        else if (direction == Direction.LEFT) {
            moveX(-speed);
        }
        else if (direction == Direction.RIGHT) {
            moveX(speed);
        }
    }

    public boolean isExpired() {
        return lifetime <= 0;
    }

    public void setOwner(GameObject owner) {
        this.owner = owner;
    }

    public Object getOwner() {
        return owner;
    }

    public static int getPlayerDamage(){
        return playerDamage;
    }

    public static void setPlayerDamage(int damage){
        playerDamage = damage;
    }

    public void setLifetime(double lifetime) {
    this.lifetime = lifetime;
    }

    public double getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void reverseDirection() {
        this.velocityX = (float)(dirX * -1);
        this.velocityY = (float)(dirY * -1);
    }

}
