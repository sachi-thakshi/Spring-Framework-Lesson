package lk.ijse.gdse.back_end.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobDTO {

    private Integer jobId;
    @NotBlank(message = "Job Title is required")
    private String jobTitle;
    @NotBlank(message = "Company is required")
    @Pattern(regexp = "[a-zA-Z ]+$", message = "Company name should contain only alphabets")
    private String company;
//    @Email(message = "Invalid email format")
    private String location;
    @NotNull(message = "Type is required")
    private String type;
    @Size(min = 10, message = "Job Description should be at least 10 characters long")
    private String jobDescription;
    private String status;
}
