package com.mcredit.downloadimage.controller;

import com.mcredit.downloadimage.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * FileController
 *
 * @author by hopnv.ous on 8/30/2022
 */
@RequestMapping("/down-load")
@RequiredArgsConstructor
@RestController
public class FileController {

    private final IFileService fileService;

    @GetMapping("/ekyc")
    public String downloadFileKYC(@RequestParam(value = "front", required = false) String front,
                                  @RequestParam(value = "back", required = false) String back,
                                  @RequestParam(value = "right", required = false) String right,
                                  @RequestParam(value = "left", required = false) String left,
                                  @RequestParam(value = "selfie", required = false) String selfie) throws IOException {
        fileService.downloadEKYC(front, back, right, left, selfie);
        return "SUCCESS";
    }

    @GetMapping("/selfie-contract")
    public String downloadSelfieByDocumentId(@RequestParam(value = "documentId") String documentId) throws IOException {
        fileService.downloadImageSelfieByLoansProfileId(documentId);
        return "SUCCESS";
    }
}
