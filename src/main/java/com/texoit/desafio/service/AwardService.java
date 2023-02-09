package com.texoit.desafio.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.texoit.desafio.model.Movie;
import com.texoit.desafio.model.Producer;
import com.texoit.desafio.model.Studio;
import com.texoit.desafio.pojo.Award;
import com.texoit.desafio.repository.MovieRepository;
import com.texoit.desafio.repository.ProducerRepository;
import com.texoit.desafio.repository.StudioRepository;

@Service
public class AwardService {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private StudioRepository studioRepository;

	@Autowired
	private ProducerRepository producerRepository;
	
	@Value("${importacao.path}")
	private String importacaotPath;

	@Transactional
	public void init() throws IOException {
		FileReader in = new FileReader(new File(importacaotPath));
		try (BufferedReader br = new BufferedReader(in)) {
			String line;
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				String[] values = line.split(";");

				Movie movie = new Movie();
				movie.setYear(Integer.valueOf(values[0]));
				movie.setTitle(values[1]);
				movie.setWinner(values.length > 4 && "yes".equals(values[4]));
				movieRepository.save(movie);

				Stream.of(values[3].split(",")).flatMap(a -> Stream.of(a.split(" and "))).filter(f -> !"".equals(f))
						.map(String::trim).forEach(s -> {
							Producer producer = new Producer();
							producer.setName(s);
							producerRepository.save(producerRepository.findOne(Example.of(producer)).map(p -> {
								p.getMovies().add(movie);
								return p;
							}).orElseGet(() -> {
								producer.setMovies(new ArrayList<>());
								producer.getMovies().add(movie);
								return producer;
							}));
						});

				Stream.of(values[2].split(",")).map(String::trim).forEach(s -> {

					Studio studio = new Studio();
					studio.setName(s);

					studioRepository.save(studioRepository.findOne(Example.of(studio)).map(p -> {
						p.getMovies().add(movie);
						return p;
					}).orElseGet(() -> {
						studio.setMovies(new ArrayList<>());
						studio.getMovies().add(movie);
						return studio;
					}));

				});

			}
		}

	}

	public Award getAwards() {

		Award award = new Award();
		award.setMin(producerRepository.findPremiadosPrimeiro(null,	producerRepository.findPremiadosPrimeiro(PageRequest.of(0, 1), null).get(0).getInterval()));
		award.setMax(producerRepository.findPremiadosUltimo(null, producerRepository.findPremiadosUltimo(PageRequest.of(0, 1), null).get(0).getInterval()));

		return award;
	}

}
