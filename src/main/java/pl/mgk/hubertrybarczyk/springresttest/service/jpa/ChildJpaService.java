package pl.mgk.hubertrybarczyk.springresttest.service.jpa;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import pl.mgk.hubertrybarczyk.springresttest.model.Child;
import pl.mgk.hubertrybarczyk.springresttest.repository.ChildRepository;
import pl.mgk.hubertrybarczyk.springresttest.service.ChildService;


@Service
public class ChildJpaService implements ChildService {

    private final ChildRepository childRepository;

    public ChildJpaService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public Set<Child> findAll() {
        Set<Child> tasks = new HashSet<>();
        childRepository.findAll().forEach(tasks::add);
        return tasks;
    }


    public Set<Child> findByCustomerId(String customerId) {
        return childRepository.findChildrenByCustomerId(customerId);
    }


    @Override
    public Child findById(Long aLong) {
        return childRepository.findById(aLong).orElse(null);
    }

    @Override
    public Child save(Child object) {
        return childRepository.save(object);
    }

    @Override
    public void delete(Child object) {
        childRepository.delete(object);
    }

    @Override
    public void deleteId(Long aLong) {
        childRepository.deleteById(aLong);
    }
}
