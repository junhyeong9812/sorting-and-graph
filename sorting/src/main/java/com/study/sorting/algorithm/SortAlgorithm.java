package com.study.sorting.algorithm;

/**
 * 정렬 알고리즘 공통 인터페이스
 */
public interface SortAlgorithm {

    void sort(int[] arr);

    String getName();

    default String getTimeComplexity() {
        return "Unknown";
    }

    default String getSpaceComplexity() {
        return "Unknown";
    }

    default boolean isStable() {
        return false;
    }
}