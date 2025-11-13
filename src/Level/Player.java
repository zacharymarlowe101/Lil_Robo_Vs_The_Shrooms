package Level;

import java.awt.Color;
import java.util.ArrayList;

import Engine.GamePanel;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.ImageEffect;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Projectiles.Projectile;
import Utils.Direction;
import Utils.ImageUtils;
import Utils.Point;
import java.util.LinkedList;

import java.awt.event.MouseMotionListener;

public abstract class Player extends GameObject {
    // values that affect player movement
    // these should be set in a subclass
    protected float walkSpeed = 0;
    protected int interactionRange = 1;
    protected Direction currentWalkingXDirection;
    protected Direction currentWalkingYDirection;
    protected Direction lastWalkingXDirection;
    protected Direction lastWalkingYDirection;

    // values used to handle player movement
    protected float moveAmountX, moveAmountY;
    protected float lastAmountMovedX, lastAmountMovedY;

    // values used to keep track of player's current state
    protected PlayerState playerState;
    protected PlayerState previousPlayerState;
    protected Direction facingDirection;
    protected Direction lastMovementDirection;

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key MOVE_LEFT_KEY = Key.LEFT;
    protected Key MOVE_RIGHT_KEY = Key.RIGHT;
    protected Key MOVE_UP_KEY = Key.UP;
    protected Key MOVE_DOWN_KEY = Key.DOWN;
    protected Key INTERACT_KEY = Key.SPACE;

    protected boolean isLocked = false;
    
    //projectile spawning control
    //public boolean didProjectileSpawn = false;
    protected ArrayList<NPC> projectiles = new ArrayList<NPC>();
    protected ArrayList<Projectile> projectile = new ArrayList<Projectile>();
    protected ArrayList<Direction> directions = new ArrayList<Direction>();

    //Health
    protected int health = 10;
    protected boolean isDead = false;

    //Projectile Cooldown
    public double projectileCooldown = 500; //milliseconds
    public boolean canShoot = true;
    public boolean inDialogue = false;

    //Reflect Variables
    protected Key RELFLECT_KEY = Key.R;
    private int maxReflects = 10;
    private int reflectCount = 0;

