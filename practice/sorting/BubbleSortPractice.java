import java.util.Arrays;

/**
 * 버블 정렬 연습
 * 
 * [원리]
 * - 인접한 두 원소를 비교해서 순서가 잘못되면 교환
 * - 큰 값이 거품처럼 뒤로 이동
 * 
 * [동작 예시]
 * [5, 3, 8, 4, 2]
 *  ↓
 * [3, 5, 8, 4, 2]  (5 > 3, 교환)
 * [3, 5, 4, 8, 2]  (8 > 4, 교환)
 * [3, 5, 4, 2, 8]  (8 > 2, 교환) → 8 확정
 *  ↓ 반복...
 * [2, 3, 4, 5, 8]
 * 
 * [복잡도]
 * - 시간: O(n²)
 * - 공간: O(1)
 */
public class BubbleSortPractice {

    public static void sort(int[] arr) {
        int n = arr.length;
        
        // TODO: 직접 구현해보세요
        // 힌트:
        // 1. 바깥 루프: i = 0 ~ n-2
        // 2. 안쪽 루프: j = 0 ~ n-i-2
        // 3. arr[j] > arr[j+1] 이면 교환
        // 4. (최적화) 교환이 없으면 break

        for (int index = 0; index < n - 1; index++) {
            for (int check = 0; check < n - index - 1; check++){
                if (arr[check]>arr[check+1]) {
                    int temp = arr[check];
                    arr[check] = arr[check + 1];
                    arr[check + 1] = temp;
                }
            }
        }
        
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 4, 2};
        System.out.println("정렬 전: " + Arrays.toString(arr));
        
        sort(arr);
        
        System.out.println("정렬 후: " + Arrays.toString(arr));
        System.out.println("기대값:  [2, 3, 4, 5, 8]");
    }
}
