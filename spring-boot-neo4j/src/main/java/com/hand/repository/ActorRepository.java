package com.hand.repository;

import com.hand.domain.Actor;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jinliang on 2017/9/24.
 */
@Repository
public interface ActorRepository extends GraphRepository<Actor>{
}
