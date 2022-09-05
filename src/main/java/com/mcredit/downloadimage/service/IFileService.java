package com.mcredit.downloadimage.service;

import java.io.IOException;

/**
 * IFileService
 *
 * @author by hopnv.ous on 8/30/2022
 */
public interface IFileService {

    String downloadEKYC(String front, String back, String right, String left, String selfie) throws IOException;

    String downloadImageSelfieByLoansProfileId(String documentId) throws IOException;

}
