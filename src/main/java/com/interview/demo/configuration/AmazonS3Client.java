package com.interview.demo.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.interview.demo.constants.AWSConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Client {

    @Bean
    public AmazonS3 s3Client() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWSConstants.AWS_ACCESS_KEY, AWSConstants.AWS_SECRET_KEY)))
                .withRegion(Regions.fromName(AWSConstants.AWS_REGION_NAME))
                .build();
    }

    public ListObjectsV2Request getListObjectsV2RequestForFolder(String folderPrefix) {
        return new ListObjectsV2Request().withBucketName(AWSConstants.AWS_BUCKET_NAME).withPrefix(folderPrefix);
    }
}
