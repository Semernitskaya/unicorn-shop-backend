package com.ol.controller;

/**
 * Created by Semernitskaya on 15.01.2019.
 */

import com.ol.model.Unicorn;
import io.swagger.annotations.*;
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
@Api
public class Controller {

    private Map<Long, Unicorn> unicorns;

    @PostConstruct
    public void init() {
        unicorns = new HashMap<>(Map.of(
                1L, new Unicorn(1L, "beauty"),
                2L, new Unicorn(2L, "smart"),
                3L, new Unicorn(3L, "funny")));
    }

    @ApiOperation(value = "Returns a list of unicorns",
            notes = "Returns full list of unicorns or unicorn founded by id",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Full list of unicorn was returned or unicorn was found by id"),
            @ApiResponse(code = 404, message = "Unicorn wasn't found by id"),
    })
    @GetMapping(value = "/unicorn")
    public ResponseEntity<Collection<Unicorn>> getUnicorn(
            @ApiParam(value = "Id of unicorn in case of looking for certain unicorn", example = "1") @RequestParam(required = false) Long id) {
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


    @ApiOperation(value = "Creates a new unicorn",
            notes = "Creates a new unicorn and set new unique id to it. Not thread-safe",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Unicorn was created"),
    })
    @PostMapping(value = "/unicorn")
    public ResponseEntity<Unicorn> createUnicorn(@RequestBody Unicorn unicorn) {
        unicorn.setId(unicorns.size() + 1L);
        unicorns.put(unicorn.getId(), unicorn);
        return new ResponseEntity<>(unicorn, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Updates an unicorn if it exists",
            notes = "Updates an unicorn if it exists. Not thread-safe")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Unicorn was successfully updated"),
            @ApiResponse(code = 404, message = "Unicorn wasn't found by id")
    })
    @PutMapping(value = "/unicorn")
    public ResponseEntity<?> updateUnicorn(
            @ApiParam(value = "Unicorn for update", required = true) @RequestBody Unicorn unicorn) {
        var localUnicorn = unicorns.get(unicorn.getId());
        if (localUnicorn == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            localUnicorn.setDescription(unicorn.getDescription());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "Delete an unicorn if it exists",
            notes = "Updates an unicorn if it exists. Not thread-safe",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Unicorn was successfully deleted"),
            @ApiResponse(code = 404, message = "Unicorn wasn't found by id")
    })
    @DeleteMapping("/unicorn")
    public ResponseEntity<Unicorn> deleteUnicorn(
            @ApiParam(value = "Id of unicorn for delete", required = true, example = "1") @RequestParam Long id) {
        var unicorn = unicorns.remove(id);
        if (unicorn == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(unicorn, HttpStatus.OK);
        }
    }


}