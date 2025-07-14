package lk.ijse.gdse.back_end.repository;

import jakarta.transaction.Transactional;
import lk.ijse.gdse.back_end.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Job j SET j.status = " +
            "CASE WHEN j.status = 'ACTIVE' THEN 'DEACTIVATED' ELSE 'ACTIVE' END " +
            "WHERE j.jobId = :id")
    void updateJobStatus(@Param("id") String id);

    List<Job> findJobsByJobTitleContainingIgnoreCase(String jobTitle);

    @Query(value = "SELECT j FROM Job j WHERE j.jobTitle LIKE %:keyword%")
    Page<Job> findJobDataWithPaging(String keyword, PageRequest of);
}
