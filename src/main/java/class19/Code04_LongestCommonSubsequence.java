package class19;

public class Code04_LongestCommonSubsequence {

    public static int longestCommonSubsequence1(String s1,String s2){
        if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0){
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return process1(str1,str2,str1.length - 1,str2.length - 1);
    }
    /**
     * str1[0...1]和str2[0...j]这个范围内最长公共子序列长度是多少
     * 可能性分类
     * a) 最长公共子序列，一定不以str1[i]字符结尾，也一定不以str2[i]字符结尾
     * b) 最长公共子序列，可能以str1[i]字符结尾，一定不以str2[i]字符结尾
     * c) 最长公共子序列，一定不以str1[i]字符结尾，可能以str2[i]字符结尾
     * d) 最长公共子序列，必须以str1[i]字符结尾，也必须以str2[i]字符结尾
     * 注意：a)、b)、c)、d)并不是完全互斥，可能会有重叠的情况
     * 但可以肯定答案不超过这四种可能性的范围
     * 分别看一下这几种可能性怎么调用后续的递归
     * a)
     * 如果是这种情况，那么有没有str[i]和str2[i]就不重要了
     * 砍掉这两个字符，最长公共子序列 = str1[0...i-1]与str2[0...j-1]的最长公共子序列长度（后续递归）
     * b)
     * 这种情况我们可以确定str2[i]一定没用，砍掉，str1[i]可能有用，保留
     * 所以，最长公共子序列 = str1[0...i]与str2[0...j-1]的最长公共子序列（后续递归）
     * c)
     * 所以，最长公共子序列 = str1[0...i-1]与str2[0...j]的最长公共子序列（后续递归）
     * d)
     * 所以，最长公共子序列 = str1[0...i-1]与str2[0...j-1]的最长公共子序列（后续递归）+ 1（共同的结尾）
     * 综上 四种情况穷尽了所有可能性，其中去最大的即可，且其中b)、c)一定参与最大值的比较
     * 当str1[i] == str2[j]时，a)一定比b)小所以d)参与
     * 当str1[i] ！= str2[j]时，d)压根不存在，所以a)参与
     * 但是
     * a)中str1的范围 < b)中str1的范围，a)中str2的范围 == b)中str2的范围
     * 所以a)天然小于b) 因为有一个样本的范围比b)小
     * a)中str1的范围 < c)中str1的范围，a)中str2的范围 == c)中str2的范围
     * 所以a)天然小于c) 因为有一个样本的范围比c)小
     * 至此可以知道有a)无a)都不影响最大值的决策
     * 所以当str1[i] == str2[j]时，从b)、c)、d)中选最大值
     * str1[i] ！= str2[j]时，b)、c)中选最大值
     */
    public static int process1(char[] str1,char[] str2,int i,int j){
        if(i == 0 && j == 0){
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            if (str1[i] == str2[j]){
                return 1;
            }else {
                return process1(str1,str2,i,j - 1);
            }
        } else if (j == 0) {
            if (str1[i] == str2[j]){
                return 1;
            }else {
                return process1(str1,str2,i - 1,j);
            }
        }else {
            int p1 = process1(str1,str2,i - 1,j);
            int p2 = process1(str1,str2,i,j - 1);
            int p3 = str1[i] == str2[j] ? (1 + process1(str1,str2,i - 1,j - 1)) : 0;
            return Math.max(p1,Math.max(p2,p3));
        }
    }

    public static int longestCommonSubsequence2(String s1,String s2){
        if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0){
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < M; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1,Math.max(p2,p3));
            }
        }
        return dp[N - 1][M - 1];
    }
}
