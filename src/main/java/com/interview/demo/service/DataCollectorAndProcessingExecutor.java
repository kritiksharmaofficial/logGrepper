package com.interview.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.interview.demo.configuration.AmazonS3Client;
import com.interview.demo.constants.AWSConstants;
import com.interview.demo.entities.LogSearchRequest;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataCollectorAndProcessingExecutor {

    AmazonS3Client amazonS3Client;

    AmazonS3 s3Client;

    public DataCollectorAndProcessingExecutor(AmazonS3Client amazonS3Client, AmazonS3 amazonS3) {
        this.amazonS3Client = amazonS3Client;
        this.s3Client = amazonS3;
    }
    public List<String> execute(LogSearchRequest logSearchRequest) throws RuntimeException {
        List<String> response = new ArrayList<>();
        LocalDateTime start = LocalDateTime.parse(logSearchRequest.getFrom());
        LocalDateTime end = LocalDateTime.parse(logSearchRequest.getTo());
        while (start.isBefore(end) || start.isEqual(end)) {
            String folderPrefix = String.format("%4d-%02d-%02d",
                    start.getYear(), start.getMonthValue(), start.getDayOfMonth());
            ListObjectsV2Request request = amazonS3Client.getListObjectsV2RequestForFolder(folderPrefix);
            ListObjectsV2Result result;
            try {
                do {
                    result = s3Client.listObjectsV2(request);
                    for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
                        String objectKey = objectSummary.getKey();
                        if(objectKey.contains(start.getHour() + ".txt")) {
                            S3Object s3Object = s3Client.getObject(AWSConstants.AWS_BUCKET_NAME, objectKey);
                            InputStream inputStream = s3Object.getObjectContent();

                            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    if (line.contains(logSearchRequest.getSearchKeyword())) {
                                        response.add(line);
                                    }
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    request.setContinuationToken(result.getNextContinuationToken());
                } while (result.isTruncated());
            } catch (AmazonS3Exception e) {
                throw new RuntimeException(e);
            }
            start = start.plusHours(1);
        }
        return response;
    }
}
