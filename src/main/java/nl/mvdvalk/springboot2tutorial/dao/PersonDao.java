package nl.mvdvalk.springboot2tutorial.dao;

import nl.mvdvalk.springboot2tutorial.model.Person;

import java.util.List;
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

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id,person);
    }

    List<Person> selectAllPeople();
}
