package gr.codelearn.spring.showcase.app.repository.demo;

public interface JokeRepository {
	String getSingleJoke(String... genres);
}
