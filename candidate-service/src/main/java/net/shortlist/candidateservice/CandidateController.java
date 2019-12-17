package net.shortlist.candidateservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.shortlist.candidateservice.repo.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;

    @RequestMapping("/candidates")
    @HystrixCommand(fallbackMethod = "fallbackIndex")
    public String index() {
        StringBuilder sb = new StringBuilder();

        System.out.println("fetching candidates...");
        try {
            sb.append(this.fetchCandidates());
        } catch(Exception e) {
            System.out.println("Error in fetching candidate data, " + e.getMessage());
            sb.append("Error fetching candidate data");
        }

        System.out.println("fetching employers...");
        try {
            sb.append(this.fetchEmployers());
        } catch(Exception e) {
            System.out.println("Error in employer service, " + e.getMessage());
            sb.append("Error in employer-service");
        }
        return sb.toString();
    }

    private StringBuilder fetchCandidates() {
        StringBuilder sb = new StringBuilder();

        candidateRepository.findAll().forEach(c -> sb.append(c + "\n"));

        return sb;
    }

    private StringBuilder fetchEmployers() {
        StringBuilder sb = new StringBuilder();

        ResponseEntity<String> responseEntity
                = new RestTemplate()
                .getForEntity("http://localhost:8080/employers", String.class, new HashMap<String, String>());

        sb.append(responseEntity.getBody());

        return sb;
    }

    public String fallbackIndex() {
        return "Error in candidate-service";
    }
}
