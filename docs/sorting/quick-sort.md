# 퀵 정렬 (Quick Sort)

## 요약

| 항목 | 내용 |
|-----|------|
| 시간복잡도 (최선) | O(n log n) |
| 시간복잡도 (평균) | O(n log n) |
| 시간복잡도 (최악) | O(n²) - 이미 정렬된 경우 + 나쁜 피벗 |
| 공간복잡도 | O(log n) - 재귀 스택 |
| 안정성 | 불안정 정렬 |

## 원리

1. 피벗(pivot)을 선택
2. 피벗보다 작은 값은 왼쪽, 큰 값은 오른쪽으로 분할
3. 왼쪽과 오른쪽을 재귀적으로 정렬

## 동작 과정
```
초기: [5, 3, 8, 4, 2, 7, 1, 6]
                          ↑ 피벗 = 6

분할 과정 (6보다 작으면 왼쪽으로):
  i: 피벗보다 작은 원소들의 경계
  
  5 < 6 → 왼쪽 유지
  3 < 6 → 왼쪽 유지
  8 > 6 → 오른쪽
  4 < 6 → 8과 교환 → [5, 3, 4, 8, 2, 7, 1, 6]
  2 < 6 → 8과 교환 → [5, 3, 4, 2, 8, 7, 1, 6]
  7 > 6 → 오른쪽
  1 < 6 → 8과 교환 → [5, 3, 4, 2, 1, 7, 8, 6]
  
  피벗을 경계에 배치 → [5, 3, 4, 2, 1, 6, 8, 7]
                              ↑ 피벗 확정

왼쪽 [5, 3, 4, 2, 1]과 오른쪽 [8, 7]을 재귀 정렬

결과: [1, 2, 3, 4, 5, 6, 7, 8]
```

## 코드
```java
public void sort(int[] arr) {
    if (arr.length <= 1) return;
    quickSort(arr, 0, arr.length - 1);
}

private void quickSort(int[] arr, int low, int high) {
    if (low < high) {
        int pivotIdx = partition(arr, low, high);
        quickSort(arr, low, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, high);
    }
}

private int partition(int[] arr, int low, int high) {
    // 랜덤 피벗 선택 (최악 회피)
    int randomIdx = low + new Random().nextInt(high - low + 1);
    swap(arr, randomIdx, high);

    int pivot = arr[high];
    int i = low - 1;

    for (int j = low; j < high; j++) {
        if (arr[j] < pivot) {
            i++;
            swap(arr, i, j);
        }
    }

    swap(arr, i + 1, high);
    return i + 1;
}
```

## 피벗 선택 전략

| 전략 | 장점 | 단점 |
|-----|------|-----|
| 첫 번째 원소 | 단순 | 정렬된 데이터에서 O(n²) |
| 마지막 원소 | 단순 | 정렬된 데이터에서 O(n²) |
| 중간 원소 | 약간 개선 | 여전히 최악 가능 |
| **랜덤 선택** | 최악 확률적 회피 | 난수 생성 비용 |
| Median of 3 | 좋은 피벗 가능성 높음 | 추가 비교 필요 |

## 특징

**장점**
- 평균적으로 가장 빠른 정렬
- 캐시 효율이 좋음 (지역성)
- In-place 정렬 (O(log n) 스택만 사용)

**단점**
- 최악의 경우 O(n²)
- 불안정 정렬
- 피벗 선택이 성능에 큰 영향

**사용 시기**
- 평균 성능이 중요할 때
- 메모리가 제한적일 때
- 대부분의 범용 정렬 (Java의 Arrays.sort도 퀵 정렬 변형)
