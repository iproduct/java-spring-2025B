package course.spring.dao;

import course.spring.model.Identifiable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K, V extends Identifiable<K> & Named & Serializable> {
    List<V> findAll();
    Optional<V> findById(K id);
    V create(V entity);
    Optional<V> update(V entity);
    Optional<V> deleteById(K id);
    long count();
}
