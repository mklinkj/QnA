package org.mklinkj.qna.spring_batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@EnableBatchProcessing
@SpringBootApplication
public class JobParamTestJob {

  private final JobBuilderFactory jobBuilderFactory;

  private final StepBuilderFactory stepBuilderFactory;

  @Bean
  JobParametersValidator validator() {
    DefaultJobParametersValidator defaultJobParametersValidator = //
        new DefaultJobParametersValidator(
            new String[] {"param1", "param2"}, //
            new String[] {});
    defaultJobParametersValidator.afterPropertiesSet();
    return defaultJobParametersValidator;
  }

  @Bean
  public Job job() {
    return jobBuilderFactory
        .get("JobParamTestJob") //
        .start(step1())
        .validator(validator())
        .build();
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory
        .get("step1") //
        .tasklet(helloworldTasklet(null, null))
        .build();
  }

  @StepScope
  @Bean
  public Tasklet helloworldTasklet( //
      @Value("#{jobParameters['param1']}") String param1, //
      @Value("#{jobParameters['param2']}") String param2) {

    return ((contribution, chunkContext) -> {
      System.out.printf("param1: %s / param2: %s!%n", param1, param2);
      return RepeatStatus.FINISHED;
    });
  }

  public static void main(String[] args) {
    SpringApplication.run(JobParamTestJob.class, args);
  }
}
