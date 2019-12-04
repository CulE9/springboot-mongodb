package pl.cule.sbmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AnimalApi {

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalApi(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        animalRepository.deleteAll();
        Animal a1 = new Animal("Azor", 3);
        Animal a2 = new Animal("Filemon", 1);
        Animal a3 = new Animal("Hipo", 12);
        Animal a4 = new Animal("Goat", 5);
        Animal a5 = new Animal("Barnaba", 9);
        Animal a6 = new Animal("Denis", 9);
        Animal a7 = new Animal("Denis", 7);
        animalRepository.save(a1);
        animalRepository.save(a2);
        animalRepository.save(a3);
        animalRepository.save(a4);
        animalRepository.save(a5);
        animalRepository.save(a6);
        animalRepository.save(a7);

        printTest();
    }

    private void printTest() {
//        for (Animal animal : animalRepository.findAll()) System.err.println(animal);
//        animalRepository.findAll().forEach(System.err::println);
        getAll().forEach(System.err::println);
    }

    @GetMapping("/getById")
    public Optional<Animal> getById(@RequestParam String id) {
        return animalRepository.findById(id);
    }

    @GetMapping("/getByName")
    public List<Animal> getByName(@RequestParam String name) {
        return animalRepository.findByName(name);
    }

    @GetMapping("/getGreater")
    public List<Animal> getGreater(@RequestParam int age) {
        return animalRepository.findByAgeGreaterThan(age);
    }

    @GetMapping("/getLess")
    public List<Animal> getLess(@RequestParam int age) {
        return animalRepository.findByAgeLessThan(age);
    }

    @GetMapping("/getEquals")
    public List<Animal> getEquals(@RequestParam int age) {
        return animalRepository.findByAgeEquals(age);
    }

    @GetMapping("/getAll")
    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    @GetMapping(value = "/check", produces = "application/json; charset=utf-8")
    public String getHealthCheck() {
        return "{ \"isWorking\" : true }";
    }
}
