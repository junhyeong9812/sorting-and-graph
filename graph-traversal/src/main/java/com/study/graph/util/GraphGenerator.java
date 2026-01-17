package com.study.graph.util;

import org.springframework.stereotype.Component;
import java.util.Random;

/**
 * 벤치마크용 그래프 생성기
 */
@Component
public class GraphGenerator {

    private final Random random = new Random();

    public enum GraphType {
        TREE,       // 트리 구조
        RANDOM,     // 랜덤 그래프
        GRID,       // 격자 그래프
        COMPLETE    // 완전 그래프
    }

    public enum Density {
        SPARSE,     // E ≈ V
        MEDIUM,     // E ≈ V log V
        DENSE       // E ≈ V²
    }

    public Graph generate(int nodeCount, GraphType type, Density density) {
        return switch (type) {
            case TREE -> generateTree(nodeCount);
            case RANDOM -> generateRandom(nodeCount, density);
            case GRID -> generateGrid(nodeCount);
            case COMPLETE -> generateComplete(nodeCount);
        };
    }

    private Graph generateTree(int nodeCount) {
        Graph graph = new Graph(nodeCount, false);
        for (int i = 1; i < nodeCount; i++) {
            int parent = random.nextInt(i);
            graph.addEdge(parent, i);
        }
        return graph;
    }

    private Graph generateRandom(int nodeCount, Density density) {
        Graph graph = new Graph(nodeCount, false);

        int edgeCount = switch (density) {
            case SPARSE -> nodeCount;
            case MEDIUM -> (int) (nodeCount * Math.log(nodeCount));
            case DENSE -> nodeCount * nodeCount / 4;
        };

        // 연결성 보장을 위한 스패닝 트리
        for (int i = 1; i < nodeCount; i++) {
            int parent = random.nextInt(i);
            graph.addEdge(parent, i);
        }

        // 추가 간선
        int additionalEdges = edgeCount - (nodeCount - 1);
        for (int i = 0; i < additionalEdges; i++) {
            int from = random.nextInt(nodeCount);
            int to = random.nextInt(nodeCount);
            if (from != to) {
                graph.addEdge(from, to);
            }
        }

        return graph;
    }

    private Graph generateGrid(int nodeCount) {
        int side = (int) Math.sqrt(nodeCount);
        Graph graph = new Graph(side * side, false);

        for (int i = 0; i < side; i++) {
            for (int j = 0; j < side; j++) {
                int current = i * side + j;
                // 오른쪽 연결
                if (j < side - 1) {
                    graph.addEdge(current, current + 1);
                }
                // 아래쪽 연결
                if (i < side - 1) {
                    graph.addEdge(current, current + side);
                }
            }
        }

        return graph;
    }

    private Graph generateComplete(int nodeCount) {
        Graph graph = new Graph(nodeCount, false);
        for (int i = 0; i < nodeCount; i++) {
            for (int j = i + 1; j < nodeCount; j++) {
                graph.addEdge(i, j);
            }
        }
        return graph;
    }
}