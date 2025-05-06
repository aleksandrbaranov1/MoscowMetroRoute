package org.example.moscowmetroroute.Graph;

import java.util.*;

public class MetroMap<T> implements Graph<T> {
    HashMap<T, List<Edge<T>>> adj = new HashMap<>();

    @Override
    public void addStation(T vertex) {
        adj.putIfAbsent(vertex, new ArrayList<>());
    }

    @Override
    public void addEdge(T from, T to, int weight) {
        addStation(from);
        addStation(to);
        adj.get(from).add(new Edge<>(to, weight));
        adj.get(to).add(new Edge<>(from, weight));
    }


    @Override
    public List<T> getNeighbors(T vertex) {
        List<T> neighbors = new ArrayList<>();
        for (Edge<T> edge : adj.getOrDefault(vertex, new ArrayList<>())) {
            neighbors.add(edge.getTarget());
        }
        return neighbors;
    }

    @Override
    public int getWeight(T from, T to) {
        for (Edge<T> edge : adj.getOrDefault(from, new ArrayList<>())) {
            if (edge.getTarget().equals(to)) return edge.getWeight();
        }
        return -1;
    }

    @Override
    public Set<T> getAllVertices() {
        return adj.keySet();
    }

    @Override
    public Path<T> getShortestPathWidthDistance(T start, T end) {
        Map<T, Integer> dist = new HashMap<>();
        Map<T, T> prev = new HashMap<>();
        PriorityQueue<Node<T>> pq = new PriorityQueue<>();

        for (T vertex : adj.keySet()) {
            dist.put(vertex, Integer.MAX_VALUE);
        }
        dist.put(start, 0);
        pq.offer(new Node<>(start, 0));

        while (!pq.isEmpty()) {
            Node<T> current = pq.poll();
            T u = current.getVertex();

            if (u.equals(end)) break;

            for (Edge<T> edge : adj.getOrDefault(u, new ArrayList<>())) {
                T v = edge.getTarget();
                int weight = edge.getWeight();

                int newDist = dist.get(u) + weight;
                if (newDist < dist.getOrDefault(v, Integer.MAX_VALUE)) {
                    dist.put(v, newDist);
                    prev.put(v, u);
                    pq.offer(new Node<>(v, newDist));
                }
            }
        }

        // Восстановление пути
        List<T> path = new ArrayList<>();
        T step = end;
        if (!prev.containsKey(end) && !start.equals(end)) {
            return new Path<>(path, -1); // путь не найден
        }
        while (step != null) {
            path.add(0, step);
            step = prev.get(step);
        }
        String transfer = "-п.";
        ListIterator<T> iterator = path.listIterator();
        if (!path.isEmpty()) {
            T previous = iterator.next();
            while (iterator.hasNext()) {
                T current = iterator.next();
                if (previous.toString().contains(transfer) && current.toString().contains(transfer)) {
                    // Удаляем текущий элемент
                    iterator.remove();
                    // Назад на предыдущий элемент
                    iterator.previous();
                    // Заменяем его на объединённый

                    iterator.set((T) (previous.toString().replace("-п.", "") + "-" + current.toString().replace("-п.", "")));
                    // Двигаемся вперёд для следующей итерации
                    if (iterator.hasNext()) {
                        previous = iterator.next();
                    } else {
                        break;
                    }
                } else {
                    previous = current;
                }
            }
        }

        return new Path<>(path, dist.getOrDefault(end, -1));
    }

}