    // track up to 120 frames of player position history (2 seconds if 60 FPS)
    private static final int MAX_POSITION_HISTORY = 120;
    private LinkedList<Point> positionHistory = new LinkedList<>();


    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
        super(spriteSheet, x, y, startingAnimationName);
        facingDirection = Direction.RIGHT;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        this.affectedByTriggers = true;
    }

    public void update() {
        if (!isLocked) {
            moveAmountX = 0;
            moveAmountY = 0;

            // if player is currently playing through level (has not won or lost)
            // update player's state and current actions, which includes things like determining how much it should move each frame and if its walking or jumping
            do {
                previousPlayerState = playerState;
                handlePlayerState();
            } while (previousPlayerState != playerState);

            // move player with respect to map collisions based on how much player needs to move this frame
            lastAmountMovedY = super.moveYHandleCollision(moveAmountY);
            lastAmountMovedX = super.moveXHandleCollision(moveAmountX);
        }


        if((GamePanel.isMouseClicked() || Keyboard.isKeyDown(Key.SPACE)) && canShoot && !inDialogue){ //Spawn projectile //Keyboard.isKeyDown(Key.E)
            Projectile projectile = new Projectile(x + this.getBounds().getWidth() / 2f, y,new Frame(ImageUtils.createSolidImage(new Color(255, 0, 0), 20, 20), ImageEffect.NONE, 1, null), new Point(this.getCalibratedXLocation(),this.getCalibratedYLocation()),GamePanel.getMousePositionPoint());
            projectileCooldown = projectile.getCooldown();
            projectile.setOwner(this);
            canShoot = false;
            //didProjectileSpawn = true;
            map.addProjectile(projectile);
            projectile.setOwner(this);
        }

        if(Keyboard.isKeyDown(Key.R) && reflectCount !=0 && !inDialogue){ //Reflect projectile
            for(Projectile p : map.getProjectiles()){
                if(p.getOwner() != this){
                    reflectCount--;
                    p.setOwner(this);
                    //float tempVelX = p.getVelocityX();
                    //float tempVelY = p.getVelocityY();
                }
            }

        }
        // if(!GamePanel.isMouseClicked() && didProjectileSpawn == true){ //Reset projectile spawn //Keyboard.isKeyUp(Key.E)
        //     didProjectileSpawn = false;
        // }

        projectileCooldown -= 16.67; //approx 60 FPS
        if(projectileCooldown <= 0 && canShoot == false){
            //System.out.println("Projectile Cooldown Reset");
            canShoot = true;
        }

        handlePlayerAnimation();

        updateLockedKeys();

        // update player's animation
        super.update();
    }

    // based on player's current state, call appropriate player state handling method
    protected void handlePlayerState() {
        switch (playerState) {
            case STANDING:
                playerStanding();
                break;
            case WALKING:
                playerWalking();
                break;
        }
    }

    // player STANDING state logic
    protected void playerStanding() {
        if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
            keyLocker.lockKey(INTERACT_KEY);
            map.entityInteract(this);
        }

        // if a walk key is pressed, player enters WALKING state
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(Key.A) || Keyboard.isKeyDown(MOVE_RIGHT_KEY) || Keyboard.isKeyDown(Key.D) || Keyboard.isKeyDown(MOVE_UP_KEY) || Keyboard.isKeyDown(Key.W) || Keyboard.isKeyDown(MOVE_DOWN_KEY) || Keyboard.isKeyDown(Key.S)) {
            playerState = PlayerState.WALKING;
        }
    }

    // player WALKING state logic
    protected void playerWalking() {
        if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
            keyLocker.lockKey(INTERACT_KEY);
            map.entityInteract(this);
        }

        // if walk left key is pressed, move player to the left
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(Key.A)) {
            moveAmountX -= walkSpeed;
            facingDirection = Direction.LEFT;
            currentWalkingXDirection = Direction.LEFT;
            lastWalkingXDirection = Direction.LEFT;
            lastMovementDirection = Direction.LEFT;
            playerOutOfDialogue();
        }

        // if walk right key is pressed, move player to the right
        else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY) || Keyboard.isKeyDown(Key.D)) {
            moveAmountX += walkSpeed;
            facingDirection = Direction.RIGHT;
            currentWalkingXDirection = Direction.RIGHT;
            lastWalkingXDirection = Direction.RIGHT;
            lastMovementDirection = Direction.RIGHT;
            playerOutOfDialogue();
        }
        else {
            currentWalkingXDirection = Direction.NONE;
        }

        if (Keyboard.isKeyDown(MOVE_UP_KEY) || Keyboard.isKeyDown(Key.W)) {
            moveAmountY -= walkSpeed;
            currentWalkingYDirection = Direction.UP;
            lastWalkingYDirection = Direction.UP;
            lastMovementDirection = Direction.UP;
            playerOutOfDialogue();
        }
        else if (Keyboard.isKeyDown(MOVE_DOWN_KEY) || Keyboard.isKeyDown(Key.S)) {
            moveAmountY += walkSpeed;
            currentWalkingYDirection = Direction.DOWN;
            lastWalkingYDirection = Direction.DOWN;
            lastMovementDirection = Direction.DOWN;
            playerOutOfDialogue();
        }
        else {
            currentWalkingYDirection = Direction.NONE;
        }

        if ((currentWalkingXDirection == Direction.RIGHT || currentWalkingXDirection == Direction.LEFT) && currentWalkingYDirection == Direction.NONE) {
            lastWalkingYDirection = Direction.NONE;
        }

        if ((currentWalkingYDirection == Direction.UP || currentWalkingYDirection == Direction.DOWN) && currentWalkingXDirection == Direction.NONE) {
            lastWalkingXDirection = Direction.NONE;
        }

        if (!Keyboard.isKeyDown(MOVE_LEFT_KEY) && !Keyboard.isKeyDown(Key.A) && !Keyboard.isKeyDown(MOVE_RIGHT_KEY) && !Keyboard.isKeyDown(Key.D) && !Keyboard.isKeyDown(MOVE_UP_KEY) && !Keyboard.isKeyDown(Key.W) && !Keyboard.isKeyDown(MOVE_DOWN_KEY) && !Keyboard.isKeyDown(Key.S)) {
            playerState = PlayerState.STANDING;
        }
    }

    protected void updateLockedKeys() {
        if (Keyboard.isKeyUp(INTERACT_KEY) && !isLocked) {
            keyLocker.unlockKey(INTERACT_KEY);
        }
    }

    // anything extra the player should do based on interactions can be handled here
    protected void handlePlayerAnimation() {
        if (playerState == PlayerState.STANDING) {
            // sets animation to a STAND animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
        }
        else if (playerState == PlayerState.WALKING) {
            // sets animation to a WALK animation based on which way player is facing
            this.currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, GameObject entityCollidedWith) { }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, GameObject entityCollidedWith) { }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public Rectangle getInteractionRange() {
        return new Rectangle(
                getBounds().getX1() - interactionRange,
                getBounds().getY1() - interactionRange,
                getBounds().getWidth() + (interactionRange * 2),
                getBounds().getHeight() + (interactionRange * 2));
    }

    public Key getInteractKey() { return INTERACT_KEY; }
    public Direction getCurrentWalkingXDirection() { return currentWalkingXDirection; }
    public Direction getCurrentWalkingYDirection() { return currentWalkingYDirection; }
    public Direction getLastWalkingXDirection() { return lastWalkingXDirection; }
    public Direction getLastWalkingYDirection() { return lastWalkingYDirection; }

    
    public void lock() {
        isLocked = true;
        playerState = PlayerState.STANDING;
        this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
    }

    public void unlock() {
        isLocked = false;
        playerState = PlayerState.STANDING;
        this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
    }

    // used by other files or scripts to force player to stand
    public void stand(Direction direction) {
        playerState = PlayerState.STANDING;
        facingDirection = direction;
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "STAND_RIGHT";
        }
        else if (direction == Direction.LEFT) {
            this.currentAnimationName = "STAND_LEFT";
        }
    }

    // used by other files or scripts to force player to walk
    public void walk(Direction direction, float speed) {
        playerState = PlayerState.WALKING;
        facingDirection = direction;
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "WALK_RIGHT";
        }
        else if (direction == Direction.LEFT) {
            this.currentAnimationName = "WALK_LEFT";
        }
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
        //playerOutOfDialogue();
    }

    public void takeDamage(int amount) {
        if (isDead) return;

        health -= amount;

        if (health <= 0) {
            health = 0;
            isDead = true;
            System.out.println("Player died.");
        } else {
            System.out.println("Player took " + amount + " damage. Remaining health: " + this.health);
        }
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int newHealth){
        this.health = newHealth;
    }

    public abstract void setWalkSpeed(float walkSpeed);
    
    public abstract float getWalkSpeed();

    public abstract void setAnimationDelay(int animationDelay);

    public abstract int getAnimationDelay();

    public boolean isDead(){
        return isDead;
    }

    public void playerInDialogue(){
        inDialogue = true;
    }

    public void playerOutOfDialogue(){
        inDialogue = false;
    }

}
