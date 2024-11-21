package ma.ensa.system_metuelle.config;

import ma.ensa.system_metuelle.exceptions.ValidationException;
import ma.ensa.system_metuelle.models.Dossier;
import ma.ensa.system_metuelle.models.RembAssure;
import ma.ensa.system_metuelle.repositories.RembAssureRepository;
import org.aspectj.apache.bcel.Repository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.listener.CompositeItemProcessListener;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemReaderException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.ItemWriterException;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
public class BatchConfig {
    private final RembAssureRepository rembAssureRepository;
    private final CompositeItemProcessor compositeItemProcessor;
    private JobRepository jobRepository;
    private PlatformTransactionManager platformTransactionManager;

    public BatchConfig(RembAssureRepository rembAssureRepository, JobRepository jobRepository, PlatformTransactionManager platformTransactionManager, CompositeItemProcessor compositeItemProcessor) {
        this.rembAssureRepository = rembAssureRepository;
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
        this.compositeItemProcessor = compositeItemProcessor;
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
     @Bean
     public ItemReader<Dossier> jsonReader(){
        return new JsonItemReaderBuilder<Dossier>()
                .name("DossierMetuelle")
                .jsonObjectReader(new JacksonJsonObjectReader<>(Dossier.class))
                 .resource(new FileSystemResource("src/main/resources/dossiers.json"))
                 .build();

     }
     @Bean
     public CompositeItemProcessor compositeItemProcessor(){
        return new CompositeItemProcessor();
     }

    private ItemWriter<RembAssure> writer() {
        RepositoryItemWriter<RembAssure>  writer = new RepositoryItemWriter<>();
        writer.setRepository(rembAssureRepository);
        writer.setMethodName("save");
        return  writer;

    }

}
