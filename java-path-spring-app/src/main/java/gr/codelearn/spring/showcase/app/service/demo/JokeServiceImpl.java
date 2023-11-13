package gr.codelearn.spring.showcase.app.service.demo;

import gr.codelearn.spring.showcase.app.repository.demo.JokeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JokeServiceImpl implements JokeService {
	private final JokeRepository jokeRepository;

	@Override
	public String getSingleJoke(String... genres) {
		return jokeRepository.getSingleJoke(genres);
	}
}
