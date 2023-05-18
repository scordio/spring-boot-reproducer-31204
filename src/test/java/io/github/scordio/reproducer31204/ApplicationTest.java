package io.github.scordio.reproducer31204;

import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class ApplicationTest {

    @SpyBean
    private PersonJpaRepository repository;

    private final Pageable pageable = PageRequest.of(0, 10);
    private final Predicate predicate = QPersonEntity.personEntity.id.isNotNull();

    @Order(1)
    @Test
    void first() {
        doThrow(new NullPointerException()).when(repository).findAll(any(Predicate.class), any(Pageable.class));
        assertThatNullPointerException().isThrownBy(() -> repository.findAll(predicate, pageable)); // succeeds
    }

    @Order(2)
    @Test
    void second() {
        assertThatNoException().isThrownBy(() -> repository.findAll(predicate, pageable)); // should fail
    }

    @Order(3)
    @Test
    void third() {
        doThrow(new IllegalStateException()).when(repository).findAll(any(Predicate.class), any(Pageable.class));
        assertThatIllegalStateException().isThrownBy(() -> repository.findAll(predicate, pageable)); // succeeds
    }

}
