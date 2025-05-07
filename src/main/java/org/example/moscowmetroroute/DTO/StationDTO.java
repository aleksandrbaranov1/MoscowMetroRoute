package org.example.moscowmetroroute.DTO;

public class StationDTO {
    private Long id;
    private String name;
    private String line;

    public StationDTO() {
    }

    public StationDTO(Long id, String name, String line) {
        this.id = id;
        this.name = name;
        this.line = line;
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
