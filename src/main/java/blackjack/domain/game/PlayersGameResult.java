package blackjack.domain.game;

import java.util.HashMap;
import java.util.Map;

public class PlayersGameResult {
    private final Map<String, PlayerGameResult> playersResults;

    PlayersGameResult() {
        this.playersResults = new HashMap<>();
    }

    public void put(String name, PlayerGameResult playerGameResult) {
        playersResults.put(name, playerGameResult);
    }

    public Map<String, PlayerGameResult> getPlayersResults() {
        return playersResults;
    }

    public Map<PlayerGameResult, Integer> getDealerResult() {
        Map<PlayerGameResult, Integer> dealerResults = new HashMap<>();
        playersResults.values().forEach(gameResult -> {
                    PlayerGameResult reversedResult = gameResult.reverse();
                    dealerResults.put(reversedResult, dealerResults.getOrDefault(reversedResult, 0) + 1);
                }
        );
        return dealerResults;
    }
}
