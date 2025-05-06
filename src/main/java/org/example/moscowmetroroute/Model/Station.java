package org.example.moscowmetroroute.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "line", nullable = false)
    private String line;

    // Конструктор без параметров обязателен для JPA
    public Station() {}

    public Station(String name, String line) {
        this.name = name;
        this.line = line;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return name + " (" + line + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(name, station.name) &&
                Objects.equals(line, station.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, line);
    }
}
