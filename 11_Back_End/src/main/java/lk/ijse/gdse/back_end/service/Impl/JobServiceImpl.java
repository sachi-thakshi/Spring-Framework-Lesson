package lk.ijse.gdse.back_end.service.Impl;

import lk.ijse.gdse.back_end.dto.JobDTO;
import lk.ijse.gdse.back_end.entity.Job;
import lk.ijse.gdse.back_end.exceptions.ResourceNotFound;
import lk.ijse.gdse.back_end.repository.JobRepository;
import lk.ijse.gdse.back_end.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveJob(JobDTO jobDTO) {

        if (jobDTO == null){
            log.info("Job Id is null");
            throw new IllegalArgumentException("JobDTO cannot be null.");
        }
        jobRepository.save(modelMapper.map(jobDTO,Job.class));

        // created bean class in the Application.java
        /*Job job = new Job();

        job.setJobTitle(jobDTO.getJobTitle());
        job.setCompany(jobDTO.getCompany());
        job.setLocation(jobDTO.getLocation());
        job.setType(jobDTO.getType());
        job.setJobDescription(jobDTO.getJobDescription());

        jobRepository.save(job);*/
    }

    @Override
    public void updateJob(JobDTO jobDTO) {

        if (jobDTO==null||jobDTO.getJobId()==null){
            throw new IllegalArgumentException("Job Id cannot be null");
        }

        jobRepository.findById(jobDTO.getJobId()).orElseThrow(
                ()->new ResourceNotFound("Job Not Found"));

        jobRepository.save(modelMapper.map(jobDTO,Job.class));
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> allJobs=jobRepository.findAll();

        if (allJobs.isEmpty()){
            throw new ResourceNotFound("No Job Found");
        }

        return modelMapper.map(allJobs, new TypeToken<List<JobDTO>>(){}.getType());
    }

    @Override
    public void changeJobStatus(Integer jobId) {

        if (jobId==null){
            throw new IllegalArgumentException("Job Id cannot be null");
        }

        jobRepository.updateJobStatus(jobId);
    }

    @Override
    public List<JobDTO> getAllJobsByKeyword(String keyword) {

        if (keyword==null){
            throw new IllegalArgumentException("Keyword cannot be null");
        }

        List<Job> allJobs = jobRepository.findJobsByJobTitleContainingIgnoreCase(keyword);

        if (allJobs.isEmpty()){
            throw new ResourceNotFound("No Job Found");
        }
        return modelMapper.map(allJobs, new TypeToken<List<JobDTO>>(){}.getType());
    }

    @Override
    public Page<JobDTO> getAllJobsWithPaging(int page, int perPage, String keyword, String direction, String sort) {
        Page<Job> jobPage;

        if (direction.equalsIgnoreCase("asc")) {
            jobPage = jobRepository.findJobDataWithPaging(keyword, PageRequest.of(page, perPage, Sort.by(Sort.Direction.ASC, sort)));
        } else {
            jobPage = jobRepository.findJobDataWithPaging(keyword, PageRequest.of(page, perPage, Sort.by(Sort.Direction.DESC, sort)));
        }

        // Map Page<Job> -> Page<JobDTO>
        return jobPage.map(job -> modelMapper.map(job, JobDTO.class));
    }
}

