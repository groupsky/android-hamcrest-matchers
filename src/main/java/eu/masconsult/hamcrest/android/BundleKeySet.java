package eu.masconsult.hamcrest.android;

import java.util.Set;

import org.hamcrest.Factory;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import android.os.Bundle;

public class BundleKeySet extends FeatureMatcher<Bundle, Set<String>> {

	public BundleKeySet(Matcher<? super Set<String>> subMatcher) {
		super(subMatcher, "has keySet()", "keySet");
	}

	@Override
	protected Set<String> featureValueOf(Bundle actual) {
		return actual.keySet();
	}

	@Factory
	public static BundleKeySet keySet(Matcher<? super Set<String>> subMatcher) {
		return new BundleKeySet(subMatcher);
	}

}
