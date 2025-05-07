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

    @Transient
    private int[] rgbColor;
    public Station() {}

    public Station(String name, String line) {
        this.name = name;
        this.line = line;
        this.rgbColor = getColorByLine(line);
    }
    private int[] getColorByLine(String line) {
        switch (line) {
            case "Сокольническая":
                return new int[]{255, 0, 0};
            case "Замоскворецкая":
                return new int[]{0, 255, 0};
            case "Арбатско-Покровская":
                return new int[]{0, 0, 255};
            case "Филёвская":
                return new int[]{0, 255, 255};
            case "Кольцевая":
                return new int[]{128, 0, 0};
            default:
                return new int[]{128, 128, 128}; // серый по умолчанию
        }
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

    public int[] getRgbColor() {
        return rgbColor;
    }

    public void setRgbColor(int[] rgbColor) {
        this.rgbColor = rgbColor;
    }
    @PostLoad
    public void initColor() {
        this.rgbColor = getColorByLine(this.line);
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
