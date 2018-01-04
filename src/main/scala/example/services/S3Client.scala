package example.services

import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.PutObjectResult
import com.typesafe.config.{Config, ConfigFactory}

class S3Client {
  private val config: Config = ConfigFactory.load
  private val awsConfig = config.getConfig("aws-s3")
  private val clientConf = new ClientConfiguration()
  private val credentials = new BasicAWSCredentials(awsConfig.getString("aws.s3.accesskey"), awsConfig.getString("aws.s3.secretkey"))
  private val credentialsProvider = new AWSStaticCredentialsProvider(credentials)

  private val s3clientBuilder = AmazonS3ClientBuilder.standard()
  s3clientBuilder
    .withCredentials(credentialsProvider)
    .withClientConfiguration(clientConf)
    .withRegion(Regions.EU_WEST_1)

  private   val s3client = s3clientBuilder.build()

  def putObject(filePath: String, content: String) : PutObjectResult = {
    return s3client.putObject("test-scala-api", filePath, content)
  }
}
