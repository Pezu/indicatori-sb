package com.xiia.indicatori.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xiia.indicatori.domain.Article;
import com.xiia.indicatori.domain.Category;
import com.xiia.indicatori.domain.Group;
import com.xiia.indicatori.domain.MonthlyType;
import com.xiia.indicatori.domain.Relation;
import com.xiia.indicatori.domain.Split;
import com.xiia.indicatori.domain.Unit;
import com.xiia.indicatori.pojo.AutocompleteEntry;
import com.xiia.indicatori.repositories.RepositoryRegistry;

@Service
public class CatalogService {
	
    private final RepositoryRegistry repositoryRegistry;
    
    public CatalogService(final RepositoryRegistry repositoryRegistry) {
    	this.repositoryRegistry = repositoryRegistry;
	}

	public List<Category> getCategories() {
		return repositoryRegistry.getCategoriesRepository().findAll(new Sort(Sort.Direction.ASC, "name"));
	}

	public List<Unit> getUnits() {
		return repositoryRegistry.getUnitsRepository().findAll(new Sort(Sort.Direction.ASC,"name"));
	}
	
	public List<MonthlyType> getMonthlyType() {
		return repositoryRegistry.getMonthlyTypeRepository().findAll();
	}
	
	public List<Group> getGroups() {
		return repositoryRegistry.getGroupsRepository().findAll();
	}

    public List<Category> getCategoriesByGroupId(Integer group) {
		return repositoryRegistry.getCategoriesRepository().findAllByGroupId(group);
	}
	
    public List<Article> getArticlesByCategoryId(Integer category) {
		return repositoryRegistry.getArticlesRepository().findAllByCategoryId(category);
	}
	
	public List<Unit> getChildUnits(Integer parentId) {
		
		List<Relation> relationList = repositoryRegistry.getRelationsRepository().findAllByParentId(parentId);
		List<Unit> units = new ArrayList<Unit>();
		
		for (Relation relation : relationList) {
			units.add(relation.getChild());
		}
		
		return units;
		
	}

	public List<Split> getSplits() {
		return repositoryRegistry.getSplitsRepository().findAll();
	}

	public List<AutocompleteEntry> getArticlesForAutocomplete() {
		List<Article> articles = repositoryRegistry.getArticlesRepository().findAll();

		List<AutocompleteEntry> response = new ArrayList<AutocompleteEntry>();
		for (Article article : articles) {
			response.add( new AutocompleteEntry(
								article.getId(),
								article.getCode(), 
								article.getName(), 
								article.getCategory().getGroup().getCode(), 
								article.getCategory().getCode()));
		}
		
		return response;
	}
    
}
