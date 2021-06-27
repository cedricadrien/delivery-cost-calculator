package com.mynt.calculator.deliverycost.repository;

import com.mynt.calculator.deliverycost.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {

    List<Rule> getAllByOrderByPriorityAsc();
}
