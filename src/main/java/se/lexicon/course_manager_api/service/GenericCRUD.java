package se.lexicon.course_manager_api.service;

import java.util.List;

public interface GenericCRUD <ENTITY, DTO, ID>{
    ENTITY create(DTO dto);
    ENTITY findById(ID id);
    List<ENTITY> findAll();
    ENTITY update(ID id, DTO dto);
    boolean delete(ID id);
}
