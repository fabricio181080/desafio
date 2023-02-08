package com.texoit.desafio.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.texoit.desafio.model.Producer;
import com.texoit.desafio.projection.Interval;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

	public static final String FIND_PREMIADOS = " select " 
			+ "p.name as producer, " 
			+ "pw.year as previousWin, " 
			+ "min(fw.year) as followingWin, " 
			+ "min(fw.year) - pw.year as interval "
			+ "from Producer p " 
			+ "inner join p.movies pw on pw.winner is true "
			+ "inner join p.movies fw on pw.winner is true and fw.year > pw.year " 
			+ "group by p, pw "
			+ "having :interval is null or min(fw.year) - pw.year = :interval "
			+ "order by 4 ";


	@Query(FIND_PREMIADOS + " asc, 1 asc ")
	List<Interval> findPremiadosPrimeiro(Pageable page, Integer interval);

	@Query(FIND_PREMIADOS + " desc, 1 asc ")
	List<Interval> findPremiadosUltimo(Pageable page, Integer interval);
	

}
