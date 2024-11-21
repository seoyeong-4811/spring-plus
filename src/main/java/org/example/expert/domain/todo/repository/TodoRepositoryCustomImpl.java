package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.stereotype.Repository;
import org.example.expert.domain.todo.entity.QTodo;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TodoRepositoryCustomImpl implements TodoRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {
        Todo result = queryFactory
                .selectFrom(QTodo.todo)
                .leftJoin(QTodo.todo.user).fetchJoin()
                .where(QTodo.todo.id.eq(todoId))
                .fetchOne();

        return Optional.ofNullable(result);
    }
}
