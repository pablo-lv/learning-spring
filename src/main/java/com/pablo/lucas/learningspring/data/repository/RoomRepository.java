package com.pablo.lucas.learningspring.data.repository;

import com.pablo.lucas.learningspring.data.entity.Room;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{
    
}
