package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.PlayerGameResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.gamer.Dealer.HIT_UPPER_BOUND;

public class OutputView {
    static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final String DEAL_ANNOUNCE_FORMAT = String.format("%s와 %%s에게 2장을 나누었습니다.", DEALER_NAME);
    private static final String DEALER_HIT_FORMAT = String.format("%s는 %d 이하라 한장의 카드를 더 받았습니다.", DEALER_NAME, HIT_UPPER_BOUND);
    private static final String CARD_FORMAT = "%s 카드 : %s";
    private static final String RESULT_SCORES_FORMAT = " - 결과: %d";
    private static final String WIN_STATUS_FORMAT = "%s: %s";
    private static final String WIN_ANNOUNCE = "## 최종 승패";

    public static void printDealAnnounce(List<String> playersName) {
        String nameFormat = String.join(DELIMITER, playersName);
        System.out.printf(LINE_SEPARATOR + DEAL_ANNOUNCE_FORMAT + LINE_SEPARATOR, nameFormat);
    }

    public static void printDealerDealCard(List<Card> cards) {
        System.out.printf(CARD_FORMAT + LINE_SEPARATOR, DEALER_NAME, formatCards(cards));
    }

    public static void printDealCards(String name, List<Card> cards) {
        System.out.printf(CARD_FORMAT + LINE_SEPARATOR, name, formatCards(cards));
    }

    private static String formatCards(List<Card> cards) {
        return cards.stream()
                .map(OutputView::formatCard)
                .collect(Collectors.joining(DELIMITER));
    }

    private static String formatCard(Card card) {
        return CardScoreName.convert(card.rank()) + CardSymbolName.convert(card.suit());
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printDealerHitAnnounce() {
        System.out.println(LINE_SEPARATOR + DEALER_HIT_FORMAT + LINE_SEPARATOR);
    }

    public static void printDealerCards(List<Card> cards, int totalScore) {
        System.out.printf(CARD_FORMAT + RESULT_SCORES_FORMAT + LINE_SEPARATOR, DEALER_NAME, formatCards(cards), totalScore);
    }

    public static void printPlayerCards(String name, List<Card> cards, int totalScore) {
        System.out.printf(CARD_FORMAT + RESULT_SCORES_FORMAT + LINE_SEPARATOR, name, formatCards(cards), totalScore);
    }

    public static void printWinAnnounce() {
        System.out.println(LINE_SEPARATOR + WIN_ANNOUNCE);
    }

    // TODO: 플레이어와 딜러 게임결과 반환타입이 다른 것 수정
    public static void printDealerWinStatus(Map<PlayerGameResult, Integer> gameResults) {
        String gameResultsFormat = gameResults.keySet().stream()
                .map(key -> gameResults.get(key) + GameResultName.convert(key))
                .collect(Collectors.joining(" "));
        System.out.printf(WIN_STATUS_FORMAT + LINE_SEPARATOR, DEALER_NAME, gameResultsFormat);
    }

    public static void printPlayerWinStatus(String name, PlayerGameResult playerGameResult) {
        System.out.printf(WIN_STATUS_FORMAT + LINE_SEPARATOR, name, GameResultName.convert(playerGameResult));
    }
}
