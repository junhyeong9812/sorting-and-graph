import java.util.Arrays;

/**
 * 선택 정렬 연습
 * 
 * [원리]
 * - 전체에서 최솟값을 찾아서 맨 앞과 교환
 * - 그 다음 최솟값을 찾아서 두 번째와 교환
 * - 반복
 * 
 * [동작 예시]
 * [5, 3, 8, 4, 2]
 *              ↑ 최솟값 = 2
 * [2, 3, 8, 4, 5]  (5와 2 교환)
 *     ↑ 최솟값 = 3 (이미 제자리)
 * [2, 3, 8, 4, 5]
 *        ↑ 4가 최솟값
 * [2, 3, 4, 8, 5]  (8과 4 교환)
 * ...
 * [2, 3, 4, 5, 8]
 * 
 * [복잡도]
 * - 시간: O(n²) - 항상
 * - 공간: O(1)
 */
public class SelectionSortPractice {

//    public static void sort(int[] arr) {
//        int n = arr.length;
//
//        // TODO: 직접 구현해보세요
//        // 힌트:
//        // 1. i = 0 ~ n-2 반복
//        // 2. i부터 끝까지 중 최솟값의 인덱스(minIdx) 찾기
//        // 3. arr[i]와 arr[minIdx] 교환
//        for (int index = 0; index < n; index++) {
//            for (int loop = index; loop < n; loop++) {
//                if (arr[loop]<arr[index]) {
//                    int minIdx = arr[index];
//                    arr[index] = arr[loop];
//                    arr[loop] = minIdx;
//                }
//            }
//        }
//    }

    public static interface Sort {
        public void sort(int[] arr);
    }
    public static class SelectionSort implements Sort{

        public SelectionSort() {

        }

        @Override
        public void sort(int[] arr){
            int size = arr.length;
            checkIndex(arr, size);
        }
        private void checkIndex(int[] arr, int size) {
            for (int index = 0; index < size; index++){
                findSortingData(arr, index, size);
            }
        }

        private void findSortingData(int[] arr, int index, int size) {
            for(int loop = index; loop < size; loop++){
                swapIndex(arr, index, loop);
            }
        }

        private void swapIndex(int[] arr, int targetIndex,int loopIndex) {
            if(arr[loopIndex]<arr[targetIndex]){
                int temp = arr[targetIndex];
                arr[targetIndex] = arr[loopIndex];
                arr[loopIndex] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 4, 2};
        Sort sort = new SelectionSort();
        System.out.println("정렬 전: " + Arrays.toString(arr));

//        sort(arr);
        sort.sort(arr);
        
        System.out.println("정렬 후: " + Arrays.toString(arr));
        System.out.println("기대값:  [2, 3, 4, 5, 8]");
    }
}
