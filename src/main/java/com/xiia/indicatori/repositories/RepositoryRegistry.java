package com.xiia.indicatori.repositories;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
public class RepositoryRegistry {

    private final ArticlesRepository articlesRepository;
    private final CategoriesRepository categoriesRepository;
    private final ExpensesRepository expensesRepository;
    private final GroupsRepository groupsRepository;
    private final SplitsRepository splitsRepository;
    private final TokensRepository tokensRepository;
    private final UnitsRepository unitsRepository;
    private final UsersRepository usersRepository;
    private final MonthlyRepository monthlyRepository;
    private final MonthlyAllowedRepository monthlyAllowedRepository;
    private final MonthlyTypeRepository monthlyTypeRepository;
    private final RelationsRepository relationsRepository;
    private final PercentagesRepository percentageRepository;
    private final FixedRepository fixedRepository;
    private final AccountsRepository accountsRepository;
    private final FixedHistoryRepository fixedHistoryRepository;
    private final DefaultSplitRepository defaultSplitRepository;
 
}
