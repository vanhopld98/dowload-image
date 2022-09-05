package com.mcredit.downloadimage.service.impl;

import com.amazonaws.services.s3.AmazonS3URI;
import com.mcredit.downloadimage.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * FileServiceImpl
 *
 * @author by hopnv.ous on 8/30/2022
 */

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements IFileService {

    private static final String PREFIX_FRONT = "front_";
    private static final String PREFIX_BACK = "back_";
    private static final String PREFIX_RIGHT = "right_";
    private static final String PREFIX_LEFT = "left_";
    private static final String PREFIX_SELFIE = "selfie_";

    private final AwsS3ServiceImpl awsS3Service;
//
//    private final ECMProxy ecmProxy;
//
//    private final PortalProxy portalProxy;

    @Override
    public String downloadEKYC(String front, String back, String right, String left, String selfie) throws IOException{
        download(front, PREFIX_FRONT);
        download(back, PREFIX_BACK);
        download(right, PREFIX_RIGHT);
        download(selfie, PREFIX_LEFT);
        download(left, PREFIX_SELFIE);
        return "SUCCESS";
    }

    private void download(String url, String prefix) throws IOException {
        if (url != null) {
            String imageURLKey = new AmazonS3URI(url).getKey();
            MultipartFile multipartFile = awsS3Service.download2S3bucketWithName(imageURLKey, UUID.randomUUID() + ".jpg");
            File file = new File(prefix + Objects.requireNonNull(multipartFile.getOriginalFilename()));
            multipartFile.transferTo(file);
        }
    }

    public String downloadImageSelfieByLoansProfileId(String loansProfileId) throws IOException{
//        List<DocumentContract> documentContractList = portalProxy.getListDocumentByCaseNumber(loansProfileId);
//        Optional<DocumentContract> selfieFileInfoOpt = documentContractList.stream().filter(doc -> "FUM07".equals(doc.getType())).findFirst();
//        byte[] a = ecmProxy.downloadDocument(selfieFileInfoOpt.get().getUrl());
//        FileUtils.writeByteArrayToFile(new File(PREFIX_SELFIE + "loanProfileId_"+ loansProfileId + UUID.randomUUID().toString() + ".jpg"), a);
//        return "SUCCESS";
        return null;
    }
}
