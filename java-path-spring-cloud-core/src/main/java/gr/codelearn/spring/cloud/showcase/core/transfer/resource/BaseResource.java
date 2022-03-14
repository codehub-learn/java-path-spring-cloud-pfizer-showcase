package gr.codelearn.spring.cloud.showcase.core.transfer.resource;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResource implements Serializable {
	protected Long id;
}
