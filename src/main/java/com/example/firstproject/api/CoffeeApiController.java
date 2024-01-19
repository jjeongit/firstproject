package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CoffeeApiController {

    @Autowired
    private CoffeeService coffeeService;

    // GET
    @GetMapping("/api/coffees") // 전체조회
    public Iterable<Coffee> index(){
        return coffeeService.index();
    }

    @GetMapping("/api/coffee/{id}") // 단일 조회
    public ResponseEntity<Coffee> show(@PathVariable Long id){
        Coffee coffee =  coffeeService.show(id);

        return (coffee != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // POST
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto coffeeDto){
        Coffee created = coffeeService.create(coffeeDto);

        return (created != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto coffeeDto){

        Coffee updated = coffeeService.update(id, coffeeDto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee deleted = coffeeService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }



    /* 서비스층 생기기전
    @Autowired
    private CoffeeRepository coffeeRepository;

    // GET
    @GetMapping("/api/coffees") // 전체조회
    public Iterable<Coffee> index(){
        return coffeeRepository.findAll();
    }

    @GetMapping("/api/coffee/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        return (coffee != null) ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // POST
    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto CoffeeDto){
        Coffee coffee = CoffeeDto.toEntity();
        if(coffee.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Coffee created = coffeeRepository.save(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PATCH
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto coffeeDto){
        Coffee coffee = coffeeDto.toEntity();
        log.info("id: {}, coffee: {}", id, coffee.toString());
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if (target == null || id != coffee.getId()) {
           log.info("잘못된 요청! id: {}, coffee: {}", id, coffee.toString());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        target.patch(coffee);
        Coffee updated = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);

    }

    // DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee target = coffeeRepository.findById(id).orElse(null);
        if(target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        coffeeRepository.delete(target);
        return  ResponseEntity.status(HttpStatus.OK).build();
    }*/
}
