package com.kspo.voc.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kspo.voc.batch.common.util.VocobListener;
import com.kspo.voc.batch.tasklet.SyncJadeOrgTasklet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SyncJadeJob {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final SyncJadeOrgTasklet orgTask;


	@Autowired
	VocobListener jobListener;

	@Bean("syncJadeJob")
	Job jadeJob() {
		log.debug("syncJadeJob");
		return jobBuilderFactory.get("syncJadeJob").listener(jobListener).start(stepOrg()).build();
	}

	@Bean("stepOrg")
	Step stepOrg() {
		log.debug("stepOrg");
		return stepBuilderFactory.get("stepOrg").tasklet(orgTask).build();
	}

}
