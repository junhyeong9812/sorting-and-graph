package com.study.graph.algorithm;

import com.study.graph.util.Graph;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DFS 재귀 구현
 *
 * 원리: 한 방향으로 끝까지 탐색 후 백트래킹
 * 시간복잡도: O(V + E)
 * 공간복잡도: O(V) - 재귀 스택
 */
@Component
public class DFSRecursive implements GraphTraversal {

    @Override
    public List<Integer> traverse(Graph graph, int startNode) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfs(graph, startNode, visited, result);
        return result;
    }

    private void dfs(Graph graph, int node, Set<Integer> visited, List<Integer> result) {
        visited.add(node);
        result.add(node);

        for (int neighbor : graph.getNeighbors(node)) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited, result);
            }
        }
    }

    @Override
    public String getName() {
        return "DFS (Recursive)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(V) - recursive stack";
    }
}