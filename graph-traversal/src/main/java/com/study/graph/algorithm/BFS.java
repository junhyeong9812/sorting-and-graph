package com.study.graph.algorithm;

import com.study.graph.util.Graph;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * BFS 구현
 *
 * 원리: 가까운 노드부터 차례대로 탐색
 * 시간복잡도: O(V + E)
 * 공간복잡도: O(V)
 * 특징: 가중치 없는 그래프에서 최단 경로 보장
 */
@Component
public class BFS implements GraphTraversal {

    @Override
    public List<Integer> traverse(Graph graph, int startNode) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startNode);
        queue.offer(startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int neighbor : graph.getNeighbors(node)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    @Override
    public String getName() {
        return "BFS";
    }
}