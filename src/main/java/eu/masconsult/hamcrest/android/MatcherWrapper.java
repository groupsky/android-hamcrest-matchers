package eu.masconsult.hamcrest.android;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class MatcherWrapper<FromType, ToType> extends BaseMatcher<ToType> {

	private Matcher<FromType> subMatcher;

	public MatcherWrapper(Matcher<FromType> subMatcher) {
		this.subMatcher = subMatcher;
	}

	@Override
	public void describeTo(Description description) {
		subMatcher.describeTo(description);
	}

	@Override
	public boolean matches(Object item) {
		return subMatcher.matches(item);
	}

	@Override
	public void describeMismatch(Object item, Description mismatchDescription) {
		subMatcher.describeMismatch(item, mismatchDescription);
	}

	@Factory
	public static <FromType, ToType> Matcher<ToType> wrap(
			Matcher<FromType> subMatcher) {
		return new MatcherWrapper<FromType, ToType>(subMatcher);
	}
}
