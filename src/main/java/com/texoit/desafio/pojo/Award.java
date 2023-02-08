package com.texoit.desafio.pojo;

import java.util.List;

import com.texoit.desafio.projection.Interval;

public class Award {

	private List<Interval> min;

	private List<Interval> max;

	public List<Interval> getMin() {
		return min;
	}

	public void setMin(List<Interval> min) {
		this.min = min;
	}

	public List<Interval> getMax() {
		return max;
	}

	public void setMax(List<Interval> max) {
		this.max = max;
	}

}
