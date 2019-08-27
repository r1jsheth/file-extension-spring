package com.filetypeIdentification.mvp1.repository;

/*
 * mvp1
 * 27/8/19
 */

import com.filetypeIdentification.mvp1.document.Extension;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ExtensionRepository extends MongoRepository<Extension, String> {


}
