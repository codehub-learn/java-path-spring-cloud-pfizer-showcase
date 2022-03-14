package gr.codelearn.spring.cloud.showcase.core.transfer;

import lombok.ToString;
import lombok.Value;

import java.io.Serializable;

@Value
@ToString
public class ApiError implements Serializable {
	Integer status;
	String message;
	String path;
}
