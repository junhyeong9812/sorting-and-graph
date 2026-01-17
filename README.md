# Sorting and Graph Traversal Study

정렬 알고리즘과 그래프 탐색 알고리즘을 학습하고, 실제 환경에서 성능을 측정하는 실험 프로젝트입니다.

## 🚀 Quick Start
```bash
# 빌드 & 실행
docker compose up -d --build

# 상태 확인
docker compose ps

# 벤치마크 실행
curl "http://localhost:8081/api/sort/benchmark?dataSize=10000&dataType=RANDOM" | jq
curl "http://localhost:8091/api/graph/benchmark?nodeCount=1000&graphType=RANDOM&density=SPARSE" | jq
```

---

## 📊 벤치마크 결과 요약

### 정렬 알고리즘 (100,000개 랜덤 데이터)

| 알고리즘 | 실행 시간 | 복잡도 | 추천 상황 |
|---------|----------|--------|----------|
| **Quick Sort** | **7.6ms** | O(n log n) | 일반적 상황 |
| Merge Sort | 9.8ms | O(n log n) | 안정 정렬 필요시 |
| Heap Sort | 10.9ms | O(n log n) | 메모리 제한시 |
| Insertion Sort | 463ms | O(n²) | 거의 정렬된 데이터 |
| Selection Sort | 4,089ms | O(n²) | ❌ 비추천 |
| Bubble Sort | 14,033ms | O(n²) | ❌ 비추천 |

> **O(n log n) vs O(n²) 차이**: 100,000개 기준 **1,800배 이상** 성능 차이!

### 그래프 탐색 (100,000 노드)

| 알고리즘 | 실행 시간 | 최단경로 | 추천 상황 |
|---------|----------|---------|----------|
| Bidirectional BFS | 38.8ms | ✅ | 두 노드 간 경로 |
| DFS (Iterative) | 39.3ms | ❌ | 대규모 그래프 |
| BFS | 48.6ms | ✅ | **최단 경로 필요시** |
| DFS (Recursive) | 48.9ms | ❌ | 소규모 그래프 |

→ 상세 결과: [docs/sorting/BENCHMARK.md](./docs/sorting/BENCHMARK.md) | [docs/graph/BENCHMARK.md](./docs/graph/BENCHMARK.md)

---

## 📚 문서

### 정렬 알고리즘
| 문서 | 설명 |
|-----|------|
| [개요](./docs/sorting/README.md) | 시간/공간 복잡도 개념 |
| [버블 정렬](./docs/sorting/bubble-sort.md) | O(n²), 안정, 교육용 |
| [선택 정렬](./docs/sorting/selection-sort.md) | O(n²), 불안정 |
| [삽입 정렬](./docs/sorting/insertion-sort.md) | O(n²), 거의 정렬된 데이터에 강함 |
| [병합 정렬](./docs/sorting/merge-sort.md) | O(n log n), 안정, 추가 메모리 |
| [퀵 정렬](./docs/sorting/quick-sort.md) | O(n log n), 평균 최고 성능 |
| [힙 정렬](./docs/sorting/heap-sort.md) | O(n log n), In-place |
| [**벤치마크 결과**](./docs/sorting/BENCHMARK.md) | 실측 데이터 |

### 그래프 탐색
| 문서 | 설명 |
|-----|------|
| [개요](./docs/graph/README.md) | 그래프 기초 개념 |
| [DFS](./docs/graph/dfs.md) | 깊이 우선, 백트래킹 |
| [BFS](./docs/graph/bfs.md) | 너비 우선, 최단 경로 |
| [**벤치마크 결과**](./docs/graph/BENCHMARK.md) | 실측 데이터 |

---

## 🛠 기술 스택

| 구분 | 기술 |
|-----|------|
| Language | Java 21 |
| Framework | Spring Boot 3.5.0 |
| Build | Gradle 8.x |
| Container | Docker |

## 📁 프로젝트 구조
```
sorting-and-graph/
├── sorting/                 # 정렬 알고리즘 모듈 (port: 8081~8083)
├── graph-traversal/         # 그래프 탐색 모듈 (port: 8091~8093)
├── docs/                    # 문서
│   ├── sorting/            
│   │   ├── README.md       # 개념 정리
│   │   ├── BENCHMARK.md    # 벤치마크 결과
│   │   └── *.md            # 각 알고리즘 설명
│   └── graph/              
│       ├── README.md
│       ├── BENCHMARK.md
│       └── *.md
├── docker-compose.yml
└── README.md
```

---

## 🔬 리소스 제한 실험

Docker를 통해 다양한 리소스 환경에서 테스트 가능:

| 프로필 | CPU | Memory | Sorting Port | Graph Port |
|-------|-----|--------|--------------|------------|
| low | 1 core | 256MB | 8081 | 8091 |
| medium | 2 cores | 512MB | 8082 | 8092 |
| high | 4 cores | 1GB | 8083 | 8093 |
```bash
# low vs high 비교
curl "http://localhost:8081/api/sort/benchmark?dataSize=100000&dataType=RANDOM" | jq
curl "http://localhost:8083/api/sort/benchmark?dataSize=100000&dataType=RANDOM" | jq
```

---

## 📡 API 엔드포인트

### Sorting API
```bash
# 알고리즘 목록
GET /api/sort/algorithms

# 전체 벤치마크
GET /api/sort/benchmark?dataSize=10000&dataType=RANDOM

# 단일 정렬
POST /api/sort
{"algorithm":"quicksort","dataSize":10000,"dataType":"RANDOM"}
```

### Graph API
```bash
# 알고리즘 목록
GET /api/graph/algorithms

# 전체 벤치마크
GET /api/graph/benchmark?nodeCount=1000&graphType=RANDOM&density=SPARSE

# 단일 탐색
POST /api/graph/traverse
{"algorithm":"bfs","nodeCount":1000,"graphType":"RANDOM","density":"SPARSE","startNode":0}
```

---

## 📖 학습 포인트

### 1. 시간복잡도가 실제로 중요한 이유
```
100,000개 데이터:
  O(n log n) Quick Sort:  7.6ms
  O(n²) Bubble Sort:      14,033ms
  
→ 1,846배 차이!
```

### 2. 데이터 상태가 성능에 미치는 영향
```
Insertion Sort (10,000개):
  RANDOM:        29.7ms
  NEARLY_SORTED:  3.8ms  ← 7.8배 빨라짐!
```

### 3. 메모리 vs 속도 트레이드오프
```
Merge Sort: 빠르지만 O(n) 추가 메모리 (10MB+)
Heap Sort:  약간 느리지만 O(1) 추가 메모리
```

---

## 참고 자료

- [VisuAlgo - 알고리즘 시각화](https://visualgo.net/)
- [Big-O Cheat Sheet](https://www.bigocheatsheet.com/)
- Introduction to Algorithms (CLRS)
