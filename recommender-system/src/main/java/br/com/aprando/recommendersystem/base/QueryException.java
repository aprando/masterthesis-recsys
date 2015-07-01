package br.com.aprando.recommendersystem.base;

public class QueryException extends BaseException {
	private static final long serialVersionUID = 5758008003263059155L;

	private String query;

	public QueryException(final String erro, final String query) {
		super(erro);
		this.query = query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}

}
