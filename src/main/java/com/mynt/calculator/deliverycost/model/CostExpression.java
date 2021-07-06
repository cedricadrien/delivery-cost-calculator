package com.mynt.calculator.deliverycost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "cost_expression")
@EntityListeners(AuditingEntityListener.class)
public class CostExpression {

    @Id
    @GeneratedValue(generator = "cost_expression_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cost_expression_gen", sequenceName = "cost_expression_seq", allocationSize = 1)
    @Column(name = "id_cost_expression", nullable = false, unique = true, updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn (name = "id_param_type")
    private ParamTypeCodelist paramType;

    @OneToOne
    @JoinColumn (name = "id_arithmetic_operation")
    private ArithmeticOperationCodelist arithmeticOperation;

    @Column(name = "value_operand")
    private Double operandValue;

    @CreatedDate
    @Column(name = "dtime_created")
    private LocalDateTime createdDate;
}
