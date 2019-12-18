package net.shortlist.candidateservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.shortlist.candidateservice.repo.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@RestController
public class CandidateController {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private DiscoveryClient discoveryClient;

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
            sb.append(this.fetchEmployers(getEmployerServiceUrl()));
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

    private StringBuilder fetchEmployers(String uri) {
        StringBuilder sb = new StringBuilder();

        ResponseEntity<String> responseEntity
                = new RestTemplate()
                .getForEntity(uri, String.class, new HashMap<String, String>());

        sb.append(responseEntity.getBody());

        return sb;
    }

    private String getEmployerServiceUrl() {
        List<ServiceInstance> lstServiceInstances = this.discoveryClient.getInstances("employer-service");
        return lstServiceInstances.get(0).getUri().toString() + "/employers";
    }

    public String fallbackIndex() {
        return "Error in candidate-service";
    }
}
