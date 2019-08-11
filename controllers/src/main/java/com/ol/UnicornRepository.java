package com.ol;

import com.ol.model.Unicorn;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Semernitskaya on 8/11/19.
 */
public interface UnicornRepository extends MongoRepository<Unicorn, Long> {
}
