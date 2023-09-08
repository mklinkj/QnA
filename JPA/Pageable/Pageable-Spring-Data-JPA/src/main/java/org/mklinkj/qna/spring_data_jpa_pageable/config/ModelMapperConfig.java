package org.mklinkj.qna.spring_data_jpa_pageable.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
  @Bean
  ModelMapper getMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper
        .getConfiguration() //
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(AccessLevel.PRIVATE)
        .setMatchingStrategy(MatchingStrategies.STRICT);
    return modelMapper;
  }
}
