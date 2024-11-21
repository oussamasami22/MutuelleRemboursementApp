package ma.ensa.system_metuelle.config;

import ma.ensa.system_metuelle.models.Dossier;
import ma.ensa.system_metuelle.models.RembAssure;
import ma.ensa.system_metuelle.repositories.RembAssureRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
public class BatchConfig {
    private final RembAssureRepository rembAssureRepository;
    private JobRepository jobRepository;
    private PlatformTransactionManager platformTransactionManager;

    public BatchConfig(RembAssureRepository rembAssureRepository, JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.rembAssureRepository = rembAssureRepository;
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }
     @Bean
    public Job job(){
        return new JobBuilder("job", jobRepository)
                .start(step1())
                .build();
     }
     @Bean
    public  Step step1(){
        return new StepBuilder("step1", jobRepository)
                .<Dossier, RembAssure>chunk(10, platformTransactionManager)
                .reader(jsonReader())
                .processor(compositeItemProcessor)
                .writer(writer())
                .faultTolerant()
                .retryLimit(3)
                .retry(ItemReaderException.class)
                .retry(ItemWriterException.class)
                .skipLimit(10)
                .skip(ValidationException.class)
                .skip(RuntimeException.class)
                .build();


     }

    private ItemWriter<? super RembAssure> writer() {
    }

}
