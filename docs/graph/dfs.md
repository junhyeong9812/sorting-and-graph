# DFS (Depth-First Search, 깊이 우선 탐색)

## 요약

| 항목 | 내용 |
|-----|------|
| 시간복잡도 | O(V + E) |
| 공간복잡도 | O(V) |
| 자료구조 | 스택 또는 재귀 |
| 최단 경로 | 보장 안됨 |

## 원리

한 방향으로 **끝까지** 탐색하고, 막히면 되돌아가서 다른 방향을 탐색합니다.
미로에서 한 길을 끝까지 가보고, 막히면 돌아와서 다른 길을 시도하는 것과 같습니다.

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

### 재귀 방식
```
DFS(1) 시작, visited = {1}
│
├─ DFS(2), visited = {1, 2}
│  ├─ DFS(5), visited = {1, 2, 5}
│  │  └─ 2는 이미 방문 → 리턴
│  └─ DFS(6), visited = {1, 2, 5, 6}
│     └─ 2는 이미 방문 → 리턴
│
├─ DFS(3), visited = {1, 2, 5, 6, 3}
│  └─ 1은 이미 방문 → 리턴
│
└─ DFS(4), visited = {1, 2, 5, 6, 3, 4}
   └─ DFS(7), visited = {1, 2, 5, 6, 3, 4, 7}
      └─ 4는 이미 방문 → 리턴

방문 순서: 1 → 2 → 5 → 6 → 3 → 4 → 7
```

### 스택 방식
```
초기: stack = [1], visited = {}

pop 1 → visited = {1}, push [4, 3, 2]
pop 2 → visited = {1, 2}, push [6, 5]
pop 5 → visited = {1, 2, 5}
pop 6 → visited = {1, 2, 5, 6}
pop 3 → visited = {1, 2, 5, 6, 3}
pop 4 → visited = {1, 2, 5, 6, 3, 4}, push [7]
pop 7 → visited = {1, 2, 5, 6, 3, 4, 7}

stack 비어있음 → 종료
```

## 코드

### 재귀 구현
```java
public List<Integer> traverse(Graph graph, int start) {
    List<Integer> result = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();
    dfs(graph, start, visited, result);
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
```

### 스택 구현
```java
public List<Integer> traverse(Graph graph, int start) {
    List<Integer> result = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();
    Deque<Integer> stack = new ArrayDeque<>();

    stack.push(start);

    while (!stack.isEmpty()) {
        int node = stack.pop();

        if (visited.contains(node)) continue;

        visited.add(node);
        result.add(node);

        // 역순으로 push해야 순서 유지
        List<Integer> neighbors = graph.getNeighbors(node);
        for (int i = neighbors.size() - 1; i >= 0; i--) {
            if (!visited.contains(neighbors.get(i))) {
                stack.push(neighbors.get(i));
            }
        }
    }

    return result;
}
```

## 활용 사례

| 용도 | 설명 |
|-----|------|
| 경로 존재 확인 | A에서 B로 갈 수 있는지 |
| 사이클 탐지 | 그래프에 순환이 있는지 |
| 위상 정렬 | 선후 관계가 있는 작업 순서 정하기 |
| 연결 요소 | 연결된 컴포넌트 개수 세기 |
| 백트래킹 | N-Queen, 스도쿠, 미로 탐색 |

## 재귀 vs 스택

| | 재귀 | 스택 |
|--|------|------|
| 코드 | 간결함 | 약간 복잡 |
| 메모리 | 시스템 스택 사용 | 명시적 스택 |
| 깊은 그래프 | StackOverflow 위험 | 안전 |
| 추천 | 일반적 상황 | 깊이가 깊을 때 |
