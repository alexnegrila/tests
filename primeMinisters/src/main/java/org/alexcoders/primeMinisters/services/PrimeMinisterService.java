package org.alexcoders.primeMinisters.services;

import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

public interface PrimeMinisterService {

	String getAge(String name) throws RepositoryException, MalformedQueryException, QueryEvaluationException;

	String getBirthPlace(String name) throws RepositoryException, MalformedQueryException, QueryEvaluationException;

	String getAnswer(String question) throws RepositoryException, MalformedQueryException, QueryEvaluationException;
}
