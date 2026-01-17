# 힙 정렬 (Heap Sort)

## 요약

| 항목 | 내용 |
|-----|------|
| 시간복잡도 (최선) | O(n log n) |
| 시간복잡도 (평균) | O(n log n) |
| 시간복잡도 (최악) | O(n log n) |
| 공간복잡도 | O(1) |
| 안정성 | 불안정 정렬 |

## 힙(Heap)이란?

완전 이진 트리 형태의 자료구조입니다.

**최대 힙 (Max Heap)**: 부모 ≥ 자식
```
       8
      / \
     5   7
    / \
   3   4
배열: [8, 5, 7, 3, 4]
```

**배열로 힙 표현**
```
부모 인덱스:     (i - 1) / 2
왼쪽 자식:      2 * i + 1
오른쪽 자식:    2 * i + 2
```

## 원리

1. 배열을 최대 힙으로 구성 (Build Heap)
2. 루트(최댓값)를 맨 뒤로 이동
3. 힙 크기를 줄이고 다시 힙 속성 복구 (Heapify)
4. 2-3 반복

## 동작 과정
```
초기: [5, 3, 8, 4, 2]

=== 1단계: 최대 힙 구성 ===

트리 형태:
     5
    / \
   3   8
  / \
 4   2

마지막 비단말 노드(인덱스 1)부터 heapify:

인덱스 1 (값 3): 자식 4, 2 중 4가 큼, 3 < 4 이므로 교환
     5
    / \
   4   8
  / \
 3   2

인덱스 0 (값 5): 자식 4, 8 중 8이 큼, 5 < 8 이므로 교환
     8
    / \
   4   5
  / \
 3   2

최대 힙 완성: [8, 4, 5, 3, 2]

=== 2단계: 정렬 ===

Step 1: 루트(8)와 마지막(2) 교환, 힙 크기 축소
  [2, 4, 5, 3 | 8]
  heapify → [5, 4, 2, 3 | 8]

Step 2: 루트(5)와 마지막(3) 교환
  [3, 4, 2 | 5, 8]
  heapify → [4, 3, 2 | 5, 8]

Step 3: 루트(4)와 마지막(2) 교환
  [2, 3 | 4, 5, 8]
  heapify → [3, 2 | 4, 5, 8]

Step 4: 루트(3)와 마지막(2) 교환
  [2 | 3, 4, 5, 8]

결과: [2, 3, 4, 5, 8]
```

## 코드
```java
public void sort(int[] arr) {
    int n = arr.length;

    // 최대 힙 구성
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i);
    }

    // 힙에서 원소 추출
    for (int i = n - 1; i > 0; i--) {
        swap(arr, 0, i);      // 루트를 맨 뒤로
        heapify(arr, i, 0);   // 힙 속성 복구
    }
}

private void heapify(int[] arr, int heapSize, int rootIdx) {
    int largest = rootIdx;
    int left = 2 * rootIdx + 1;
    int right = 2 * rootIdx + 2;

    if (left < heapSize && arr[left] > arr[largest]) {
        largest = left;
    }
    if (right < heapSize && arr[right] > arr[largest]) {
        largest = right;
    }

    if (largest != rootIdx) {
        swap(arr, rootIdx, largest);
        heapify(arr, heapSize, largest);
    }
}
```

## 특징

**장점**
- 항상 O(n log n) 보장
- O(1) 추가 메모리 (In-place)
- 최악 성능이 좋음

**단점**
- 캐시 효율이 낮음 (비연속 메모리 접근)
- 실제로 퀵 정렬보다 느린 경우 많음
- 불안정 정렬

**사용 시기**
- 메모리 제한이 심할 때
- 최악 성능 보장이 필요할 때
- 우선순위 큐 구현
