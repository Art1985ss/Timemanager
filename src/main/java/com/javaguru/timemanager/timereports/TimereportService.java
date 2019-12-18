package com.javaguru.timemanager.timereports;

import com.javaguru.timemanager.projects.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TimereportService {
    private TimereportRepository timereportRepository;

    @Autowired
    public TimereportService(TimereportRepository timereportRepository) {
        this.timereportRepository = timereportRepository;
    }

    public Long create(Timereport timereport) {
        return timereportRepository.save(timereport).getId();
    }

    public Timereport findById(Long id) {
        return timereportRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No time report found with id : " + id));
    }

    public List<Timereport> getAll() {
        return timereportRepository.findAll();
    }

    public void deleteById(Long id) {
        Timereport timereport = timereportRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No time report found with id : " + id + " to delete"));
        timereportRepository.delete(timereport);
    }

    public void update(Timereport timereport) {
        timereportRepository.save(timereport);
    }

    public Timereport setProject(Timereport timereport, Project project) {
        timereport.setProject(project);
        this.update(timereport);
        return timereport;
    }
}
