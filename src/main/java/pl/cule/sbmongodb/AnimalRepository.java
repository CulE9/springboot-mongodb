package pl.cule.sbmongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, String> {

    List<Animal> findByAgeGreaterThan(int age);

    List<Animal> findByAgeLessThan(int age);

    List<Animal> findByAgeEquals(int age);

    Optional<Animal> findById(String id);

    List<Animal> findByName(String name);
}
