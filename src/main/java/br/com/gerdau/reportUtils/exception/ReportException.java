package br.com.gerdau.reportUtils.exception;

public class ReportException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReportException(String msg) {
		super(msg);
	}

	public ReportException(Exception e) {
		super(e);
	}

	public ReportException(String msg, Exception e) {
		super(msg, e);
	}

}
