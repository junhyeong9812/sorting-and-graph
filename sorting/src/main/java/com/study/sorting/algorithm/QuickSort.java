package com.study.sorting.algorithm;

import org.springframework.stereotype.Component;
import java.util.Random;

/**
 * 퀵 정렬 (Quick Sort)
 *
 * 원리: 피벗 기준으로 작은 값은 왼쪽, 큰 값은 오른쪽으로 분할
 * 시간복잡도: O(n log n) 평균, O(n²) 최악
 * 공간복잡도: O(log n) - 재귀 스택
 * 안정 정렬: No
 */
@Component
public class QuickSort implements SortAlgorithm {

    private final Random random = new Random();

    @Override
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
        // 랜덤 피벗 선택
        int randomIdx = low + random.nextInt(high - low + 1);
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

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }

    @Override
    public String getTimeComplexity() {
        return "O(n log n) avg, O(n²) worst";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(log n)";
    }

    @Override
    public boolean isStable() {
        return false;
    }
}