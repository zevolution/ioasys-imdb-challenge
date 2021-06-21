package br.com.zevolution.imdb.infrastructure.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import br.com.zevolution.imdb.domain.entity.Movie;
import br.com.zevolution.imdb.domain.repository.MovieRepositoryCustom;

@Component
public class MovieRepositoryImpl implements MovieRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Page<Movie> findMoviesByFilters(Long directorId, String name, String genre,
			List<Long> actorsId, Pageable pageOption) {
		
		Query countQuery = getQueryFindMoviesByFilters(directorId, name, genre, actorsId, true);
		Number countResult = (Number) countQuery.getSingleResult();
		
		TypedQuery<Movie> query = (TypedQuery<Movie>) getQueryFindMoviesByFilters(directorId, name, 
				genre, actorsId, false);
		query.setFirstResult(pageOption.getPageNumber() * pageOption.getPageSize());
		query.setMaxResults(pageOption.getPageSize());
		
		return PageableExecutionUtils.getPage(query.getResultList(), pageOption, () -> (long) countResult);
	}

	private Query getQueryFindMoviesByFilters(Long directorId, String name, String genre,
			List<Long> actorsId, boolean isCount) {
		String sql = "SELECT DISTINCT a FROM Movie a ";
		
		if (isCount) {
			sql = "SELECT COUNT(DISTINCT a) FROM Movie a ";
		}
		
		String join = " ";
		String where = "WHERE 1=1 ";
		String orderBy = "ORDER BY a.averageScore DESC, a.name ";
		
		Map<String, Object> parameters = new HashMap<>();
		if (ObjectUtils.compare(directorId, 0L) > 0) {
			join += "JOIN FETCH a.director b ";
			where += "AND b.id = :directorId ";
			parameters.put("directorId", directorId);
		}
		
		if (StringUtils.isNotBlank(name)) {
			where += "AND LOWER(a.name) CONTAINS :name ";
			parameters.put("name", StringUtils.lowerCase(name));
		}
		
		if (StringUtils.isNotBlank(genre)) {
			where += "AND LOWER(a.genre) CONTAINS :genre ";
			parameters.put("genre", StringUtils.lowerCase(genre));
		}
		
		if (actorsId != null && !actorsId.isEmpty()) {
			join += "JOIN FETCH a.actors c ";
			where += "AND c.id IN (:actorsId) ";
			parameters.put("actorsId", actorsId);
		}
		
		String statement = String.format("%s%s%s%s", sql, join, where, orderBy);
		if (isCount) {
			statement = String.format("%s%s%s", sql, join, where);
		}
		
		if (isCount) {
			statement = RegExUtils.replaceAll(statement, "FETCH", "");
			Query query = this.entityManager.createQuery(statement);
			parameters.forEach(query::setParameter);
			return query;
		} else {
			TypedQuery<Movie> query = entityManager.createQuery(statement, Movie.class);
			parameters.forEach(query::setParameter);
			return query;
		}
	}

}
