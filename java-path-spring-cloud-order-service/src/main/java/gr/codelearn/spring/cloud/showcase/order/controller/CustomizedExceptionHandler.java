package gr.codelearn.spring.cloud.showcase.order.controller;

import gr.codelearn.spring.cloud.showcase.order.base.AbstractLogComponent;
import gr.codelearn.spring.cloud.showcase.order.transfer.ApiError;
import gr.codelearn.spring.cloud.showcase.order.transfer.ApiResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

/**
 * This class is responsible for handling all errors, exceptions in a wider sense, that can be thrown while handling the
 * incoming request.
 */
@RestControllerAdvice
public class CustomizedExceptionHandler extends AbstractLogComponent {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiResponse<?>> handleAllExceptions(final Exception ex, final WebRequest request) {
		logger.error("Unexpected exception occurred.", ex);
		return new ResponseEntity<>(
				ApiResponse.builder().apiError(getApiError(ex, HttpStatus.INTERNAL_SERVER_ERROR, request)).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public final ResponseEntity<ApiResponse<?>> handleNonExistence(final NoSuchElementException ex,
																   final WebRequest request) {
		logger.warn("Reference to a non-existent object. Details: {}.", ex.getMessage());
		return new ResponseEntity<>(
				ApiResponse.builder().apiError(getApiError(ex, HttpStatus.NOT_FOUND, request)).build(),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DataAccessException.class)
	public final ResponseEntity<ApiResponse<?>> handleDataErrors(final DataAccessException ex,
																 final WebRequest request) {
		logger.warn("There was something wrong while interacting with the associated database. Details: {}.",
					ex.getMessage());
		return new ResponseEntity<>(
				ApiResponse.builder().apiError(getApiError(ex, HttpStatus.NOT_ACCEPTABLE, request)).build(),
				HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<ApiResponse<?>> handleMissingServletRequestParameter(
			final MissingServletRequestParameterException ex, final WebRequest request) {
		logger.warn("There was a parameter missing from incoming request. Details: {}.", ex.getMessage());
		return new ResponseEntity<>(
				ApiResponse.builder().apiError(getApiError(ex, HttpStatus.BAD_REQUEST, request)).build(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
																		  final WebRequest request) {
		logger.warn("Method argument is invalid. Details: {}.", ex.getMessage());
		return new ResponseEntity<>(
				ApiResponse.builder().apiError(getApiError(ex, HttpStatus.BAD_REQUEST, request)).build(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse<?>> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
																		   final WebRequest request) {
		logger.warn("Method argument, although matched, is of wrong type. Details: {}.", ex.getMessage());
		return new ResponseEntity<>(
				ApiResponse.builder().apiError(getApiError(ex, HttpStatus.BAD_REQUEST, request)).build(),
				HttpStatus.BAD_REQUEST);
	}

	private ApiError getApiError(final Exception ex, final HttpStatus status, final WebRequest request) {
		String path = request.getDescription(false);
		if (path.indexOf("uri=") == 0) {
			path = StringUtils.replace(path, "uri=", "");
		}
		return new ApiError(status.value(), ex.getMessage(), path);
	}
}
