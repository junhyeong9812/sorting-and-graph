package com.study.graph.benchmark;

import com.study.graph.algorithm.GraphTraversal;
import com.study.graph.util.Graph;
import com.study.graph.util.GraphGenerator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BenchmarkRunner {

    private final GraphGenerator graphGenerator;

    public BenchmarkRunner(GraphGenerator graphGenerator) {
        this.graphGenerator = graphGenerator;
    }

    public BenchmarkResult run(GraphTraversal algorithm, int nodeCount,
                               GraphGenerator.GraphType graphType,
                               GraphGenerator.Density density) {
        Graph graph = graphGenerator.generate(nodeCount, graphType, density);

        System.gc();

        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        long startTime = System.nanoTime();
        List<Integer> result = algorithm.traverse(graph, 0);
        long endTime = System.nanoTime();

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = memoryAfter - memoryBefore;

        return BenchmarkResult.of(
                algorithm.getName(),
                graph.getNodeCount(),
                graph.getEdgeCount(),
                graphType.name(),
                endTime - startTime,
                Math.max(0, memoryUsed),
                result.size()
        );
    }

    public void warmUp(GraphTraversal algorithm, int iterations) {
        for (int i = 0; i < iterations; i++) {
            Graph graph = graphGenerator.generate(100, GraphGenerator.GraphType.RANDOM,
                    GraphGenerator.Density.SPARSE);
            algorithm.traverse(graph, 0);
        }
    }
}