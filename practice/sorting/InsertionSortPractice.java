import java.util.Arrays;

/**
 * 삽입 정렬 연습 ⭐ 이거 먼저 해보세요!
 * 
 * [원리]
 * - 카드 게임처럼 손에 든 카드(정렬된 부분)에 새 카드를 끼워넣기
 * - 새 원소를 정렬된 부분의 올바른 위치에 삽입
 * 
 * [동작 예시]
 * [5, | 3, 8, 4, 2]  ← 5는 이미 정렬됨
 *      ↓ 3을 어디에 넣을까?
 * [3, 5, | 8, 4, 2]  ← 3 < 5 이므로 5 앞에
 *         ↓ 8은?
 * [3, 5, 8, | 4, 2]  ← 8 > 5 이므로 그대로
 *            ↓ 4는?
 * [3, 4, 5, 8, | 2]  ← 4는 3과 5 사이에
 *               ↓ 2는?
 * [2, 3, 4, 5, 8]    ← 2는 맨 앞에
 * 
 * [복잡도]
 * - 시간: O(n²), 거의 정렬된 경우 O(n)!
 * - 공간: O(1)
 */
public class InsertionSortPractice {

    public static void sort(int[] arr) {
        int n = arr.length;
        
        // TODO: 직접 구현해보세요
        // 힌트:
        // 1. i = 1부터 시작 (0번은 이미 정렬됨)
        // 2. key = arr[i] 저장 (삽입할 값)
        // 3. j = i-1부터 시작해서
        // 4. arr[j] > key 인 동안 arr[j+1] = arr[j] (한 칸씩 밀기)
        // 5. 빈 자리에 key 삽입
        
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 4, 2};
        System.out.println("정렬 전: " + Arrays.toString(arr));
        
        sort(arr);
        
        System.out.println("정렬 후: " + Arrays.toString(arr));
        System.out.println("기대값:  [2, 3, 4, 5, 8]");
    }
}
