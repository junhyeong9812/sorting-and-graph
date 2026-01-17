package com.study.graph.dto;

import com.study.graph.benchmark.BenchmarkResult;
import java.util.List;

public record TraversalResponse(
        String algorithm,
        int nodeCount,
        int edgeCount,
        String graphType,
        long executionTimeMs,
        long executionTimeNs,
        long memoryUsedBytes,
        int visitedNodes,
        List<Integer> visitOrder,
        boolean success,
        String message
) {
    public static TraversalResponse from(BenchmarkResult result, List<Integer> visitOrder) {
        return new TraversalResponse(
                result.algorithmName(),
                result.nodeCount(),
                result.edgeCount(),
                result.graphType(),
                result.executionTimeMs(),
                result.executionTimeNs(),
                result.memoryUsedBytes(),
                result.visitedNodes(),
                visitOrder.size() <= 100 ? visitOrder : visitOrder.subList(0, 100),
                true,
                "탐색 완료"
        );
    }

    public static TraversalResponse from(BenchmarkResult result) {
        return new TraversalResponse(
                result.algorithmName(),
                result.nodeCount(),
                result.edgeCount(),
                result.graphType(),
                result.executionTimeMs(),
                result.executionTimeNs(),
                result.memoryUsedBytes(),
                result.visitedNodes(),
                null,
                true,
                "탐색 완료"
        );
    }

    public static TraversalResponse error(String message) {
        return new TraversalResponse(null, 0, 0, null, 0, 0, 0, 0, null, false, message);
    }
}