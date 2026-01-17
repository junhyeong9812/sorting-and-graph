package com.study.graph.algorithm;

import com.study.graph.util.Graph;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 양방향 BFS
 *
 * 원리: 시작점과 끝점에서 동시에 BFS 수행
 * 용도: 두 노드 사이 최단 경로 찾기
 * 장점: 일반 BFS보다 탐색 범위 감소
 */
@Component
public class BidirectionalBFS implements GraphTraversal {

    @Override
    public List<Integer> traverse(Graph graph, int startNode) {
        // 기본 traverse는 일반 BFS로 동작
        return new BFS().traverse(graph, startNode);
    }

    /**
     * 두 노드 사이의 최단 경로 탐색
     */
    public List<Integer> findShortestPath(Graph graph, int start, int end) {
        if (start == end) {
            return List.of(start);
        }

        Set<Integer> visitedFromStart = new HashSet<>();
        Set<Integer> visitedFromEnd = new HashSet<>();
        Map<Integer, Integer> parentFromStart = new HashMap<>();
        Map<Integer, Integer> parentFromEnd = new HashMap<>();

        Queue<Integer> queueFromStart = new LinkedList<>();
        Queue<Integer> queueFromEnd = new LinkedList<>();

        visitedFromStart.add(start);
        visitedFromEnd.add(end);
        queueFromStart.offer(start);
        queueFromEnd.offer(end);
        parentFromStart.put(start, -1);
        parentFromEnd.put(end, -1);

        while (!queueFromStart.isEmpty() && !queueFromEnd.isEmpty()) {
            // 시작점에서 확장
            Integer meetingPoint = expandLevel(graph, queueFromStart, visitedFromStart,
                    parentFromStart, visitedFromEnd);
            if (meetingPoint != null) {
                return buildPath(meetingPoint, parentFromStart, parentFromEnd);
            }

            // 끝점에서 확장
            meetingPoint = expandLevel(graph, queueFromEnd, visitedFromEnd,
                    parentFromEnd, visitedFromStart);
            if (meetingPoint != null) {
                return buildPath(meetingPoint, parentFromStart, parentFromEnd);
            }
        }

        return List.of(); // 경로 없음
    }

    private Integer expandLevel(Graph graph, Queue<Integer> queue, Set<Integer> visited,
                                Map<Integer, Integer> parent, Set<Integer> otherVisited) {
        int levelSize = queue.size();
        for (int i = 0; i < levelSize; i++) {
            int node = queue.poll();
            for (int neighbor : graph.getNeighbors(node)) {
                if (otherVisited.contains(neighbor)) {
                    parent.put(neighbor, node);
                    return neighbor;
                }
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, node);
                    queue.offer(neighbor);
                }
            }
        }
        return null;
    }

    private List<Integer> buildPath(int meetingPoint, Map<Integer, Integer> parentFromStart,
                                    Map<Integer, Integer> parentFromEnd) {
        List<Integer> path = new ArrayList<>();

        // 시작점에서 만나는 점까지
        int node = meetingPoint;
        while (node != -1) {
            path.add(0, node);
            node = parentFromStart.getOrDefault(node, -1);
        }

        // 만나는 점에서 끝점까지
        node = parentFromEnd.getOrDefault(meetingPoint, -1);
        while (node != -1) {
            path.add(node);
            node = parentFromEnd.getOrDefault(node, -1);
        }

        return path;
    }

    @Override
    public String getName() {
        return "Bidirectional BFS";
    }
}