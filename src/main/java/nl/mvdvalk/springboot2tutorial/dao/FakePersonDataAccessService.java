package nl.mvdvalk.springboot2tutorial.dao;

import nl.mvdvalk.springboot2tutorial.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }
}
