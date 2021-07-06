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
@Table(name = "condition")
@EntityListeners(AuditingEntityListener.class)
public class Condition {

    @Id
    @GeneratedValue(generator = "condition_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "condition_gen", sequenceName = "condition_seq", allocationSize = 1)
    @Column(name = "id_condition", nullable = false, unique = true, updatable = false)
    private Long id;

    @OneToOne
    @JoinColumn (name = "id_param_type")
    private ParamTypeCodelist paramType;

    @OneToOne
    @JoinColumn (name = "id_relational_operation")
    private RelationalOperationCodelist relationalOperationId;

    @Column(name = "value_operand")
    private Double operandValue;

    @CreatedDate
    @Column(name = "dtime_created")
    private LocalDateTime createdDate;
}
