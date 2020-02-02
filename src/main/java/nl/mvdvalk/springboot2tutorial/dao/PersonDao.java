package nl.mvdvalk.springboot2tutorial.dao;

import nl.mvdvalk.springboot2tutorial.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
 Necessary for instantiation to set the used implementation of this
 interface use @Repository("name"), see also @Qualifier("name") in PersonService
 This sets which kind of database access will be used
 Note: to change the implementation, change the Qualifier in PersonService
 as well as adding another implementation of PersonDao
*/
public interface PersonDao {
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    // return list of persons currently in the database
    List<Person> selectAllPeople();

    // select a person by id
    Optional<Person> selectPersonById(UUID id);

    // delete a person from the database by id
    int deletePersonById(UUID id);

    // update a person from the database by id
    int updatePersonById(UUID id, Person person);
}
