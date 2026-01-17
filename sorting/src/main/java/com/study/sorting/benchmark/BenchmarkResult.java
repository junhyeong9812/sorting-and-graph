package com.study.sorting.benchmark;

import java.time.LocalDateTime;

public record BenchmarkResult(
        String algorithmName,
        int dataSize,
        String dataType,
        long executionTimeNs,
        long executionTimeMs,
        long memoryUsedBytes,
        LocalDateTime timestamp
) {
    public static BenchmarkResult of(String algorithmName, int dataSize, String dataType,
                                     long executionTimeNs, long memoryUsedBytes) {
        return new BenchmarkResult(
                algorithmName,
                dataSize,
                dataType,
                executionTimeNs,
                executionTimeNs / 1_000_000,
                memoryUsedBytes,
                LocalDateTime.now()
        );
    }
}