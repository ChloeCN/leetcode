package DailyCode;

/**
 * 2399. 检查相同字母间的距离
 */
public class CheckDistanceCode {
    public boolean checkDistances(String s, int[] distance) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && distance[s.charAt(i) - 'a'] != j - i - 1){
                    return false;
                }
            }
        }
        return true;
    }
}
