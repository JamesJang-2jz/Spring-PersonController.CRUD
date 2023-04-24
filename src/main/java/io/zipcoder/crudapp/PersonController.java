package io.zipcoder.crudapp;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping("/{id}/{firstName}/{lastName}")
    public ResponseEntity<Person> createPerson(@RequestBody Person p) {
        return new ResponseEntity<>(personService.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        return new ResponseEntity<Person>(personService.findOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersonList() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person p) {
        Person existing = personService.findOne(id);
        p.setId(id);
        Person saved = personService.save(p);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(saved);
        }
    }

    @PostMapping("/")

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id) {
        personService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
