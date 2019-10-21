package pl.mgk.hubertrybarczyk.springresttest.bootstrap;

import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.mgk.hubertrybarczyk.springresttest.model.Child;
import pl.mgk.hubertrybarczyk.springresttest.service.ChildService;


@Component
public class DataLoader implements CommandLineRunner {

    private final ChildService childService;


    public DataLoader(ChildService childService) {
        this.childService = childService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = childService.findAll().size();

        if (count == 0 ){
            loadData();
        }
    }

    private void loadData() {
        Child child1 = new Child();
        Child child2 = new Child();
        child1.setBirthDate(LocalDate.of(2000, 1, 20));
        child1.setName("Marian");

        child2.setBirthDate(LocalDate.of(1992, 1, 20));
        child2.setName("Kuba");

        childService.save(child1);
        childService.save(child2);

        System.out.println("Add data");
    }
}
