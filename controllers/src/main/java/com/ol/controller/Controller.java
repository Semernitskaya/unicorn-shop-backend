package com.ol.controller;

/**
 * Created by Semernitskaya on 15.01.2019.
 */

import com.ol.model.Unicorn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("unicorn-shop")
public class Controller {

    private Map<Long, Unicorn> unicorns;

    @PostConstruct
    public void init() {
        unicorns = new HashMap<>(Map.of(
                1L, new Unicorn(1L, "beauty"),
                2L, new Unicorn(2L, "smart"),
                3L, new Unicorn(3L, "funny")));
    }

    @GetMapping(value = "/unicorn")
    public ResponseEntity<Collection<Unicorn>> getUnicorn(@RequestParam(required = false) Long id) {
        if (id == null) {
            return new ResponseEntity(unicorns.values(), HttpStatus.OK);
        }
        var unicorn = unicorns.get(id);
        if (unicorn == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(List.of(unicorn), HttpStatus.OK);
        }
    }


    @PostMapping(value = "/unicorn")
    public ResponseEntity<Unicorn> createUnicorn(@RequestBody Unicorn unicorn) {
        unicorn.setId(unicorns.size() + 1);
        unicorns.put(unicorn.getId(), unicorn);
        return new ResponseEntity<>(unicorn, HttpStatus.CREATED);
    }


    @PutMapping(value = "/unicorn")
    public ResponseEntity<?> updateUnicorn(@RequestBody Unicorn unicorn) {
        var localUnicorn = unicorns.get(unicorn.getId());
        if (localUnicorn == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            localUnicorn.setDescription(unicorn.getDescription());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/unicorn")
    public ResponseEntity<Unicorn> deleteUnicorn(@RequestParam Long id){
        var unicorn = unicorns.remove(id);
        if (unicorn == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(unicorn, HttpStatus.OK);
        }
    }



}