package pl.mgk.hubertrybarczyk.springresttest.repository;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import pl.mgk.hubertrybarczyk.springresttest.model.Child;

public interface ChildRepository extends CrudRepository<Child, Long> {
    Set<Child> findChildrenByCustomerId(String customerId);
}

