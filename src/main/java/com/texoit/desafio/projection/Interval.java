package com.texoit.desafio.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.texoit.desafio.model.Producer;

@Projection(name = "interval", types = { Producer.class })
public interface Interval {

	@Value("#{target.producer}")
	public String getProducer();
	
	@Value("#{target.previousWin}")
	public Integer getPreviousWin();

	@Value("#{target.followingWin}")
	public Integer getFollowingWin();

	@Value("#{target.interval}")
	public Integer getInterval();
	
	@Value("#{target.movie}")
	public String getMovie();


}
