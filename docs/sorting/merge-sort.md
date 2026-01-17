# 병합 정렬 (Merge Sort)

## 요약

| 항목 | 내용 |
|-----|------|
| 시간복잡도 (최선) | O(n log n) |
| 시간복잡도 (평균) | O(n log n) |
| 시간복잡도 (최악) | O(n log n) |
| 공간복잡도 | O(n) |
| 안정성 | 안정 정렬 |

## 원리

**분할 정복 (Divide and Conquer)** 전략을 사용합니다.
1. 분할(Divide): 배열을 반으로 나눔
2. 정복(Conquer): 각 부분을 재귀적으로 정렬
3. 병합(Merge): 정렬된 부분들을 합침

## 동작 과정
```
초기: [5, 3, 8, 4, 2, 7, 1, 6]

=== 분할 (Divide) ===
[5, 3, 8, 4, 2, 7, 1, 6]
         ↓
[5, 3, 8, 4] | [2, 7, 1, 6]
      ↓             ↓
[5, 3] | [8, 4] | [2, 7] | [1, 6]
   ↓       ↓       ↓       ↓
[5] [3] [8] [4] [2] [7] [1] [6]

=== 병합 (Merge) ===
[5] [3] → [3, 5]     (3 < 5)
[8] [4] → [4, 8]     (4 < 8)
[2] [7] → [2, 7]     (2 < 7)
[1] [6] → [1, 6]     (1 < 6)

[3, 5] [4, 8] → [3, 4, 5, 8]
[2, 7] [1, 6] → [1, 2, 6, 7]

[3, 4, 5, 8] [1, 2, 6, 7] → [1, 2, 3, 4, 5, 6, 7, 8]

결과: [1, 2, 3, 4, 5, 6, 7, 8]
```

## 코드
```java
public void sort(int[] arr) {
    if (arr.length <= 1) return;
    mergeSort(arr, 0, arr.length - 1);
}

private void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;

        mergeSort(arr, left, mid);      // 왼쪽 정렬
        mergeSort(arr, mid + 1, right); // 오른쪽 정렬
        merge(arr, left, mid, right);   // 병합
    }
}

private void merge(int[] arr, int left, int mid, int right) {
    int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
    int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

    int i = 0, j = 0, k = left;

    while (i < leftArr.length && j < rightArr.length) {
        if (leftArr[i] <= rightArr[j]) {
            arr[k++] = leftArr[i++];
        } else {
            arr[k++] = rightArr[j++];
        }
    }

    while (i < leftArr.length) arr[k++] = leftArr[i++];
    while (j < rightArr.length) arr[k++] = rightArr[j++];
}
```

## 특징

**장점**
- 항상 O(n log n) 보장
- 안정 정렬
- 연결 리스트 정렬에 적합
- 외부 정렬(External Sort)의 기반

**단점**
- O(n) 추가 메모리 필요
- 소규모 데이터에서 오버헤드

**사용 시기**
- 안정 정렬이 필요할 때
- 최악 성능 보장이 필요할 때
- 연결 리스트 정렬
- 대용량 파일 정렬 (외부 정렬)
