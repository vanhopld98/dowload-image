package com.mcredit.downloadimage.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.mcredit.downloadimage.service.IAwsS3Service;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

/**
 * AwsS3ServiceImpl
 *
 * @author by hopnv.ous on 8/30/2022
 */

@Service
public class AwsS3ServiceImpl implements IAwsS3Service {

    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.s3.accessKey}")
    private String accessKey;

    @Value("${aws.s3.secretKey}")
    private String secretKey;

    @PostConstruct
    private void initializeAmazone() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.amazonS3 = AmazonS3ClientBuilder.standard().withRegion("ap-southeast-1")
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }

    @Override
    public MultipartFile download2S3bucketWithName(String keyImage, String name){
        try {
            S3Object s3object = amazonS3.getObject(bucketName, keyImage);
            S3ObjectInputStream inputStream = s3object.getObjectContent();
            return new MockMultipartFile(name, name,
                                         ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
        }catch (Exception e){
            // no thing
        }
        return null;
    }
}
