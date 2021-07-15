package io.github.arsonistcook.saladereuniao.controller;

import io.github.arsonistcook.saladereuniao.exception.ResourceNotFoundException;
import io.github.arsonistcook.saladereuniao.model.Room;
import io.github.arsonistcook.saladereuniao.repository.RoomRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") long roomId) throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException(String.format("Room not found: %d", roomId)));
        return ResponseEntity.ok(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room newRoom) {
        return roomRepository.save(newRoom);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value = "id") long roomId, @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException {
        Room roomToUpdate = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException(String.format("Room not found: %d", roomId)));
        roomToUpdate.setName(roomDetails.getName());
        roomToUpdate.setDate(roomDetails.getDate());
        roomToUpdate.setStartHour(roomDetails.getStartHour());
        roomToUpdate.setEndHour(roomDetails.getEndHour());

        final Room updatedRoom = roomRepository.save(roomToUpdate);

        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String,Boolean> deleteRoom(@PathVariable(value="id") long roomId) throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException(String.format("Room not found: %d", roomId)));

        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

