package com.springdemo.issuedashboard.issuedashboard.events;

import com.springdemo.issuedashboard.issuedashboard.github.GithubClient;
import com.springdemo.issuedashboard.issuedashboard.github.RepositoryEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class EventsController {

    private final GithubProjectRepository githubProjectRepository;

    private final GithubClient githubClient;

    public EventsController(GithubProjectRepository githubProjectRepository, GithubClient githubClient) {
        this.githubProjectRepository = githubProjectRepository;
        this.githubClient = githubClient;
    }

    @GetMapping("/events/{repoName}")
    @ResponseBody
    public RepositoryEvent[] fetchEvents(@PathVariable final String repoName) {
        GithubProject project = githubProjectRepository.findByRepoName(repoName);
        return this.githubClient.fetchEvents(project.getOrgName(), project.getRepoName()).getBody();
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        Iterable<GithubProject> projects = githubProjectRepository.findAll();
        List<DashboardEntry> entries = StreamSupport
                .stream(projects.spliterator(), true)
                .map(p -> new DashboardEntry(p, this.githubClient.fetchEventsList(p.getOrgName(), p.getRepoName())))
                .collect(Collectors.toList());

        model.addAttribute("entries", entries);
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("projects", githubProjectRepository.findAll());
        return "admin";
    }
}
