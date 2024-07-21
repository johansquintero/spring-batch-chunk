package com.batch.persistence.entities.repositories;

import com.batch.persistence.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpersonCrudRepository extends JpaRepository<PersonEntity,Long> {
}
