package com.study.graph.service;

import com.study.graph.algorithm.GraphTraversal;
import com.study.graph.benchmark.BenchmarkResult;
import com.study.graph.benchmark.BenchmarkRunner;
import com.study.graph.dto.GraphRequest;
import com.study.graph.dto.TraversalResponse;
import com.study.graph.util.GraphGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GraphService {

    private final Map<String, GraphTraversal> algorithms;
    private final BenchmarkRunner benchmarkRunner;

    public GraphService(List<GraphTraversal> algorithmList, BenchmarkRunner benchmarkRunner) {
        this.algorithms = algorithmList.stream()
                .collect(Collectors.toMap(
                        algo -> algo.getName().toLowerCase().replace(" ", "").replace("(", "").replace(")", ""),
                        Function.identity()
                ));
        this.benchmarkRunner = benchmarkRunner;
    }

    public TraversalResponse traverse(GraphRequest request) {
        String key = request.algorithm().toLowerCase().replace(" ", "").replace("(", "").replace(")", "");
        GraphTraversal algorithm = algorithms.get(key);

        if (algorithm == null) {
            return TraversalResponse.error("알고리즘을 찾을 수 없습니다: " + request.algorithm());
        }

        try {
            GraphGenerator.GraphType graphType = GraphGenerator.GraphType.valueOf(request.graphType().toUpperCase());
            GraphGenerator.Density density = GraphGenerator.Density.valueOf(request.density().toUpperCase());

            BenchmarkResult result = benchmarkRunner.run(algorithm, request.nodeCount(), graphType, density);
            return TraversalResponse.from(result);
        } catch (IllegalArgumentException e) {
            return TraversalResponse.error("잘못된 파라미터: " + e.getMessage());
        }
    }

    public List<String> getAvailableAlgorithms() {
        return algorithms.values().stream()
                .map(GraphTraversal::getName)
                .sorted()
                .toList();
    }

    public List<TraversalResponse> runAllBenchmarks(int nodeCount, String graphType, String density) {
        GraphGenerator.GraphType type;
        GraphGenerator.Density den;

        try {
            type = GraphGenerator.GraphType.valueOf(graphType.toUpperCase());
            den = GraphGenerator.Density.valueOf(density.toUpperCase());
        } catch (IllegalArgumentException e) {
            return List.of(TraversalResponse.error("잘못된 파라미터: " + e.getMessage()));
        }

        return algorithms.values().stream()
                .map(algo -> {
                    BenchmarkResult result = benchmarkRunner.run(algo, nodeCount, type, den);
                    return TraversalResponse.from(result);
                })
                .sorted((a, b) -> Long.compare(a.executionTimeNs(), b.executionTimeNs()))
                .toList();
    }
}