package com.javaguru.timemanager.users;

import com.javaguru.timemanager.Converter;
import com.javaguru.timemanager.timereports.Timereport;
import com.javaguru.timemanager.timereports.TimereportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
class UserController {
    private UserService userService;
    private TimereportService timereportService;

    @Autowired
    public UserController(UserService userService, TimereportService timereportService) {
        this.userService = userService;
        this.timereportService = timereportService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        userService.getAll().forEach(u -> userDtoList.add(Converter.convertToDto(u)));
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        UserDto userDto = Converter.convertToDto(userService.findById(id));
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/")
    public ResponseEntity<UserDto> findByName(@RequestParam String name) {
        UserDto userDto = Converter.convertToDto(userService.findByName(name));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDto request) {
        User user = Converter.convertFromDto(request);
        Long id = userService.create(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(location)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UserDto userDto) {
        User user = Converter.convertFromDto(userDto);
        userService.update(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .location(location)
                .build();
    }

    @PutMapping("/{id}/timereports/{timereport_id}")
    public ResponseEntity<Void> addTimereport(@PathVariable Long id, @PathVariable Long timereport_id) {
        User user = userService.findById(id);
        Timereport timereport = timereportService.findById(timereport_id);
        user = userService.addTimereport(user, timereport);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.status(HttpStatus.ACCEPTED).location(location).build();
    }

    @GetMapping("/{id}/?year")
    public ResponseEntity<Map<Month, BigDecimal>> getSalaryPerMonth(@RequestParam int year, @PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user.getSalaryPerMonth(year));
    }
}
