package com.study.sorting.algorithm;

import org.springframework.stereotype.Component;

/**
 * 삽입 정렬 (Insertion Sort)
 *
 * 원리: 정렬된 부분에 새 원소를 적절한 위치에 삽입
 * 시간복잡도: O(n²), 최선 O(n) - 거의 정렬된 경우
 * 공간복잡도: O(1)
 * 안정 정렬: Yes
 */
@Component
public class InsertionSort implements SortAlgorithm {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    @Override
    public String getName() {
        return "Insertion Sort";
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