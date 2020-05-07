package model;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class WinningLotto {
    private final Lotto lotto;
    private final int bonusNo;

    public WinningLotto(Lotto lotto, int bonusNo) {
        this.lotto = lotto;
        this.bonusNo = bonusNo;
    }

    public TreeMap<Rank, Integer> getRankCount(List<Lotto> lottoTickets) {
        TreeMap<Rank, Integer> rankCount = initRankCount();
        for (Lotto userLotto : lottoTickets) {
            Rank rank = match(userLotto);
            rankCount.put(rank, rankCount.get(rank) + 1);
        }
        return rankCount;
    }

    private TreeMap<Rank, Integer> initRankCount() {
        TreeMap<Rank, Integer> rankCount = new TreeMap<>(new rankComparator());
        for (Rank rank : Rank.values()) {
            rankCount.put(rank, 0);
        }
        return rankCount;
    }

    private Rank match(Lotto userLotto) {
        int matchCount = 0;
        for (int lottoNumber : lotto.getNumbers()) {
            matchCount += userLotto.containsNumber(lottoNumber) ? 1 : 0;
        }
        boolean matchBonus = userLotto.containsNumber(bonusNo);
        return Rank.valueOf(matchCount, matchBonus);
    }

    private class rankComparator implements Comparator<Rank> {

        @Override
        public int compare(Rank o1, Rank o2) {
            return Integer.compare(o1.getWinningMoney(), o2.getWinningMoney());
        }
    }
}
