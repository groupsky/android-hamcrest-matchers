package eu.masconsult.hamcrest.android;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import android.os.Bundle;

public class BundleValue<T> extends FeatureMatcher<Bundle, T> {

	private String key;

	public BundleValue(String key, Matcher<T> subMatcher) {
		super(subMatcher, "value of " + key, key + " value");
		this.key = key;
	}

	@Override
	protected T featureValueOf(Bundle actual) {
		return (T) actual.get(key);
	}

	@Factory
	public static <T> BundleValue<T> value(String key, Matcher<T> subMatcher) {
		return new BundleValue<T>(key, subMatcher);
	}

}
