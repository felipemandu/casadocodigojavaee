package br.com.felipemandu.casadocodigojavaee.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.Part;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class FileAdmin {
	

	private AmazonS3Client s3;
	
	public static final String SERVER_PATH = "C:/Users/felip/projetos/casadocodigojavaee";
	public static final String AWS_NINJA_PATH = "http://localhost:9444/s3/casadocodigojavaee";
	
	public String write(String baseFolder, Part file) {
		try {
			s3 = getClient();
			s3.putObject("casadocodigojavaee", file.getSubmittedFileName(), file.getInputStream(), new ObjectMetadata());
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
		return generateAWSPath(file);
	}
	
	private String generateAWSPath(Part file) {
		StringBuilder sb = new StringBuilder();
		return sb.append(AWS_NINJA_PATH)
			.append(File.separator)
			.append(file.getSubmittedFileName())
			.append("?noAuth=true")
			.toString();
	}
	
	@SuppressWarnings("unused")
	private String generateRelativePath(String baseFolder, Part file) {
		StringBuilder sb = new StringBuilder();
		return sb.append(baseFolder)
			.append(File.separator)
			.append(file.getSubmittedFileName())
			.toString();
	}

	@SuppressWarnings("unused")
	private String generateAbsolutePath(String relativePath) {
		StringBuilder sb = new StringBuilder();
		return sb.append(SERVER_PATH)
			.append(File.separator)
			.append(relativePath)
			.toString();
	}
	
	@SuppressWarnings("deprecation")
	public AmazonS3Client getClient() {
		AWSCredentials credentials = new BasicAWSCredentials("AKIAIOSFODNN7EXAMPLE",
				"wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY");
		AmazonS3Client newClient = new AmazonS3Client(credentials, new ClientConfiguration());
		newClient.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build());
		newClient.setEndpoint("http://localhost:9444/s3");

		return newClient;
	}

}
