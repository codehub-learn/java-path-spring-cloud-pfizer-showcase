package gr.codelearn.spring.cloud.showcase.app.transfer;

public record ApiError(Integer status, String message, String path) {
}
