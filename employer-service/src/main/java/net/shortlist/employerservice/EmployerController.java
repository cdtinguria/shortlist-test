package net.shortlist.employerservice;

import net.shortlist.employerservice.domain.Employer;
import net.shortlist.employerservice.repo.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployerController {
    @Autowired
    private EmployerRepository employerRepository;

    @RequestMapping("/employers")
    public String index() {
        final StringBuilder sb = new StringBuilder();
        employerRepository.findAll().forEach(e -> sb.append(e.toString() + " "));
        return sb.toString();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/employers")
    public Employer create(@RequestBody Employer employerDetails) {
        return employerRepository.save(employerDetails);
    }
}
