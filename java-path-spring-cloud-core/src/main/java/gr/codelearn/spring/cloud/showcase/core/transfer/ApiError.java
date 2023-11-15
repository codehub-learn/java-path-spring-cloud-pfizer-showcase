package gr.codelearn.spring.cloud.showcase.core.transfer;

public record ApiError(Integer status, String message, String path) {
}
