package org.example.moscowmetroroute.Repository;

import org.example.moscowmetroroute.Model.Edge;
import org.example.moscowmetroroute.Model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EdgeRepository extends JpaRepository<Edge, Integer> {
    List<Edge> findByFromStation(Station from);
}
