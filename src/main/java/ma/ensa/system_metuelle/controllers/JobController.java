package ma.ensa.system_metuelle.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.ensa.system_metuelle.resps.ApiResponse;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("/api/batch/dossier/jobs")
public class JobController {
    private final  Job job;
    private final JobLauncher jobLauncher;

    @GetMapping("/start")
    public ResponseEntity<ApiResponse>  startjob(){
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("start at",System.currentTimeMillis()).toJobParameters();
            jobLauncher.run(job,jobParameters);
            return ResponseEntity.ok(new ApiResponse("donne succesfull",null));
        } catch (JobExecutionAlreadyRunningException|JobRestartException|JobInstanceAlreadyCompleteException |JobParametersInvalidException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",null));
        }
    }
}