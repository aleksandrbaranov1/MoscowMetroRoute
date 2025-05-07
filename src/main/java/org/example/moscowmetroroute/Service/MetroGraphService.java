package org.example.moscowmetroroute.Service;

import jakarta.annotation.PostConstruct;
import org.example.moscowmetroroute.Graph.Graph;
import org.example.moscowmetroroute.Graph.MetroMap;
import org.example.moscowmetroroute.Model.Edge;
import org.example.moscowmetroroute.Model.Station;
import org.example.moscowmetroroute.Repository.EdgeRepository;
import org.example.moscowmetroroute.Repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetroGraphService {
    private final StationRepository stationRepository;
    private final EdgeRepository edgeRepository;
    private final Graph<Station> graph = new MetroMap<>();

    @Autowired
    public MetroGraphService(StationRepository stationRepository, EdgeRepository edgeRepository) {
        this.stationRepository = stationRepository;
        this.edgeRepository = edgeRepository;
        initializeGraph();
    }

    @PostConstruct
    private void initializeGraph() {
        List<Station> stations = stationRepository.findAll();
        for (Station station : stations) {
            graph.addStation(station);
        }

        List<Edge> edges = edgeRepository.findAll();
        for (Edge edge : edges) {
            graph.addEdge(edge.getFromStation(), edge.getToStation(), edge.getWeight());
        }

        System.out.println("Граф инициализирован:");
        for (Station station : graph.getAllVertices()) {
            System.out.println("Станция: " + station);
            for (Station neighbor : graph.getNeighbors(station)) {
                System.out.println("  -> сосед: " + neighbor);
            }
        }
    }

    public List<Station> findShortestPath(int fromId, int toId) {
        Station from = stationRepository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Станция не найдена"));
        Station to = stationRepository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Станция не найдена: "));

        System.out.println("Поиск маршрута от: " + from);
        System.out.println("До: " + to);
        System.out.println("Все станции в графе: ");
        for (Station station : graph.getAllVertices()) {
            System.out.println("- " + station);
        }

        Graph.Path<Station> path = graph.getShortestPathWidthDistance(from, to);

        System.out.println("Найденный путь:");
        for (Station station : path.getPath()) {
            System.out.println("-> " + station);
        }
        System.out.println("Общая дистанция: " + path.getDistance());

        return path.getPath();
    }
    public int findDistance(int fromId, int toId){
        Station from = stationRepository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Станция не найдена"));
        Station to = stationRepository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Станция не найдена: "));

        Graph.Path<Station> path = graph.getShortestPathWidthDistance(from, to);

        return path.getDistance();
    }
    public List<Station> getStationByNameContaining(String name){
        String capName = name.substring(0, 1).toUpperCase() + name.substring(1);
        return stationRepository.findByNameContainingIgnoreCase(capName);
    }
}
