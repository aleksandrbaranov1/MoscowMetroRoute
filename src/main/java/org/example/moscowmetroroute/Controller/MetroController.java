package org.example.moscowmetroroute.Controller;

import org.example.moscowmetroroute.DTO.StationDTO;
import org.example.moscowmetroroute.Graph.MetroMap;
import org.example.moscowmetroroute.Model.Station;
import org.example.moscowmetroroute.Repository.StationRepository;
import org.example.moscowmetroroute.Service.MetroGraphService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MetroController {
    private final MetroGraphService metroGraphService;

    public MetroController(MetroGraphService metroGraphService, StationRepository stationRepository) {
        this.metroGraphService = metroGraphService;
    }

    @GetMapping("/getRoute")
    public List<StationDTO> getRoute(@RequestParam int start, @RequestParam int end) {

        return metroGraphService.findShortestPath(start, end)
                .stream()
                .map(station -> new StationDTO(
                        station.getId(),
                        station.getName(),
                        station.getLine(),
                        station.getRgbColor()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/getDistance")
    public int getDistance(@RequestParam int start, @RequestParam int end){
        return metroGraphService.findDistance(start, end);
    }

    @GetMapping("/getFromStation")
    private List<StationDTO> getFromStations(@RequestParam String name){
        return metroGraphService.getStationByNameContaining(name)
                .stream()
                .map(station -> new StationDTO(station.getId(), station.getName(), station.getLine(), station.getRgbColor()))
                .collect(Collectors.toList());
    }
    @GetMapping("/getToStation")
    private List<StationDTO> getToStations(@RequestParam String name){
        return metroGraphService.getStationByNameContaining(name)
                .stream()
                .map(station -> new StationDTO(station.getId(), station.getName(), station.getLine(), station.getRgbColor()))
                .collect(Collectors.toList());
    }
}
