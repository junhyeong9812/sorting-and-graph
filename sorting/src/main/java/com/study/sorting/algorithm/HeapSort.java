package com.study.sorting.algorithm;

import org.springframework.stereotype.Component;

/**
 * 힙 정렬 (Heap Sort)
 *
 * 원리: 최대 힙 구성 후 루트를 맨 뒤로 보내며 정렬
 * 시간복잡도: O(n log n) 항상
 * 공간복잡도: O(1)
 * 안정 정렬: No
 */
@Component
public class HeapSort implements SortAlgorithm {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;

        // 최대 힙 구성
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 힙에서 원소 추출
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
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

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public String getName() {
        return "Heap Sort";
    }

    @Override
    public String getTimeComplexity() {
        return "O(n log n)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(1)";
    }

    @Override
    public boolean isStable() {
        return false;
    }
}