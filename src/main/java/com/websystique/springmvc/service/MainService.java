package com.websystique.springmvc.service;

import com.websystique.springmvc.model.UserDocument;
import com.websystique.springmvc.model.UserDocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class MainService {
    @Autowired
    private UserDocumentDao userDocumentDao;

    public void saveDocument() {
        List<UserDocument> documentList = userDocumentDao.findAll();
        for (UserDocument userDocument: documentList) {
            System.out.println(userDocument.getId()+" "+userDocument.getName()+" "+userDocument.getType());
        }

        File file = new File("/home/tomek/Documents/samples3/db-saving-files/files/12.docx");
        try {
            FileCopyUtils.copy(documentList.get(0).getContent(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
