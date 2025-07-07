package lk.ijse.gdse.back_end.service.Impl;

import lk.ijse.gdse.back_end.dto.JobDTO;
import lk.ijse.gdse.back_end.entity.Job;
import lk.ijse.gdse.back_end.repository.JobRepository;
import lk.ijse.gdse.back_end.service.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveJob(JobDTO jobDTO) {

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
    public JobDTO updateJob(JobDTO jobDTO) {
        Job updatedJob = jobRepository.save(modelMapper.map(jobDTO, Job.class));
        return modelMapper.map(updatedJob, JobDTO.class);
    }

    @Override
    public List<JobDTO> getAllJobs() {
        List<Job> allJobs=jobRepository.findAll();
        return modelMapper.map(allJobs, new TypeToken<List<JobDTO>>(){}.getType());
    }

    @Override
    public void changeJobStatus(String jobId) {
        jobRepository.updateJobStatus(jobId);
    }

    @Override
    public List<JobDTO> getAllJobsByKeyword(String keyword) {
        List<Job> allJobs = jobRepository.findJobsByJobTitleContainingIgnoreCase(keyword);
        return modelMapper.map(allJobs, new TypeToken<List<JobDTO>>(){}.getType());
    }
}

