import java.util.*;

class HandofStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int card : hand) {
            countMap.put(card, countMap.getOrDefault(card, 0) + 1);
        }

        List<Integer> sortedKeys = new ArrayList<>(countMap.keySet());
        Collections.sort(sortedKeys);

        for (int card : sortedKeys) {
            int count = countMap.get(card);
            if (count > 0) {
                for (int i = 0; i < groupSize; i++) {
                    int currentCard = card + i;
                    if (countMap.getOrDefault(currentCard, 0) < count) {
                        return false;
                    }
                    countMap.put(currentCard, countMap.get(currentCard) - count);
                }
            }
        }
        return true;
    }
}
