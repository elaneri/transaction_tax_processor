package com.transaction.tax_processor.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.transaction.processor.dto.TransactionDetailDTO;

@Configuration
@EnableKafka
public class TaxProcessorConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public <T> ConsumerFactory<String, T> typeConsumerFactory(Class<T> clazz) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

		JsonDeserializer<T> jsonDeserializer = new JsonDeserializer<>(clazz);
		jsonDeserializer.setTypeMapper(new CustomTypeMapper<>(clazz));

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
	}

	private <T> ConcurrentKafkaListenerContainerFactory<String, T> initFactory(Class<T> clazz) {
		ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(typeConsumerFactory(clazz));
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ?> kafkaListenerContainerFactory() {
		return initFactory(TransactionDetailDTO.class);
	}

	private static class CustomTypeMapper<T> extends DefaultJackson2JavaTypeMapper {
		private final Class<T> clazz;

		public CustomTypeMapper(Class<T> clazz) {
			this.clazz = clazz;
		}

		@Override
		public JavaType toJavaType(Headers headers) {
			try {
				return TypeFactory.defaultInstance()
						.constructType(ClassUtils.forName(clazz.getName(), getClassLoader()));
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}
}