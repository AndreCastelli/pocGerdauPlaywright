package br.com.gerdau.reportUtils.screenshot;

import com.google.common.base.Function;

public class ReportRunFunction {

	public <V, T> V take(Function<? super T, V> isTrue) {
		try {
			return isTrue.apply(null);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}
}