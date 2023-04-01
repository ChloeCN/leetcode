package class18;

 /**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数
*/
public class Code2_CardsInLine {
 public static int win1(int[] arr){
  if(arr == null || arr.length == 0){
   return 0;
  }
  int first = f1(arr,0, arr.length - 1);
  int second = g1(arr,0, arr.length - 1);
  return Math.max(first,second);
 }

 public static int f1(int[] arr,int L,int R){
  if(L == R){
   return arr[L];
  }
  int p1 = arr[L] + g1(arr,L + 1,R);
  int p2 = arr[L] + g1(arr,L,R - 1);
  return Math.max(p1,p2);
 }

 public static int g1(int[] arr,int L,int R){
  if(L == R){
   return 0;
  }
  int p1 = f1(arr, L + 1, R);
  int p2 = f1(arr, L, R - 1);
  return Math.min(p1,p2);
 }

//  public static int win2(int[] arr){
//   if(arr == null || arr.length == 0){
//    return 0;
//   }
//   int N = arr.length;
//   int[][] fmap = new int[N][N];
//   int[][] gmap = new int[N][N];
//   for (int i = 0; i < N; i++) {
//    for (int j = 0; j < N; j++) {
//      fmap[i][j] = -1;
//      gmap[i][j] = -1;
//    }
//   }
//   int first = f2(arr,0,arr.length - 1,fmap,gmap);
//   int second = g2(arr,0,arr.length - 1,fmap,gmap);
//   return Math.min(first,second);
//  }

 public static int f2(int[] arr,int L,int R,int[][] fmap,int[][] gmap){
  if(fmap[L][R] != -1){
   return fmap[L][R];
  }
  int ans = 0;
  if(L == R){
   ans = arr[L];
  }else{
   int p1 = arr[L] + g2(arr,L + 1,R,fmap,gmap);
   int p2 = arr[L] + g2(arr,L + 1,R,fmap,gmap);
   ans = Math.max(p1,p2);
  }
  fmap[L][R] = ans;
  return ans;
}

  public static int g2(int[] arr,int L,int R,int[][] fmap,int[][] gmap){
   if(gmap[L][R] != -1){
    return gmap[L][R];
   }
   int ans = 0;
   if(L != R){
    int p1 = f2(arr,L + 1,R,fmap,gmap);
    int p2 = f2(arr,L,R - 1,fmap,gmap);
    ans = Math.min(p1,p2);
   }
   gmap[L][R] = ans;
   return ans;
  }

  public static int win3(int[] arr){
  if(arr == null || arr.length == 0){
   return 0;
  }
  int N = arr.length;;
  int[][] fmap = new int[N][N];
  int[][] gmap = new int[N][N];
   for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
     gmap[i][j] = 1;
    }
   }
   return N;
  }

}
