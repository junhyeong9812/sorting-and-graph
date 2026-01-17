package com.study.graph.benchmark;

import java.time.LocalDateTime;

public record BenchmarkResult(
        String algorithmName,
        int nodeCount,
        int edgeCount,
        String graphType,
        long executionTimeNs,
        long executionTimeMs,
        long memoryUsedBytes,
        int visitedNodes,
        LocalDateTime timestamp
) {
    public static BenchmarkResult of(String algorithmName, int nodeCount, int edgeCount,
                                     String graphType, long executionTimeNs,
                                     long memoryUsedBytes, int visitedNodes) {
        return new BenchmarkResult(
                algorithmName,
                nodeCount,
                edgeCount,
                graphType,
                executionTimeNs,
                executionTimeNs / 1_000_000,
                memoryUsedBytes,
                visitedNodes,
                LocalDateTime.now()
        );
    }
}