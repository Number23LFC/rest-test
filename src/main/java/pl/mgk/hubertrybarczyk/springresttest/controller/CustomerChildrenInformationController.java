package pl.mgk.hubertrybarczyk.springresttest.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.mgk.hubertrybarczyk.springresttest.model.Child;
import pl.mgk.hubertrybarczyk.springresttest.model.ChildDto;
import pl.mgk.hubertrybarczyk.springresttest.service.ChildService;


@RestController()
public class CustomerChildrenInformationController {
    
    private final ChildService childService;

    private ModelMapper modelMapper;

    public CustomerChildrenInformationController(ChildService childService, ModelMapper modelMapper) {
        this.childService = childService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/children/PID={pid}")
    public Set<ChildDto> findChildrenByCustomerId(@PathVariable String pid) {
        return childService.findByCustomerId(pid)
                .stream()
                .map(child -> convertToDto(child))
                .collect(Collectors.toSet());
    }

    @PostMapping("/children")
    public Child create(@RequestBody Child child) {
        System.out.println("DODAJE DZIECKO: " + child);
        return childService.save(child);
    }

    @DeleteMapping("children/PID={id}")
    public ResponseEntity<String> deleteAllChildrenByCustomerId(@PathVariable("id") String id) {
        System.out.println("Delete Objective with ID = " + id + "...");
        Set <Child> childrenToDelete = childService.findByCustomerId(id);
        for (Child child : childrenToDelete) {
            childService.deleteId(child.getId());
        }
        return new ResponseEntity<>("Child has been deleted!", HttpStatus.OK);
    }

    private ChildDto convertToDto(Child child) {
        ChildDto childDto = modelMapper.map(child, ChildDto.class);
        if (child.getBirthDate() != null) {
            Period p = Period.between(child.getBirthDate(), LocalDate.now());
            int age = p.getYears();
            childDto.setAge(age);
        }
        return childDto;
    }
}
