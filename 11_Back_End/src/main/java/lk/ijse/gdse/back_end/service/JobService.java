package lk.ijse.gdse.back_end.service;

import lk.ijse.gdse.back_end.dto.JobDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {
    void saveJob(JobDTO jobDTO);
    void updateJob(JobDTO jobDTO);
    List<JobDTO> getAllJobs();
    void changeJobStatus(Integer jobId);
    List<JobDTO> getAllJobsByKeyword(String keyword);

    Page<JobDTO> getAllJobsWithPaging(int page, int perPage, String keyword, String direction, String sort);
}
