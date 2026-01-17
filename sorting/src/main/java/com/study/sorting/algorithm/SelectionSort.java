package com.study.sorting.algorithm;

import org.springframework.stereotype.Component;

/**
 * 선택 정렬 (Selection Sort)
 *
 * 원리: 최솟값을 찾아 맨 앞과 교환하는 과정을 반복
 * 시간복잡도: O(n²) 항상
 * 공간복잡도: O(1)
 * 안정 정렬: No
 */
@Component
public class SelectionSort implements SortAlgorithm {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }

    @Override
    public String getName() {
        return "Selection Sort";
    }

    @Override
    public String getTimeComplexity() {
        return "O(n²)";
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