package lk.ijse.gdse.back_end.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse.back_end.dto.JobDTO;
import lk.ijse.gdse.back_end.entity.Job;
import lk.ijse.gdse.back_end.service.JobService;
import lk.ijse.gdse.back_end.util.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class JobController {

    private final JobService jobService;

    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @PostMapping("create")
    public ResponseEntity <APIResponse<String>>  createJob(@RequestBody @Valid JobDTO jobDTO){

//        logger.info("Job Created Successfully");
//        logger.debug("Job Details: {}", jobDTO);

        // if  use @Slf4j
        log.info("Job Created Successfully"); // business logics - information
        log.debug("Job Details: {}", jobDTO); // details of debugging information
        log.error("Job Creation Failed"); // system error oor failers
        log.trace("Job Details: {}", jobDTO); // data tracing
        log.warn("Job Creation Failed");// potential problems

        jobService.saveJob(jobDTO);
        return new ResponseEntity<>(new APIResponse<>(
                201,
                "Job Created Successfully",
                null
        ), HttpStatus.CREATED);
//        return "Job Created";
    }

    @PutMapping("update")
    public ResponseEntity <APIResponse<String>> updateJob(@RequestBody @Valid JobDTO jobDTO) {
        jobService.updateJob(jobDTO);
        return ResponseEntity.ok(new APIResponse<>(
                200,
                "Job List Fetched Successfully",
                null
        ));
//        return "Job Updated";
    }

    @GetMapping("getall")
    public ResponseEntity<APIResponse <List<JobDTO>>>  getAllJob() {
        List<JobDTO> jobDTOS = jobService.getAllJobs();
        return ResponseEntity.ok(new APIResponse<>(200,
                                                 "Job List Fetched Successfully",
                                                          jobDTOS
        ));
//        return jobService.getAllJobs();
    }

    @PatchMapping("status/{id}")
    private ResponseEntity <APIResponse <String>> changeJobStatus(@PathVariable("id") String jobId){
        System.out.println("JobId: " + jobId);
        jobService.changeJobStatus(jobId);
        return ResponseEntity.ok(
                new APIResponse<>(
                        200,
                        "Job Status Changed Successfully",
                        null));
    }

    @GetMapping("search/{keyword}")
    public ResponseEntity <APIResponse <List<JobDTO>>> searchJob(@PathVariable("keyword") String keyword) {
        List<JobDTO> jobDTOS = jobService.getAllJobsByKeyword(keyword);
        return ResponseEntity.ok(new APIResponse<>(
                   200,
                "Job List Fetched Successfully",
                         jobDTOS
        ));
//        return jobService.getAllJobsByKeyword(keyword);
    }

    @GetMapping("paging")
    public Page<JobDTO> getAllJobsWithPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int per_page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "id") String sort
    ) {
        return jobService.getAllJobsWithPaging(page, per_page, keyword, direction, sort);
    }
//    public ResponseEntity<APIResponse<Page<JobDTO>>>  getAllJobsWithPaging(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "5") int per_page,
//            @RequestParam(defaultValue = "") String keyword,
//            @RequestParam(defaultValue = "asc") String direction,
//            @RequestParam(defaultValue = "id") String sort
//    ) {
//        Page<JobDTO> jobDTOS = jobService.getAllJobsWithPaging(page, per_page, keyword, direction, sort);
//
//        return ResponseEntity.ok(new APIResponse<>(
//                200,
//                "ob List Fetched Successfully",
//                jobDTOS
//        ));
//    }
}
