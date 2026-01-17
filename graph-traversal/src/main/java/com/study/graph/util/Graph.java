package com.study.graph.util;

import java.util.*;

/**
 * 인접 리스트 기반 그래프
 */
public class Graph {

    private final int nodeCount;
    private final Map<Integer, List<Integer>> adjacencyList;
    private final boolean directed;

    public Graph(int nodeCount, boolean directed) {
        this.nodeCount = nodeCount;
        this.directed = directed;
        this.adjacencyList = new HashMap<>();
        for (int i = 0; i < nodeCount; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int from, int to) {
        adjacencyList.get(from).add(to);
        if (!directed) {
            adjacencyList.get(to).add(from);
        }
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public List<Integer> getNeighbors(int node) {
        return adjacencyList.getOrDefault(node, List.of());
    }

    public int getEdgeCount() {
        int count = adjacencyList.values().stream()
                .mapToInt(List::size)
                .sum();
        return directed ? count : count / 2;
    }

    public boolean isDirected() {
        return directed;
    }
}