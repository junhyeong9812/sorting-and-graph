# BFS (Breadth-First Search, 너비 우선 탐색)

## 요약

| 항목 | 내용 |
|-----|------|
| 시간복잡도 | O(V + E) |
| 공간복잡도 | O(V) |
| 자료구조 | 큐 (Queue) |
| 최단 경로 | **보장됨** (가중치 없는 그래프) |

## 원리

시작점에서 **가까운 노드부터** 차례대로 탐색합니다.
물결이 퍼져나가듯이 탐색 범위가 점점 넓어집니다.

## 동작 과정
```
그래프:
    1
   /|\
  2 3 4
 /|   |
5 6   7

인접 리스트:
1: [2, 3, 4]
2: [1, 5, 6]
3: [1]
4: [1, 7]
5: [2]
6: [2]
7: [4]
```
```
초기: queue = [1], visited = {1}

=== Level 0 ===
dequeue 1 → 인접 [2, 3, 4] 추가
  visited = {1, 2, 3, 4}
  queue = [2, 3, 4]

=== Level 1 ===
dequeue 2 → 인접 [5, 6] 추가 (1은 이미 방문)
  visited = {1, 2, 3, 4, 5, 6}
  queue = [3, 4, 5, 6]

dequeue 3 → 인접 없음 (1은 이미 방문)
  queue = [4, 5, 6]

dequeue 4 → 인접 [7] 추가
  visited = {1, 2, 3, 4, 5, 6, 7}
  queue = [5, 6, 7]

=== Level 2 ===
dequeue 5 → 인접 없음
dequeue 6 → 인접 없음
dequeue 7 → 인접 없음

queue 비어있음 → 종료

방문 순서: 1 → 2 → 3 → 4 → 5 → 6 → 7

레벨별:
  Level 0: [1]
  Level 1: [2, 3, 4]
  Level 2: [5, 6, 7]
```

## 코드
```java
public List<Integer> traverse(Graph graph, int start) {
    List<Integer> result = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();

    visited.add(start);
    queue.offer(start);

    while (!queue.isEmpty()) {
        int node = queue.poll();
        result.add(node);

        for (int neighbor : graph.getNeighbors(node)) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);  // 큐에 넣을 때 방문 처리!
                queue.offer(neighbor);
            }
        }
    }

    return result;
}
```

## 최단 경로

BFS는 가중치 없는 그래프에서 **최단 경로를 보장**합니다.
```
시작점 S에서 각 노드까지 최단 거리:

    S(0)
   / | \
 A(1) B(1) C(1)
 |       |
D(2)   E(2)

S → D 최단 거리 = 2
S → E 최단 거리 = 2
```

## 활용 사례

| 용도 | 설명 |
|-----|------|
| **최단 경로** | 가중치 없는 그래프에서 최단 거리 |
| 레벨별 탐색 | 트리에서 같은 깊이의 노드 처리 |
| 최소 이동 횟수 | 퍼즐, 게임에서 최소 스텝 |
| 이분 그래프 판별 | 두 그룹으로 나눌 수 있는지 |
| 플러드 필 | 이미지에서 연결 영역 채우기 |

## 실전 예제: 미로 최단 경로
```
미로:
S . . # .
# # . # .
. . . . .
. # # # .
. . . . E

BFS 결과 (숫자 = 시작점에서의 거리):
0 1 2 # .
# # 3 # .
. . 4 5 6
. # # # 7
. . . . 8

S → E 최단 거리 = 8
```

## DFS vs BFS 선택 기준

| 상황 | 선택 | 이유 |
|-----|------|-----|
| 최단 경로 필요 | **BFS** | 최단 보장 |
| 모든 경로 탐색 | DFS | 메모리 효율적 |
| 경로 특징 저장 | DFS | 백트래킹 용이 |
| 무한 그래프 | **BFS** | 레벨별 제어 가능 |
| 깊이 제한 있음 | **BFS** | 깊이별 탐색 |
