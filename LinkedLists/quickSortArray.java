import java.util.Random;

public class quickSortArray{

    public static Random rand = new Random();

    public static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i]= arr[j];
        arr[j]= temp;
    }
    public static int segregate(int[] arr, int pivotIdx, int sp, int ep){
        swap(arr, pivotIdx, ep);
        int p = sp-1, itr = sp;
        while(itr <= ep){
            if(arr[itr] <= arr[ep])
                swap(arr, ++p, itr);
            itr++;
        }
        return p;
    }
    public static void quickSort(int[] arr, int si, int ei){ //T(N)
        if(si > ei) return;

        int pivotIdx = rand.nextInt(ei - si + 1) + si;
        pivotIdx = segregate(arr, pivotIdx, si, ei); //N

        boolean flag = true;
        for(int i = si+1; i<=ei; i++){
            if(arr[i-1] > arr[i]){
                flag = false; //not sorted
                break;
            }
        }
        if(flag) return; //sorted

        quickSort(arr, si, pivotIdx-1); // T(N/2)
        quickSort(arr, pivotIdx+1, ei); // T(N/2)
    }
    public static void main(String[] args) {
        int[] arr = { -12, 2, 7, 4, 34, 23, 0, 1, -1, -50, 16, 23, 7, 4, 2, 3 };
        quickSort(arr, 0, arr.length - 1);

        for (int ele : arr)
            System.out.print(ele + " ");
    }

}