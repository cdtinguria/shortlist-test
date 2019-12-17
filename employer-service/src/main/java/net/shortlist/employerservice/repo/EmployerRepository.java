package net.shortlist.employerservice.repo;

import net.shortlist.employerservice.domain.Employer;
import org.springframework.data.repository.CrudRepository;

public interface EmployerRepository extends CrudRepository<Employer, Integer> {
}
