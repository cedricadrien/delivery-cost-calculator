package com.mynt.calculator.deliverycost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "rule")
@EntityListeners(AuditingEntityListener.class)
public class Rule {

    @Id
    @GeneratedValue(generator = "rule_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rule_gen", sequenceName = "rule_seq", allocationSize = 1)
    @Column(name = "id_rule", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "num_priority")
    private Integer priority;

    @Column(name = "name_rule")
    private String ruleName;

    @OneToOne
    @JoinColumn(name = "id_condition", referencedColumnName = "id_condition")
    private Condition condition;

    @OneToOne
    @JoinColumn(name = "id_cost_expression", referencedColumnName = "id_cost_expression")
    private CostExpression costExpression;

    private Boolean isActive;
}
