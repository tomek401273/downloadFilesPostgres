package com.websystique.springmvc.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentDao extends JpaRepository<UserDocument, Integer> {
}
