package io.zipcoder.crudapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    public Person findOne(int id) {
        return personRepository.findOne(id);
    }

    public Person save(Person p) {
        return personRepository.save(p);
    }

    public void delete(int id) {
        personRepository.delete(id);
    }

}