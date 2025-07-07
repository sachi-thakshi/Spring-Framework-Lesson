package lk.ijse.gdse.back_end.controller;

import lk.ijse.gdse.back_end.dto.JobDTO;
import lk.ijse.gdse.back_end.entity.Job;
import lk.ijse.gdse.back_end.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
@RequiredArgsConstructor
@CrossOrigin
public class JobController {

    private final JobService jobService;

    @PostMapping("create")
    public String createJob(@RequestBody JobDTO jobDTO){
        jobService.saveJob(jobDTO);
        return "Job Created";
    }

    @PutMapping("/update")
    public String updateJob(@RequestBody JobDTO jobDTO) {
        jobService.updateJob(jobDTO);
        return "Job Updated";
    }

    @GetMapping("getall")
    public List<JobDTO> getAllJob() {
        return jobService.getAllJobs();
    }

    @PatchMapping("status/{id}")
    private String changeJobStatus(@PathVariable("id") String jobId){
        System.out.println("JobId: " + jobId);
        jobService.changeJobStatus(jobId);
        return "Changed Job Status";
    }

    @GetMapping("search/{keyword}")
    public List<JobDTO> searchJob(@PathVariable("keyword") String keyword) {
        return jobService.getAllJobsByKeyword(keyword);
    }
}
