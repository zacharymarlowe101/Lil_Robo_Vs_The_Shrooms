package Game;

public class GameSession {
    private static int difficultyLevel = 1;

    public static int getDifficultyLevel() {
        return difficultyLevel;
    }

    public static void increaseDifficultyLevel() {
        difficultyLevel++;
    }
}
