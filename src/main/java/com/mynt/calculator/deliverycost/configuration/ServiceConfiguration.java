package com.mynt.calculator.deliverycost.configuration;

import com.mynt.calculator.deliverycost.integration.VoucherClient;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableFeignClients(clients = VoucherClient.class)
@ComponentScan(basePackages = {"com.mynt.calculator"})
@EnableJpaAuditing
@EnableJpaRepositories("com.mynt.calculator.deliverycost.repository")
@EntityScan("com.mynt.calculator.deliverycost.model")
public class ServiceConfiguration {
}
