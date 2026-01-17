package com.study.graph.algorithm;

import com.study.graph.util.Graph;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * DFS 반복(스택) 구현
 *
 * 원리: 스택을 사용한 반복적 깊이 우선 탐색
 * 시간복잡도: O(V + E)
 * 공간복잡도: O(V)
 */
@Component
public class DFSIterative implements GraphTraversal {

    @Override
    public List<Integer> traverse(Graph graph, int startNode) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(startNode);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (visited.contains(node)) {
                continue;
            }

            visited.add(node);
            result.add(node);

            // 역순으로 넣어야 순서 유지
            List<Integer> neighbors = graph.getNeighbors(node);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }

        return result;
    }

    @Override
    public String getName() {
        return "DFS (Iterative)";
    }

    @Override
    public String getSpaceComplexity() {
        return "O(V) - explicit stack";
    }
}