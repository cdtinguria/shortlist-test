package net.shortlist.candidateservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Candidate {
    @Id
    private String id;

    private String name;

    private String location;

    private String salary;

    protected Candidate() {}

    public Candidate(String name, String location, String salary) {
        this.name = name;
        this.location = location;
        this.salary = salary;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public String getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return String.format("Candidate[name=%s, location=%s, salary=%s]", this.name, this.location, this.salary);
    }
}
