package com.xiia.indicatori.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xiia.indicatori.domain.Expense;
import com.xiia.indicatori.domain.Percentage;

public interface PercentagesRepository extends JpaRepository<Percentage, Long> {

	List<Percentage> findAllByArticleIdAndChildIdAndParentId(Integer articleId, Integer childId, Integer parentId);

}