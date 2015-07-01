package br.com.aprando.recommendersystem.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recomendacao")
public class Recomendacao {

	@Id
	private String id;

	private String kmeansClusterGroup;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKmeansClusterGroup() {
		return kmeansClusterGroup;
	}

	public void setKmeansClusterGroup(String kmeansClusterGroup) {
		this.kmeansClusterGroup = kmeansClusterGroup;
	}

}
