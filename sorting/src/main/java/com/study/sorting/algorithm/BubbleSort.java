package com.study.sorting.algorithm;

import org.springframework.stereotype.Component;

/**
 * 버블 정렬 (Bubble Sort)
 *
 * 원리: 인접한 두 원소를 비교하여 순서가 잘못되어 있으면 교환
 * 시간복잡도: O(n²), 최선 O(n)
 * 공간복잡도: O(1)
 * 안정 정렬: Yes
 */
@Component
public class BubbleSort implements SortAlgorithm {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            // 교환이 없었으면 이미 정렬 완료
            if (!swapped) break;
        }
    }

    @Override
    public String getName() {
        return "Bubble Sort";
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
        return true;
    }
}