package org.example.moscowmetroroute.DTO;

public class StationDTO {
    private Long id;
    private String name;
    private String line;

    public int[] getRgbColor() {
        return rgbColor;
    }

    public void setRgbColor(int[] rgbColor) {
        this.rgbColor = rgbColor;
    }

    private int[] rgbColor;


    public StationDTO() {
    }

    public StationDTO(Long id, String name, String line, int[] rgbColor) {
        this.id = id;
        this.name = name;
        this.line = line;
        this.rgbColor = rgbColor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
