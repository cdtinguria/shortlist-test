package net.shortlist.employerservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employer {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50)
    private String companyName;

    @Column(length = 50)
    private String jobTitle;

    @Column(length = 50)
    private String jobLocation;

    @Column
    private int salary;

    protected Employer() {};

    public Employer(String companyName, String jobTitle, String jobLocation, int salary) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.jobLocation = jobLocation;
        this.salary = salary;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public String getJobLocation() {
        return this.jobLocation;
    }

    public int getSalary() {
        return this.salary;
    }

    @Override
    public String toString() {
        return String.format("Employer[name=%s, title=%s, location=%s, salary=%s]", this.companyName, this.jobTitle, this.jobLocation, this.salary);
    }
}
