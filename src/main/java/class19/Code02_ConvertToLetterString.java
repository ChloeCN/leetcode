package class19;

public class Code02_ConvertToLetterString {

    public static int number(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        return process(str.toCharArray(),0);
    }
    public static int process(char[] str,int i){
        if (i == str.length){
            return 1;
        }//i没到最后说明有字符
        if (str[i] == '0') {
            return 0;//说明之前的决定有问题
        }
        int ways = process(str,i + 1);
        if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27){
            ways += process(str,i + 2);
        }
        return ways;
    }
}
