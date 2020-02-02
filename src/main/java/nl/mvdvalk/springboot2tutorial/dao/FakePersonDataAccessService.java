package nl.mvdvalk.springboot2tutorial.dao;

import nl.mvdvalk.springboot2tutorial.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
 Necessary for instantiation, see also @Qualifier in PersonService
 This sets which kind of database access will be used
 Note: to change the implementation, change the Qualifier in PersonService
 as well as adding another implementation of PersonDao
*/
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB;

    static {
        DB = new ArrayList<>();
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person = selectPersonById(id);
        if (person.isEmpty()) {
            return 0;
        }
        // Optional needs to use get to return the object from the collection
        DB.remove(person.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person updatePerson) {
        // use streams here to get the index of the person to modify
        return selectPersonById(id)
                .map(person -> {
                    int indexOfPersonToUpdate = DB.indexOf(person);
                    if (indexOfPersonToUpdate >= 0) {
                        Person personToUpdate = new Person(id,updatePerson.getName());
                        DB.set(indexOfPersonToUpdate, personToUpdate);
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }
}
