package com.mynt.calculator.deliverycost.model;

import com.mynt.calculator.deliverycost.constant.ParamType;
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
@Table(name = "param_type_clt")
@EntityListeners(AuditingEntityListener.class)
public class ParamTypeCodelist {

    @Id
    @GeneratedValue(generator = "param_type_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "param_type_gen", sequenceName = "param_type_seq", allocationSize = 1)
    @Column(name = "id_param_type", nullable = false, unique = true, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "code_param")
    private ParamType paramCode;

    @Column(name = "desc_param")
    private String paramDescription;

    @CreatedDate
    @Column(name = "dtime_created")
    private LocalDateTime createdDate;
}
