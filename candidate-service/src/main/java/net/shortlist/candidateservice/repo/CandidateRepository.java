package net.shortlist.candidateservice.repo;

import net.shortlist.candidateservice.domain.Candidate;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends CrudRepository<Candidate, Integer> {
}
