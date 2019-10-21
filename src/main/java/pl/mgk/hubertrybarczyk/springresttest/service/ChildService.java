package pl.mgk.hubertrybarczyk.springresttest.service;

import java.util.Set;
import pl.mgk.hubertrybarczyk.springresttest.model.Child;

public interface ChildService extends CrudService<Child, Long> {
    Set<Child> findByCustomerId(String id);
}