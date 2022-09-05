package com.mcredit.downloadimage.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * IAwsS3Service
 *
 * @author by hopnv.ous on 8/30/2022
 */
public interface IAwsS3Service {

    MultipartFile download2S3bucketWithName(String keyImage, String name);

}
