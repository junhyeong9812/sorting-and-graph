package com.study.sorting.dto;

import com.study.sorting.benchmark.BenchmarkResult;

public record SortResponse(
        String algorithm,
        int dataSize,
        String dataType,
        long executionTimeMs,
        long executionTimeNs,
        long memoryUsedBytes,
        boolean success,
        String message
) {
    public static SortResponse from(BenchmarkResult result) {
        return new SortResponse(
                result.algorithmName(),
                result.dataSize(),
                result.dataType(),
                result.executionTimeMs(),
                result.executionTimeNs(),
                result.memoryUsedBytes(),
                true,
                "정렬 완료"
        );
    }

    public static SortResponse error(String message) {
        return new SortResponse(null, 0, null, 0, 0, 0, false, message);
    }
}