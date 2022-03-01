public class recursionTrees{
    public static int permutationWithInfiCoins(int[] arr, int tar, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int ele : arr){
            if((tar - ele)>=0){
                count += permutationWithInfiCoins(arr, tar - ele, ans + ele);
            } 
        }
        return count;
    }

    public static int combinationWithInfiCoins(int[] arr, int tar, int idx, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = idx; i < arr.length; i++){
            if(tar - arr[i] >= 0){
                count += combinationWithInfiCoins(arr, tar - arr[i], i, ans + arr[i]);
            } 
        }
        return count;
    }
    public static int combinationWithSingleCoin(int[] arr, int tar, String ans, int idx){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = idx; i < arr.length; i++){
            if(tar - arr[idx] >= 0){
                count += combinationWithSingleCoin(arr, tar - arr[i], ans + arr[i], i+1);
            } 
        }
        return count;
    }
    public static int permutationWithSingleCoin(int[] arr, int tar, String ans){
        if(tar == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if (arr[i] > 0 && tar - arr[i] >= 0) {
                int val = arr[i];
                arr[i] = -arr[i];
                count += permutationWithSingleCoin(arr, tar - val, ans + val);
                arr[i] = -arr[i];
            }
        }
        return count;
    }
    public static int permutationWithSingleCoin(int[] arr, boolean[] vis, int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (!vis[i] && tar - arr[i] >= 0) {
                vis[i] = true;
                count += permutationWithSingleCoin(arr, vis, tar - arr[i], ans + arr[i]);
                vis[i] = false;
            }
        }

        return count;
    }

    // ====================================================================================================

    public static int permutationWithInfiCoins_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += permutationWithInfiCoins_subSeq(arr, tar - arr[idx], 0, ans + arr[idx]);
        count += permutationWithInfiCoins_subSeq(arr, tar, idx + 1, ans);

        return count;
    }
    public static int combinationWithInfiCoins_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }

            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += combinationWithInfiCoins_subSeq(arr, tar - arr[idx], idx, ans + arr[idx]);
        count += combinationWithInfiCoins_subSeq(arr, tar, idx + 1, ans);

        return count;
    }
    public static int combinationWithSingleCoin_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (tar - arr[idx] >= 0)
            count += combinationWithSingleCoin_subSeq(arr, tar - arr[idx], idx + 1, ans + arr[idx]);
        count += combinationWithSingleCoin_subSeq(arr, tar, idx + 1, ans);

        return count;
    }
    public static int permutationWithSingleCoin_subSeq(int[] arr, int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (arr[idx] > 0 && tar - arr[idx] >= 0) {
            int val = arr[idx];
            arr[idx] = -arr[idx];
            count += permutationWithSingleCoin_subSeq(arr, tar - val, 0, ans + val);
            arr[idx] = -arr[idx];
        }
        count += permutationWithSingleCoin_subSeq(arr, tar, idx + 1, ans);
        return count;
    }

    
    public static void coinChange() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;

        // System.out.println(permutationWithInfi(arr, tar, ""));
        // System.out.println(coFmbinationWithInfi(arr, tar,0, ""));
        // System.out.println(combinationWithSingle(arr, tar, 0, ""));
        // System.out.println(permutationWithSingleCoin(arr, tar, ""));

        // System.out.println(permutationWithInfi_subSeq(arr, tar, 0, ""));
        // System.out.println(combinationWithInfi_subSeq(arr, tar, 0, ""));
        // System.out.println(combinationWithSingle_subSeq(arr, tar, 0, ""));
        // System.out.println(permutationWithSingleCoin_subSeq(arr, tar, 0, ""));
    }

    
    public static void main(String[] args) {
        //queenSet();
        Nqueen();
        
    }
}