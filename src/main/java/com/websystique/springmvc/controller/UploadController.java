package com.websystique.springmvc.controller;

import com.websystique.springmvc.model.DocumentDto;
import com.websystique.springmvc.model.UserDocument;
import com.websystique.springmvc.model.UserDocumentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/upload")
@CrossOrigin(origins = "*")
public class UploadController {

    @Autowired
    private UserDocumentDao userDocumentDao;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file")MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws Exception {
        System.out.println(multipartFile.getOriginalFilename());
//        Integer id = Math.toIntExact(userDocumentDao.count())+1;
//        userDocument.setId(id);
        UserDocument userDocument = new UserDocument();
        userDocument.setName(multipartFile.getOriginalFilename());
        userDocument.setType(multipartFile.getContentType());
        userDocument.setContent(multipartFile.getBytes());

        userDocumentDao.save(userDocument);

        return "Logic";
    }

    @RequestMapping(value = { "/d" }, method = RequestMethod.GET)
    @Transactional
    public void downloadDocument(@RequestParam Integer id, HttpServletResponse response) throws IOException {


        UserDocument document = userDocumentDao.getOne(id);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");

        FileCopyUtils.copy(document.getContent(), response.getOutputStream());
//        File file = new File("/home/tomek/Documents/samples3/db-saving-files/files/1.docx");
//        FileCopyUtils.copy(document.getContent(), file);
    }

    @RequestMapping(value = "/getAllFileNames", method = RequestMethod.GET)
    public List<DocumentDto> getAllFileNames () {
        List<UserDocument> userDocuments = userDocumentDao.findAll();
        List<DocumentDto> documentDtos = new ArrayList<>();
        for (UserDocument d: userDocuments) {
            documentDtos.add(new DocumentDto(d.getId(), d.getName()));
        }
        return documentDtos;
    }
}
