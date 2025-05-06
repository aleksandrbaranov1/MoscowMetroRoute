package org.example.moscowmetroroute.Graph;

import java.util.List;
import java.util.Set;
public interface Graph <T>{
    void addStation(T vertex);
    void addEdge(T from, T to, int weight);
    List<T> getNeighbors(T vertex);
    int getWeight(T from, T to);
    Set<T> getAllVertices();
    Path<T> getShortestPathWidthDistance(T start, T end);

    class Edge<T>{
        private T target;
        private int weight;

        public Edge(T target, int weight){
            this.target = target;
            this.weight = weight;
        }

        public T getTarget() {
            return target;
        }

        public int getWeight() {
            return weight;
        }

        public void setTarget(T target) {
            this.target = target;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
    static class Node<T> implements Comparable<Node<T>>{
        private T vertex;
        private int distance;
        public Node(T vertex, int distance){
            this.vertex = vertex;
            this.distance = distance;
        }

        public int compareTo(Node<T> other){
            return Integer.compare(this.distance, other.distance);
        }

        public T getVertex() {
            return vertex;
        }

        public void setVertex(T vertex) {
            this.vertex = vertex;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
    class Path<T>{
        private List<T> path;
        private int distance;

        public Path(List<T> path, int distance){
            this.path = path;
            this.distance = distance;
        }

        public List<T> getPath() {
            return path;
        }

        public void setPath(List<T> path) {
            this.path = path;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }
}

