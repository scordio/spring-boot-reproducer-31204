package io.github.scordio.reproducer31204;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, String>, QuerydslPredicateExecutor<PersonEntity> {
}
