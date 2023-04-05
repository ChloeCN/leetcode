package class19;

public class Code03_StickersToSpellWord {

    public static int minStickers1(String[] stickers,String target){
        int ans = process1(stickers,target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static int process1(String[] stickers,String target){
        if(target.length() == 0){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(String first : stickers){
            String rest = minus(target,first);
            if(rest.length() != target.length()){
                min = Math.min(min,process1(stickers,rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String s1,String s2){
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] count = new int[26];
        for (char cha : str1){
            count[cha - 'a']++;
        }
        for (char cha : str2){
            count[cha - 'a']++;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0){
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }

    public static int process2(int[][] stickers,String t){
        if(t.length() == 0){
            return 0;
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for(char cha : target){
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            if(sticker[target[0] - 'a'] > 0){
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if(tcounts[j] > 0){
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min,process2(stickers,rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }
}
