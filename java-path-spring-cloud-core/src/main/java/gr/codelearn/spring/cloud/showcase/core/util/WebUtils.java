package gr.codelearn.spring.cloud.showcase.core.util;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;

import java.util.concurrent.TimeUnit;

public final class WebUtils {
	public static CacheControl getCacheHeaders(int cacheDuration) {
		// https://www.imperva.com/learn/performance/cache-control/
		// Note: Header "Expires" is ignored in max-age is set.
		// response.addHeader("Cache-Control", "max-age=60, must-revalidate, no-transform");
		return CacheControl.maxAge(cacheDuration, TimeUnit.SECONDS).noTransform().mustRevalidate();
	}

	public static HttpHeaders getNoCacheHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return headers;
	}

	public static HttpHeaders getDownloadHeaders(final String filename) {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + filename);
		return headers;
	}
}
