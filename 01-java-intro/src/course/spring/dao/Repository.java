package course.spring.dao;

import java.util.List;
import java.util.Optional;

public interface Repository<K, V> {
    List<V> findAll();
    Optional<V> findById(K id);
    V create(V entity);
    Optional<V> update(V entity);
    Optional<V> deleteById(K id);
    long count();
}
