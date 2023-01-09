package com.kspo.voc.batch.job;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kspo.voc.batch.common.constant.Constants;
import com.kspo.voc.batch.common.util.VocJobListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ExampleRPWJob {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	@Autowired
	VocJobListener jobListener;

	@Bean("exampleJob")
	Job exampleJob() {
		log.debug("exampleJob");
//		return jobBuilderFactory.get("customerEncryptJob").listener(jobListener).start(stepEncTask()).next(stepContactTask()).build();
		return jobBuilderFactory.get("exampleJob").listener(jobListener).start(stepExample()).build();
	}

	@Bean("stepExample")
	Step stepExample() {
		log.debug("stepVocCustomer");
		return stepBuilderFactory.get("stepVocCustomer").<Object, Object>chunk(Constants.FETCH_COUNT)
				.reader(readerExample()).writer(writerExample()).build();
	}

	@Bean("readerExample")
	ItemReader<Object> readerExample() {
		return new ItemReader<Object>() {

			@Override
			public Object read()
					throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
				return null;
			}
		};
	}

	@Bean("writerExample")
	ItemWriter<Object> writerExample() {
		return new ItemWriter<Object>() {

			@Override
			public void write(List<? extends Object> items) throws Exception {
				for (int i = 0; i < items.size(); i++) {
				}

			}
		};
	}
}
