package course.spring.dao.impl;

import course.spring.dao.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RepositoryInMemory<K,V> implements Repository<K,V> {
    private Map<K,V> entities = new HashMap<>();
    @Override
    public List<V> findAll() {
        return List.copyOf(entities.values());
    }

    @Override
    public Optional<V> findById(K id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public V create(V entity) {
        return null;
    }

    @Override
    public Optional<V> update(V entity) {
        return Optional.empty();
    }

    @Override
    public Optional<V> deleteById(K id) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return 0;
    }
}
