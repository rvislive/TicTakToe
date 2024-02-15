package factories;

import strategies.botplayingstrategy.BotPlayingStrategy;
import strategies.botplayingstrategy.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getStrategyForDifficultyLevel() {
        return new RandomBotPlayingStrategy();
    }
}
