package com.mynt.calculator.deliverycost.model;

import com.mynt.calculator.deliverycost.constant.ArithmeticOperation;
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
@Table(name = "arithmetic_operation_clt")
@EntityListeners(AuditingEntityListener.class)
public class ArithmeticOperationCodelist {

    @Id
    @GeneratedValue(generator = "arith_operation_clt_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "arith_operation_clt_gen", sequenceName = "arith_operation_clt_seq", allocationSize = 1)
    @Column(name = "id_arithmetic_operation", nullable = false, unique = true, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "code_operation")
    private ArithmeticOperation operationCode;

    @Column(name = "desc_operation")
    private String operationDescription;

    @CreatedDate
    @Column(name = "dtime_created")
    private LocalDateTime createdDate;
}
