package org.alexcoders.primeMinisters.services;

import java.util.Calendar;
import java.util.Date;

import org.openrdf.model.Value;
import org.openrdf.model.impl.LiteralImpl;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sparql.SPARQLRepository;

public class PrimeMinisterServiceImpl implements PrimeMinisterService {

	public String getAge(String name) throws RepositoryException,
			MalformedQueryException, QueryEvaluationException {
		int age = -1;
		SPARQLRepository sparqlRepository = new SPARQLRepository(
				"http://dbpedia.org/sparql");

		sparqlRepository.initialize();

		RepositoryConnection conn = sparqlRepository.getConnection();
		try {
			String sparqlQuery = " PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
					+ "PREFIX dbpprop: <http://dbpedia.org/property/>"
					+ "PREFIX dbres: <http://dbpedia.org/resource/>"
					+ "select distinct ?birthDate WHERE {"
					+ " ?person dbpprop:name \""
					+ name
					+ "\"@en;"
					+ "    dbpprop:title dbres:Prime_Minister_of_the_United_Kingdom;"
					+ "    dbpedia-owl:birthDate ?birthDate ." + " } ";
			TupleQuery query = conn.prepareTupleQuery(QueryLanguage.SPARQL,
					sparqlQuery);
			TupleQueryResult result = query.evaluate();
			if (result.hasNext()) {
				Value value = result.next().getValue("birthDate");

				if (value != null) {
					Date dateOfBirth = ((LiteralImpl) value).calendarValue()
							.toGregorianCalendar().getTime();
					Calendar dob = Calendar.getInstance();
					dob.setTime(dateOfBirth);
					Calendar today = Calendar.getInstance();
					age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
					if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
						age--;
					} else if (today.get(Calendar.MONTH) == dob
							.get(Calendar.MONTH)
							&& today.get(Calendar.DAY_OF_MONTH) < dob
									.get(Calendar.DAY_OF_MONTH)) {
						age--;
					}
				}
			}
		} finally {
			conn.close();
		}
		return age + "";
	}

	@Override
	public String getBirthPlace(String name) throws RepositoryException,
			MalformedQueryException, QueryEvaluationException {
		String birthPlace = "";
		SPARQLRepository sparqlRepository = new SPARQLRepository(
				"http://dbpedia.org/sparql");

		sparqlRepository.initialize();

		RepositoryConnection conn = sparqlRepository.getConnection();
		try {
			String sparqlQuery = " PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>"
					+ "PREFIX dbpprop: <http://dbpedia.org/property/>"
					+ "PREFIX dbres: <http://dbpedia.org/resource/>"
					+ "select distinct ?birthPlaceName WHERE {"
					+ " ?person dbpprop:name \""
					+ name
					+ "\"@en;"
					+ "    dbpprop:title dbres:Prime_Minister_of_the_United_Kingdom;"
					+ "    dbpedia-owl:birthPlace ?birthPlace ."
					+ "    ?birthPlace dbpprop:name ?birthPlaceName" + " } ";
			TupleQuery query = conn.prepareTupleQuery(QueryLanguage.SPARQL,
					sparqlQuery);
			TupleQueryResult result = query.evaluate();
			while (result.hasNext()) {
				Value value = result.next().getValue("birthPlaceName");

				if (value != null) {
					birthPlace += value.stringValue();
					if (result.hasNext()) {
						birthPlace+=",";
					}
				}
			}
		} finally {
			conn.close();
		}
		return birthPlace;
	}

	@Override
	public String getAnswer(String question) throws RepositoryException,
			MalformedQueryException, QueryEvaluationException {
		if (question != null) {
			if ((question.contains("How") || question.contains("how"))
					&& question.contains("old")) {
				String name = question.substring(question.indexOf("is ") + 3,
						question.indexOf("?"));
				return getAge(name);
			} else if ((question.contains("Where") || question
					.contains("where")) && question.contains("born")) {
				String name = question.substring(question.indexOf("was ") + 4,
						question.indexOf(" born?"));
				return getBirthPlace(name);
			}
		}
		return null;
	}
}
