package com.example.customerManagement;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email); // Declaration required
    Optional<Customer> findByPhone(String phone); // Declaration required
    Page<Customer> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

//Core Methods from JpaRepository
//
//	•	Saving and Updating:
//        •	save(S entity): Saves a given entity.
//        •	saveAll(Iterable<S> entities): Saves all given entities.
//        •	Retrieving Data:
//        •	findById(ID id): Retrieves an entity by its ID.
//        •	findAll(): Retrieves all entities.
//	•	findAllById(Iterable<ID> ids): Retrieves all entities by their IDs.
//        •	count(): Returns the number of entities.
//	•	existsById(ID id): Checks if an entity with the given ID exists.
//	•	Deleting Data:
//        •	delete(T entity): Deletes a given entity.
//        •	deleteById(ID id): Deletes the entity with the given ID.
//	•	deleteAll(Iterable<? extends T> entities): Deletes all given entities.
//        •	deleteAll(): Deletes all entities in the repository.

