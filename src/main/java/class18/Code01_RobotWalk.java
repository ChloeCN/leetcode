package class18;

/**
 * 18 暴力递归到动态规划（一）
 * 内容：
 * 讲述暴力递归和动态规划的关系
 * 记忆化搜索
 * 动态规划都可以由暴力递归改进过来，解决动态规划的套路
 * 常见的尝试模型
 * 设计尝试过程的原则
 * 本节是暴力递归到动态规划的总纲（很重要）
 * 后续的课都是在讲述这一系列的套路
 * 题目：
 *
 * 假设有排成一行的N个位置记为1~N，N一定大于或等于2
 * 开始时机器人在其中的M位置上(M一定是1~N中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数
 */
public class Code01_RobotWalk {
    public static int ways1(int N,int start,int aim,int k){
        if(N < 2 || start < 1 || start > N || aim < 1 || aim > N || k < 1){
            return -1;
        }
        return process1(start,k,aim,N);
    }

    /**
     *
     * @param cur 机器人当前的位置
     * @param rest 还有rest步走到aim
     * @param aim 最终位置aim
     * @param N 总共有1 ~ N 的位置
     * @return 机器人从cur出发 走过rest步之后，最终停在aim的方法有多少种
     */
    public static int process1(int cur,int rest,int aim,int N){
        if(rest == 0){
            return cur == aim ? 1 : 0;
        }
        if(cur == 1){
            return process1(2, rest - 1,aim,N);
        }
        if(cur == N){
            return process1(N - 1,rest - 1,aim,N);
        }
        return process1(cur - 1,rest - 1,aim,N) + process1(cur + 1, rest - 1, aim, N);
    }

    public static int ways2(int N,int start,int aim,int k){
        if(N < 2 || start < 1 || start > N || aim < 1 || aim > N || k < 1){
            return -1;
        }
        int[][] dp = new int[N + 1][k + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(start,k,aim,N,dp);
    }

    public static int process2(int cur,int rest,int aim,int N,int[][] dp){
        if(dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        int ans = 0;
        if(rest == 0){
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2,rest - 1,aim,N,dp);
        } else if (cur == N) {
            ans = process2(N - 1,rest - 1,aim,N,dp);
        }else {
            ans = process2(cur - 1,rest - 1,aim,N,dp) + process2(cur + 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    public static int ways3(int N,int start,int aim,int k){
        if(N < 2 || start < 1 || start > N || aim < 1 || aim > N || k < 1){
            return -1;
        }
        int[][] dp =new int[N + 1][k + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= k; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] =dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][k];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5,2,4,6));
        System.out.println(ways2(5,2,4,6));
        System.out.println(ways3(5,2,4,6));
    }
}
